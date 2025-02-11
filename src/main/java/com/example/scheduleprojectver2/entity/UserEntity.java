package com.example.scheduleprojectver2.entity;

import com.example.scheduleprojectver2.dto.users.UserResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    public UserEntity(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserResponseDto toDto() {
        return new UserResponseDto(this.id, this.username, this.email);
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
