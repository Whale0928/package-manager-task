package app.task.domain;

import app.task.dto.response.ImageInfoResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class PackageImages {
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "packageId"
    )
    List<PackageImage> images = new ArrayList<>();

    public List<ImageInfoResponse> getImageInfoResponse() {
        return images.stream()
                .map(i -> ImageInfoResponse.of(i.getFilename(), i.getType()))
                .toList();
    }
}
