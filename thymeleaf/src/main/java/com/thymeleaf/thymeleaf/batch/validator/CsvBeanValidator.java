package com.thymeleaf.thymeleaf.batch.validator;

import com.thymeleaf.thymeleaf.batch.bean.BatchPerson;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CsvBeanValidator<T> implements Validator<T>, InitializingBean {
    private javax.validation.Validator validator;
    @Override
    public void afterPropertiesSet() throws Exception {//使用JSR-303的validator来校验数据，初始化JSR-303的Validator
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }
    @Override
    public void validate(T value) throws ValidationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(value);//使用Validator的validate方法校验数据

        if(constraintViolations.size() > 0){
            StringBuilder message = new StringBuilder();
            for(ConstraintViolation<Object> objectConstraintViolation:constraintViolations){
                message.append(objectConstraintViolation.getMessage()+"\n");
                throw new javax.validation.ValidationException(message.toString());
            }
        }
    }
}
