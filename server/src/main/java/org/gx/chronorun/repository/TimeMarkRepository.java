package org.gx.chronorun.repository;

import org.gx.chronorun.model.TimeMark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "timemark",   path = "mark")
@CrossOrigin
public interface TimeMarkRepository extends MongoRepository<TimeMark, String> {
}
