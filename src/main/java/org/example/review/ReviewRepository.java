package org.example.review;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
    // สามารถเพิ่มเมธอดค้นหาต่างๆ ได้ตามต้องการ
    boolean existsByTitleAndAuthor(String title, String author);  // ตรวจสอบรีวิวซ้ำ
}