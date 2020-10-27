package br.edu.ifsp.arq.goliveiracod.school.controller.dto;

import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Program;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProgramDto {
    private Long id;
    private String name;
    private List<Mentor> mentors;
    private LocalDateTime createdAt;

    public ProgramDto(Program program) {
        id = program.getId();
        name = program.getName();
        mentors = program.getMentors();
        createdAt = program.getCreatedAt();
    }

    public static Page<ProgramDto> converter(Page<Program> programs) {
        return programs.map(ProgramDto::new);
    }
}
