package com.adkan.adkan.dates;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdkanDateRepository extends CrudRepository<AdkanDate, Integer> {
}
