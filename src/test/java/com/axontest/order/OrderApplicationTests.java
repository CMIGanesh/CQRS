package com.axontest.order;

import com.axontest.order.commands.CreateOrderCommand;
import com.axontest.order.commands.ShipOrderCommand;
import com.axontest.order.domains.OrderAggregate;
import com.axontest.order.events.OrderConfirmedEvent;
import com.axontest.order.events.OrderCreatedEvent;
import com.axontest.order.events.OrderShippedEvent;
import com.axontest.order.exceptions.UnconfirmedOrderException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class OrderApplicationTests {
	private FixtureConfiguration<OrderAggregate> fixture = new AggregateTestFixture<>(OrderAggregate.class);


	@Test
	void testCreateOrder(){
		String orderId = UUID.randomUUID().toString();
		String productId = "Deluxe Chair";
		fixture.givenNoPriorActivity()
				.when(new CreateOrderCommand(orderId, productId))
				.expectEvents(new OrderCreatedEvent(orderId, productId));
	}

	@Test
	void testShipOrder(){
		String orderId = UUID.randomUUID().toString();
		String productId = "Deluxe Chair";
		fixture.given(new OrderCreatedEvent(orderId, productId))
				.when(new ShipOrderCommand(orderId))
				.expectException(UnconfirmedOrderException.class);
	}

	@Test
	void testOrderShipped(){
		String orderId = UUID.randomUUID().toString();
		String productId = "Deluxe Chair";
		fixture.given(new OrderCreatedEvent(orderId, productId), new OrderConfirmedEvent(orderId))
				.when(new ShipOrderCommand(orderId))
				.expectEvents(new OrderShippedEvent(orderId));
	}

}
