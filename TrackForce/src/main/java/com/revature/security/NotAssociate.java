package com.revature.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * 
 * @author Michael Tseng
 *
 *This interface is used to create a custom annotations for the NotAssociate filter
 *Any REST method with this annotation will be intercepted by the above filter
 *And checks if the user is an associate
 *
 *This annotation is meant to prevent associates from accessing sensitive data
 */
@NameBinding
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NotAssociate {

}
