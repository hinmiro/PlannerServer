package org.example.plannerserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "application_data")
@Getter
@Setter
public class ApplicationData {
    @Id
    @SequenceGenerator(name="app_sequence", sequenceName="app_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_sequence")
    private Long data_id;
}
