package br.edu.ifsp.arq.goliveiracod.school.repository;

import br.edu.ifsp.arq.goliveiracod.school.model.StudentDisciplineGivenByTheMentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDisciplineGivenByTheMentorRepository
        extends JpaRepository<StudentDisciplineGivenByTheMentor, Long> {

    Page<StudentDisciplineGivenByTheMentor> findByMentorName(String name, Pageable pageable);

    Page<StudentDisciplineGivenByTheMentor> findByStudentName(String name, Pageable pageable);

    Page<StudentDisciplineGivenByTheMentor> findByDisciplineName(String name, Pageable pageable);
}
