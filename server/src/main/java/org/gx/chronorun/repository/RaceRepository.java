package org.gx.chronorun.repository;

import org.gx.chronorun.model.Race;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "race",   path = "race")
@CrossOrigin
public interface RaceRepository extends MongoRepository<Race, String> {
}
