package org.example.plannerserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "app_user")
@Data
@Builder
public class User {

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

    @JsonProperty("applicationData")
    public Long getApplicationDataId() {
        return applicationData != null ? applicationData.getData_id() : null;
    }
}