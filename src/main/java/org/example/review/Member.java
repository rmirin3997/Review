package org.example.review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member {

    @Id
    private String id;

    private String name;
    private String membername;
    private String bio = "ComSci";
    private String profileImageUrl = "";

    // Default constructor
    public Member() {
    }

    // Constructor with all fields
    public Member(String id, String name, String membername, String bio, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.membername = membername;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", membername='" + membername + '\'' +
                ", bio='" + bio + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}
