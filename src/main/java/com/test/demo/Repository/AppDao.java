package com.test.demo.Repository;

import javax.persistence.EntityManagerFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.test.demo.Entities.Node;

@Repository
public class AppDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	public <T> Node getNodebyId(String id, Class<T> classname) {
		Session session = null;
		Node node = null;
		try {
			session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(classname);
			criteria.add(Restrictions.eq("name", id));
			node = (Node) criteria.uniqueResult();
			return node;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public Object saveOrUpdateObject(Object object) {
		Session session = null;
		try {
			session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace(System.out);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return object;
	}

}
