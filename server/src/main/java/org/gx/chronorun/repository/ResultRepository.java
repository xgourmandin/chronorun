package org.gx.chronorun.repository;

import org.gx.chronorun.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "result",   path = "raceresult")
@CrossOrigin
public interface ResultRepository extends MongoRepository<Result, String> {

    Optional<Result> findByContestantBib(Integer bib);

    List<Result> findAll();
}
