package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.util.*;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client extends Application {
    public static User user = new User("Chase", 1000000);

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public Game sendRequest(int id) throws Exception {
        out.println(CustomerRequest.toJSON(new CustomerRequest(id)));
        return Game.fromJSON(in.readLine());
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    public Game accessServer(int id) {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 4444);
            System.out.println("Sending request");
            return client.sendRequest(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        launch();// Run this Application.
    }


    @Override
    public void start(Stage stage) throws Exception {
        Client client = new Client();
        ArrayList<Game> response = new ArrayList<Game>();
        response.add(accessServer(1));
        System.out.println(response.toString());
//
//        VBox labelView = new VBox(10);
//        HBox userInfo = new HBox(10);
//        VBox betList = new VBox(10);
//
//        try {
//
//            for (Game game : response) {
//                HBox gameBox = new HBox(10); // Encapsulating HBox for all of the labels and buttons about a game
//
//                HBox versusBox = new HBox(5); // Encapsulating all the labels (teams, date) about a game
//
//                // Label for the first team
//                Label team1 = new Label(game.getTeam1());
//                team1.setTextFill(Color.color(1, 0, 0));
//                team1.setStyle("-fx-font-weight: bold");
//
//                Label vs = new Label("vs. ");
//
//                // Label for the second team
//                Label team2 = new Label(game.getTeam2());
//                team2.setTextFill(Color.color(0, 0, 1));
//                team2.setStyle("-fx-font-weight: bold");
//
//                Locale loc = new Locale("en", "US");
//                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
//                String stringDate = dateFormat.format(game.getDate());
//
//                // Label for the date
//                Label date = new Label(stringDate);
//                date.setTextFill(Color.rgb(117, 117, 117));
//
//                // Place the team 1, team 2 and date labels into the label box
//                gameBox.getChildren().addAll(team1, vs, team2, date);
//
//                HBox betBox = new HBox(5); // Encapsulating HBox for Bet action buttons
//
//                // Button to bet on the first team
//                Button betTeam1 = new Button("Bet " + game.getTeam1());
//                betTeam1.setOnAction(evt -> {
//                    try {
//                        new BetView().betView(stage, game);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//
//                Label team1Odds = new Label(game.getTeam1Odd() + "%");
//
//                // Button to bet on the second team
//                Button betTeam2 = new Button("Bet " + game.getTeam2());
//                betTeam2.setOnAction(evt -> {
//                    try {
//                        new BetView().betView(stage, game);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//
//                Label team2Odds = new Label(game.getTeam2Odd() + "%");
//
//
//                betBox.getChildren().addAll(betTeam1, team1Odds, betTeam2, team2Odds); // Add the bet buttons to the encapsulating HBox
//
//                gameBox.getChildren().addAll(versusBox, betBox); // Add everything involving the game (labels, bet buttons) to a row
//
//                labelView.getChildren().add(gameBox); // Add new row to the list of upcoming games
//            }
//
//            userInfo.setBackground(Background.fill(Color.color(0, 1, 0)));
//            Label userName = new Label(user.getName());
//
//            Label money = new Label("" + user.getMoney());
//
//
//            for (Bet bet : user.getBets()) {
//                HBox betBox = new HBox(10);
//                Label game = new Label(bet.getGame().toString());
//                Label betAmt = new Label("Bet $" + bet.getBetAmt());
//                Label winAmt = new Label("Win $ " + bet.getWinAmt());
//                betBox.getChildren().addAll(game, betAmt, winAmt);
//                betList.getChildren().add(betBox);
//            }
//
//            userInfo.getChildren().addAll(userName, money);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        labelView.setAlignment(Pos.CENTER);
//
//        VBox everything = new VBox(10);
//        everything.getChildren().addAll(userInfo, labelView, betList);
//
//        Scene scene = new Scene(everything, 800, 800);
//        stage.setScene(scene);
//        stage.setTitle("Marauder Bets");
//        stage.show();
//

    }
} //end class Client

