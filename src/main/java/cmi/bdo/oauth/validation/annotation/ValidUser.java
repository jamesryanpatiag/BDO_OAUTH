package cmi.bdo.oauth.validation.annotation;

import cmi.bdo.oauth.validation.validator.UserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 12:06 PM
 */

@Documented
@Constraint(validatedBy = {UserValidator.class})
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUser {

    String message() default "{cmi.bdo.oauth.validation.validator.UserValidator}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
