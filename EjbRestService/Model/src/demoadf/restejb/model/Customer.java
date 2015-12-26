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
@NamedQueries({ @NamedQuery(name = "Customer.findAll", query = "select o from Customer o") })
@XmlRootElement
@XmlType(propOrder={"customerCode", "fullName", "phoneNumber", "email", "address"})
public class Customer implements Serializable {
    private static final long serialVersionUID = -7963769794376538852L;
    @Column(nullable = false, length = 100)
    private String address;
    @Id
    @Column(name = "CUSTOMER_CODE", nullable = false)
    private Long customerCode;
    @Column(length = 50)
    private String email;
    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;
    @Column(name = "PHONE_NUMBER", length = 30)
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String address, Long customerCode, String email, String fullName, String phoneNumber) {
        this.address = address;
        this.customerCode = customerCode;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("address=");
        buffer.append(getAddress());
        buffer.append(',');
        buffer.append("customerCode=");
        buffer.append(getCustomerCode());
        buffer.append(',');
        buffer.append("email=");
        buffer.append(getEmail());
        buffer.append(',');
        buffer.append("fullName=");
        buffer.append(getFullName());
        buffer.append(',');
        buffer.append("phoneNumber=");
        buffer.append(getPhoneNumber());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
