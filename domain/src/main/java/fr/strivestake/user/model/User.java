package fr.strivestake.user.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String email;
    private AccountStatusEnum accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User accountStatus(AccountStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public User createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public User updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
