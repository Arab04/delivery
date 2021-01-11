package com.startup.delivery.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.startup.delivery.modul.Order;
import com.startup.delivery.modul.UserData;

@Component
public class Connection {
	
//	private EntityManagerFactory em = Persistence.createEntityManagerFactory("com.startup.delivery.modul");
//	
//	public EntityManager getEntityManager() {
//		return em.createEntityManager();
//	}
	
//	public void saveData(UserData in) {
//		EntityManager ma = getEntityManager();
//		 ma.getTransaction().begin();
//		 ma.persist(in);
//		 ma.getTransaction().commit();
//		 ma.close();
//	} 
}
