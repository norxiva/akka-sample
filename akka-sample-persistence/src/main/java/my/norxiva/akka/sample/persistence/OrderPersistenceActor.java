package my.norxiva.akka.sample.persistence;

import akka.persistence.AbstractPersistentActor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderPersistenceActor extends AbstractPersistentActor {

    private static final String PERSISTENCE_ID = "order-persistence-id";

    private OrderState orderState = new OrderState();

    @Override
    public String persistenceId() {
        return PERSISTENCE_ID;
    }

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder()
                .match(OrderCreated.class, orderState::add)
                .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CreateOrder.class, createOrder -> {
                    log.info("creating order: {}", createOrder);

                    getContext().system().eventStream()
                            .publish(OrderCreated
                                    .builder()
                                    .no(createOrder.getNo())
                                    .amount(createOrder.getAmount())
                                    .status(1)
                                    .build()
                            );
                })
                .build();
    }
}
