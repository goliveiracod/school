package br.edu.ifsp.arq.goliveiracod.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Mentor> mentors;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Program() {

    }

    public Program(String name) {
        this.name = name;
    }
}
