package edu.sdccd.cisc191.template;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class UI {

    FridgeManager fm;
    Stage window;
    // 2 background images - 1 daytime 1 nightime
    public Pane[] bgPane = new Pane[2];

    public UI(FridgeManager fm) {
        this.fm = fm;
    }

    public void createStartScreen(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Huangsung Smart-Fridge");
        window.setWidth(700);
        window.setHeight(900);

        Pane root = new Pane();
        root.setStyle("-fx-background-color: black;");

        // Floating Text
        Text floatingText = new Text(80, 200, "Lehuang Smart Fridge");
        floatingText.setFont(Font.font("Times New Roman", 50));
        floatingText.setFill(Color.DARKRED);

        root.getChildren().addAll(floatingText);

        Scene titleScene = new Scene(root, 900, 700);
        window.setScene(titleScene);
        window.show();
    }
    public void createObject(int bgNum, int objx, int objy, int objWidth, int objHeight, String objFileName) {

        // For food items and trash can
        Image objectImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(objFileName)));
        ImageView objectImageView = new ImageView(objectImage);
        objectImageView.setFitWidth(objWidth);
        objectImageView.setFitHeight(objHeight);
        objectImageView.setLayoutX(objx);
        objectImageView.setLayoutY(objy);

        bgPane[bgNum].getChildren().add(objectImageView);
    }
}
