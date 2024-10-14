package com.khalouda.hotelhub.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khalouda.hotelhub.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tokens")
@Entity
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
