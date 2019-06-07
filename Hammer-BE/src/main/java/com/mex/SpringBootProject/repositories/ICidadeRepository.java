package com.mex.SpringBootProject.repositories;

import com.mex.SpringBootProject.entities.Cidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface ICidadeRepository extends CrudRepository<Cidade, Long> {

    public Cidade getCidadeByDescricaoAndEstado(String descricao, String estado);
}
