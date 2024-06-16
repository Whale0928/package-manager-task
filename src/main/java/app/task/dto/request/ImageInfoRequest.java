package app.task.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ImageInfoRequest(
        @NotBlank(message = "이미지 파일명은 필수입니다.")
        String filename,
        @NotBlank(message = "이미지 타입은 필수입니다.")
        String type
) {
}
