package demoadf.restejb.adapter;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Rest sample source for JDeveloper 12c REST article in Oracle Magazine Mar/Apr 2014
 *
 * Custom XMLAdapter to support JAX-B conversion from String to date so the Employees
 * entity hiredate attribute can be updated with the correct type
 *
 * @author Frank Nimphius 2014
 */
public class DateStringToCalendar  extends XmlAdapter<String, Date> {
    
        public DateStringToCalendar() {
            super();
        }
          
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String marshal(Date date) throws Exception {
        if(date != null){            
            return dateFormat.format(date);
        }
        else{
            return null;
        }
    }

    @Override
    public Date unmarshal(String dateString) throws Exception {
        if (dateString != null){
            Date _date = dateFormat.parse(dateString);
            return _date;
        }
        else{
            return null;
        }        
        
    }
}
