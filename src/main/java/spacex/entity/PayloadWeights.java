package spacex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadWeights {

    private String id;
    private int name;
    private double kg;
    private double lb;

}
