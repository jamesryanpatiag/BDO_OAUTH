package cmi.bdo.oauth.validation.annotation;

import cmi.bdo.oauth.validation.validator.AuthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:16 PM
 */

@Documented
@Constraint(validatedBy = {AuthValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAuthParams {

    String message() default "{cmi.bdo.oauth.validation.validator.AuthValidator}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
