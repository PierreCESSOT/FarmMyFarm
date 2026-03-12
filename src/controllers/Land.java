package controllers;

import javafx.scene.control.Button;
import Plants.Plant;
import Plants.Mais;
import Plants.Patate;

public class Land {

    public Button land;
    public Plant plant;

    public Land() {
        this.land = new Button();
        land.setStyle("-fx-background-color: #8B5A2B; -fx-border-radius: 0; -fx-background-radius: 0; -fx-padding: 0;");
        land.setMinWidth(50);
        land.setMinHeight(50);
        land.setMaxWidth(50);
        land.setMaxHeight(50);
        addPlant();
    }

    public void addPlant() {
        land.setOnAction(e -> {

            // Si la parcelle est libre alors on plante
            if (this.plant == null || (!this.plant.isGrowing && !this.plant.collectAuthorized)) {

                if (LandFarm.selectedPlantType == null) {
                    LandFarm.statusLabel.setText("Sélectionnez une plante d'abord");
                    return;
                }

                if (!Stocks.instance.useSeed(LandFarm.selectedPlantType)) {
                    LandFarm.statusLabel.setText("Plus de graines de " + LandFarm.selectedPlantType + " ! Achetez-en au marché.");
                    return;
                }

                switch (LandFarm.selectedPlantType) {
                    case "Patate":
                        this.plant = new Patate();
                        break;
                    case "Maïs":
                        this.plant = new Mais();
                        break;
                    default:
                        LandFarm.statusLabel.setText("Type de plante inconnu");
                        return;
                }

                this.plant.growthDuration(this.land);
                int remaining = Stocks.seeds.getOrDefault(LandFarm.selectedPlantType, 0);
                LandFarm.statusLabel.setText(this.plant.name + " planté (" + remaining + " graines restantes)");

                if (LandFarm.instance != null) {
                    LandFarm.instance.generatePlantsList();
                }
            }

            // Cas 2 : La plante est prête à être récoltée
            else if (this.plant.collectAuthorized) {
                Stocks.instance.add(this.plant.name, 1);

                land.setGraphic(null);
                land.setText("");

                this.plant.isGrowing = false;
                this.plant.collectAuthorized = false;
                this.plant = null;

                if (LandFarm.instance != null) {
                    LandFarm.instance.generatePlantsList();
                }

                LandFarm.statusLabel.setText("Récolte effectuée ! Stock : " + Stocks.stocks);
            }

            // Cas 3 : La plante est en train de pousser
            else if (this.plant.isGrowing) {
                LandFarm.statusLabel.setText("La plante est en train de pousser...");
            }
        });
    }

    public Button getButton() {
        return land;
    }
}