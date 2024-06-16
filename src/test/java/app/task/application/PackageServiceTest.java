package app.task.application;

import app.task.domain.Package;
import app.task.domain.PackageFixture;
import app.task.dto.request.ImageInfoRequest;
import app.task.dto.request.PackageRegisterRequest;
import app.task.dto.response.PackageDeleteResponse;
import app.task.dto.response.PackageFetchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static app.task.dto.response.PackageDeleteResponse.DeleteMessage;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PackageServiceTest {

    private PackageService packageService;
    private InmemoryPackageRepository packageRepository;

    @BeforeEach
    void setUp() {
        packageRepository = new InmemoryPackageRepository();
        packageService = new PackageService(packageRepository);
    }

    @Test
    @DisplayName("모든 패키지 목록을 조회 할 수 있다.")
    void test_1() {
        // given
        PackageFixture.setUpPackageList(packageRepository);

        // when
        List<PackageFetchResponse> packages = packageService.getAllPackages();

        // then
        assertEquals(3, packages.size());

        for (PackageFetchResponse p : packages) {
            assertAll(
                    () -> assertNotNull(p.id()),
                    () -> assertNotNull(p.trackingNo()),
                    () -> assertNotNull(p.images())
            );
        }
    }

    @Test
    @DisplayName("조회 결과가 없을 경우 빈 목록을 반환한다.")
    void test_2() {
        // given
        // when
        List<PackageFetchResponse> packages = packageService.getAllPackages();

        // then
        assertEquals(0, packages.size());
        assertEquals(emptyList(), packages);
    }

    @Test
    @DisplayName("식별자를 통해 단일 패키지를 조회 할 수 있다.")
    void test_3() {
        // given
        PackageFixture.setUpPackageList(packageRepository);

        // when
        PackageFetchResponse packages = packageService.getPackageById(1L);

        // then
        assertAll(
                () -> assertNotNull(packages.id()),
                () -> assertNotNull(packages.trackingNo()),
                () -> assertNotNull(packages.images())
        );
    }

    @Test
    @DisplayName("등록되지 않은 패키지 식별자로 조회할 경우 예외를 발생시킨다.")
    void test_4() {
        // when
        // then
        IllegalArgumentException aThrows = assertThrows(IllegalArgumentException.class, () -> packageService.getPackageById(1L));
        assertEquals("해당 패키지가 존재하지 않습니다.", aThrows.getMessage());
    }

    @Test
    @DisplayName("패키지를 등록할 수 있다.")
    void test_5() {
        // given
        List<ImageInfoRequest> imageInfoRequests = List.of(
                new ImageInfoRequest("filename1", "type1"),
                new ImageInfoRequest("filename2", "type2")
        );
        PackageRegisterRequest request = new PackageRegisterRequest("123456789012", imageInfoRequests);

        // when
        PackageFetchResponse packages = packageService.registerPackage(request);

        System.out.println(packages);
        // then
        assertAll(
                () -> assertNotNull(packages.id()),
                () -> assertNotNull(packages.trackingNo()),
                () -> assertNotNull(packages.images()),
                () -> assertEquals(2, packages.images().size())
        );
    }

    @Test
    @DisplayName("이미 등록된 송장번호로 패키지를 등록할 경우 예외를 발생시킨다.")
    void test_6() {
        // given
        PackageFixture.setUpPackageList(packageRepository);
        List<ImageInfoRequest> imageInfoRequests = List.of(
                new ImageInfoRequest("filename1", "type1"),
                new ImageInfoRequest("filename2", "type2")
        );
        String trackingNo = packageRepository.findAll()
                .stream()
                .findFirst().get()
                .getTrackingNo();

        PackageRegisterRequest request = new PackageRegisterRequest(trackingNo, imageInfoRequests);

        // when
        // then
        IllegalArgumentException aThrows = assertThrows(IllegalArgumentException.class, () -> packageService.registerPackage(request));
        assertEquals("이미 등록된 송장번호입니다.", aThrows.getMessage());
    }

    @Test
    @DisplayName("패키지에 이미지를 추가할 수 있다.")
    void test_7() {
        // given
        PackageFixture.setUpPackageList(packageRepository);
        Package aPackage = packageRepository.findAll()
                .stream()
                .findFirst().get();
        // when
        Integer beforeImageSize = aPackage.getImages().size();
        PackageFetchResponse response = packageService.addImage(aPackage.getId(), "완전 새로운 이미지 파일", "gif");
        Integer afterImageSize = aPackage.getImages().size();

        assertNotNull(response);
        assertNotEquals(beforeImageSize, afterImageSize);
        System.out.println(response);
    }

    @Test
    @DisplayName("존재하지 않는 패키지에 이미지를 추가할 경우 예외를 발생한다..")
    void test_8() {
        // when
        // then
        IllegalArgumentException aThrows = assertThrows(IllegalArgumentException.class, () -> packageService.addImage(100L, "완전 새로운 이미지 파일", "gif"));
        assertEquals("해당 패키지가 존재하지 않습니다.", aThrows.getMessage());
    }

    @Test
    @DisplayName("패키지를 삭제할 수 있다.")
    void test_9() {
        // given
        PackageFixture.setUpPackageList(packageRepository);
        Package aPackage = packageRepository.findAll()
                .stream()
                .findFirst().get();
        // when
        PackageDeleteResponse response = packageService.deletePackage(aPackage.getId());
        // then
        assertNotNull(response);
        assertEquals(aPackage.getId(), response.packageId());
        assertEquals(DeleteMessage.SUCCESS, response.messageStatus());
        assertEquals(DeleteMessage.SUCCESS.getMessage(), response.message());
    }

    @Test
    @DisplayName("존재하지 않는 패키지를 삭제할 경우 예외를 발생한다.")
    void test_10() {
        // when
        // then
        IllegalArgumentException aThrows = assertThrows(IllegalArgumentException.class, () -> packageService.deletePackage(100L));
        assertEquals("해당 패키지가 존재하지 않습니다.", aThrows.getMessage());
    }
}
