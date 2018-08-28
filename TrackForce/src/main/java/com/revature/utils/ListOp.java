package com.revature.utils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;

/** <p>Used by the Hibernate utility class</p>
 * @version v6.18.06.13 */
public interface ListOp<T> {
	List<T> operate (Session session, Object ... args) throws HibernateException;
}