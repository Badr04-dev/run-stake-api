package fr.strivestake.user.entity;

import fr.strivestake.user.model.AccountStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "APP_USER")
@Entity
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACCOUNT_STATUS")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum accountStatus;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public UserEntity id(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity username(String username) {
        this.username = username;
        return this;
    }

    public UserEntity email(String email) {
        this.email = email;
        return this;
    }

    public UserEntity accountStatus(AccountStatusEnum accountStatusEnum) {
        this.accountStatus = accountStatusEnum;
        return this;
    }

    public UserEntity createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserEntity updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
