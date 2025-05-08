package org.example.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @CrossOrigin
    @PostMapping("/upload-Memprofile")
    public ResponseEntity<?> uploadProfile(@RequestBody Member member) {
        try {
            Member savedProfile = memberService.saveProfile(member);

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
