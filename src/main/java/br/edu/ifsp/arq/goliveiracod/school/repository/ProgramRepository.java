package br.edu.ifsp.arq.goliveiracod.school.repository;

import br.edu.ifsp.arq.goliveiracod.school.model.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Page<Program> findByName(String name, Pageable pageable);
}
