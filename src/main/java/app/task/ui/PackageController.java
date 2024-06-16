package app.task.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/package")
@RestController
public class PackageController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from package controller";
    }

}
