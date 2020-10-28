package br.edu.ifsp.arq.goliveiracod.school.controller.form;

import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Program;
import br.edu.ifsp.arq.goliveiracod.school.repository.ProgramRepository;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MentorForm {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String country;

    private Program program;

    public Mentor converter() {
        return new Mentor(name, country, program);
    }
}
