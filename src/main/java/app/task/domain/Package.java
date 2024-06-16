package app.task.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "packageId"
    )
    private List<PackageImages> images = new ArrayList<>();

    public static Package create(String trackingNo) {
        return new Package(trackingNo);
    }

    private Package(String trackingNo) {
        this.trackingNo = trackingNo;
        this.images = new ArrayList<>();
    }

    public void updateTrackingNo(String trackingNo) {
        if (trackingNo.length() != 12) {
            throw new IllegalArgumentException("운송 번호는 12자리 문자열로 구성되어 있어야 합니다.");
        }
        this.trackingNo = trackingNo;
    }
}
