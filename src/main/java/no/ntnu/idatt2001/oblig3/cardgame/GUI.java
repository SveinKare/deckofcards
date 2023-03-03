package no.ntnu.idatt2001.oblig3.cardgame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import no.ntnu.idatt2001.oblig3.cardgame.cards.CardHand;
import no.ntnu.idatt2001.oblig3.cardgame.cards.DeckOfCards;
import no.ntnu.idatt2001.oblig3.cardgame.cards.PlayingCard;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

public class GUI extends Application {
  DeckOfCards deck = new DeckOfCards();
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primary) {
    primary.setTitle("Texas Fold'em");
    BorderPane main = new BorderPane();

    StackPane bottomPane = new StackPane();
    bottomPane.setPrefHeight(50);

    StackPane leftPane = new StackPane();
    leftPane.setPrefWidth(70);

    StackPane rightPane = new StackPane();
    rightPane.setPrefWidth(70);

    StackPane topPane = new StackPane();
    topPane.setPrefHeight(100);

    AnchorPane centerPane = new AnchorPane();
    HBox hand = displayHand(5);
    centerPane.getChildren().add(hand);
    AnchorPane.setBottomAnchor(hand, 10.0);
    AnchorPane.setLeftAnchor(hand, 70.0);
    AnchorPane.setRightAnchor(hand, 70.0);

    main.setCenter(centerPane);
    main.centerProperty().get().setStyle(
            "-fx-background-color:#13941C;" +
            "-fx-border-radius: 10px;" +
            "-fx-border-color: black;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-width: 10px;"
    );
    main.setBottom(bottomPane);
    main.setLeft(leftPane);
    main.setRight(rightPane);
    main.setTop(topPane);

    Scene scene = new Scene(main);

    primary.setScene(scene);
    primary.show();
  }

  /**
   * Creates a StackPane that visualises a card.
   *
   * @param suit (char)  Suit of the card.
   * @param number (int) The number of the card.
   * @return An image representing the card
   */
  public StackPane displayCard(char suit, int number) {
    char colour;
    if (suit == 'S' || suit == 'C') {
      colour = 'B';
    } else {
      colour = 'R';
    }
    Image cardBackground;
    try (FileInputStream file = new FileInputStream("src/main/resources/card.png")) {
      cardBackground = new Image(file);
    } catch (IOException e) {
      System.out.println("An error occurred: " + e.getMessage());
      return null;
    }

    Image typeImage;
    try (FileInputStream file = new FileInputStream(String.format("src/main/resources/%c%d.png", colour, number))) {
      typeImage = new Image(file);
    } catch (IOException e) {
      System.out.println("An error occurred: " + e.getMessage());
      return null;
    }

    Image suitImage;
    try (FileInputStream file = new FileInputStream(String.format("src/main/resources/%c.png", suit))) {
      suitImage = new Image(file);
    } catch (IOException e) {
      System.out.println("An error occurred: " + e.getMessage());
      return null;
    }

    ImageView card = new ImageView(cardBackground);
    card.setFitWidth(60);
    card.setFitHeight(60);

    ImageView type = new ImageView(typeImage);
    type.setFitWidth(50);
    type.setFitHeight(50);

    ImageView suitView = new ImageView(suitImage);
    suitView.setFitWidth(30);
    suitView.setFitHeight(30);

    StackPane pane = new StackPane();
    pane.getChildren().addAll(card, type, suitView);
    pane.getChildren().get(1).setTranslateY(-10);
    pane.getChildren().get(2).setTranslateY(12);
    return pane;
  }

  public HBox displayHand(int n) {
    CardHand hand = deck.dealHand(n);
    HBox displayHand = new HBox();
    for (PlayingCard card : hand.getHand()) {
      displayHand.getChildren().add(displayCard(card.getSuit(), card.getFace()));
    }
    displayHand.setAlignment(Pos.BOTTOM_CENTER);
    return displayHand;
  }
}
