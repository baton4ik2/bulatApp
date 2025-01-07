package spacex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstStage {

    private boolean reusable;
    private int engines;

    private Thrust thrustSeaLevel;
    private Thrust thrustVacuum;
}
