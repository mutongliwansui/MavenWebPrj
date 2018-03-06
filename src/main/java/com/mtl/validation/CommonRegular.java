package com.mtl.validation;

import com.mtl.validation.impl.CommonRegularValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommonRegularValidator.class)
public @interface CommonRegular {
    String message() default "格式错误";
    /**
     * @return the regular expression to match
     */
    String regexp() default "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
