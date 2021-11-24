package com.adkan.adkan.user_histories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserHistory, Integer> {
}
