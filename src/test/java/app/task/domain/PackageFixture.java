package app.task.domain;

public class PackageFixture {

    public static final String FILENAME = "추가된-이미지-파일";
    public static final String TYPE = "jpeg";
    public static Long COUNT = 0L;

    public static PackageImage createPackageImage(Package packages) {
        ++COUNT;
        return new PackageImage(COUNT, packages, FILENAME + COUNT, TYPE);
    }

    public static Package createPackage() {
        ++COUNT;
        String trackingNo = String.valueOf(100_000_000_000L + COUNT);
        return new Package(COUNT, trackingNo);
    }

    public static void setUpPackageList(PackageRepository packageRepository) {
        Package package_1 = PackageFixture.createPackage();
        Package package_2 = PackageFixture.createPackage();
        Package package_3 = PackageFixture.createPackage();

        PackageImage image_1 = PackageFixture.createPackageImage(package_1);
        PackageImage image_2 = PackageFixture.createPackageImage(package_1);
        PackageImage image_3 = PackageFixture.createPackageImage(package_2);
        PackageImage image_4 = PackageFixture.createPackageImage(package_3);

        package_1.addImage(image_1);
        package_1.addImage(image_2);
        package_2.addImage(image_3);
        package_3.addImage(image_4);

        packageRepository.save(package_1);
        packageRepository.save(package_2);
        packageRepository.save(package_3);
    }
}
