package my.norxiva.akka.sample.multiactor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExampleApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        final ActorSystem system = ActorSystem.create("multiActor");

        final ActorRef orderActorRef = system.actorOf(Props.create(OrderActorRef.class, OrderActorRef::new), "order");

        final ActorRef transactionActorRef = system.actorOf(Props.create(TransactionActorRef.class,
                TransactionActorRef::new), "transaction");
        String no = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

        orderActorRef.tell(new CreateOrder(no, new BigDecimal(101)), ActorRef.noSender());

        Thread.sleep(2000L);
        system.terminate();

    }

}
