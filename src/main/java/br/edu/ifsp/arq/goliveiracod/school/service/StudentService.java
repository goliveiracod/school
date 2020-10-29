package br.edu.ifsp.arq.goliveiracod.school.service;

import br.edu.ifsp.arq.goliveiracod.school.controller.StudentController;
import br.edu.ifsp.arq.goliveiracod.school.controller.dto.StudentDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.StudentForm;
import br.edu.ifsp.arq.goliveiracod.school.exception.MentorNotFoundException;
import br.edu.ifsp.arq.goliveiracod.school.exception.ProgramNotFoundException;
import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Student;
import br.edu.ifsp.arq.goliveiracod.school.repository.MentorRepository;
import br.edu.ifsp.arq.goliveiracod.school.repository.StudentRepository;
import br.edu.ifsp.arq.goliveiracod.school.service.util.ServiceCreateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;

    public StudentService(
            StudentRepository studentRepository,
            MentorRepository mentorRepository
    ) {
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
    }

    public Page<StudentDto> paginateAllWithFilter(Pageable pageable, String name) {
        Page<Student> students;
        if (name == null)
            students = studentRepository.findAll(pageable);
        else
            students = studentRepository.findByName(name, pageable);

        return StudentDto.converter(students);
    }

    public ServiceCreateUtil<StudentDto> create(StudentForm studentForm, UriComponentsBuilder uriComponentsBuilder) {
        Student student = studentForm.converter();
        Optional<Mentor> optionalMentor = mentorRepository.findById(student.getMentor().getId());

        if (optionalMentor.isPresent())
            student = studentRepository.save(student);
        else
            throw new MentorNotFoundException("Mentor not found");
        URI uri = uriComponentsBuilder.path(StudentController.baseURLWithId).buildAndExpand(student.getId()).toUri();
        return new ServiceCreateUtil<>(new StudentDto(student), uri);
    }

    public Optional<StudentDto> find(Long id) {
        Optional<Student> optionalProgram = studentRepository.findById(id);
        return optionalProgram.map(StudentDto::new);
    }

    public Optional<StudentDto> update(Long id, StudentForm studentForm) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Optional<Mentor> optionalMentor = mentorRepository.findById(studentForm.getMentor().getId());
            if (optionalMentor.isEmpty()) {
                throw new ProgramNotFoundException("Mentor not found");
            }
            student.setName(studentForm.getName());
            student.setMentor(studentForm.getMentor());

            return Optional.of(new StudentDto(student));
        }
        return Optional.empty();
    }

    public Boolean delete(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
