package br.edu.ifsp.arq.goliveiracod.school.controller.form;

import br.edu.ifsp.arq.goliveiracod.school.model.Program;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProgramForm {
    @NotNull
    @NotEmpty
    private String name;

    public Program converter() {
        return new Program(name);
    }
}
