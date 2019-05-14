package pbkk.spring.tcdelivery.model;

import javax.persistence.*;
import java.io.Serializable;
@Embeddable
public class MenuDetailsKey implements Serializable {
	    @Column(name = "menu_id")
	    private long menuId;
	  
	    @Column(name = "order_id")
	    private long orderId;
}