package com.masivian.casino.repositories;

import com.masivian.casino.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
