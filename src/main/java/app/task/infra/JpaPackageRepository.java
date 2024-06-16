package app.task.infra;

import app.task.domain.Package;
import app.task.domain.PackageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPackageRepository extends PackageRepository, JpaRepository<Package, Long> {

    @Override
    @Query("select p from Package p left join fetch p.images where p.id = :id")
    Optional<Package> findByIdFetchImages(Long id);

    @Override
    @Query("select p from Package p where p.trackingNo = :trackingNo")
    Optional<Package> findByTrackingNo(String trackingNo);
}
