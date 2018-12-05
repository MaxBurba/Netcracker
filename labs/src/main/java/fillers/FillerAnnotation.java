package fillers;

import analyzer.Analyzer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Burba
 *
 * <p>
 *     Is used to mark methods so that {@link Analyzer#analyze()} could find them and use.
 * </p>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FillerAnnotation {

}
