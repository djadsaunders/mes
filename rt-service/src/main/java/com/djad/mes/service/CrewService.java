package com.djad.mes.service;

import com.djad.mes.domain.crew.Crew;
import com.djad.mes.repository.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CrewService {

    CrewRepository crewRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Transactional
    public Optional<Crew> getCrew(String name) {
        return crewRepository.findByName(name);
    }

    @Transactional
    public List<Crew> getCrews() {
        return (List<Crew>)crewRepository.findAll();
    }

    @Transactional
    public Crew createCrew(String name) {
        Crew crew = new Crew(name);
        return crewRepository.save(crew);
    }
}
