package my.norxiva.akka.sample.multiactor;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOrder implements Serializable {
    private static final long serialVersionUID = 7955025166068577351L;

    private String no;
    private BigDecimal amount;



}
