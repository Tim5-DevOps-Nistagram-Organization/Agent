package rs.ac.uns.ftn.devops.tim5.agentproduct.constants;

import rs.ac.uns.ftn.devops.tim5.agentproduct.dto.ProductDTO;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Image;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Product;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

public class ProductConstants {

    public static String BASE_URL = "/product";
    public static Long PRODUCT_DELETE_ID_DOESNT_EXISTS = 10L;
    static Long PRODUCT1_ID = 1L;
    static Long PRODUCT2_ID = 2L;
    static Long PRODUCT3_ID = 3L;
    static String PRODUCT1_NAME = "Product 1";
    static String PRODUCT2_NAME = "Product 2";
    static String PRODUCT3_NAME = "Product 3";
    static Double PRODUCT_PRICE = 10.0;
    static Integer PRODUCT_ON_STOCK = 10;
    static Boolean PRODUCT_DELETED = false;
    static Image PRODUCT_IMAGE = null;
    public static Product PRODUCT1 = new Product(PRODUCT1_ID, PRODUCT1_NAME, PRODUCT_PRICE, PRODUCT_ON_STOCK, PRODUCT_DELETED,
            PRODUCT_IMAGE);
    public static Product PRODUCT2 = new Product(PRODUCT2_ID, PRODUCT2_NAME, PRODUCT_PRICE, PRODUCT_ON_STOCK, PRODUCT_DELETED,
            PRODUCT_IMAGE);
    public static Product PRODUCT3 = new Product(PRODUCT3_ID, PRODUCT3_NAME, PRODUCT_PRICE, PRODUCT_ON_STOCK, PRODUCT_DELETED,
            PRODUCT_IMAGE);
    public static Collection<Product> PRODUCTS = new ArrayList<>() {{
        this.add(PRODUCT1);
        this.add(PRODUCT2);
        this.add(PRODUCT3);
    }};
    static Long PRODUCT_DTO_IMAGE = 0L;
    static Long PRODUCT_NEW_ID = 4L;
    static String PRODUCT_NEW_NAME = "New product";
    public static Product PRODUCT_NEW = new Product(null, PRODUCT_NEW_NAME, PRODUCT_PRICE, PRODUCT_ON_STOCK,
            PRODUCT_DELETED, PRODUCT_IMAGE);
    public static ProductDTO PRODUCT_DTO_NEW = new ProductDTO(null, PRODUCT_NEW_NAME, PRODUCT_PRICE,
            PRODUCT_ON_STOCK, PRODUCT_DTO_IMAGE);
    public static Product PRODUCT_NEW_CREATED = new Product(PRODUCT_NEW_ID, PRODUCT_NEW_NAME, PRODUCT_PRICE, PRODUCT_ON_STOCK,
            PRODUCT_DELETED, PRODUCT_IMAGE);
    static Image PRODUCT_NEW_IMAGE = new Image(5L, null);
    public static Product PRODUCT_NEW_IMAGE_DONT_EXIST = new Product(null, PRODUCT_NEW_NAME, PRODUCT_PRICE, PRODUCT_ON_STOCK,
            PRODUCT_DELETED, PRODUCT_NEW_IMAGE);
    public static ProductDTO PRODUCT_DTO_NEW_IMAGE_DONT_EXIST = new ProductDTO(null, PRODUCT_NEW_NAME, PRODUCT_PRICE,
            PRODUCT_ON_STOCK, PRODUCT_NEW_IMAGE.getId());
    static Long PRODUCT_UPDATE_ID = 1L;
    static String PRODUCT_UPDATE_NAME = "Update product";
    public static Product PRODUCT_UPDATE = new Product(PRODUCT_UPDATE_ID, PRODUCT_UPDATE_NAME, PRODUCT_PRICE,
            PRODUCT_ON_STOCK, PRODUCT_DELETED, PRODUCT_IMAGE);
    public static ProductDTO PRODUCT_DTO_UPDATE = new ProductDTO(PRODUCT_UPDATE_ID, PRODUCT_UPDATE_NAME, PRODUCT_PRICE,
            PRODUCT_ON_STOCK, PRODUCT_DTO_IMAGE);
    static Double PRODUCT_UPDATE_PRICE = -0.1;
    public static ProductDTO PRODUCT_DTO_UPDATE_PRICE_MIN_IS_0 = new ProductDTO(PRODUCT_UPDATE_ID, PRODUCT_UPDATE_NAME,
            PRODUCT_UPDATE_PRICE, PRODUCT_ON_STOCK, PRODUCT_DTO_IMAGE);
    static Long PRODUCT_DELETE_ID = 5L;
    public static Product PRODUCT_DELETE = new Product(PRODUCT_DELETE_ID, PRODUCT_UPDATE_NAME, PRODUCT_PRICE,
            PRODUCT_ON_STOCK, PRODUCT_DELETED, PRODUCT_IMAGE);
}
