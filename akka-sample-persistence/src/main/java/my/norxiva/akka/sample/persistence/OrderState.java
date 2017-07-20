package my.norxiva.akka.sample.persistence;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@ToString
public class OrderState {

    private final List<OrderCreated> orderCreatedList = new ArrayList<>();

    public void add(OrderCreated orderCreated) {
        log.info("order state add: {}", orderCreated);
        orderCreatedList.add(orderCreated);
    }

    public int size() {
        return orderCreatedList.size();
    }

}
