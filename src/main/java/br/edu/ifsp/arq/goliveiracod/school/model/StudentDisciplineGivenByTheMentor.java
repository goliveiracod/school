package br.edu.ifsp.arq.goliveiracod.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"mentor_id", "student_id", "discipline_id"})})
public class StudentDisciplineGivenByTheMentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Mentor mentor;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Discipline discipline;
    private Float note;
    private LocalDateTime last_change_note;
    private LocalDateTime createdAt = LocalDateTime.now();
}
