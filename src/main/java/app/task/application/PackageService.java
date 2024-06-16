package app.task.application;

import app.task.domain.Package;
import app.task.domain.PackageRepository;
import app.task.dto.response.ImageInfoResponse;
import app.task.dto.response.PackageFetchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .map(p -> {
                            List<ImageInfoResponse> images = p.getImages()
                                    .stream()
                                    .map(i -> ImageInfoResponse.of(i.getFilename(), i.getType()))
                                    .toList();
                            return PackageFetchResponse.of(p.getId(), p.getTrackingNo(), images);
                        }
                ).toList();
    }

    public PackageFetchResponse getPackageById(Long id) {

        Package aPackage = packageRepository.findByIdFetchImages(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 패키지가 존재하지 않습니다."));

        List<ImageInfoResponse> images = aPackage.getImages()
                .stream()
                .map(i -> ImageInfoResponse.of(i.getFilename(), i.getType()))
                .toList();

        return PackageFetchResponse.of(aPackage.getId(), aPackage.getTrackingNo(), images);
    }


}
