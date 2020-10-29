package br.edu.ifsp.arq.goliveiracod.school.controller.form;

import br.edu.ifsp.arq.goliveiracod.school.model.Discipline;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DisciplineForm {
    @NotNull
    @NotEmpty
    private String name;

    public Discipline converter() {
        return new Discipline(name);
    }
}
