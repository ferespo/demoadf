package restdemo.rservice;


import demoadf.restejb.SessionBean;
import demoadf.restejb.model.SalesOrder;

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
import restdemo.rservice.model.SalesOrderList;
//import oramag.sample.one.two.fourteen.restejb.OraMagSessionBeanLocal;


//the annotated @EJB lookup appears not working without this
//singleton annotation

@Singleton
@Path("adfdemo/salesorder")
public class SalesOrderRestService {

    //Lookup EJB session bean
    @EJB
    SessionBean sessionBean;

    public SalesOrderRestService() {
        super();
    }



    /**
     * Queries all salesOrders accessible from the EJB facade
     * @return ListOfSalesOrders class that wraps a List of SalesOrders objects
     */
    @GET
    @Produces("application/xml")
    @Path("/")
    public SalesOrderList getAll() {
        SalesOrderList salesOrders = new SalesOrderList();
        salesOrders.setSalesOrderList(sessionBean.getSalesOrderFindAll());
        return salesOrders;
    }

    /**
     * Method to call to obtain the detail data (SalesOrder) for a selected department
     * (departmentId). This eliminates the need for nested objects to be send to the
     * client to display master - detail behaviors
     *
     * @param orderNumber
     * @return ListOfSalesOrder class that wraps a List of SalesOrders objects
     */
    @GET
    @Produces("application/xml")
    @Path("/{orderNumber}")
    public SalesOrder getByOrderNumber(@PathParam("orderNumber") long orderNumber) {
        System.out.println("SalesOrder getByOrderNumber(@PathParam(\"orderNumber\") long orderNumber) {");
        SalesOrder salesOrder = sessionBean.findSalesOrderById(orderNumber);
        return salesOrder;
    }

    /**
     * Updates an existing SalesOrder row identified by the SalesOrder ID
     * @param SalesOrder
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Path("/")
    public Response update(SalesOrder salesOrder) {        
        //find the SalesOrder record
        SalesOrder rec = sessionBean.findSalesOrderById(salesOrder.getOrderNumber());        
        if (rec != null) {
            sessionBean.mergeSalesOrder(salesOrder);
        }else{
             throw new ResourceNotFoundException("The SalesOrder resource with the id "+ salesOrder.getOrderNumber()+ " could not be found");
        }
        return Response.ok().build();
    }


    /**
     * Inserts an existing SalesOrder 
     * @param SalesOrder
     * @return
     */
    @PUT
    @Consumes("application/xml")
    @Path("/")
    public Response insert(SalesOrder salesOrder) {        
        //find the SalesOrder record
        SalesOrder rec = sessionBean.findSalesOrderById(salesOrder.getOrderNumber());        
        if (rec == null) {
           sessionBean.persistSalesOrder(salesOrder);
        }else{
             throw new ResourceAlreadyExistsException("The SalesOrder resource with the id "+ salesOrder.getOrderNumber()+ " already exists");
        }
        return Response.ok().build();
    }


    @DELETE
    @Path("/{orderNumber}")
    public Response removeByCode(@PathParam("orderNumber") long orderNumber) {
        SalesOrder salesOrder = sessionBean.findSalesOrderById(orderNumber);
        if (salesOrder != null) {
            sessionBean.removeSalesOrder(salesOrder);            
        }
        else{
            throw new ResourceNotFoundException("The SalesOrder resource with the id "+ orderNumber + "could not be found");
        }
        return Response.ok().build();
    }

}