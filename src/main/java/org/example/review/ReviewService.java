package org.example.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Search and filter reviews
    public List<Review> filterReviews(String searchTerm, String category) {
        // If no search term or category is provided, return all reviews
        if ((searchTerm == null || searchTerm.isEmpty()) &&
                (category == null || category.isEmpty())) {
            return reviewRepository.findAll();
        }

        // Use custom query with search parameters
        return reviewRepository.findBySearchTermAndCategory(searchTerm, category);
    }

    // ฟังก์ชันกรองรีวิวตามหมวดหมู่
    public List<Review> filterReviewsByCategory(String category) {
        if (category == null || category.isEmpty()) {
            return reviewRepository.findAll();  // หากไม่มีหมวดหมู่, ส่งคืนรีวิวทั้งหมด
        }
        return reviewRepository.findByCategory(category);  // ส่งคืนรีวิวที่กรองตามหมวดหมู่
    }

    public boolean checkInappropriateContent(String title, String description) {
        // List of inappropriate words
        String[] badWords = {"badword","spam", "scam", "xxx", "casino", "gambling"};

        // Check if any of the bad words exist in the title or description
        for (String badWord : badWords) {
            if (title.contains(badWord) || description.contains(badWord)) {
                return true; // If any bad word is found, return true
            }
        }

        return false; // Return false if no bad words are found
    }

    public boolean checkDuplicateReview(String title, String author) {
        // ตัวอย่างการตรวจสอบรีวิวซ้ำ
        return reviewRepository.existsByTitleAndAuthor(title, author);
    }

    public void addReview(Review review) {
        reviewRepository.save(review);
    }
}
