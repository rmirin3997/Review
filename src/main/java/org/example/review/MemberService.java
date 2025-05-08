package org.example.review;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveProfile(Member member) {
        // ตรวจสอบว่ามีผู้ใช้นี้อยู่แล้วหรือไม่
        Member existingMember = memberRepository.findByMembername(member.getMembername());

        if (existingMember != null) {
            // อัปเดตข้อมูลของผู้ใช้ที่มีอยู่แล้ว
            existingMember.setName(member.getName());
            existingMember.setBio(member.getBio());
            existingMember.setProfileImageUrl(member.getProfileImageUrl());
            return memberRepository.save(existingMember);
        } else {
            // สร้างผู้ใช้ใหม่
            return memberRepository.save(member);
        }
    }
}
