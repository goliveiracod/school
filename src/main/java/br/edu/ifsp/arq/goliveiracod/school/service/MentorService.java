package br.edu.ifsp.arq.goliveiracod.school.service;

import br.edu.ifsp.arq.goliveiracod.school.controller.MentorController;
import br.edu.ifsp.arq.goliveiracod.school.controller.dto.MentorDto;
import br.edu.ifsp.arq.goliveiracod.school.controller.form.MentorForm;
import br.edu.ifsp.arq.goliveiracod.school.exception.ProgramNotFoundException;
import br.edu.ifsp.arq.goliveiracod.school.model.Mentor;
import br.edu.ifsp.arq.goliveiracod.school.model.Program;
import br.edu.ifsp.arq.goliveiracod.school.repository.MentorRepository;
import br.edu.ifsp.arq.goliveiracod.school.repository.ProgramRepository;
import br.edu.ifsp.arq.goliveiracod.school.service.util.ServiceCreateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;
    private final ProgramRepository programRepository;

    public MentorService(
            MentorRepository mentorRepository,
            ProgramRepository programRepository
    ) {
        this.mentorRepository = mentorRepository;
        this.programRepository = programRepository;
    }

    public Page<MentorDto> paginateAllWithFilter(Pageable pageable, String name) {
        Page<Mentor> mentors;
        if (name == null)
            mentors = mentorRepository.findAll(pageable);
        else
            mentors = mentorRepository.findByName(name, pageable);

        return MentorDto.converter(mentors);
    }

    public ServiceCreateUtil<MentorDto> create(MentorForm mentorForm, UriComponentsBuilder uriComponentsBuilder) throws ProgramNotFoundException {
        Mentor mentor = mentorForm.converter();
        Optional<Program> optionalProgram = programRepository.findById(mentor.getProgram().getId());

        if (optionalProgram.isPresent())
            mentor = mentorRepository.save(mentor);
        else
            throw new ProgramNotFoundException("Program not found");

        URI uri = uriComponentsBuilder.path(MentorController.baseURLWithId).buildAndExpand(mentor.getId()).toUri();
        return new ServiceCreateUtil<>(new MentorDto(mentor), uri);
    }

    public Optional<MentorDto> find(Long id) {
        Optional<Mentor> optionalProgram = mentorRepository.findById(id);
        return optionalProgram.map(MentorDto::new);
    }

    public Optional<MentorDto> update(Long id, MentorForm mentorForm) {
        Optional<Mentor> optionalMentor = mentorRepository.findById(id);
        if (optionalMentor.isPresent()) {
            Mentor mentor = optionalMentor.get();
            Optional<Program> optionalProgram = programRepository.findById(mentorForm.getProgram().getId());
            if (optionalProgram.isEmpty()) {
                throw new ProgramNotFoundException("Program not found");
            }
            mentor.setName(mentorForm.getName());
            mentor.setCountry(mentorForm.getCountry());
            mentor.setProgram(mentorForm.getProgram());
            return Optional.of(new MentorDto(mentor));
        }
        return Optional.empty();
    }

    public Boolean delete(Long id) {
        Optional<Mentor> optionalMentor = mentorRepository.findById(id);
        if (optionalMentor.isPresent()) {
            mentorRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
