package rs.ac.uns.ftn.devops.tim5.agentreport.constants;

import rs.ac.uns.ftn.devops.tim5.agentreport.model.Item;
import rs.ac.uns.ftn.devops.tim5.agentreport.model.Product;

public class ReportConstants {
    public static final String BASE_URL = "/report";
    static Long PRODUCT_ID = 1L;
    public static Product PRODUCT = new Product(PRODUCT_ID, "Product1", 10.0, 10, false, null);
    public static Integer PRODUCT_SOLD = 5;
    public static Double PRODUCT_EARNED = 50.0;
}
