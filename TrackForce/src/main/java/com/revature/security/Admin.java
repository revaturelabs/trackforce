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
 *This interface is used to create a custom annotations for the admin filter
 *Any REST method with this annotation will be intercepted by the AdminFilter
 *And checks if the user is an admin
 */
@NameBinding
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Admin {

}
