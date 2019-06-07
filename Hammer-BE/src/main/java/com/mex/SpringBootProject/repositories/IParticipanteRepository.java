package com.mex.SpringBootProject.repositories;

import com.mex.SpringBootProject.entities.Participante;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface IParticipanteRepository extends CrudRepository<Participante, Long> {

    @Query(value = "select p from Participante p where p.dataNascimento between ?1 and ?2")
    public List<Participante> getPartipantesByDate(Date from, Date to);
}
