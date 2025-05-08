package org.example.review;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveProfile(User user) {
        // ตรวจสอบว่ามีผู้ใช้นี้อยู่แล้วหรือไม่ (ถ้าต้องการอัพเดทแทนที่จะสร้างใหม่)
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null) {
            // อัพเดทข้อมูลของผู้ใช้ที่มีอยู่แล้ว
            existingUser.setName(user.getName());
            existingUser.setBio(user.getBio());
            existingUser.setProfileImageUrl(user.getProfileImageUrl());
            return userRepository.save(existingUser);
        } else {
            // สร้างผู้ใช้ใหม่
            return userRepository.save(user);
        }
    }
}