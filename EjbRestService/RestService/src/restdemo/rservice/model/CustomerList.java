package restdemo.rservice.model;

import demoadf.restejb.model.Customer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerList {

    List<Customer> customerList = new ArrayList<Customer>();

    @XmlElement(name="allcustomers")
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    };
    
}
