package br.com.bruno.contacts;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.bruno.audit.AuditTracer;

@Stateless
@Interceptors(AuditTracer.class)
public class ContactRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public Contact add(Contact contact) {
		for (SocialMedia sm : contact.getMedias()) {
			sm.setContact(contact);
		}
		
		em.merge(contact);
		em.flush();
//		for (SocialMedia sm : contact.getMedias()) {
//			sm.setContact(contact);
//			em.persist(sm);
//		}
		
		return contact;
	}
	
	public List<Contact> findAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    CriteriaQuery<Contact> query = criteriaBuilder.createQuery(Contact.class);
	    Root<Contact> contact = query.from(Contact.class);
	    contact.fetch("medias", JoinType.LEFT);
	    query.select(contact);
	    query.distinct(true);
		return em.createQuery(query).getResultList();
	}
	
	public Contact findById(long id) {
		return em.find(Contact.class, id);
	}

	public Contact update(Contact contact) {
		return em.merge(contact);
		
//		System.out.println("should call em.merge here");
//		return contact;
	}

	public void remove(Long id) {
		remove(findById(id));
	}

	public void remove(Contact contact) {
		em.remove(em.merge(contact));
	}
}
