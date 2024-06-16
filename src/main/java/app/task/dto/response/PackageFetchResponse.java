package app.task.dto.response;

import java.util.List;

public record PackageFetchResponse(
        Long id,
        String trackingNo,
        List<ImageInfoResponse> images
) {
    public static PackageFetchResponse of(Long id, String trackingNo, List<ImageInfoResponse> images) {
        return new PackageFetchResponse(id, trackingNo, images);
    }
}
