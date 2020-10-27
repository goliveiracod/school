package br.edu.ifsp.arq.goliveiracod.school.controller;

import br.edu.ifsp.arq.goliveiracod.school.controller.dto.ProgramDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.ProgramForm;
import br.edu.ifsp.arq.goliveiracod.school.service.ProgramService;
import br.edu.ifsp.arq.goliveiracod.school.service.util.ServiceCreateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/program")
public class ProgramController {

    public final static String baseURLWithId = "/program/{id}";

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public Page<ProgramDto> paginateAllWithFilter(
            @RequestParam(required = false) String name
            , @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return programService.paginateAllWithFilter(pageable, name);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProgramDto> create(
            @Validated @RequestBody ProgramForm programForm, UriComponentsBuilder uriComponentsBuilder
    ) {
        ServiceCreateUtil<ProgramDto> programServiceCreateUtil = programService.create(programForm, uriComponentsBuilder);

        return ResponseEntity.created(programServiceCreateUtil.getUri()).body(programServiceCreateUtil.getDto());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProgramDto> find(@PathVariable Long id) {
        Optional<ProgramDto> optionalProgramDto = programService.find(id);

        return optionalProgramDto.map(program -> ResponseEntity.ok(optionalProgramDto.get()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProgramDto> update(
            @PathVariable Long id, @Validated @RequestBody ProgramForm programForm
    ) {
        Optional<ProgramDto> optionalProgramDto = programService.update(id, programForm);
        return optionalProgramDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (programService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
