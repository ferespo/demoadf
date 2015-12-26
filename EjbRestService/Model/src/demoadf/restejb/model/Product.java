package demoadf.restejb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "select o from Product o") })
@XmlRootElement
@XmlType(propOrder={"productCode", "description", "price", "quantity"})
public class Product implements Serializable {
    private static final long serialVersionUID = 6831824263914343411L;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false)
    private Integer price;
    @Id
    @Column(name = "PRODUCT_CODE", nullable = false)
    private String productCode;
    @Column(nullable = false)
    private Integer quantity;
    

    public Product() {
    }

    public Product(String description, Integer price, String productCode, Integer quantity) {
        this.description = description;
        this.price = price;
        this.productCode = productCode;
        this.quantity = quantity;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("description=");
        buffer.append(getDescription());
        buffer.append(',');
        buffer.append("price=");
        buffer.append(getPrice());
        buffer.append(',');
        buffer.append("productCode=");
        buffer.append(getProductCode());
        buffer.append(',');
        buffer.append("quantity=");
        buffer.append(getQuantity());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
