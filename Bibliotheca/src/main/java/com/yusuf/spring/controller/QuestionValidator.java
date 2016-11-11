package com.yusuf.spring.controller;

import com.yusuf.spring.pojo.Question;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class QuestionValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Question.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Question question = (Question) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question", "error.invalid.question", "Please type a question");
       

    }
}
