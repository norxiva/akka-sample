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
public class CreateOrder implements Serializable {
    private static final long serialVersionUID = 8632026717675761447L;

    private String no;
    private BigDecimal amount;
}
