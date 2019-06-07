package com.mex.SpringBootProject.repositories;

import com.mex.SpringBootProject.entities.ApplicationInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface IAppInfoRepository extends CrudRepository<ApplicationInfo, Long> {
}
