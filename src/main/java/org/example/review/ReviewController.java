package org.example.review;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // ฟังก์ชัน GET สำหรับดึงข้อมูลรีวิว
    @GetMapping("/all_review")
    public List<Review> getReviews() {
        return reviewService.getAllReviews();
    }


    // ฟังก์ชัน POST สำหรับเพิ่มรีวิว
    @PostMapping("/addreview")
    public ResponseEntity<?> addReview(@RequestBody Review review, HttpServletRequest request) {
        // ดึง IP และ User-Agent
        String ip = request.getHeader("X-Forwarded-For");
        String userAgent = request.getHeader("User-Agent");

        // ตรวจสอบข้อมูล
        if (review.getTitle() == null || review.getDescription() == null || review.getCategory() == null || review.getAuthor() == null || review.getRating() == 0) {
            return ResponseEntity.badRequest().body("{\"message\": \"Missing fields\"}");
        }

        // ตรวจสอบเนื้อหาที่ไม่เหมาะสม
        if (reviewService.checkInappropriateContent(review.getTitle(), review.getDescription())) {
            return ResponseEntity.badRequest().body("{\"message\": \"Inappropriate content detected\"}");
        }

        // ตรวจสอบการส่งซ้ำ
        if (reviewService.checkDuplicateReview(review.getTitle(), review.getAuthor())) {
            return ResponseEntity.status(429).body("{\"message\": \"Duplicate review detected\"}");
        }

        // เพิ่มข้อมูลรีวิว
        review.setIp(ip != null ? ip : "unknown");
        review.setUserAgent(userAgent != null ? userAgent : "unknown");
        review.setCreatedAt(new Date());
        reviewService.addReview(review);

        // ส่งคำตอบในรูปแบบ JSON ที่มีข้อมูลเพิ่มเติม
        ReviewResponse response = new ReviewResponse();
        response.setMessage("Review submitted successfully!");
        response.setRedirect("/all_review"); // Provide the redirection URL

        return ResponseEntity.ok(response);
    }
}
