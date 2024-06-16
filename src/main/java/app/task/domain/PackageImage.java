package app.task.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class PackageImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private Package packages;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String type;

    public static PackageImage create(Package packages, String filename, String type) {
        PackageImage image = new PackageImage(packages, filename, type);
        packages.getImages().add(image);
        return image;
    }

    public PackageImage(Package packages, String filename, String type) {
        this.packages = packages;
        this.filename = filename;
        this.type = type;
    }
}
