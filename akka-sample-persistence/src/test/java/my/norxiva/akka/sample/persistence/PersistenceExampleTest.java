package my.norxiva.akka.sample.persistence;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class PersistenceExampleTest {

    private ActorSystem system;

    @Before
    public void setUp() {
        system = ActorSystem.create("testPersistenceExample");
    }

    @After
    public void teardown() {
        TestKit.shutdownActorSystem(system);
    }


    @Test
    public void testPersistence() {
        final TestKit testProbe = new TestKit(system);

        final ActorRef orderPersistenceActorRef = system.actorOf(
                Props.create(OrderPersistenceActor.class), "orderPersistenceActor");


        CreateOrder createOrder = CreateOrder.builder()
                .no(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                .amount(new BigDecimal(102L))
                .build();
        orderPersistenceActorRef.tell(createOrder, ActorRef.noSender());

        // need to fix.

//        CreateOrder expectCreateOrder = testProbe.expectMsgClass(CreateOrder.class);
//
//        assertEquals(createOrder.getNo(), expectCreateOrder.getNo());


    }
}
