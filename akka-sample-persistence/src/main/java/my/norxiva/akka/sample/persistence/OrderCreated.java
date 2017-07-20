package my.norxiva.akka.sample.persistence;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@ToString
public class OrderCreated implements Serializable{
    private static final long serialVersionUID = -5886442272359419161L;

    private String no;
    private BigDecimal amount;
    private Integer status;
}
