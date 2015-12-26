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
@NamedQueries({ @NamedQuery(name = "Carrier.findAll", query = "select o from Carrier o") })
@XmlRootElement
@XmlType(propOrder={"carrierCode", "name"})
public class Carrier implements Serializable {
    private static final long serialVersionUID = 7364099969058356222L;
    @Id
    @Column(name = "CARRIER_CODE", nullable = false)
    private Long carrierCode;
    @Column(nullable = false, length = 100)
    private String name;

    public Carrier() {
    }

    public Carrier(Long carrierCode, String name) {
        this.carrierCode = carrierCode;
        this.name = name;
    }


    public Long getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(Long carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("carrierCode=");
        buffer.append(getCarrierCode());
        buffer.append(',');
        buffer.append("name=");
        buffer.append(getName());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
