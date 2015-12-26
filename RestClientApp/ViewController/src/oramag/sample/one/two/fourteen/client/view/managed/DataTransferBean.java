package oramag.sample.one.two.fourteen.client.view.managed;

import java.io.Serializable;

import java.util.HashMap;
import java.util.logging.Level;

import javax.el.ELContext;
import javax.el.ValueExpression;

import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import oracle.adf.model.bean.DCDataRow;
import oracle.adf.share.logging.ADFLogger;

/**
 * Sample for Oracle Magazine Mar/Apr 2014
 * -----------------------------------------
 *
 * This managed bean is configured in page flow scope and is used by the DeptEmp.jsf page to pass the selected
 * employee data to the edit page. The edit page is based on a method that has input arguments for each of the
 * employee attributes.
 *
 * The editEmployees.jsf page uses JSF 2 component system events to default the form values with the data read from
 * the employees table of the previous page.
 *
 * Note that this managed bean provides a generic component event handler
 * that can be used in cases where the source entity attributes match the name of he argument names in the method. If
 * this wasn't the case, an extra mapping would be needed.
 *
 * @author Frank Nimphius 2014
 */
public class DataTransferBean implements Serializable {

    //object that holds the employee row passed from the
    //DeptEmp view to the edit page
    Object rw = null;

    //track keeping of the selected department
    Object departmentId = null;

    public DataTransferBean() {
        super();
    }


    public void setDepartmentId(Object departmentId) {
        this.departmentId = departmentId;
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public void setRw(Object rw) {
        this.rw = rw;
    }

    public Object getRw() {
        return rw;
    }


    /**
     * Method that is called by an f:event tag (type=preRenderComponent) on each of the form fields
     * @param componentSystemEvent
     */
    public void beforeRenderBindingAttribute(ComponentSystemEvent componentSystemEvent) {
        //Determine the component instance that the component event is reaised for
        UIComponent component = componentSystemEvent.getComponent();
                    
        ADFLogger adfLogger = ADFLogger.createADFLogger("DataTransferBean.java");

        //ensure component event is correctly added to value holder input component and not a layout component
        //or similar that has no "value" property
        if (component instanceof ValueHolder) {
            //The value property expression string for ADF bound fields is #{bindings.attributeName.inputValue} .
            //We need this expression to dynamically determine the actual attribute name as this is the same name
            //as in the row. Note that in your applicaton you will have to check inthe PageDef file if the attribute
            //names in the calling page's PageDef file match the names of the row attribute sor if the have numeric
            //appendinx like "attributeName1" - In this case the string parsing needs to reflect this
            ValueExpression attrValExpr = component.getValueExpression("value");

            String expression = attrValExpr.getExpressionString();

            //Start removing the "#{bindings." prefix
            String attributeBindingName_p1 = expression.substring(expression.indexOf("bindings.") + 9);
            //Finally, remove the ".inputValue" appendix
            String attributeBindingName_p2 = attributeBindingName_p1.substring(0, attributeBindingName_p1.indexOf("."));


            //Next, get the current value of this field to determine whether or not the field should be populated with
            //the default value coming from the previous page.
            FacesContext fctx = FacesContext.getCurrentInstance();
            ELContext elctx = fctx.getELContext();
            //The next lines of code need explanation. The row object actually represents the REST service data
            //collection. The rw variable in this class that gets the selected row on the deptEmp page is of type
            //HashMap and contains attributes of the "allemployees" REST collection
            HashMap row = (HashMap) ((DCDataRow) rw).getDataProvider();
            attrValExpr.setValue(elctx, row.get(attributeBindingName_p2));
        }
        else{
            //component is not a value holder and should not have the f:event tag added
            adfLogger.log(Level.ALL, "OraMag Mar/Apr 2014 - Warning :: The component "+component.getId()+" is not a value " +
                "holder component and should not have the f:event tag added. Please correct this error in EditEmployees.jsf");
        }
    }
}
