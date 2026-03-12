package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Market {

    @FXML private Label lblPatateStock;
    @FXML private Label lblMaisStock;

    @FXML private Label lblPatateSeeds;
    @FXML private Label lblMaisSeeds;

    @FXML private Button btnSellPatate;
    @FXML private Button btnSellMais;

    @FXML private Button btnBuyPatate;
    @FXML private Button btnBuyMais;

    @FXML private Label lblMoney;
    @FXML private Label lblMarketStatus;

    public static int money = 1000;

    private static final int PRICE_SEED_PATATE = 50;
    private static final int PRICE_SEED_MAIS   = 100;

    public void initialize() {
        refresh();

        btnSellPatate.setOnAction(e -> {
            int qty = Stocks.stocks.getOrDefault("Patate", 0);
            if (qty > 0) {
                money += qty * 100;
                Stocks.stocks.put("Patate", 0);
                refresh();
                lblMarketStatus.setText("Vendu " + qty + " patate(s) pour " + (qty * 100) + " pièces !");
            } else {
                lblMarketStatus.setText("Aucune patate récoltée à vendre !");
            }
        });

        btnSellMais.setOnAction(e -> {
            int qty = Stocks.stocks.getOrDefault("Maïs", 0);
            if (qty > 0) {
                money += qty * 300;
                Stocks.stocks.put("Maïs", 0);
                refresh();
                lblMarketStatus.setText("Vendu " + qty + " maïs pour " + (qty * 300) + " pièces !");
            } else {
                lblMarketStatus.setText("Aucun maïs récolté à vendre !");
            }
        });

        btnBuyPatate.setOnAction(e -> {
            if (money >= PRICE_SEED_PATATE) {
                money -= PRICE_SEED_PATATE;
                Stocks.instance.addSeeds("Patate", 1);
                refresh();
                lblMarketStatus.setText("🥔 Graine de patate achetée pour " + PRICE_SEED_PATATE + " pièces !");
            } else {
                lblMarketStatus.setText("Pas assez d'argent ! (besoin : " + PRICE_SEED_PATATE + " pièces)");
            }
        });

        btnBuyMais.setOnAction(e -> {
            if (money >= PRICE_SEED_MAIS) {
                money -= PRICE_SEED_MAIS;
                Stocks.instance.addSeeds("Maïs", 1);
                refresh();
                lblMarketStatus.setText("🌽 Graine de maïs achetée pour " + PRICE_SEED_MAIS + " pièces !");
            } else {
                lblMarketStatus.setText("Pas assez d'argent ! (besoin : " + PRICE_SEED_MAIS + " pièces)");
            }
        });
    }

    private void refresh() {
        lblMoney.setText(money + " pièces");
        lblPatateStock.setText("Récolte : " + Stocks.stocks.getOrDefault("Patate", 0));
        lblMaisStock.setText("Récolte : " + Stocks.stocks.getOrDefault("Maïs", 0));
        lblPatateSeeds.setText("Graines : " + Stocks.seeds.getOrDefault("Patate", 0));
        lblMaisSeeds.setText("Graines : " + Stocks.seeds.getOrDefault("Maïs", 0));
    }

    @FXML
    public void goToFarm(ActionEvent event) throws IOException {
        Parent farmView = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(farmView));
        stage.setFullScreen(true);
        stage.show();
    }
}