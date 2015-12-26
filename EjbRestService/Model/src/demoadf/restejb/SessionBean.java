package demoadf.restejb;

import java.util.List;

import javax.ejb.Local;

import demoadf.restejb.model.Carrier;
import demoadf.restejb.model.Customer;
import demoadf.restejb.model.Product;
import demoadf.restejb.model.SalesOrder;

@Local
public interface SessionBean {
    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);



    Carrier persistCarrier(Carrier carrier);

    Carrier mergeCarrier(Carrier carrier);

    void removeCarrier(Carrier carrier);

    List<Carrier> getCarrierFindAll();
    
    public Carrier findCarrierById(Long carrierCode);

    /* -------------------------------------------------------------------------- */


    Product persistProduct(Product product);

    Product mergeProduct(Product product);

    void removeProduct(Product product);

    List<Product> getProductFindAll();
    
    public Product findProductById(String productCode);    
    
    /* -------------------------------------------------------------------------- */


    Customer persistCustomer(Customer customer);

    Customer mergeCustomer(Customer customer);

    void removeCustomer(Customer customer);

    List<Customer> getCustomerFindAll();
    
    public Customer findCustomerById(Long customerCode);    
    
    /* -------------------------------------------------------------------------- */


    SalesOrder persistSalesOrder(SalesOrder salesOrder);

    SalesOrder mergeSalesOrder(SalesOrder salesOrder);

    void removeSalesOrder(SalesOrder salesOrder);

    List<SalesOrder> getSalesOrderFindAll();
    
    public SalesOrder findSalesOrderById(Long salesOrderCode);    
    
    
}
