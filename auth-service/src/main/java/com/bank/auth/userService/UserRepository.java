package com.bank.auth.userService;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    com.openshift.demo.model.User save(User user);

    com.openshift.demo.model.User save(User user);

    com.openshift.demo.model.User save(User user);
}
