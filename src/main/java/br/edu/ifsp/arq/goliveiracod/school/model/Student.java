package br.edu.ifsp.arq.goliveiracod.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @JsonIgnore
    @ManyToOne
    private Mentor mentor;
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentDisciplineNoteGivenByTheMentor> studentDisciplineNoteGivenByTheMentors;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Student() {

    }

    public Student(String name, Mentor mentor) {
        this.name = name;
        this.mentor = mentor;
    }
}
