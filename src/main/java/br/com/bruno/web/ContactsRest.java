package br.com.bruno.web;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.bruno.contacts.Contact;
import br.com.bruno.contacts.ContactRepository;

@Path("/contacts")
public class ContactsRest {

	@EJB
	private ContactRepository contactRepository;
	
    @Context
    private UriInfo uriInfo;
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Contact> findAllContacts() {
    	System.out.println("GET findAllContacts");
    	
    	List<Contact> contacts = contactRepository.findAll();
    	
//    	System.out.println("contacts = " + contacts);
    	
    	return contacts;
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Contact find(@PathParam("id") Long id) {
    	System.out.println("GET /" + id);
    	
    	Contact c = contactRepository.findById(id);
    	
    	System.out.println("contact = " + c);
    	
        return c;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Contact contact) {
        Contact c = contactRepository.add(contact);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(c.getId())).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, Contact contact) {
    	contact.setId(id);
    	contactRepository.update(contact);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Long id) {
    	contactRepository.remove(id);
        return Response.noContent().build();
    }
	
}
