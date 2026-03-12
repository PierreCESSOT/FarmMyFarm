package Plants;

import java.util.ArrayList;
import java.util.Arrays;

public class Patate extends Plant {
    public Patate() {
        this.name = "Patate";
        this.buyMoney = 50;
        this.sellMoney = 100;
        this.durations = new ArrayList<>(Arrays.asList(1, 2));
        this.imageList = new ArrayList<>(Arrays.asList(
                "/img/patate/patate1.png",
                "/img/patate/patate2.png",
                "/img/patate/patate3.png"
        ));
    }
}