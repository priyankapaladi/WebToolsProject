package com.yusuf.spring.controller;

import com.yusuf.spring.pojo.Category;
import com.yusuf.spring.pojo.LoginModel;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class RegistrationValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(LoginModel.class);
    }

    public void validate(Object obj, Errors errors)
    {
        LoginModel loginModel = (LoginModel) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "Username Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nuID", "error.invalid.nuID", "NUID Required");


    }
}
