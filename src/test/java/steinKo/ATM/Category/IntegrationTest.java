package steinKo.ATM.Category;

import org.junit.jupiter.api.Tag;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Tag("IntegrationTest")
public @interface IntegrationTest {

}
