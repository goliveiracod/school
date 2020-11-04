package br.edu.ifsp.arq.goliveiracod.school.controller.dto;

import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Student;
import br.edu.ifsp.arq.goliveiracod.school.model.StudentDisciplineNoteGivenByTheMentor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private Mentor mentor;
    private List<StudentDisciplineNoteGivenByTheMentor> studentDisciplineNoteGivenByTheMentors;
    private LocalDateTime createdAt;

    public StudentDto(Student student) {
        id = student.getId();
        name = student.getName();
        mentor = student.getMentor();
        studentDisciplineNoteGivenByTheMentors = student.getStudentDisciplineNoteGivenByTheMentors();
        createdAt = student.getCreatedAt();
    }

    public static Page<StudentDto> converter(Page<Student> students) {
        return students.map(StudentDto::new);
    }
}
