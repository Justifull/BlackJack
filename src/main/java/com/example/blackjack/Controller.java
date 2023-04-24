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

    public static ArrayList<Card> cardsP2 = new ArrayList<>();

    public static ArrayList<Card> cardsP3 = new ArrayList<>();

    public static ArrayList<Card> cardsDealer = new ArrayList<>();

    public static int gewinnP2;

    public static int gewinnP3;

    public static int multiplier;

    public static int superWin;

    public static int cardsCountedValue;

    public static int cardsCountedValueP2;

    public static int cardsCountedValueP3;

    public static int cardsCountedDealer;

    public static int guthaben = 2000;

    public static int guthabenP2 = 2000;

    public static int guthabenP3 = 2000;

    public static boolean played = false;

    public static int betInput;

    public static int betInputP2;

    public static int betInputP3;

    public static boolean stayP2 = false;

    public static boolean stayP3 = false;

    public static int gewinn;

    public static boolean cheat = false;

    public static boolean kiWinP2 = false;

    public static boolean kiWinP3 = false;

    public static boolean lostP2 = false;

    public static boolean lostP3 = false;

    public static int profitValue;

    public static int profitValueP2;

    public static int profitValueP3;

    @FXML
    private Label guthabenLabelP2;

    @FXML
    private Label guthabenLabelP3;

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
    private Label cardDeck2;

    @FXML
    private Label cardDeck3;

    @FXML
    private Label countedCards2;

    @FXML
    private Label countedCards3;

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
    private Label betP2;

    @FXML
    private Label betP3;

    @FXML
    private Button restartButton;

    @FXML
    private Label profit1;

    @FXML
    private Label profit2;

    public void fail() {
        guthabenLabel.setText(guthaben + "€");

        profit.setVisible(true);
        profit.setTextFill(RED);
        profit.setText("[-" + profitValue / multiplier + "]");

        countedCardsBank.setText("[ " + cardsCountedDealer + " ]");

        String update = "";
        for (Card c : cardsDealer) {
            update += "[" + c.getName() + "]\n";
        }
        cardDeckDealer.setText("Dealer\n \n" + update);

        gewinn = 0;

        if (guthaben == 0) {
            restartButton.setVisible(true);
            newCardButton.setDisable(true);
            stopping.setDisable(true);
            return;
        }
        endingKiP3();
        endingKiP2();
        restart();
    }

    public void win() {
        guthaben += gewinn;
        guthabenLabel.setText(guthaben + "€");

        profit.setVisible(true);
        profit.setTextFill(GREEN);
        profit.setText("[+" + profitValue + "]");

        countedCardsBank.setText("[ " + cardsCountedDealer + " ]");

        String update = "";
        for (Card c : cardsDealer) {
            update += "[" + c.getName() + "]\n";
        }
        cardDeckDealer.setText("Dealer\n \n" + update);

        gewinn = 0;
        endingKiP3();
        endingKiP2();
        restart();
    }

    public void winKiP2() {
        kiWinP2 = true;
        guthabenP2 += gewinnP2;

        profit1.setVisible(true);
        profit1.setTextFill(GREEN);
        profit1.setText("[+" + profitValueP2 + "]");

        String updateP2 = "";
        for (Card c : cardsP2) {
            updateP2 += "[" + c.getName() + "]\n";
        }
        cardDeck2.setText("Cards P2\n \n" + updateP2);

        guthabenLabelP2.setText(guthabenP2 + "€");

        gewinnP2 = 0;
    }

    public void winKiP3() {
        kiWinP3 = true;
        guthabenP3 += gewinnP3;

        profit2.setVisible(true);
        profit2.setTextFill(GREEN);
        profit2.setText("[+" + profitValueP2 + "]");

        String updateP3 = "";
        for (Card c : cardsP3) {
            updateP3 += "[" + c.getName() + "]\n";
        }
        cardDeck3.setText("Cards P3\n \n" + updateP3);

        guthabenLabelP3.setText(guthabenP3 + "€");

        gewinnP3 = 0;
    }

    public void failKiP2() {
        guthabenLabelP2.setText(guthabenP2 + "€");

        countedCards2.setText("[ " + cardsCountedValueP2 + " ]");

        profit1.setVisible(true);
        profit1.setTextFill(RED);
        profit1.setText("[-" + profitValueP2 / multiplier + "]");

        String updateP2 = "";
        for (Card c : cardsP2) {
            updateP2 += "[" + c.getName() + "]\n";
        }
        cardDeck2.setText("Cards P2\n \n" + updateP2);

        gewinnP2 = 0;

        if (guthabenP2 == 0) {
            lostP2 = true;
        }

        stayP2 = true;
    }

    public void failKiP3() {
        guthabenLabelP3.setText(guthabenP3 + "€");

        countedCards3.setText("[ " + cardsCountedValueP3 + " ]");

        profit2.setVisible(true);
        profit2.setTextFill(RED);
        profit2.setText("[-" + profitValueP3 / multiplier + "]");

        String updateP3 = "";
        for (Card c : cardsP3) {
            updateP3 += "[" + c.getName() + "]\n";
        }
        cardDeck3.setText("Cards P3\n \n" + updateP3);

        gewinnP3 = 0;

        if (guthabenP3 == 0) {
            lostP2 = true;
        }

        stayP3 = true;
    }

    public void endingKiP2() {
        String updateP2 = "";
        while (cardsCountedValueP2 < 16) {
            Card newP2 = new Card(2);
            cardsCountedValueP2 += newP2.getValue();
            cardsP2.add(newP2);
            for (Card c : cardsP2) {
                updateP2 += "[" + c.getName() + "]\n";
            }
        }
        cardDeck2.setText("Cards P2\n \n" + updateP2);
        countedCards2.setText("[ " + cardsCountedValueP2 + " ]");
        if (cardsCountedValueP2 > 21) {
            failKiP2();
            return;
        }
        if (cardsCountedDealer >= cardsCountedValueP2) {
            failKiP2();
        } else {
            winKiP2();
        }
    }

    public void endingKiP3() {
        String updateP3 = "";
        while (cardsCountedValueP3 < 16) {
            Card newP3 = new Card(3);
            cardsCountedValueP3 += newP3.getValue();
            cardsP3.add(newP3);
            for (Card c : cardsP3) {
                updateP3 += "[" + c.getName() + "]\n";
            }
        }
        cardDeck3.setText("Cards P3\n \n" + updateP3);
        countedCards3.setText("[ " + cardsCountedValueP3 + " ]");
        if (cardsCountedValueP3 > 21) {
            failKiP3();
            return;
        }
        if (cardsCountedDealer >= cardsCountedValueP3) {
            failKiP3();
        } else {
            winKiP3();
        }
    }

    public void restart() {
        played = false;
        stopping.setText("Start");
        betting.setDisable(true);
        betValue.setDisable(true);
        stopping.setDisable(false);
        newCardButton.setDisable(true);
        betted.setText("[]");
        betP2.setText("[]");
        betP3.setText("[]");
        onWinLabel.setText("[]");
    }

    @FXML
    protected void pullNewCard() {
        Card neu = new Card(1);
        cards.add(neu);
        cardsCountedValue += neu.getValue();
        String update = "";
        for (Card c : cards) {
            update += "[" + c.getName() + "]\n";
        }
        cardDeck.setText("Cards YOU\n \n" + update);
        countedCards.setText("[ " + cardsCountedValue + " ]");
        if (cardsCountedValue > 21) {
            if (!cheat) {
                fail();
            } else {
                win();
            }
        }
        if (kiWinP2 == false && stayP2 == false && lostP2 == false) {
            Card neuP2 = new Card(2);
            cardsP2.add(neuP2);
            cardsCountedValueP2 += neuP2.getValue();
            String updateP2 = "";
            for (Card c : cardsP2) {
                updateP2 += "[" + c.getName() + "]\n";
            }
            cardDeck2.setText("Cards P2\n \n" + updateP2);
            countedCards2.setText("[ " + cardsCountedValueP2 + " ]");
            if (cardsCountedValueP2 > 21) {
                failKiP2();
            } else if (cardsCountedValueP2 > 16) {
                stayP2 = true;
            }
        }
        if (kiWinP3 == false && stayP3 == false && lostP3 == false) {
            Card neuP3 = new Card(3);
            cardsP3.add(neuP3);
            cardsCountedValueP3 += neuP3.getValue();
            String updateP3 = "";
            for (Card c : cardsP3) {
                updateP3 += "[" + c.getName() + "]\n";
            }
            cardDeck3.setText("Cards P3\n \n" + updateP3);
            countedCards3.setText("[ " + cardsCountedValueP3 + " ]");
            if (cardsCountedValueP3 > 21) {
                failKiP3();
            } else if (cardsCountedValueP3 > 16) {
                stayP3 = true;
            }
        }
    }

    @FXML
    protected void restartPress() {
        lostP3 = false;
        lostP2 = false;
        guthaben = 2000;
        guthabenP2 = 2000;
        guthabenP3 = 2000;
        gewinn = 0;
        gewinnP2 = 0;
        gewinnP3 = 0;
        guthabenLabel.setText(guthaben + "€");
        guthabenLabelP2.setText(guthabenP2 + "€");
        guthabenLabelP3.setText(guthabenP3 + "€");
        restartButton.setVisible(false);
        cardDeckDealer.setText("Dealer");
        cardDeck.setText("Cards YOU");
        cardDeck2.setText("Cards P2");
        cardDeck3.setText("Cards P3");
        profit.setText("[]");
        profit1.setText("[]");
        profit2.setText("[]");
        stopping.setText("Stop");
        betting.setDisable(false);
        betValue.setDisable(false);
        betValue.setEditable(true);
        stopping.setDisable(true);
        newCardButton.setDisable(true);
        profit.setVisible(false);
        profit1.setVisible(false);
        profit2.setVisible(false);
        played = true;
        countedCardsBank.setText("[]");
        countedCards.setText("[]");
        countedCards2.setText("[]");
        countedCards3.setText("[]");
        mult.setText("[]");
        restart();
    }

    public void ending() {
        String update = "";
        while (cardsCountedDealer < 17) {
            Card newDealer = new Card(3);
            cardsCountedDealer += newDealer.getValue();
            cardsDealer.add(newDealer);
            for (Card c : cardsDealer) {
                update += "[" + c.getName() + "]\n";
            }
        }
        cardDeckDealer.setText("Dealer\n \n" + update);
        countedCardsBank.setText("[ " + cardsCountedDealer + " ]");

        endingKiP2();
        endingKiP3();
        if (cardsCountedDealer > 21) {
            if (cardsCountedValue < 21) win();
            if (cardsCountedValueP2 < 21) winKiP2();
            if (cardsCountedValueP3 < 21) winKiP3();
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
        if (cardsCountedDealer >= cardsCountedValueP2) {
            failKiP2();
        } else {
            winKiP2();
        }
        if (cardsCountedDealer >= cardsCountedValueP3) {
            failKiP3();
        } else {
            winKiP3();
        }
    }

    @FXML
    protected void start() {
        cards.clear();
        cardsP2.clear();
        cardsP3.clear();
        cardsDealer.clear();
        cardsCountedValue = 0;
        cardsCountedValueP2 = 0;
        cardsCountedValueP3 = 0;
        cardsCountedDealer = 0;

        Card onePlayer = new Card(1);
        cards.add(onePlayer);
        cardsCountedValue += onePlayer.getValue();
        Card onePlayer2 = new Card(2);
        if (lostP2 == false) {
            cardsP2.add(onePlayer2);
            cardsCountedValueP2 += onePlayer2.getValue();
        }
        Card onePlayer3 = new Card(3);
        if (lostP3 == false) {
            cardsP3.add(onePlayer3);
            cardsCountedValueP3 += onePlayer3.getValue();
        }
        Card oneDealer = new Card(4);
        cardsDealer.add(oneDealer);
        cardsCountedDealer += oneDealer.getValue();
        Card twoPlayer = new Card(1);
        cards.add(twoPlayer);
        cardsCountedValue += twoPlayer.getValue();
        Card twoPlayer2 = new Card(2);
        if (lostP2 == false) {
            cardsP2.add(twoPlayer2);
            cardsCountedValueP2 += twoPlayer2.getValue();
        }
        Card twoPlayer3 = new Card(3);
        if (lostP3 == false) {
            cardsP3.add(twoPlayer3);
            cardsCountedValueP3 += twoPlayer3.getValue();
        }
        Card twoDealer = new Card(4);
        cardsDealer.add(twoDealer);
        cardsCountedDealer += twoDealer.getValue();

        String update = "";
        for (Card c : cards) {
            update += "[" + c.getName() + "]\n";
        }
        cardDeck.setText("Cards YOU\n \n" + update);
        countedCards.setText("[ " + cardsCountedValue + " ]");

        if (lostP2 == false) {
            String updateP2 = "";
            for (Card c : cardsP2) {
                updateP2 += "[" + c.getName() + "]\n";
            }
            cardDeck2.setText("Cards P2\n \n" + updateP2);
            countedCards2.setText("[ " + cardsCountedValueP2 + " ]");
        }

        if (lostP3 == false) {
            String updateP3 = "";
            for (Card c : cardsP3) {
                updateP3 += "[" + c.getName() + "]\n";
            }
            cardDeck3.setText("Cards P3\n \n" + updateP3);
            countedCards3.setText("[ " + cardsCountedValueP3 + " ]");
        }

        cardDeckDealer.setText("Dealer\n \n[" + cardsDealer.get(0).getName() + "]");
        countedCardsBank.setText("[ " + cardsDealer.get(0).getValue() + " ]");

        stopping.setDisable(false);

        newCardButton.setDisable(false);

        betValue.setText("");

        if (cardsCountedValue == 21) {
            win();
        }

        if (lostP2 == false) {
            if (cardsCountedValueP2 == 21) {
                winKiP2();
            } else if (cardsCountedValueP2 > 16) {
                stayP2 = true;
            }
        }

        if (lostP3 == false) {
            if (cardsCountedValueP3 == 21) {
                winKiP3();
            } else if (cardsCountedValueP3 > 16) {
                stayP3 = true;
            }
        }
    }

    @FXML
    protected void stopCardPull() {
        if (played == false) {
            cardDeck.setText("Cards YOU");
            cardDeck2.setText("Cards P2");
            cardDeck3.setText("Cards P3");
            cardDeckDealer.setText("Dealer");
            stopping.setText("Stop");
            betting.setDisable(false);
            betValue.setDisable(false);
            betValue.setEditable(true);
            stopping.setDisable(true);
            newCardButton.setDisable(true);
            played = true;
            countedCardsBank.setText("[]");
            countedCards.setText("[]");
            countedCards2.setText("[]");
            countedCards3.setText("[]");
            mult.setText("[]");
            return;
        }
        if (cardsCountedDealer < 17) {
            ending();
            return;
        }
        if (cardsCountedValue <= cardsCountedDealer) {
            if (!cheat) {
                fail();
            } else {
                win();
            }
        } else win();

        if (cardsCountedValueP2 <= cardsCountedDealer) {
            failKiP2();
        } else winKiP2();

        if (cardsCountedValueP3 <= cardsCountedDealer) {
            failKiP3();
        } else winKiP3();
    }

    public void pressEnter(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)) {
            betMoney();
        }
    }

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
                    betValue.clear();
                    return;
                } else {
                    cheat = false;
                    betting.setText("Bet");
                    betValue.clear();
                    return;
                }
            }

            if (betInput > guthaben) {
                betValue.clear();
                return;
            }
            if (betInput < 0) {
                betValue.clear();
                return;
            }

            betInputP2 = (int) (Math.random() * (guthabenP2 - guthabenP2 / 10)) + guthabenP2 / 10;
            betInputP3 = (int) (Math.random() * (guthabenP3 - guthabenP3 / 10)) + guthabenP3 / 10;
            multiplier = (int) (Math.random() * 10 + 2);
            superWin = (int) (Math.random() * 100 + 1);

            if (superWin == 42) {
                multiplier = 100;
            }

            guthaben -= betInput;
            guthabenP2 -= betInputP2;
            guthabenP3 -= betInputP3;
            gewinn = betInput * multiplier;
            gewinnP2 = betInputP2 * multiplier;
            gewinnP3 = betInputP3 * multiplier;
            profitValue = betInput * multiplier;
            profitValueP2 = betInputP2 * multiplier;
            profitValueP3 = betInputP3 * multiplier;
            betted.setText("[ " + betInput + " ]€");
            betP2.setText("[ " + betInputP2 + " ]€");
            betP3.setText("[ " + betInputP3 + " ]€");
            onWinLabel.setText("[ " + gewinn + " ]€");
            betting.setDisable(true);
            betValue.setEditable(false);
            guthabenLabel.setText(guthaben + "€");
            guthabenLabelP2.setText(guthabenP2 + "€");
            guthabenLabelP3.setText(guthabenP3 + "€");
            mult.setText("[ " + multiplier + "x ]");
            start();
        }
        catch (NumberFormatException f) {
            betValue.clear();
        }
        catch (Exception g) {
            betValue.clear();
        }
    }
}