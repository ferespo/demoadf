package demoadf.restejb;

import demoadf.restejb.model.Carrier;
import demoadf.restejb.model.Customer;
import demoadf.restejb.model.Product;
import demoadf.restejb.model.SalesOrder;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Rest sample source for JDeveloper 12c REST article in Oracle Magazine Mar/Apr 2014
 *
 * EJB session facade referenced from the POJO bean exposed as a REST Services. The EJB bean is
 * configured as a Singleton.
 *
 * @author Frank Nimphius 2014
 */

@Singleton (name = "SessionBeanImpl", mappedName = "SessionBeanImpl")
public class SessionBeanImpl implements SessionBean {
    @Resource
    SessionContext sessionContext;
    
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public SessionBeanImpl() {
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
    }

    public Carrier persistCarrier(Carrier carrier) {
        em.persist(carrier);
        return carrier;
    }

    public Carrier mergeCarrier(Carrier carrier) {
        return em.merge(carrier);
    }

    public void removeCarrier(Carrier carrier) {
        carrier = em.find(Carrier.class, carrier.getCarrierCode());
        em.remove(carrier);
    }
 
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Carrier> getCarrierFindAll() {
        return em.createNamedQuery("Carrier.findAll", Carrier.class).getResultList();
    }
    
    public Carrier findCarrierById(Long carrierCode){
        return  em.find(Carrier.class,carrierCode);
    }
    
    /* ***************************************************************************** */
    public Product persistProduct(Product product) {
        em.persist(product);
        return product;
    }

    public Product mergeProduct(Product product) {
        return em.merge(product);
    }

    public void removeProduct(Product product) {
        product = em.find(Product.class, product.getProductCode());
        em.remove(product);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Product> getProductFindAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }
    
    public Product findProductById(String productCode){
        return  em.find(Product.class,productCode);
    }
        
    /* ***************************************************************************** */        
    public Customer persistCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public Customer mergeCustomer(Customer customer) {
        return em.merge(customer);
    }

    public void removeCustomer(Customer customer) {
        customer = em.find(Customer.class, customer.getCustomerCode());
        em.remove(customer);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Customer> getCustomerFindAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }
    
    public Customer findCustomerById(Long customerCode){
        return  em.find(Customer.class,customerCode);
    }
    

    /* ***************************************************************************** */        
    public SalesOrder persistSalesOrder(SalesOrder salesOrder) {
        em.persist(salesOrder);
        return salesOrder;
    }

    public SalesOrder mergeSalesOrder(SalesOrder salesOrder) {
        return em.merge(salesOrder);
    }

    public void removeSalesOrder(SalesOrder salesOrder) {
        salesOrder = em.find(SalesOrder.class, salesOrder.getOrderNumber());
        em.remove(salesOrder);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SalesOrder> getSalesOrderFindAll() {
        return em.createNamedQuery("SalesOrder.findAll", SalesOrder.class).getResultList();
    }
    
    public SalesOrder findSalesOrderById(Long salesOrderCode){
        System.out.println("***** public SalesOrder findSalesOrderById(Long salesOrderCode){");
        
        return  em.find(SalesOrder.class,salesOrderCode);
    }

    
}
