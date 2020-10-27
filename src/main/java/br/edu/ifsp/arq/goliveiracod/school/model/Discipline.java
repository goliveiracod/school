package br.edu.ifsp.arq.goliveiracod.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "discipline")
    private List<StudentDisciplineGivenByTheMentor> studentDisciplineNoteGivenByTheMentors;
    private LocalDateTime createdAt = LocalDateTime.now();
}
