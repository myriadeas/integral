package my.com.myriadeas.integral.core.listener;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public interface EventListener {

	public void listen(DomainEvent domainEvent);
}
