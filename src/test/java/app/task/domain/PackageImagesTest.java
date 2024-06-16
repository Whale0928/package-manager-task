package app.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PackageImagesTest {

    @Test
    @DisplayName("1급 객체 리스트에 이미지를 생성할 수 있다.")
    void test_1() {
        // given
        Package aPackage = PackageImageFixture.createPackage();
        PackageImage image_1 = PackageImageFixture.createPackageImage(aPackage);
        PackageImage image_2 = PackageImageFixture.createPackageImage(aPackage);

        // when
        PackageImages images = PackageImages.create(List.of(image_1, image_2));

        // then
        assertNotNull(images);
        assertAll(
                () -> assertEquals(2, images.size()),
                () -> assertEquals(image_1, images.get(image_1.getId()).get()),
                () -> assertEquals(image_2, images.get(image_2.getId()).get())
        );
        assertEquals(2, images.size());
    }

    @Test
    @DisplayName("1급 객체 리스트에 이미지를 추가할 수 있다.")
    void test_2() {
        // given
        Package aPackage = PackageImageFixture.createPackage();
        PackageImage image_1 = PackageImageFixture.createPackageImage(aPackage);
        PackageImage image_2 = PackageImageFixture.createPackageImage(aPackage);
        PackageImages images = PackageImages.create(Collections.singletonList(image_1));

        // when
        Integer before = images.size();
        images.add(image_2);
        Integer after = images.size();

        // then
        assertNotEquals(before, after);
        assertAll(
                () -> assertEquals(2, images.size()),
                () -> assertEquals(image_1, images.get(image_1.getId()).get()),
                () -> assertEquals(image_2, images.get(image_2.getId()).get())
        );
    }

    @Test
    @DisplayName("1급 객체 리스트에 식별자를 통해 이미지를 조회할 수 있다.")
    void test_3() {
        // given
        Package aPackage = PackageImageFixture.createPackage();
        PackageImage image_1 = PackageImageFixture.createPackageImage(aPackage);
        PackageImage image_2 = PackageImageFixture.createPackageImage(aPackage);
        PackageImages images = PackageImages.create(List.of(image_1, image_2));
        Long id = image_1.getId();

        // when
        PackageImage found = images.get(id).get();

        // then
        assertEquals(image_1, found);
    }
}
