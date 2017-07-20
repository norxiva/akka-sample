package my.norxiva.akka.sample.persistence;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersistenceExample {

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("persistenceActor");

        ActorRef orderPersistenceActorRef = system.actorOf(
                Props.create(OrderPersistenceActor.class), "orderPersistenceActor");

        orderPersistenceActorRef.tell(CreateOrder.builder()
                .no(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                .amount(new BigDecimal(102L))
                .build(),
                ActorRef.noSender()
        );

        Thread.sleep(1000L);

        system.terminate();

    }

}
