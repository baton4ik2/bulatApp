package spacex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeFairing {

    private RocketHeight rocketHeight;
    private RocketDiameter rocketDiameter;
}
