package br.com.bruno.contacts;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bruno.audit.AuditTracer;

@Stateless
@Interceptors(AuditTracer.class)
public class ContactRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public Contact add(Contact contact) {
		em.persist(contact);
		
		return contact;
	}
	
	public List<Contact> findAll() {
		Query query = em.createQuery("SELECT c FROM Contact c");
		return (List<Contact>) query.getResultList();
	}
	
	public Contact findById(long id) {
		return em.find(Contact.class, id);
	}

	public Contact update(Contact contact) {
//		return em.merge(contact);
		
		System.out.println("should call em.merge here");
		
		return contact;
	}

	public void remove(Long id) {
		remove(findById(id));
	}

	public void remove(Contact contact) {
		em.remove(em.merge(contact));
	}
}
