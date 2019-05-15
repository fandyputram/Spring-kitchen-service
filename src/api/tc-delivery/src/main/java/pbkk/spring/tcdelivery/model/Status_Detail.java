package pbkk.spring.tcdelivery.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

public class Status_Detail {
	@Entity
	@Table(name = "kitchen_status_detail")
	@EntityListeners(AuditingEntityListeners.class)
	
	public class Status_Detail implements Serializable {
		@ManyToOne
		@JoinColumn(nullable = false, name = "s_id")
		private Status status;
		
		@ManyToOne
		@JoinColumn(nullable = false, name = "o_id")
		private Order order;
	}
	
}
