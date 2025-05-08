package org.example.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin
    @PostMapping("/upload-profile")
    public ResponseEntity<?> uploadProfile(@RequestBody User user) {
        try {
            User savedProfile = userService.saveProfile(user);

            // สร้าง response map ที่มีฟิลด์ success เพื่อให้สอดคล้องกับ frontend
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Profile saved successfully");
            response.put("data", savedProfile);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error saving profile: " + e.getMessage());

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}