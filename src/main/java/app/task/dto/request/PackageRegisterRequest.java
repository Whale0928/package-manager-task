package app.task.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PackageRegisterRequest(
        @Size(min = 12, max = 12, message = "송장번호는 12자리여야 합니다.")
        @NotBlank(message = "송장번호는 필수입니다.")
        String trackingNo,

        @Size(min = 1, message = "이미지는 최소 1개 이상이어야 합니다.")
        List<@Valid ImageInfoRequest> images
) {
}
