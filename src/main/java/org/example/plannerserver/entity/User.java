package org.example.plannerserver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "app_user")
@Data
@Builder
public class User implements UserDetails {

    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY) CAN ALSO BE USED
    @SequenceGenerator(name="user_sequence", sequenceName="user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;
    private String username;
    private String password;
    private String email;

//    CONNECTION TO app_data TABLE
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="appdata_id", referencedColumnName = "data_id")
    private ApplicationData applicationData;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {}

    public User(Long userId, String username, String password, String email, ApplicationData applicationData) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.applicationData = applicationData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}