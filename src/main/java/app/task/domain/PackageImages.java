package app.task.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class PackageImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String type;

    public static PackageImages create(Long packageId, String filename, String type) {
        return new PackageImages(packageId, filename, type);
    }

    public PackageImages(Long packageId, String filename, String type) {
        this.packageId = packageId;
        this.filename = filename;
        this.type = type;
    }
}
