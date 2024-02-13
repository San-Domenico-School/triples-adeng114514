import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dealer here.
 * 
 * @Aivn) 
 * @version (a version number or a date)
 */
import greenfoot.*;
import java.util.ArrayList;
public class Dealer extends Actor {
    private int numCardsInDeck;
    private int tripleRemaining;
    private Deck deck;
    private ArrayList<Card> cardsOnBoard;
    private ArrayList<Integer> selectedCardsIndex;
    private Card[] cardsSelected;
    public Dealer(int numCardsInDeck) 
    {
        this.numCardsInDeck = numCardsInDeck;
        this.tripleRemaining = numCardsInDeck / 3;
        this.deck = new Deck(numCardsInDeck);
        this.cardsSelected = new Card[3];
    }
    public void addedToWorld(World world) 
    {
        dealBoard();
    }
    public void dealBoard() 
    {
        Greenfoot.playSound("shuffle.wav");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {
                Card card = deck.getTopCard();
                if (card != null) {
                    getWorld().addObject(card, row * 130 + 80, col * 80 + 60);
                }
            }
        }
    }
    public void setUI()
    {
        int cardsRemaining = deck.getNumCardsInDeck();
        int recentScore = Scorekeeper.getScore();
        String recentScoreString = Integer.toString(recentScore);
        String cardsRemainingString = Integer.toString(cardsRemaining);
        getWorld().showText(cardsRemainingString, 310, 470);
        getWorld().showText(recentScoreString, 310, 505);
    }
    public void endGame() 
    {
        if(numCardsInDeck == 0)
        {
            Greenfoot.stop();
            getWorld().showText("You won!",200,300);
        }
    }
    public void checkIfTriple(ArrayList<Card> cardsOnBoard, Card[]cardsSelected, ArrayList<Integer> selected) 
    {
        int shapes = cardsSelected[0].getShape().ordinal() + cardsSelected[1].getShape().ordinal() + cardsSelected[2].getShape().ordinal();
        int shadings = cardsSelected[0].getShading() + cardsSelected[1].getShading() + cardsSelected[2].getShading();
        int colors = cardsSelected[0].getColor().ordinal() + cardsSelected[1].getColor().ordinal() + cardsSelected[2].getColor().ordinal();
        int numberOfShapes = cardsSelected[0].getNumberOfShapes() + cardsSelected[1].getNumberOfShapes() + cardsSelected[2].getNumberOfShapes();
        if ((shapes % 3 == 0) && (shadings % 3 == 0) && (colors % 3 == 0) && (numberOfShapes % 3 == 0)) 
        {
            actionIfTriple(cardsOnBoard, cardsSelected, selectedCardsIndex);
        } 
        else
        {
            Animations.wobble(cardsSelected);
        }
    }
    public void actionIfTriple(ArrayList<Card> cardsOnBoard, Card[] cardsSelected, ArrayList<Integer> selectedCardsIndex) 
    {
        int[][] cardsXYCoordinate = new int[3][2];  
       for(int card = 0; card < 3; card++)
       {
            cardsXYCoordinate[card][0] = cardsSelected[card].getX();
            cardsXYCoordinate[card][1] = cardsSelected[card].getY();
       }    
       Animations.slideAndTurn(cardsSelected);     
       for(int card = 0; card < 3; card++)
       { 
           getWorld().removeObject(cardsSelected[card]);
           if(deck.getNumCardsInDeck() > 0)
           {
               cardsOnBoard.set(selectedCardsIndex.get(card),deck.getTopCard());
               getWorld().addObject(cardsOnBoard.get(selectedCardsIndex.get(card)), 
               cardsXYCoordinate[card][0], 
               cardsXYCoordinate[card][1]);
           }
       }
       Scorekeeper.updateScore();
       setUI();
       numCardsInDeck --;
    }
    public boolean setCardsSelected(ArrayList<Card> cards, ArrayList<Integer> indices, Card[] selectedCards) 
    {
        int shapes = cardsSelected[0].getShape().ordinal() + cardsSelected[1].getShape().ordinal() + cardsSelected[2].getShape().ordinal();
        int shadings = cardsSelected[0].getShading() + cardsSelected[1].getShading() + cardsSelected[2].getShading();
        int colors = cardsSelected[0].getColor().ordinal() + cardsSelected[1].getColor().ordinal() + cardsSelected[2].getColor().ordinal();
        int numberOfShapes = cardsSelected[0].getNumberOfShapes() + cardsSelected[1].getNumberOfShapes() + cardsSelected[2].getNumberOfShapes();
        if ((shapes % 3 == 0) && (shadings % 3 == 0) && (colors % 3 == 0) && (numberOfShapes % 3 == 0)) 
        {
            return true;      
        } 
        else
        {
            return false;
        }
    }
}
