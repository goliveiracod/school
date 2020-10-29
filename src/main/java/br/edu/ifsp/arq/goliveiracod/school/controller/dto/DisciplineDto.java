package br.edu.ifsp.arq.goliveiracod.school.controller.dto;

import br.edu.ifsp.arq.goliveiracod.school.model.Discipline;
import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Student;
import br.edu.ifsp.arq.goliveiracod.school.model.StudentDisciplineGivenByTheMentor;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DisciplineDto {

    private Long id;
    private String name;
    private List<StudentDisciplineGivenByTheMentor> studentDisciplineNoteGivenByTheMentors;
    private LocalDateTime createdAt;

    public DisciplineDto(Discipline discipline) {
        id = discipline.getId();
        name = discipline.getName();
        studentDisciplineNoteGivenByTheMentors = discipline.getStudentDisciplineNoteGivenByTheMentors();
        createdAt = discipline.getCreatedAt();
    }

    public static Page<DisciplineDto> converter(Page<Discipline> disciplines) {
        return disciplines.map(DisciplineDto::new);
    }
}
