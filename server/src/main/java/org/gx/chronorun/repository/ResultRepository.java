package org.gx.chronorun.repository;

import org.gx.chronorun.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends MongoRepository<Result, String> {

    Optional<Result> findByContestantBib(Integer bib);
}
