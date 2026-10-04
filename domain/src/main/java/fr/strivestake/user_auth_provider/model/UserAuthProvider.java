package fr.strivestake.user_auth_provider.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class UserAuthProvider {

    private Long id;
    private Long userId;
    private ProviderEnum provider;
    private String providerUserId;
    private LocalDateTime createdAt;

    public UserAuthProvider id(Long id) {
        this.id = id;
        return this;
    }

    public UserAuthProvider userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserAuthProvider provider(ProviderEnum provider) {
        this.provider = provider;
        return this;
    }

    public UserAuthProvider providerUserId(String providerUserId) {
        this.providerUserId = providerUserId;
        return this;
    }

    public UserAuthProvider createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserAuthProvider that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
