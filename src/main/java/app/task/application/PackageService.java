package app.task.application;

import app.task.domain.Package;
import app.task.domain.PackageImage;
import app.task.domain.PackageImages;
import app.task.domain.PackageRepository;
import app.task.dto.request.ImageInfoRequest;
import app.task.dto.request.PackageRegisterRequest;
import app.task.dto.response.ImageInfoResponse;
import app.task.dto.response.PackageDeleteResponse;
import app.task.dto.response.PackageFetchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Transactional(readOnly = true)
    public List<PackageFetchResponse> getAllPackages() {
        return packageRepository.findAll()
                .stream()
                .map(p -> PackageFetchResponse.of(p.getId(), p.getTrackingNo(), p.getImages().getImageInfoResponse())
                ).toList();
    }

    @Transactional(readOnly = true)
    public PackageFetchResponse getPackageById(final Long id) {
        Package aPackage = packageRepository.findByIdFetchImages(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 패키지가 존재하지 않습니다."));
        List<ImageInfoResponse> images = aPackage.getImages().getImageInfoResponse();

        return PackageFetchResponse.of(aPackage.getId(), aPackage.getTrackingNo(), images);
    }

    @Transactional
    public PackageFetchResponse registerPackage(PackageRegisterRequest request) {
        final String trackingNo = Objects.requireNonNull(request.trackingNo());
        final List<ImageInfoRequest> images = Objects.requireNonNull(request.images());

        packageRepository.findByTrackingNo(trackingNo)
                .ifPresent(p -> {
                    throw new IllegalArgumentException("이미 등록된 송장번호입니다.");
                });

        final Package aPackage = Package.create(trackingNo);
        final PackageImages packageImages = PackageImages.create(images.stream()
                .map(image -> PackageImage.create(aPackage, image.filename(), image.type()))
                .toList());

        Package save = packageRepository.save(aPackage);

        return PackageFetchResponse.of(save.getId(), save.getTrackingNo(), packageImages.getImageInfoResponse());
    }


    @Transactional
    public PackageFetchResponse addImage(
            final Long packageId,
            final String filename,
            final String type
    ) {
        Package aPackage = packageRepository.findByIdFetchImages(packageId)
                .orElseThrow(() -> new IllegalArgumentException("해당 패키지가 존재하지 않습니다."));
        PackageImage newImage = PackageImage.create(aPackage, filename, type);
        aPackage.addImage(newImage);
        return PackageFetchResponse.of(aPackage.getId(), aPackage.getTrackingNo(), aPackage.getImages().getImageInfoResponse());
    }

    @Transactional
    public PackageDeleteResponse deletePackage(Long id) {

        Package aPackage = packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 패키지가 존재하지 않습니다."));

        packageRepository.delete(aPackage);

        return PackageDeleteResponse.success(id);
    }
}
