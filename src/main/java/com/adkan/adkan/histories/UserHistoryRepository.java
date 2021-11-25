package com.adkan.adkan.histories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserHistory, Integer> {
}
