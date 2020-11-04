package br.edu.ifsp.arq.goliveiracod.school.service;

import br.edu.ifsp.arq.goliveiracod.school.controller.StudentDisciplineNoteGivenByTheMentorController;
import br.edu.ifsp.arq.goliveiracod.school.controller.dto.StudentDisciplineNoteGivenByTheMentorDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.StudentDisciplineNoteGivenByTheMentorForm;
import br.edu.ifsp.arq.goliveiracod.school.model.StudentDisciplineNoteGivenByTheMentor;
import br.edu.ifsp.arq.goliveiracod.school.repository.StudentDisciplineNoteGivenByTheMentorRepository;
import br.edu.ifsp.arq.goliveiracod.school.service.util.ServiceCreateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class StudentDisciplineNoteGivenByTheMentorService {
    private final StudentDisciplineNoteGivenByTheMentorRepository studentDisciplineNoteGivenByTheMentorRepository;

    public StudentDisciplineNoteGivenByTheMentorService(
            StudentDisciplineNoteGivenByTheMentorRepository studentDisciplineNoteGivenByTheMentorRepository
    ) {
        this.studentDisciplineNoteGivenByTheMentorRepository = studentDisciplineNoteGivenByTheMentorRepository;
    }

    public Page<StudentDisciplineNoteGivenByTheMentorDto> paginateAllWithFilter(
            Pageable pageable,
            String studentName,
            String disciplineName,
            String mentorName

    ) {
        Page<StudentDisciplineNoteGivenByTheMentor> studentDisciplineNoteGivenByTheMentors =
                studentDisciplineNoteGivenByTheMentorRepository.findByMentorNameAndAndStudentNameAndDisciplineName(
                        pageable,
                        studentName,
                        disciplineName,
                        mentorName
                );


        return StudentDisciplineNoteGivenByTheMentorDto.converter(studentDisciplineNoteGivenByTheMentors);
    }

    public ServiceCreateUtil<StudentDisciplineNoteGivenByTheMentorDto> create(
            StudentDisciplineNoteGivenByTheMentorForm studentDisciplineNoteGivenByTheMentorForm
            , UriComponentsBuilder uriComponentsBuilder
    ) {
        StudentDisciplineNoteGivenByTheMentor studentDisciplineNoteGivenByTheMentor
                = studentDisciplineNoteGivenByTheMentorRepository.save(
                studentDisciplineNoteGivenByTheMentorForm.converter()
        );
        URI uri = uriComponentsBuilder
                .path(StudentDisciplineNoteGivenByTheMentorController.baseURLWithId)
                .buildAndExpand(studentDisciplineNoteGivenByTheMentor.getId())
                .toUri();
        return new ServiceCreateUtil<>(
                new StudentDisciplineNoteGivenByTheMentorDto(studentDisciplineNoteGivenByTheMentor), uri
        );
    }

//    public Optional<StudentDisciplineNoteGivenByTheMentorDto> find(Long id) {
//        Optional<Discipline> optionalDiscipline = studentDisciplineNoteGivenByTheMentorRepository.findById(id);
//        return optionalDiscipline.map(StudentDisciplineNoteGivenByTheMentorDto::new);
//    }
//
//    public Optional<StudentDisciplineNoteGivenByTheMentorDto> update(Long id, DisciplineForm studentDisciplineNoteGivenByTheMentorForm) {
//        Optional<Discipline> optionalDiscipline = studentDisciplineNoteGivenByTheMentorRepository.findById(id);
//        if (optionalDiscipline.isPresent()) {
//            Discipline discipline = optionalDiscipline.get();
//            discipline.setName(studentDisciplineNoteGivenByTheMentorForm.getName());
//            return Optional.of(new StudentDisciplineNoteGivenByTheMentorDto(discipline));
//        }
//        return Optional.empty();
//    }
//
//    public Boolean delete(Long id) {
//        Optional<Discipline> optionalDiscipline = studentDisciplineNoteGivenByTheMentorRepository.findById(id);
//        if (optionalDiscipline.isPresent()) {
//            studentDisciplineNoteGivenByTheMentorRepository.deleteById(id);
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }
}
