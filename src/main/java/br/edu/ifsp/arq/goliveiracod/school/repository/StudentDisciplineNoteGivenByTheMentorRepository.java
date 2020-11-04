package br.edu.ifsp.arq.goliveiracod.school.repository;

import br.edu.ifsp.arq.goliveiracod.school.model.StudentDisciplineNoteGivenByTheMentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDisciplineNoteGivenByTheMentorRepository
        extends JpaRepository<StudentDisciplineNoteGivenByTheMentor, Long> {

    @Query("SELECT " +
            "s.name, d.name, m.name, sdm.note, sdm.createdAt, sdm.lastChangeNote " +
            "FROM StudentDisciplineNoteGivenByTheMentor sdm " +
            "INNER JOIN Student s ON sdm.student.id = s.id " +
            "INNER JOIN Discipline  d ON sdm.discipline.id = d.id " +
            "INNER JOIN Mentor m ON s.mentor.id = m.id " +
            "WHERE s.name LIKE %:studentName% AND d.name LIKE %:disciplineName% AND m.name LIKE %:mentorName% ")
    Page<StudentDisciplineNoteGivenByTheMentor> findByMentorNameAndAndStudentNameAndDisciplineName(
            Pageable pageable
            , @Param("studentName") String studentName
            , @Param("disciplineName") String disciplineName
            , @Param("mentorName") String mentorName
    );
}
