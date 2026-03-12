package Plants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class Plant {
    public String name;
    public double buyMoney;
    public double sellMoney;
    public ArrayList<Integer> durations;
    public ArrayList<String> imageList;
    public boolean collectAuthorized = false;
    public boolean isGrowing = false;

    private ImageView makeImageView(String path) {
        Image img = new Image(getClass().getResourceAsStream(path));
        ImageView iv = new ImageView(img);
        iv.setFitWidth(50);
        iv.setFitHeight(50);
        iv.setPreserveRatio(false);
        return iv;
    }

    public void growthDuration(Button land) {
        if (!collectAuthorized && !isGrowing) {
            isGrowing = true;
            land.setText("");
            land.setGraphic(makeImageView(imageList.get(0)));

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(durations.get(0)),
                            e -> land.setGraphic(makeImageView(imageList.get(1)))),
                    new KeyFrame(Duration.seconds(durations.get(1)),
                            e -> land.setGraphic(makeImageView(imageList.get(2))))
            );

            timeline.setOnFinished(e -> {
                this.collectAuthorized = true;
                this.isGrowing = false;
            });

            timeline.play();
        }
    }
}