package restdemo.rservice;

import demoadf.restejb.SessionBean;
import demoadf.restejb.model.Product;

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
import restdemo.rservice.model.ProductList;


//the annotated @EJB lookup appears not working without this
//singleton annotation

@Singleton
@Path("adfdemo/product")
public class ProductService {

    //Lookup EJB session bean
    @EJB
    SessionBean oraMagSessionBean;

    public ProductService() {
        super();
    }



    /**
     * Queries all products accessible from the EJB facade
     * @return ListOfProducts class that wraps a List of Products objects
     */
    @GET
    @Produces("application/xml")
    @Path("/")
    public ProductList getAll() {
        ProductList products = new ProductList();
        products.setProductList(oraMagSessionBean.getProductFindAll());
        return products;
    }

    /**
     * Method to call to obtain the detail data (Product) for a selected department
     * (departmentId). This eliminates the need for nested objects to be send to the
     * client to display master - detail behaviors
     *
     * @param productCode
     * @return ListOfProduct class that wraps a List of Products objects
     */
    @GET
    @Produces("application/xml")
    @Path("/{productCode}")
    public Product getByProductCode(@PathParam("productCode") String productCode) {
        
        Product product = oraMagSessionBean.findProductById(productCode);
        return product;
    }

    /**
     * Updates an existing Product row identified by the Product ID
     * @param Product
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Path("/")
    public Response update(Product product) {        
        //find the Product record
        Product rec = oraMagSessionBean.findProductById(product.getProductCode());        
        if (rec != null) {
           oraMagSessionBean.mergeProduct(product);           
        } else{
           throw new ResourceNotFoundException("The Product resource with the id "+ product.getProductCode()+ " could not be found");
        }
        return Response.ok().build();
    }


    /**
     * Inserts an existing Product 
     * @param Product
     * @return
     */
    @PUT
    @Consumes("application/xml")
    @Path("/")
    public Response insert(Product product) {        
        //find the Product record
        Product rec = oraMagSessionBean.findProductById(product.getProductCode());        
        if (rec == null) {
           oraMagSessionBean.persistProduct(product);
        } else{
           throw new ResourceAlreadyExistsException("The Product resource with the id "+ product.getProductCode()+ " already exists");
        }
        return Response.ok().build();
    }


    @DELETE
    @Path("/{ProductCode}")
    public Response removeByCode(@PathParam("productCode") String productCode) {
        Product product = oraMagSessionBean.findProductById(productCode);
        if (product != null) {
            oraMagSessionBean.removeProduct(product);            
        }
        else{
            throw new ResourceNotFoundException("The Product resource with the id "+ productCode + "could not be found");
        }
        return Response.ok().build();
    }

}
