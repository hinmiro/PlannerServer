package org.example.plannerserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "calendar_date")
@Getter
@Setter
public class CalendarDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long calendarDateId;

    @OneToMany(mappedBy = "calendarDate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

    @OneToMany(mappedBy = "calendarDate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoList> todoLists;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;
}
