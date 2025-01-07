package spacex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecondStage {

    private boolean reusable;
    private int engines;

    private Thrust thrust;
    private Payloads payloads;
}
