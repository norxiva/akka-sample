package my.norxiva.akka.sample.multiactor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderActorRef extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CreateOrder.class, createOrder -> {
                    log.info("create order: {}", createOrder);
                    getContext().actorFor("akka://" + getContext().getSystem().name() + "/user/transaction")
                            .tell(new CreateTransaction(createOrder.getNo(), createOrder.getAmount()),
                                    ActorRef.noSender());
                })
                .build();
    }
}
