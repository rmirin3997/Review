package org.example.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
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
