package org.bankapi.controllers;

import org.bankapi.models.User;
import org.bankapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController<User> {
    @Autowired
    public UserController(UserService service) {
        super(service);
    }
}
