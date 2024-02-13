import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * Aivn Deng
 * FEB 16
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Dealer dealer;
    private Card[] cardsSelected; 
    private ArrayList<Card> cardsOnBoard; 
    private ArrayList<Integer> selectedCardsIndex; 
    public Player(Dealer dealer)
    {
        this.dealer = dealer; 
        cardsOnBoard = new ArrayList<Card>(); 
        selectedCardsIndex = new ArrayList<Integer>(); 
        cardsSelected = new Card[3];
    }
    
    public void act()
    {
        selectCards(); 
        if(threeCardsHaveBeenSelected())
        {
            dealer.checkIfTriple(cardsOnBoard, cardsSelected, selectedCardsIndex);
            resetCardsSelected();
        }
    }
    
    public void addedToWorld(World world)
    {
        cardsOnBoard = (ArrayList) getWorld().getObjects(Card.class);
    }
    
    private void selectCards()
    {
        for (int i = 0; i < cardsOnBoard.size(); i++) 
        {
            Card currentCard = cardsOnBoard.get(i);
            if (Greenfoot.mouseClicked(currentCard)) 
            {
                if (currentCard.getIsSelected()) 
                {
                    unselectCard(currentCard, i);
                } 
                else 
                {
                    selectCard(currentCard, i);
                }
            }
        }
    }
    
    private void selectCard(Card card, int index)
    {
        card.setIsSelected(true);
        card.setImage(card.getSelectedCardImage());
        selectedCardsIndex.add(index);
    }
    
    private void unselectCard(Card card, int index) 
    {
        card.setIsSelected(false);
        card.setImage(card.getCardImage());
        selectedCardsIndex.remove(Integer.valueOf(index));
    }
    
    private boolean threeCardsHaveBeenSelected()
    {
         if(selectedCardsIndex.size() == 3)
         {
               cardsSelected[0] = cardsOnBoard.get(selectedCardsIndex.get(0));
               cardsSelected[1] = cardsOnBoard.get(selectedCardsIndex.get(1)); 
               cardsSelected[2] = cardsOnBoard.get(selectedCardsIndex.get(2));
               return true;
         }
         else
         {
              return false;
         }
    }
    
    private void resetCardsSelected()
    {
          for(int i = 0; i < cardsOnBoard.size(); i++)
          {
                cardsOnBoard.get(i).setImage(cardsOnBoard.get(i).getCardImage());
                cardsOnBoard.get(i).setIsSelected(false);
          }
          selectedCardsIndex.clear();
    }
}
