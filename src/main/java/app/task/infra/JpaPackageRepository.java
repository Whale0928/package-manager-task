package app.task.infra;

import app.task.domain.Package;
import app.task.domain.PackageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPackageRepository extends PackageRepository, JpaRepository<Package, Long> {
}
