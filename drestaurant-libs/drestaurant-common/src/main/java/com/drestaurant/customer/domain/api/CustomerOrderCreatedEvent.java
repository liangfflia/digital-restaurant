package com.drestaurant.customer.domain.api;

import com.drestaurant.common.domain.event.AuditableAbstractEvent;
import com.drestaurant.common.domain.model.AuditEntry;

public class CustomerOrderCreatedEvent extends AuditableAbstractEvent {

	private static final long serialVersionUID = 1L;


	public CustomerOrderCreatedEvent(String orderId, AuditEntry auditEntry) {
		super(orderId, auditEntry);
	
	}

}