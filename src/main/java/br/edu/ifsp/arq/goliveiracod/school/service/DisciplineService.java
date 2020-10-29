package br.edu.ifsp.arq.goliveiracod.school.service;

import br.edu.ifsp.arq.goliveiracod.school.controller.DisciplineController;
import br.edu.ifsp.arq.goliveiracod.school.controller.dto.DisciplineDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.DisciplineForm;
import br.edu.ifsp.arq.goliveiracod.school.model.Discipline;
import br.edu.ifsp.arq.goliveiracod.school.repository.DisciplineRepository;
import br.edu.ifsp.arq.goliveiracod.school.service.util.ServiceCreateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public Page<DisciplineDto> paginateAllWithFilter(Pageable pageable, String name) {
        Page<Discipline> disciplines;
        if (name == null)
            disciplines = disciplineRepository.findAll(pageable);
        else
            disciplines = disciplineRepository.findByName(name, pageable);

        return DisciplineDto.converter(disciplines);
    }

    public ServiceCreateUtil<DisciplineDto> create(DisciplineForm disciplineForm, UriComponentsBuilder uriComponentsBuilder) {
        Discipline discipline = disciplineRepository.save(disciplineForm.converter());
        URI uri = uriComponentsBuilder.path(DisciplineController.baseURLWithId).buildAndExpand(discipline.getId()).toUri();
        return new ServiceCreateUtil<>(new DisciplineDto(discipline), uri);
    }

    public Optional<DisciplineDto> find(Long id) {
        Optional<Discipline> optionalDiscipline = disciplineRepository.findById(id);
        return optionalDiscipline.map(DisciplineDto::new);
    }

    public Optional<DisciplineDto> update(Long id, DisciplineForm disciplineForm) {
        Optional<Discipline> optionalDiscipline = disciplineRepository.findById(id);
        if (optionalDiscipline.isPresent()) {
            Discipline discipline = optionalDiscipline.get();
            discipline.setName(disciplineForm.getName());
            return Optional.of(new DisciplineDto(discipline));
        }
        return Optional.empty();
    }

    public Boolean delete(Long id) {
        Optional<Discipline> optionalDiscipline = disciplineRepository.findById(id);
        if (optionalDiscipline.isPresent()) {
            disciplineRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
