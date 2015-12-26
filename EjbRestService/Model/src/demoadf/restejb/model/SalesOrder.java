package demoadf.restejb.model;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Entity
@NamedQueries({ @NamedQuery(name = "SalesOrder.findAll", query = "select o from SalesOrder o") })
@Table(name = "SALES_ORDER")
@XmlRootElement
@XmlType(propOrder={"orderNumber", "orderDate", "customer", "carrier", "salesOrderDetails"})
public class SalesOrder implements Serializable {
    private static final long serialVersionUID = -3465021904129363920L;
    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE", nullable = false)
    private Date orderDate;
    @Id
    @Column(name = "ORDER_NUMBER", nullable = false)
    private Long orderNumber;
    @OneToMany(mappedBy = "salesOrder", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<SalesOrderDetail> salesOrderDetails;
    @ManyToOne
    @JoinColumn(name = "CARRIER_CODE")
    private Carrier carrier;
    
    
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_CODE")
    private Customer customer;

    public SalesOrder() {
    }

    public SalesOrder(Carrier carrier, Customer customer, Date orderDate, Long orderNumber) {
        this.carrier = carrier;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    //@XmlTransient
    public List<SalesOrderDetail> getSalesOrderDetails() {
        return salesOrderDetails;
    }

    public void setSalesOrderDetails(List<SalesOrderDetail> salesOrderDetailList1) {
        this.salesOrderDetails = salesOrderDetailList1;
    }

    public SalesOrderDetail addSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
        getSalesOrderDetails().add(salesOrderDetail);
        salesOrderDetail.setSalesOrder(this);
        return salesOrderDetail;
    }

    public SalesOrderDetail removeSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
        getSalesOrderDetails().remove(salesOrderDetail);
        salesOrderDetail.setSalesOrder(null);
        return salesOrderDetail;
    }

    //@XmlTransient
    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    //@XmlTransient
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("orderDate=");
        buffer.append(getOrderDate());
        buffer.append(',');
        buffer.append("orderNumber=");
        buffer.append(getOrderNumber());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
