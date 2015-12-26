package restdemo.rservice.model;

import demoadf.restejb.model.Carrier;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarrierList {

    List<Carrier> carrierList = new ArrayList<Carrier>();

    @XmlElement(name="allcarriers")
    public List<Carrier> getCarrierList() {
        return carrierList;
    }

    public void setCarrierList(List<Carrier> carrierList) {
        this.carrierList = carrierList;
    }
    
}

