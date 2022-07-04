package com.katering.kateringjava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_app")
public class UserApp implements UserDetails {

    private static final String NOT_EMPTY_MSG = "Tidak Boleh Kosong";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = NOT_EMPTY_MSG)
    @NotBlank(message = NOT_EMPTY_MSG)
    @NotEmpty(message = NOT_EMPTY_MSG)
    @Column(unique = true, updatable = false)
    private String username;

    @NotNull(message = NOT_EMPTY_MSG)
    @NotBlank(message = NOT_EMPTY_MSG)
    @NotEmpty(message = NOT_EMPTY_MSG)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}