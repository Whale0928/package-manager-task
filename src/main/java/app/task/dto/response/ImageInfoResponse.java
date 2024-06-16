package app.task.dto.response;

public record ImageInfoResponse(
        String filename,
        String type
) {
    public static ImageInfoResponse of(String filename, String type) {
        return new ImageInfoResponse(filename, type);
    }
}
