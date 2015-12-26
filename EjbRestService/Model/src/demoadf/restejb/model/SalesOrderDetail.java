package demoadf.restejb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


@Entity
@NamedQueries({ @NamedQuery(name = "SalesOrderDetail.findAll", query = "select o from SalesOrderDetail o") })
@Table(name = "SALES_ORDER_DETAIL")
@XmlRootElement
@XmlType(propOrder={"orderDetailId", "salesOrder", "product", "quantity"})
public class SalesOrderDetail implements Serializable {
    private static final long serialVersionUID = 152955540024269951L;
    @Id
    @Column(name = "ORDER_DETAIL_ID", nullable = false)
    private Long orderDetailId;
    @Column(nullable = false)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_CODE")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "ORDER_NUMBER")
    @XmlElement
    @XmlInverseReference(mappedBy="salesOrderDetails")        
    private SalesOrder salesOrder;

    public SalesOrderDetail() {
    }

    public SalesOrderDetail(Long orderDetailId, SalesOrder salesOrder, Product product, Integer quantity) {
        this.orderDetailId = orderDetailId;
        this.salesOrder = salesOrder;
        this.product = product;
        this.quantity = quantity;
    }


    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //@XmlTransient
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    //@XmlTransient
    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("orderDetailId=");
        buffer.append(getOrderDetailId());
        buffer.append(',');
        buffer.append("quantity=");
        buffer.append(getQuantity());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
