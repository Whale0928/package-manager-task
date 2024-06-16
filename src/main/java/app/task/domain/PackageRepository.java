package app.task.domain;

import java.util.List;
import java.util.Optional;

public interface PackageRepository {
    Package save(Package aPackage);

    Optional<Package> findById(Long id);

    List<Package> findAll();
}
