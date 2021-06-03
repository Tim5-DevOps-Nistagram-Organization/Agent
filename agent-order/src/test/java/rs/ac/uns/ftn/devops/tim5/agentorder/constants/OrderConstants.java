package rs.ac.uns.ftn.devops.tim5.agentorder.constants;

import rs.ac.uns.ftn.devops.tim5.agentorder.dto.ItemRequestDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.OrderRequestDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.CustomerOrder;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Image;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Item;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Product;

import java.util.HashSet;
import java.util.Set;

public class OrderConstants {
    public static final String BASE_URL = "/order";
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
    static ItemRequestDTO ITEM1_DTO = new ItemRequestDTO(PRODUCT1_ID, 1);
    static ItemRequestDTO ITEM2_DTO = new ItemRequestDTO(PRODUCT2_ID, 1);
    static ItemRequestDTO ITEM3_DTO = new ItemRequestDTO(PRODUCT3_ID, 1);
    static Set<ItemRequestDTO> ITEMS_DTO = new HashSet<>() {{
        this.add(ITEM1_DTO);
        this.add(ITEM2_DTO);
        this.add(ITEM3_DTO);
    }};
    public static OrderRequestDTO ORDER_REQUEST_DTO_NEW = new OrderRequestDTO("Name",
            "Surname", "Address", ITEMS_DTO);
    static Item ITEM1 = new Item(1, PRODUCT1);
    static Item ITEM2 = new Item(1, PRODUCT2);
    static Item ITEM3 = new Item(1, PRODUCT3);
    static Set<Item> ITEMS = new HashSet<>() {{
        this.add(ITEM1);
        this.add(ITEM2);
        this.add(ITEM3);
    }};
    public static CustomerOrder ORDER_REQUEST_NEW = new CustomerOrder(null, "Name", "Surname",
            "Address", 30.0, ITEMS);

    public static CustomerOrder ORDER_REQUEST_NEW_CREATED = new CustomerOrder(1L, "Name",
            "Surname", "Address", 30.0, ITEMS);

    static ItemRequestDTO ITEM4_DTO = new ItemRequestDTO(PRODUCT1_ID, 11);
    public static OrderRequestDTO ORDER_REQUEST_DTO_NEW_NOT_AVAILABLE = new OrderRequestDTO("Name",
            "Surname", "Address", new HashSet<>() {{
        this.add(ITEM4_DTO);
    }});
}
