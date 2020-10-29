package br.edu.ifsp.arq.goliveiracod.school.controller;

import br.edu.ifsp.arq.goliveiracod.school.controller.dto.StudentDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.StudentForm;
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
@RequestMapping("/student")
public class StudentController {

    public final static String baseURLWithId = "/student/{id}";

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public Page<StudentDto> paginateAllWithFilter(
            @RequestParam(required = false) String name
            , @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return studentService.paginateAllWithFilter(pageable, name);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<StudentDto> create(
            @Validated @RequestBody StudentForm studentForm, UriComponentsBuilder uriComponentsBuilder
    ) {
        ServiceCreateUtil<StudentDto> serviceCreateUtil = studentService.create(studentForm, uriComponentsBuilder);
        return ResponseEntity.created(serviceCreateUtil.getUri()).body(serviceCreateUtil.getDto());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<StudentDto> find(@PathVariable Long id) {
        Optional<StudentDto> optionalStudentDto = studentService.find(id);

        return optionalStudentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StudentDto> update(
            @PathVariable Long id, @Validated @RequestBody StudentForm studentForm
    ) {
        Optional<StudentDto> optionalStudentDto = studentService.update(id, studentForm);
        return optionalStudentDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (studentService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
