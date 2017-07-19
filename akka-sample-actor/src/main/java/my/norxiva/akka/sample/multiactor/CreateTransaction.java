package my.norxiva.akka.sample.multiactor;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateTransaction implements Serializable {
    private static final long serialVersionUID = -6642497491817104143L;

    private String no;
    private BigDecimal amount;
}
