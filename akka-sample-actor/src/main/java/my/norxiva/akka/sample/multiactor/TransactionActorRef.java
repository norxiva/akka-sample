package my.norxiva.akka.sample.multiactor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionActorRef extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CreateTransaction.class, createTransaction -> log.info("creating transaction: {}", createTransaction))
                .build();
    }
}
