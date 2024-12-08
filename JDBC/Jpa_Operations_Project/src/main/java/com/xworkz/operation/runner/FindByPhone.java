package com.xworkz.operation.runner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.xworkz.operation.entity.PersonEntity;

public class FindByPhone {
	public static void main(String[] args) {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("xworkz");
		EntityManager eManager = emFactory.createEntityManager();
		EntityTransaction eTrans = eManager.getTransaction();

		try {
			eTrans.begin();

			Query query = eManager.createNamedQuery("findByPhone");
			query.setParameter("phone", 9449918520L);

			PersonEntity person = (PersonEntity) query.getSingleResult();

			System.out.println("Person Details: " + person);

			eTrans.commit();

		} catch (NoResultException e) {
			System.out.println("No person found with the phone number: 9449918520L");
		} catch (Exception e) {
			if (eTrans.isActive()) {
				eTrans.rollback();
			}
			e.printStackTrace();
		} finally {
			eManager.close();
			emFactory.close();
		}
	}
}