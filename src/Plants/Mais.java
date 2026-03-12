package Plants;

import java.util.ArrayList;
import java.util.Arrays;

public class Mais extends Plant {
    public Mais() {
        this.name = "Maïs";
        this.buyMoney = 100;
        this.sellMoney = 300;
        this.durations = new ArrayList<>(Arrays.asList(2, 4));
        this.imageList = new ArrayList<>(Arrays.asList(
                "/img/mais/mais1.png",
                "/img/mais/mais2.png",
                "/img/mais/mais3.png"
        ));
    }
}