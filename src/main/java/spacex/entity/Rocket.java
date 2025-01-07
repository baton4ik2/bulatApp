package spacex.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rocket {

    private int id;
    private boolean active;
    private int stages;
    private String country;
    private String company;

    private RocketHeight rocketHeight;
    private RocketDiameter rocketDiameter;
    private RocketMass rocketMass;
    private List<PayloadWeights> payloadWeights;
    private FirstStage firstStage;
    private SecondStage secondStage;
    private Engines engines;
    private LandingLegs landingLegs;
    private List<String> flickrImages;

    private String wikipedia;
    private String description;
    private String rocketId;
    private String rocketName;
    private String rocketType;

}
