package app.task.ui;

import app.task.application.PackageService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/package")
public class PackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    /**
     * 모든 패키지 목록을 조회합니다.
     */
    @GetMapping
    public ResponseEntity<?> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    /**
     * 식별자를 이용하여 단일 패키지를 조회합니다.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPackageById(
            @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    /**
     * 새 패키지를 등록합니다.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerPackage() {
        return ResponseEntity.ok(null);
    }

    /**
     * 식별자를 이용하여 패키지에 이미지를 추가합니다.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> addImage(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }

    /**
     * 식별자를 이용하여 패키지를 삭제합니다.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePackage(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }
}
