package com.neoAA.TinyBoard.validator;

import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (checkInputName(user.getUsername())){
            errors.rejectValue("username", "username.empty");
        }
        if (checkInputPassword(user.getPassword())){
            errors.rejectValue("password", "password.empty");
        }
        if (checkDupName(user.getUsername())){
            errors.rejectValue("username", "username.dup");
        }
    }

    private boolean checkInputName(String username){
        return (username.trim().length()<2||username.trim().length()>8);
    }
    private boolean checkInputPassword(String password){
        return (password.trim().length()<4 || password.trim().length()>11);
    }
    private boolean checkDupName(String username){
        User user = userRepository.findByUsername(username);
        return user!=null;
    }
}
