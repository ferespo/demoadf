package restdemo.rservice.model;

import demoadf.restejb.model.Product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductList {

    List<Product> productList = new ArrayList<Product>();

    @XmlElement(name="allproducts")
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
}

