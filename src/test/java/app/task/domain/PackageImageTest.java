package app.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageImageTest {

    @Test
    @DisplayName("패키지 이미지를 생성할 수 있다.")
    void test_1() {
        // given
        Package aPackage = PackageImageFixture.createPackage();
        PackageImage image = PackageImageFixture.createPackageImage(aPackage);

        // when
        PackageImage newImage = PackageImage.create(aPackage, image.getFilename(), image.getType());

        // then
        assertAll(
                () -> assertEquals(aPackage, newImage.getPackages()),
                () -> assertEquals(image.getFilename(), newImage.getFilename()),
                () -> assertEquals(image.getType(), newImage.getType())
        );
    }
}
