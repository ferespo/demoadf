package restdemo.rservice;

import demoadf.restejb.SessionBean;
import demoadf.restejb.model.Carrier;

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
import restdemo.rservice.model.CarrierList;


//the annotated @EJB lookup appears not working without this
//singleton annotation

@Singleton
@Path("adfdemo/carrier")
public class CarrierService {

    //Lookup EJB session bean
    @EJB
    SessionBean sessionBean;

    public CarrierService() {
        super();
    }



    /**
     * Queries all carriers accessible from the EJB facade
     * @return ListOfCarriers class that wraps a List of Carriers objects
     */
    @GET
    @Produces("application/xml")
    @Path("/")
    public CarrierList getAll() {
        CarrierList listOfCarriers = new CarrierList();
        listOfCarriers.setCarrierList(sessionBean.getCarrierFindAll());
        return listOfCarriers;
    }

    /**
     * Method to call to obtain the detail data (Carrier) for a selected department
     * (departmentId). This eliminates the need for nested objects to be send to the
     * client to display master - detail behaviors
     *
     * @param carrierCode
     * @return ListOfCarrier class that wraps a List of Carriers objects
     */
    @GET
    @Produces("application/xml")
    @Path("/{carrierCode}")
    public Carrier getByCarrierCode(@PathParam("carrierCode") long carrierCode) {
        
        Carrier carrier = sessionBean.findCarrierById(carrierCode);
        return carrier;
    }

    /**
     * Updates an existing Carrier row identified by the Carrier ID
     * @param Carrier
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Path("/")
    public Response update(Carrier carrier) {        
        //find the Carrier record
        Carrier rec = sessionBean.findCarrierById(carrier.getCarrierCode());        
        if (carrier != null) {
           sessionBean.mergeCarrier(carrier);           
           }
           else{
           
             throw new ResourceNotFoundException("The Carrier resource with the id "+ carrier.getCarrierCode()+ " could not be found");
           }
        return Response.ok().build();
    }


    /**
     * Inserts a new Carrier 
     * @param Carrier
     * @return
     */
    @PUT
    @Consumes("application/xml")
    @Path("/")
    public Response insert(Carrier carrier) {        
        //find the Carrier record
        Carrier rec = sessionBean.findCarrierById(carrier.getCarrierCode());        
        if (rec == null) {
           sessionBean.persistCarrier(carrier);           
        } else {
             throw new ResourceAlreadyExistsException("The Carrier resource with the id "+ carrier.getCarrierCode()+ " already exists");
        }
        return Response.ok().build();
    }


    @DELETE
    @Path("/{CarrierCode}")
    public Response removeByCode(@PathParam("carrierCode") long carrierCode) {
        Carrier rec = sessionBean.findCarrierById(carrierCode);
        if (rec != null) {
            sessionBean.removeCarrier(rec);            
        } else {
            throw new ResourceNotFoundException("The Carrier resource with the id "+ carrierCode + "could not be found");
        }
        return Response.ok().build();
    }

}
