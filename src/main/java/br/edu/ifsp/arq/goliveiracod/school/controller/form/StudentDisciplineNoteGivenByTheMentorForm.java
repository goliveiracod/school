package br.edu.ifsp.arq.goliveiracod.school.controller.form;

import br.edu.ifsp.arq.goliveiracod.school.model.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDisciplineNoteGivenByTheMentorForm {

    private Mentor mentor;
    private Student student;
    private Discipline discipline;
    private Float note;

    public StudentDisciplineNoteGivenByTheMentor converter() {
        return new StudentDisciplineNoteGivenByTheMentor(mentor, student, discipline, note);
    }
}
