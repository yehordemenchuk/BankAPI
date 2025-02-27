package org.bankapi.service;

import org.bankapi.models.User;
import org.bankapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
