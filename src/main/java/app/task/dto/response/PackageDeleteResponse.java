package app.task.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

public record PackageDeleteResponse(
        DeleteMessage messageStatus,
        String message,
        Long packageId,
        LocalDateTime deleteAt

) {

    public static PackageDeleteResponse success(Long packageId) {
        return new PackageDeleteResponse(
                DeleteMessage.SUCCESS,
                DeleteMessage.SUCCESS.message,
                packageId,
                LocalDateTime.now());
    }

    @Getter
    public enum DeleteMessage {
        SUCCESS("삭제에 성공하였습니다."),
        FAIL("삭제에 실패하였습니다.");

        private final String message;

        DeleteMessage(String message) {
            this.message = message;
        }
    }
}
