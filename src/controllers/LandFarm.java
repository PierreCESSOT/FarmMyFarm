package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LandFarm {

    @FXML
    public GridPane gridlands;

    @FXML
    public GridPane gridplantslist;

    @FXML
    private Label lblStatus;

    public static Label statusLabel;

    public static String selectedPlantType = null;

    public static LandFarm instance;

    public void initialize() {
        instance = this;
        statusLabel = lblStatus;
        generateLands();
        generatePlantsList();
    }

    @FXML
    public void goToMarket(ActionEvent event) throws IOException {
        Parent marketView = FXMLLoader.load(getClass().getResource("/fxml/market.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(marketView));
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    private void selectPatate() {
        selectedPlantType = "Patate";
        statusLabel.setText("🥔 Patate sélectionnée");
    }

    @FXML
    private void selectMais() {
        selectedPlantType = "Maïs";
        statusLabel.setText("🌽 Maïs sélectionné");
    }

    public void generateLands() {
        int rows = 10;
        int columns = 10;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Land land = new Land(); // BUG FIX : Land() ne prend plus de paramètre inutile

                gridlands.setHalignment(land.getButton(), javafx.geometry.HPos.CENTER);
                gridlands.setValignment(land.getButton(), javafx.geometry.VPos.CENTER);
                gridlands.setAlignment(Pos.CENTER);

                gridlands.add(land.getButton(), col, row);
            }
        }
    }

    // Génère la liste des plantes disponibles avec leur stock
    public void generatePlantsList() {
        gridplantslist.getChildren().clear();
        int count = 0;

        for (String plantName : Stocks.stocks.keySet()) {
            int qty = Stocks.stocks.get(plantName);
            Button btn = new Button(plantName + " (" + qty + ")");

            // Highlight visuel si c'est la plante actuellement sélectionnée
            if (plantName.equals(selectedPlantType)) {
                btn.setStyle("-fx-background-color: #a8e063; -fx-font-weight: bold;");
            }

            btn.setOnAction(e -> {
                selectedPlantType = plantName;
                statusLabel.setText("✅ " + plantName + " sélectionné(e)");
                generatePlantsList(); // Refresh pour mettre à jour le highlight
                System.out.println("Plante sélectionnée : " + selectedPlantType);
            });

            gridplantslist.add(btn, 0, count);
            count++;
        }
    }
}