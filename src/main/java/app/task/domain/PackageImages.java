package app.task.domain;

import app.task.dto.response.ImageInfoResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Embeddable
public class PackageImages {

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "packages"
    )
    List<PackageImage> images;

    public List<ImageInfoResponse> getImageInfoResponse() {
        return images.stream()
                .map(i -> ImageInfoResponse.of(i.getFilename(), i.getType()))
                .toList();
    }

    protected PackageImages() {
        this.images = new ArrayList<>();
    }

    private PackageImages(List<PackageImage> list) {
        this.images = list;
    }

    public static PackageImages create(List<PackageImage> list) {
        return new PackageImages(list);
    }

    public void add(PackageImage image) {
        this.images.add(image);
    }

    public Integer size() {
        return this.images.size();
    }

    public Optional<PackageImage> get(Long id) {
        return this.images.stream()
                .filter(image -> image.getId().equals(id))
                .findFirst();
    }
}
