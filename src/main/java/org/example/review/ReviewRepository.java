package org.example.review;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{ $and: [ " +
            "  { $or: [ " +
            "    { 'title': { $regex: ?0, $options: 'i' } }, " +
            "    { 'description': { $regex: ?0, $options: 'i' } }, " +
            "    { 'author': { $regex: ?0, $options: 'i' } } " +
            "  ] }, " +
            "  { 'category': { $regex: ?1, $options: 'i' } } " +
            "] }")
    List<Review> findBySearchTermAndCategory(String searchTerm, String category);

    // ฟังก์ชันกรองรีวิวตามหมวดหมู่ (category)
    List<Review> findByCategory(String category);

    // Check if a review exists with the same title and author (duplicate reviews)
    boolean existsByTitleAndAuthor(String title, String author);  // ตรวจสอบรีวิวซ้ำ
}
