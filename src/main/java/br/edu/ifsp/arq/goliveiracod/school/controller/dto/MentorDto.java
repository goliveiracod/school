package br.edu.ifsp.arq.goliveiracod.school.controller.dto;

import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Program;
import br.edu.ifsp.arq.goliveiracod.school.model.Student;

import br.edu.ifsp.arq.goliveiracod.school.model.StudentDisciplineNoteGivenByTheMentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MentorDto {
    private Long id;
    private String name;
    private String country;
    private List<Student> students;
    private Program program;
    private List<StudentDisciplineNoteGivenByTheMentor> studentDisciplineNoteGivenByTheMentors;
    private LocalDateTime createdAt;

    public MentorDto(Mentor mentor) {
        id = mentor.getId();
        name = mentor.getName();
        country = mentor.getCountry();
        students = mentor.getStudents();
        program = mentor.getProgram();
        studentDisciplineNoteGivenByTheMentors = mentor.getStudentDisciplineNoteGivenByTheMentors();
        createdAt = mentor.getCreatedAt();
    }

    public static Page<MentorDto> converter(Page<Mentor> mentors) {
        return mentors.map(MentorDto::new);
    }
}
