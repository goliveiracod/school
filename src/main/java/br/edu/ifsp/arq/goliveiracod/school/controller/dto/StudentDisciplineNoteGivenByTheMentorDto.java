package br.edu.ifsp.arq.goliveiracod.school.controller.dto;

import br.edu.ifsp.arq.goliveiracod.school.model.Discipline;
import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Student;
import br.edu.ifsp.arq.goliveiracod.school.model.StudentDisciplineNoteGivenByTheMentor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
public class StudentDisciplineNoteGivenByTheMentorDto {

    private Long id;
    private Mentor mentor;
    private Student student;
    private Discipline discipline;
    private Float note;
    private LocalDateTime createdAt;
    private LocalDateTime lastChangeNote;

    public StudentDisciplineNoteGivenByTheMentorDto(StudentDisciplineNoteGivenByTheMentor studentDisciplineNoteGivenByTheMentor) {
        id = studentDisciplineNoteGivenByTheMentor.getId();
//        mentor = studentDisciplineNoteGivenByTheMentor.getMentor();
//        student = studentDisciplineNoteGivenByTheMentor.getStudent();
//        discipline = studentDisciplineNoteGivenByTheMentor.getDiscipline();
//        note = studentDisciplineNoteGivenByTheMentor.getNote();
//        createdAt = studentDisciplineNoteGivenByTheMentor.getCreatedAt();
//        lastChangeNote = studentDisciplineNoteGivenByTheMentor.getLastChangeNote();
    }

    public static Page<StudentDisciplineNoteGivenByTheMentorDto> converter(
            Page<StudentDisciplineNoteGivenByTheMentor> studentDisciplineGivenByTheMentors) {

        System.out.println("AAAAAAAAAAAAAA@@@@@@@\n\n");
        Long id = studentDisciplineGivenByTheMentors.getContent().get(0).getId();
        System.out.println(id);
        System.out.println("!!!!!!!!!@@@@@@@@@@@@@@@@@@!!\n");

//        return studentDisciplineGivenByTheMentors.map(
//                studentDisciplineNoteGivenByTheMentor -> {
//                    System.out.println(studentDisciplineNoteGivenByTheMentor.getMentor().getName());
//                    return new StudentDisciplineNoteGivenByTheMentorDto(studentDisciplineNoteGivenByTheMentor);
//                }
//        );
        return null;
    }
}
