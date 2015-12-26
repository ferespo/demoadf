package restdemo.rservice;

import demoadf.restejb.SessionBean;
import demoadf.restejb.model.Customer;

import javax.ejb.EJB;

import javax.inject.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import restdemo.rservice.errors.ResourceAlreadyExistsException;
import restdemo.rservice.errors.ResourceNotFoundException;
import restdemo.rservice.model.CustomerList;

@Singleton
@Path("adfdemo/customer")
public class CustomerService {

    //Lookup EJB session bean
    @EJB
    SessionBean sessionBean;

    public CustomerService() {
        super();
    }



    /**
     * Queries all customers accessible from the EJB facade
     * @return ListOfCustomers class that wraps a List of Customers objects
     */
    @GET
    @Produces("application/xml")
    @Path("/")
    public CustomerList getAll() {
        CustomerList customers = new CustomerList();
        customers.setCustomerList(sessionBean.getCustomerFindAll());
        return customers;
    }

    /**
     * Method to call to obtain the detail data (Customer) for a selected department
     * (departmentId). This eliminates the need for nested objects to be send to the
     * client to display master - detail behaviors
     *
     * @param customerCode
     * @return ListOfCustomer class that wraps a List of Customers objects
     */
    @GET
    @Produces("application/xml")
    @Path("/{customerCode}")
    @SuppressWarnings("oracle.jdeveloper.webservice.rest.broken-resource-warning")
    public Customer getByCustomerCode(@PathParam("customerCode") long customerCode) {
        
        Customer customer = sessionBean.findCustomerById(customerCode);
        return customer;
    }

    /**
     * Updates an existing Customer row identified by the Customer ID
     * @param Customer
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Path("/")
    public Response update(Customer customer) {        
        //find the Customer record
        Customer rec = sessionBean.findCustomerById(customer.getCustomerCode());        
        if (rec != null) {
           sessionBean.mergeCustomer(customer);
        }else{
             throw new ResourceNotFoundException("The Customer resource with the id "+ customer.getCustomerCode()+ " could not be found");
        }
        return Response.ok().build();
    }


    /**
     * Inserts an existing Customer 
     * @param Customer
     * @return
     */
    @PUT
    @Consumes("application/xml")
    @Path("/")
    public Response insert(Customer customer) {        
        //find the Customer record
        Customer rec = sessionBean.findCustomerById(customer.getCustomerCode());        
        if (rec == null) {
           sessionBean.persistCustomer(customer);
        } else{
           throw new ResourceAlreadyExistsException("The Customer resource with the id "+ customer.getCustomerCode()+ " already exists");
        }
        return Response.ok().build();
    }


    @DELETE
    @Path("/{customerCode}")
    public Response removeByCode(@PathParam("customerCode") long customerCode) {
        Customer customer = sessionBean.findCustomerById(customerCode);
        if (customer != null) {
            sessionBean.removeCustomer(customer);            
        }
        else{
            throw new ResourceNotFoundException("The Customer resource with the id "+ customerCode + "could not be found");
        }
        return Response.ok().build();
    }

}