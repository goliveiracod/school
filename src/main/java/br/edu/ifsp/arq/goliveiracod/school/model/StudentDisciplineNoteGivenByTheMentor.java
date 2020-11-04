package br.edu.ifsp.arq.goliveiracod.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"mentor_id", "student_id", "discipline_id"})})
public class StudentDisciplineNoteGivenByTheMentor {
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
    private LocalDateTime lastChangeNote;
    private LocalDateTime createdAt = LocalDateTime.now();

    public StudentDisciplineNoteGivenByTheMentor(){

    }

    public StudentDisciplineNoteGivenByTheMentor(
            Mentor mentor,
            Student student,
            Discipline discipline,
            Float note
    ) {
        this.mentor = mentor;
        this.student = student;
        this.discipline = discipline;
        this.note = note;
        this.lastChangeNote = LocalDateTime.now();
    }
}
