package br.edu.ifsp.arq.goliveiracod.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "discipline")
    private List<StudentDisciplineNoteGivenByTheMentor> studentDisciplineNoteGivenByTheMentors;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Discipline() {

    }

    public Discipline(String name) {
        this.name = name;
    }
}
