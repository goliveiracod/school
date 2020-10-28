package br.edu.ifsp.arq.goliveiracod.school.repository;

import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Page<Mentor> findByName(String name, Pageable pageable);
}
