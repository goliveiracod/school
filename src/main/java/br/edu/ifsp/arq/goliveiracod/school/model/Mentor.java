package br.edu.ifsp.arq.goliveiracod.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String country;
    @JsonBackReference
    @OneToMany(mappedBy = "mentor")
    private List<Student> students;
    @JsonIgnore
    @NotNull
    @ManyToOne
    private Program program;
    @JsonIgnore
    @OneToMany(mappedBy = "mentor")
    private List<StudentDisciplineNoteGivenByTheMentor> studentDisciplineNoteGivenByTheMentors;
    private LocalDateTime createdAt = LocalDateTime.now();


    public Mentor() {
    }

    public Mentor(String name, String country, Program program) {
        this.name = name;
        this.country = country;
        this.program = program;
    }
}
