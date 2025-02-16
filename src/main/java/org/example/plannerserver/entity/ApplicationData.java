package org.example.plannerserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "application_data")
@Getter
@Setter
@NoArgsConstructor
public class ApplicationData implements Serializable {

    @Id
    @SequenceGenerator(name = "app_sequence", sequenceName = "app_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_sequence")
    @Column(name = "data_id")
    private Long dataId;

    @OneToOne(mappedBy = "applicationData")
    private User user;

    public ApplicationData(User user) {
        this.user = user;
    }
}
