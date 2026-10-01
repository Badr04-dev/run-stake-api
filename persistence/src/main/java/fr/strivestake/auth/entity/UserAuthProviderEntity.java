package fr.strivestake.auth.entity;

import fr.strivestake.auth.model.ProviderEnum;
import fr.strivestake.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "USER_AUTH_PROVIDER")
@Entity
@Getter
public class UserAuthProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private UserEntity user;

    @Column(name = "PROVIDER")
    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    public UserAuthProviderEntity id(Long id) {
        this.id = id;
        return this;
    }

    public UserAuthProviderEntity user(UserEntity user) {
        this.user = user;
        return this;
    }

    public UserAuthProviderEntity provider(ProviderEnum provider) {
        this.provider = provider;
        return this;
    }

    public UserAuthProviderEntity providerUserId(String providerUserId) {
        this.providerUserId = providerUserId;
        return this;
    }

    public UserAuthProviderEntity createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserAuthProviderEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
