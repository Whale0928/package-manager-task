package app.task.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String trackingNo;

    @Embedded
    private PackageImages images;

    public static Package create(String trackingNo, PackageImages images) {
        return new Package(trackingNo, images);
    }

    private Package(String trackingNo, PackageImages images) {
        validateTrackingNo(trackingNo);
        this.trackingNo = trackingNo;
        this.images = images;
    }

    public void validateTrackingNo(String trackingNo) {
        if (trackingNo.length() != 12) {
            throw new IllegalArgumentException("운송 번호는 12자리 문자열로 구성되어 있어야 합니다.");
        }
    }
}
