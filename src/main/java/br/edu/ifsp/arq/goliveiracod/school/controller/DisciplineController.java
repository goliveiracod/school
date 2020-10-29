package br.edu.ifsp.arq.goliveiracod.school.controller;

import br.edu.ifsp.arq.goliveiracod.school.controller.dto.DisciplineDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.DisciplineForm;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.StudentForm;
import br.edu.ifsp.arq.goliveiracod.school.model.Discipline;
import br.edu.ifsp.arq.goliveiracod.school.service.DisciplineService;
import br.edu.ifsp.arq.goliveiracod.school.service.StudentService;
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
@RequestMapping("/discipline")
public class DisciplineController {

    public final static String baseURLWithId = "/discipline/{id}";

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }


    @GetMapping
    public Page<DisciplineDto> paginateAllWithFilter(
            @RequestParam(required = false) String name
            , @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return disciplineService.paginateAllWithFilter(pageable, name);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DisciplineDto> create(
            @Validated @RequestBody DisciplineForm disciplineForm, UriComponentsBuilder uriComponentsBuilder
    ) {
        ServiceCreateUtil<DisciplineDto> serviceCreateUtil = disciplineService.create(disciplineForm, uriComponentsBuilder);
        return ResponseEntity.created(serviceCreateUtil.getUri()).body(serviceCreateUtil.getDto());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DisciplineDto> find(@PathVariable Long id) {
        Optional<DisciplineDto> optionalDisciplineDto = disciplineService.find(id);

        return optionalDisciplineDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DisciplineDto> update(
            @PathVariable Long id, @Validated @RequestBody DisciplineForm disciplineForm
    ) {
        Optional<DisciplineDto> optionalDisciplineDto = disciplineService.update(id, disciplineForm);
        return optionalDisciplineDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (disciplineService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
