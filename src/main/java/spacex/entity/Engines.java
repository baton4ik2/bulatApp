package spacex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engines {

    private int number;
    private String type;
    private String version;
    private String layout;

    private Isp isp;

    private int engineLossMax;
    private Thrust thrustSeaLevel;
    private Thrust thrustVacuum;
    private double thrustToWeight;
}
