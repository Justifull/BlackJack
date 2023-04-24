package com.example.blackjack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class Controller {

    public static ArrayList<Card> cards = new ArrayList<>();

    public static ArrayList<Card> cardsDealer = new ArrayList<>();

    public static int multiplier;

    public static int superWin;

    public static int cardsCountedValue;

    public static int cardsCountedDealer;

    public static int guthaben = 2000;

    public static boolean played = false;

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
    private Label cardDeck;

    @FXML
    private Label cardDeckDealer;

    @FXML
    private TextField betValue;

    @FXML
    private Label betted;

    @FXML
    private Button betting;

    @FXML
    private Button stopping;

    @FXML
    private Label winning;

    @FXML
    private Label profit;

    @FXML
    private Label mult;

    @FXML
    private Button restartButton;

    public static int gewinn;

    public static boolean cheat = false;

    public void fail() {
        guthabenLabel.setText("Guthaben: " + guthaben + "€");

        profit.setVisible(true);
        profit.setTextFill(RED);
        profit.setText("[-" + gewinn / multiplier + "]");

        countedCardsBank.setText("Bank: " + cardsCountedDealer);

        String update = "";
        for (Card c : cardsDealer) {
            update += c.getName() + "; ";
        }
        cardDeckDealer.setText("[ " + update.substring(0, update.length() - 2) + " ]");

        gewinn = 0;

        if (guthaben == 0) {
            restartButton.setVisible(true);
            newCardButton.setDisable(true);
            stopping.setDisable(true);
            return;
        }

        restart();
    }

    public void win() {
        guthaben += gewinn;
        guthabenLabel.setText("Guthaben: " + guthaben + "€");

        profit.setVisible(true);
        profit.setTextFill(GREEN);
        profit.setText("[+" + gewinn + "]");

        countedCardsBank.setText("Bank: " + cardsCountedDealer);

        String update = "";
            for (Card c : cardsDealer) {
                update += c.getName() + "; ";
            }
        cardDeckDealer.setText("[ " + update.substring(0, update.length() - 2) + " ]");

        gewinn = 0;
        restart();
    }

    public void restart() {
        played = false;
        stopping.setText("Start");
        betting.setDisable(true);
        betValue.setDisable(true);
        stopping.setDisable(false);
        newCardButton.setDisable(true);
        betted.setText("Betted:");
        onWinLabel.setText("On win:");
    }

    @FXML
    protected void pullNewCard() {
        Card neu = new Card();
        cards.add(neu);
        cardsCountedValue += neu.getValue();
        String update = "";
        for (Card c : cards) {
            update += "[" + c.getName() + "]\n";
        }
        cardDeck.setText("Cards\n" + update);
        countedCards.setText("Counted: " + cardsCountedValue);
        if (cardsCountedValue > 21) {
            if (!cheat) {
                fail();
            } else {
                win();
            }
        }
    }

    @FXML
    protected void restartPress() {
        guthaben = 2000;
        guthabenLabel.setText("Guthaben: " + guthaben + "€");
        restartButton.setVisible(false);
        cardDeckDealer.setText("[]");
        profit.setText("[]");
        stopping.setText("Stop");
        betting.setDisable(false);
        betValue.setDisable(false);
        betValue.setEditable(true);
        stopping.setDisable(true);
        newCardButton.setDisable(true);
        profit.setVisible(false);
        played = true;
        countedCardsBank.setText("Bank:");
        mult.setText("Mult:");
        restart();
    }

    public void ending() {
        String update = "";
        while (cardsCountedDealer < 17) {
            Card newDealer = new Card();
            cardsCountedDealer += newDealer.getValue();
            cardsDealer.add(newDealer);
            for (Card c : cardsDealer) {
                update += c.getName() + "; ";
            }
        }
        cardDeckDealer.setText("[ " + update.substring(0, update.length() - 2) + " ]");
        countedCardsBank.setText("Bank: " + cardsCountedDealer);
        if (cardsCountedDealer > 21) {
            win();
            return;
        }
        if (cardsCountedDealer >= cardsCountedValue) {
            if (!cheat) {
                fail();
            } else {
                win();
            }
        } else {
            win();
        }
    }

    @FXML
    protected void start() {
        cards.clear();
        cardsDealer.clear();
        cardsCountedValue = 0;
        cardsCountedDealer = 0;

        Card onePlayer = new Card();
        cards.add(onePlayer);
        Card oneDealer = new Card();
        cardsDealer.add(oneDealer);
        Card twoPlayer = new Card();
        cards.add(twoPlayer);
        Card twoDealer = new Card();
        cardsDealer.add(twoDealer);

        cardsCountedValue += onePlayer.getValue() + twoPlayer.getValue();
        cardsCountedDealer += oneDealer.getValue() + twoDealer.getValue();

        String update = "";
        for (Card c : cards) {
            update += "[" + c.getName() + "]\n";
        }
        cardDeck.setText("Cards\n" + update);

        countedCards.setText("Counted: " + cardsCountedValue);

        stopping.setDisable(false);

        newCardButton.setDisable(false);

        betValue.setText("");

        if (cardsCountedValue == 21) {
            win();
        }
    }

    @FXML
    protected void stopCardPull() {
        if (played == false) {
            cardDeckDealer.setText("[]");
            stopping.setText("Stop");
            betting.setDisable(false);
            betValue.setDisable(false);
            betValue.setEditable(true);
            stopping.setDisable(true);
            newCardButton.setDisable(true);
            played = true;
            countedCardsBank.setText("Bank:");
            mult.setText("Mult:");
            return;
        }
        if (cardsCountedDealer < 17) {
            ending();
        } else if (cardsCountedValue <= cardsCountedDealer) {
            if (!cheat) {
                fail();
            } else {
                win();
            }
        } else win();
    }

    public void pressEnter(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)) {
            betMoney();
        }
    }

    public static int betInput;

    @FXML
    protected void betMoney() {
        if (!cheat) {
            betting.setText("Bet");
        }

        try {
            betInput = Integer.parseInt(betValue.getText());

            if (betInput == -42) {
                if (!cheat) {
                    cheat = true;
                    betting.setText("Cheat");
                    return;
                } else {
                    cheat = false;
                    betting.setText("Bet");
                    return;
                }
            }

            if (betInput > guthaben) {
                betting.setText("Error1");
                return;
            }
            if (betInput < 0) {
                betting.setText("Error2");
                return;
            }
            multiplier = (int) (Math.random() * 10 + 2);
            superWin = (int) (Math.random() * 100 + 1);
            if (superWin == 42) {
                multiplier = 100;
            }
            guthaben -= betInput;
            gewinn = betInput * multiplier;
            betted.setText("Betted: [ " + betInput + " ]€");
            onWinLabel.setText("On win: [ " + gewinn + " ]€");
            betting.setDisable(true);
            betValue.setEditable(false);
            guthabenLabel.setText("Guthaben: " + guthaben + "€");
            mult.setText("Mult: [" + multiplier + "x]");
            start();
        }
        catch (NumberFormatException f) {
            betting.setText("Error3");
        }
        catch (Exception g) {
            betting.setText("Error4");
        }
    }
}