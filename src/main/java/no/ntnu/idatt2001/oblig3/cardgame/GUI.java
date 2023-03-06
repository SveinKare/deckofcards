package no.ntnu.idatt2001.oblig3.cardgame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatt2001.oblig3.cardgame.cards.CardHand;
import no.ntnu.idatt2001.oblig3.cardgame.cards.DeckOfCards;
import no.ntnu.idatt2001.oblig3.cardgame.cards.PlayingCard;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUI extends Application {
  private final DeckOfCards deck = new DeckOfCards();
  private CardHand hand = deck.dealHand(5);
  private HBox displayHand = new HBox();
  private final AnchorPane centerPane = new AnchorPane();
  private final BorderPane main = new BorderPane();
  private final TextField sumOfCardsField = new TextField();
  private final TextField cardsOfHeartsField = new TextField();
  private final TextField queenOfSpadesInHandField = new TextField();
  private final TextField handIsFlushField = new TextField();

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primary) {
    primary.setTitle("Texas Fold'em");

    String buttonStyle = "-fx-font-size: 16;" +
                         "-fx-font-family: 'Verdana Pro';" +
                         "-fx-background-color: #70160B; " +
                         "-fx-background-radius: 10px;" +
                         "-fx-text-fill: #ffffff;" +
                         "-fx-border-color: #000000;" +
                         "-fx-border-radius: 10px";

    Button dealButton = new Button("Deal");
    dealButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> dealHand(5));
    dealButton.setStyle(buttonStyle);

    Button checkButton = new Button("Check");
    checkButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> checkCurrentHand());
    checkButton.setStyle(buttonStyle);

    Button bribe = new Button("Bribe the dealer");
    bribe.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> dealRoyalFlush());
    bribe.setStyle(buttonStyle);

    HBox buttons = new HBox(5);
    buttons.getChildren().addAll(dealButton, checkButton, bribe);

    HBox checkFields = new HBox(5);

    VBox labels = new VBox(11);
    String labelStyle = "-fx-font-family: 'Verdana Pro'; -fx-font-size: 16";

    Label sumOfCardsLabel = new Label("Sum of cards:");
    sumOfCardsLabel.setStyle(labelStyle);
    Label cardOfHeartsLabel = new Label("All cards of hearts:");
    cardOfHeartsLabel.setStyle(labelStyle);
    Label queenOfSpadesLabel = new Label("Queen of spades in hand?");
    queenOfSpadesLabel.setStyle(labelStyle);
    Label handIsFlushLabel = new Label("Flush?");
    handIsFlushLabel.setStyle(labelStyle);

    labels.getChildren().addAll(
            sumOfCardsLabel,
            cardOfHeartsLabel,
            queenOfSpadesLabel,
            handIsFlushLabel);

    VBox fields = new VBox(5);
    sumOfCardsField.setText("<No info>");
    sumOfCardsField.setEditable(false);

    cardsOfHeartsField.setText("<No info>");
    cardsOfHeartsField.setEditable(false);

    queenOfSpadesInHandField.setText("<No info>");
    queenOfSpadesInHandField.setEditable(false);

    handIsFlushField.setText("<No info>");
    handIsFlushField.setEditable(false);

    fields.getChildren().addAll(
            sumOfCardsField,
            cardsOfHeartsField,
            queenOfSpadesInHandField,
            handIsFlushField);

    checkFields.getChildren().addAll(
            labels,
            fields);

    AnchorPane bottomPane = new AnchorPane();
    bottomPane.setPrefHeight(200);
    bottomPane.getChildren().addAll(buttons, checkFields);
    AnchorPane.setLeftAnchor(buttons, 150.0);
    AnchorPane.setTopAnchor(buttons, 10.0);
    AnchorPane.setRightAnchor(checkFields, 170.0);
    AnchorPane.setTopAnchor(checkFields, 10.0);


    StackPane leftPane = new StackPane();
    leftPane.setPrefWidth(150);

    StackPane rightPane = new StackPane();
    rightPane.setPrefWidth(150);

    StackPane topPane = new StackPane();
    topPane.setPrefHeight(150);

    refreshTable();

    main.setCenter(centerPane);
    main.centerProperty().get().setStyle(
            "-fx-background-color:#1A7011;" +
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
    primary.setHeight(700);
    primary.setWidth(1000);
    primary.setResizable(false);
    primary.show();
  }

  /**
   * "Deals" a royal flush.
   */
  private void dealRoyalFlush() {
    List<PlayingCard> rFlush = new ArrayList<>();
    Random random = new Random();
    char[] suits = {'S', 'C', 'H', 'D'};
    char randomSuit = suits[random.nextInt(4)];
    rFlush.add(new PlayingCard(randomSuit, 10));
    rFlush.add(new PlayingCard(randomSuit, 11));
    rFlush.add(new PlayingCard(randomSuit, 12));
    rFlush.add(new PlayingCard(randomSuit, 13));
    rFlush.add(new PlayingCard(randomSuit, 1));
    hand = new CardHand(rFlush);
    refreshTable();
  }

  /**
   * Creates an image representing a single card.
   *
   * @param suit (char)  Suit of the card.
   * @param number (int) The number of the card.
   * @return             An image representing the card
   */
  private StackPane displayCard(char suit, int number) {
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

  /**
   * Creates a visual representation of the cards in the hand.
   */
  private void refreshDisplayHand() {
    displayHand = new HBox();
    for (PlayingCard card : hand.getHand()) {
      displayHand.getChildren().add(displayCard(card.getSuit(), card.getFace()));
    }
    displayHand.setAlignment(Pos.BOTTOM_CENTER);
  }

  /**
   * Deals a new hand with n amount of card, and displays it.
   *
   * @param n (int) The amount of cards to be added to the hand.
   */
  private void dealHand(int n) {
    hand = deck.dealHand(n);
    refreshTable();
  }

  /**
   * Refreshes the poker table to display the current hand.
   */
  private void refreshTable() {
    refreshDisplayHand();
    centerPane.getChildren().removeAll();
    centerPane.getChildren().add(displayHand);
    AnchorPane.setBottomAnchor(displayHand, 10.0);
    AnchorPane.setLeftAnchor(displayHand, 70.0);
    AnchorPane.setRightAnchor(displayHand, 70.0);
  }

  /**
   * Sums up the faces of all the cards. Ace is counted as 1.
   *
   * @return The sum of all the cards in the hand.
   */
  private int sumCards() {
    return hand.getHand()
            .stream()
            .map(PlayingCard::getFace)
            .reduce(0, Integer::sum);
  }

  /**
   * Gets a list of all the cards of the suit hearts currently in the hand.
   *
   * @return A list of all the cards of the suit hearts.
   */
  private String getAllHeartCards() {
    List<PlayingCard> hearts = hand.getHand()
            .stream()
            .filter(card -> card.getSuit() == 'H')
            .toList();
    return hearts.isEmpty() ? "No hearts" : hearts.stream()
            .map(card -> card.getAsString() + " ")
            .reduce("", String::concat);
  }

  /**
   * Checks if the current hand has the queen of spades.
   *
   * @return {@code true} if the queen of spades is in the hand, {@code false} if not.
   */
  private boolean isQueenOfSpadesInHand() {
    return hand.getHand()
            .stream()
            .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
  }

  /**
   * Checks if the current hand is a flush.
   *
   * @return {@code true} if it is a flush, {@code false} if not.
   */
  private boolean isHandFlush() {
    return hand.getHand()
                   .stream()
                   .map(PlayingCard::getSuit)
                   .distinct()
                   .toList().size() == 1;
  }

  private void checkCurrentHand() {
    sumOfCardsField.setText(String.format("%d", sumCards()));
    cardsOfHeartsField.setText(getAllHeartCards());
    queenOfSpadesInHandField.setText(isQueenOfSpadesInHand() ? "Yes" : "No");
    handIsFlushField.setText(isHandFlush() ? "Yes" : "No");
  }
}
