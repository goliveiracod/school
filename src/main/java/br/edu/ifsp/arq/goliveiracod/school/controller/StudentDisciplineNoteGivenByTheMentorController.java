package br.edu.ifsp.arq.goliveiracod.school.controller;

import br.edu.ifsp.arq.goliveiracod.school.controller.dto.StudentDisciplineNoteGivenByTheMentorDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.StudentDisciplineNoteGivenByTheMentorForm;
import br.edu.ifsp.arq.goliveiracod.school.service.StudentDisciplineNoteGivenByTheMentorService;
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

@RestController
@RequestMapping("/note")
public class StudentDisciplineNoteGivenByTheMentorController {

    public final static String baseURLWithId = "/note/{id}";

    private final StudentDisciplineNoteGivenByTheMentorService studentDisciplineNoteGivenByTheMentorService;

    public StudentDisciplineNoteGivenByTheMentorController(
            StudentDisciplineNoteGivenByTheMentorService studentDisciplineNoteGivenByTheMentorService
    ) {
        this.studentDisciplineNoteGivenByTheMentorService = studentDisciplineNoteGivenByTheMentorService;
    }

    @GetMapping
    public Page<StudentDisciplineNoteGivenByTheMentorDto> paginateAllWithFilter(
            @RequestParam(required = false, defaultValue = "") String studentName
            ,@RequestParam(required = false, defaultValue = "") String disciplineName
            , @RequestParam(required = false, defaultValue = "") String mentorName
            , @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return studentDisciplineNoteGivenByTheMentorService.paginateAllWithFilter(
                pageable,
                studentName,
                disciplineName,
                mentorName
        );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<StudentDisciplineNoteGivenByTheMentorDto> create(
            @Validated @RequestBody StudentDisciplineNoteGivenByTheMentorForm studentDisciplineNoteGivenByTheMentorForm
            , UriComponentsBuilder uriComponentsBuilder
    ) {
        ServiceCreateUtil<StudentDisciplineNoteGivenByTheMentorDto> serviceCreateUtil =
                studentDisciplineNoteGivenByTheMentorService.create(
                        studentDisciplineNoteGivenByTheMentorForm
                        , uriComponentsBuilder
                );
        return ResponseEntity.created(serviceCreateUtil.getUri()).body(serviceCreateUtil.getDto());
    }
//
//    @GetMapping("/{id}")
//    @Transactional
//    public ResponseEntity<StudentDisciplineNoteGivenByTheMentorDto> find(@PathVariable Long id) {
//        Optional<StudentDisciplineNoteGivenByTheMentorDto> optionalStudentDisciplineNoteGivenByTheMentorDto = studentDisciplineNoteGivenByTheMentorService.find(id);
//
//        return optionalStudentDisciplineNoteGivenByTheMentorDto.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<StudentDisciplineNoteGivenByTheMentorDto> update(
//            @PathVariable Long id, @Validated @RequestBody StudentForm studentForm
//    ) {
//        Optional<StudentDisciplineNoteGivenByTheMentorDto> optionalStudentDisciplineNoteGivenByTheMentorDto = studentDisciplineNoteGivenByTheMentorService.update(id, studentForm);
//        return optionalStudentDisciplineNoteGivenByTheMentorDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<?> remover(@PathVariable Long id) {
//        if (studentDisciplineNoteGivenByTheMentorService.delete(id)) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
