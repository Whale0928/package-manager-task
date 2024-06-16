package app.task.application;

import app.task.domain.Package;
import app.task.domain.PackageRepository;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InmemoryPackageRepository implements PackageRepository {

    Map<Long, Package> dataSet = new HashMap<>();

    @Override
    public Package save(Package aPackage) {
        long id = dataSet.size() + 1L;
        ReflectionTestUtils.setField(aPackage, "id", id);
        dataSet.put(id, aPackage);
        System.out.println("add data = " + aPackage);
        return aPackage;
    }

    @Override
    public Optional<Package> findById(Long id) {
        return Optional.ofNullable(dataSet.get(id));
    }

    @Override
    public Optional<Package> findByIdFetchImages(Long id) {
        return Optional.ofNullable(dataSet.get(id));
    }

    @Override
    public List<Package> findAll() {
        return dataSet.values().stream().toList();
    }

    @Override
    public Optional<Package> findByTrackingNo(String trackingNo) {
        return dataSet.values().stream()
                .filter(p -> p.getTrackingNo().equals(trackingNo))
                .findFirst();
    }

    @Override
    public void delete(Package aPackage) {
        dataSet.remove(aPackage.getId());
    }

}
