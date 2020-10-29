package br.edu.ifsp.arq.goliveiracod.school.controller.form;

import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Student;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class StudentForm {
    @NotNull
    @NotEmpty
    private String name;

    private Mentor mentor;

    public Student converter() {
        return new Student(name, mentor);
    }
}
