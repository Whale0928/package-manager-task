package app.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

class PackageTest {

    @Test
    @DisplayName("운송 번호는 12자리 문자열로 구성되어 있어야 한다.")
    void test_1() {
        // given
        String overTrackingNo = "1234567890123";
        String collTrackingNo = "123456789012";

        // when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Package.create(overTrackingNo))
                .withMessage("운송 번호는 12자리 문자열로 구성되어 있어야 합니다.");

        assertThatNoException()
                .isThrownBy(() -> Package.create(collTrackingNo));
    }

    @Test
    @DisplayName("이미지를 추가할 수 있다.")
    void test_2() {
        // given
        Package aPackage = Package.create("123456789012");

        // when
        Integer beforeSize = aPackage.getImages().size();
        PackageImage image = PackageFixture.createPackageImage(aPackage);
        aPackage.addImage(image);
        PackageImages after = aPackage.getImages();

        // then
        assertThat(beforeSize).isEqualTo(0);
        assertThat(after.size()).isEqualTo(1);

        after.get(image.getId()).ifPresent(i ->
                assertAll(
                        () -> assertThat(i.getId()).isEqualTo(image.getId()),
                        () -> assertThat(i.getFilename()).isEqualTo(image.getFilename()),
                        () -> assertThat(i.getType()).isEqualTo(image.getType())
                ));
    }
}
