package br.edu.ifsp.arq.goliveiracod.school.repository;

import br.edu.ifsp.arq.goliveiracod.school.model.Discipline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    Page<Discipline> findByName(String name, Pageable pageable);
}
