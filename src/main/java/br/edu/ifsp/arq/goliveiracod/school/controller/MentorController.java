package br.edu.ifsp.arq.goliveiracod.school.controller;

import br.edu.ifsp.arq.goliveiracod.school.controller.dto.MentorDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.MentorForm;
import br.edu.ifsp.arq.goliveiracod.school.service.MentorService;
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
@RequestMapping("/mentor")
public class MentorController {

    public final static String baseURLWithId = "/mentor/{id}";

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public Page<MentorDto> paginateAllWithFilter(
            @RequestParam(required = false) String name
            , @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return mentorService.paginateAllWithFilter(pageable, name);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MentorDto> create(
            @Validated @RequestBody MentorForm mentorForm, UriComponentsBuilder uriComponentsBuilder
    ) {
        ServiceCreateUtil<MentorDto> serviceCreateUtil = mentorService.create(mentorForm, uriComponentsBuilder);
        return ResponseEntity.created(serviceCreateUtil.getUri()).body(serviceCreateUtil.getDto());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<MentorDto> find(@PathVariable Long id) {
        Optional<MentorDto> optionalProgramDto = mentorService.find(id);

        return optionalProgramDto.map(program -> ResponseEntity.ok(optionalProgramDto.get()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MentorDto> update(
            @PathVariable Long id, @Validated @RequestBody MentorForm mentorForm
    ) {
        Optional<MentorDto> optionalProgramDto = mentorService.update(id, mentorForm);
        return optionalProgramDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (mentorService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
