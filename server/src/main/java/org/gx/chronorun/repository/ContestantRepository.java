package org.gx.chronorun.repository;

import org.gx.chronorun.model.Contestant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "contestant",   path = "contestant")
@CrossOrigin
public interface ContestantRepository extends MongoRepository<Contestant, String> {
    Optional<Contestant> findByBib(Integer bib);


}
