package com.jiurong.hcx.common.util;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;

/**
 * @author hcx
 * @date 2020/3/9
 * explain: 手动校验工具
 */
public class ValidUtil {
    public static String valid(Object object) {
        Validator validator = new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());
        BindingResult bindingResult = new BindException(object, "object");
        validator.validate(object, bindingResult);
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        return null;
    }
}
