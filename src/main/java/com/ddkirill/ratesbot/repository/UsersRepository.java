package com.ddkirill.ratesbot.repository;

import com.ddkirill.ratesbot.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {

}
