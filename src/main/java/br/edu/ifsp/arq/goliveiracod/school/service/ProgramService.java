package br.edu.ifsp.arq.goliveiracod.school.service;

import br.edu.ifsp.arq.goliveiracod.school.controller.ProgramController;
import br.edu.ifsp.arq.goliveiracod.school.controller.dto.ProgramDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.ProgramForm;
import br.edu.ifsp.arq.goliveiracod.school.model.Program;
import br.edu.ifsp.arq.goliveiracod.school.repository.ProgramRepository;
import br.edu.ifsp.arq.goliveiracod.school.service.util.ServiceCreateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Page<ProgramDto> paginateAllWithFilter(Pageable pageable, String name) {
        Page<Program> programs;
        if (name == null)
            programs = programRepository.findAll(pageable);
        else
            programs = programRepository.findByName(name, pageable);

        return ProgramDto.converter(programs);
    }

    public ServiceCreateUtil<ProgramDto> create(ProgramForm programForm, UriComponentsBuilder uriComponentsBuilder) {
        Program program = programRepository.save(programForm.converter());
        URI uri = uriComponentsBuilder.path(ProgramController.baseURLWithId).buildAndExpand(program.getId()).toUri();
        return new ServiceCreateUtil<>(new ProgramDto(program), uri);
    }

    public Optional<ProgramDto> find(Long id) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        return optionalProgram.map(ProgramDto::new);
    }

    public Optional<ProgramDto> update(Long id, ProgramForm programForm) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setName(programForm.getName());
            return Optional.of(new ProgramDto(program));
        }
        return Optional.empty();
    }

    public Boolean delete(Long id) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            programRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
