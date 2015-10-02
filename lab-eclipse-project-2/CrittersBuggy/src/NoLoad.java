/**
 * Annotation that signifies that the Critter program should not load
 * the annotated Critter class.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NoLoad { /* No members */ }
