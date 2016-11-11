package com.yusuf.spring.controller;

import com.yusuf.spring.pojo.Books;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class BookValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Books.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Books books = (Books) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookName", "error.invalid.bookName", "Name of the book Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "error.invalid.author", "Author Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "edition", "error.invalid.edition", "Edition Required (Type 0 if no version)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.description", "Description Required (Type NA if no description)");
       

    }
}
