package restdemo.rservice.model;

import demoadf.restejb.model.SalesOrder;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SalesOrderList {

    List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();

    @XmlElement(name="allsalesOrders")
    public List<SalesOrder> getSalesOrderList() {
        return salesOrderList;
    }

    public void setSalesOrderList(List<SalesOrder> salesOrderList) {
        this.salesOrderList = salesOrderList;
    }
    

}