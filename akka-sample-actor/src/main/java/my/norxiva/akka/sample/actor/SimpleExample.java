package my.norxiva.akka.sample.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleExample {

    public static BigDecimal HUNDRED = new BigDecimal(100);

    public static class OrderExecutor extends AbstractActor {

        private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(Order.class, order -> {
                        log.info("receive order: {}", order);
                        getContext().actorOf(Props.create(TransactionExecutor.class, TransactionExecutor::new), "transaction")
                                .tell(new Transaction(order.getNo(), order.getAmount()), ActorRef.noSender());
                    })
                    .build();
        }
    }

    public static class Order implements Serializable{
        private String no;
        private BigDecimal amount;

        public Order(String no, BigDecimal amount) {
            this.no = no;
            this.amount = amount;
        }

        public String getNo() {
            return no;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "no='" + no + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }

    public static class TransactionExecutor extends AbstractActor {

        private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchAny(transaction -> log.info("receive transaction: {}", transaction))
                    .build();
        }
    }

    public static class Transaction implements Serializable {
        private String no;
        private BigDecimal amount;

        public Transaction(String no, BigDecimal amount) {
            this.no = no;
            this.amount = amount;
        }

        public String getNo() {
            return no;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "no='" + no + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }


    public static void main(String[] args){
        final ActorSystem system = ActorSystem.create("simpleExample");

        final ActorRef orderRef = system.actorOf(Props.create(OrderExecutor.class, OrderExecutor::new), "order");

        String no = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        orderRef.tell(new Order(no, HUNDRED), ActorRef.noSender());

        system.terminate();

    }
}
