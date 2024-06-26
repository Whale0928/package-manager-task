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

import java.util.Objects;

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
        Objects.requireNonNull(packages, "패키지 정보는 필수입니다.");
        Objects.requireNonNull(filename, "파일명은 필수입니다.");
        Objects.requireNonNull(type, "파일 타입은 필수입니다.");

        PackageImage image = new PackageImage(packages, filename, type);
        packages.addImage(image);
        return image;
    }

    private PackageImage(Package packages, String filename, String type) {
        this.packages = packages;
        this.filename = filename;
        this.type = type;
    }

    protected PackageImage(Long id, Package packages, String filename, String type) {
        this.id = id;
        this.packages = packages;
        this.filename = filename;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PackageImage{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
