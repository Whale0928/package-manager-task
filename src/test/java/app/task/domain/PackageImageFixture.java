package app.task.domain;

public class PackageImageFixture {

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
        return Package.create(trackingNo);
    }
}
