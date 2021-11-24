package com.adkan.adkan.phases;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends CrudRepository<Phase, Integer> {
}
