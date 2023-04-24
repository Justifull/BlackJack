package com.example.blackjack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class HelloController {

    private ArrayList<Card> cards = new ArrayList<>();

    public static int cardsCountedValue;

    public static int cardsCountedDealer;

    public static int guthaben;

    @FXML
    private Label onWinLabel;

    @FXML
    private Label guthabenLabel;

    @FXML
    private Button newCardButton;

    @FXML
    private Label countedCards;

    @FXML
    private Label countedCardsBank;

    @FXML
    private TableColumn cardDeck;

    @FXML
    private TableView cardOverview;

    @FXML
    private TextField betValue;

    @FXML
    private Label betted;

    @FXML
    private Button betting;

    final ObservableList<String> data = FXCollections.observableArrayList(
            new Card().getName()
    );

    public void fail() {
        guthabenLabel.setText("Guthaben: " + guthaben);

    }

    public void win() {
        guthabenLabel.setText("Guthaben: " + guthaben);

    }

    @FXML
    protected void pullNewCard() {
        Card neu = new Card();
        cards.add(neu);
        cardsCountedValue += neu.getValue();
        if (cardsCountedValue > 21) {
            fail();
        } else {
            String update = "";
            for (Card c : cards) {
                update += "[" + c.getName() + "]";
            }
            data.add(neu.getName());
            cardOverview.setItems(data);
        }
    }

    @FXML
    protected void start() {
        Card onePlayer = new Card();
        Card oneDealer = new Card();
        Card twoPlayer = new Card();
        Card twoDealer = new Card();

        cardsCountedValue += onePlayer.getValue() + twoPlayer.getValue();
        cardsCountedDealer += oneDealer.getValue() + twoDealer.getValue();

        data.add(onePlayer.getName());
        data.add(twoPlayer.getName());
        cardOverview.setItems(data);
    }

    @FXML
    protected void stopCardPull() {
        if (cardsCountedValue > 21) {
            fail();
        } else {
            win();
        }
    }

    @FXML
    protected void betMoney() {
        if (Integer.parseInt(betValue.getText()) > guthaben) {
            betted.setText("Error");
            return;
        }
        if (Integer.parseInt(betValue.getText()) < 0) {
            betted.setText("Error");
            return;
        }
        guthaben -= Integer.parseInt(betValue.getText());
        betted.setText("Betted: [ " + Integer.parseInt(betValue.getText()) + " ]");
        onWinLabel.setText("On win: [ " + Integer.parseInt(betValue.getText()) * 2 + " ]");
    }
}