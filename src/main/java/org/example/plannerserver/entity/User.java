package org.example.plannerserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_user")
@Getter
@Setter
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

    public User(String username, String password, String email, ApplicationData applicationData) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.applicationData = applicationData;
    }

    public User() {}
}
