import greenfoot.*; 

/**
 * Game Board for Triples
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Card extends Actor
{
    public enum Shape
    {
       CIRCLE, TRIANGLE, SQUARE, NO_SHAPE
    }
    public enum Color
    {
        BLUE, GREEN, RED, NO_COLOR
    }
    private boolean isSelected;
    private Shape shape;
    private Color color;
    private GreenfootImage cardImage;
    private GreenfootImage selectedCardImage;
    private int numberOfShapes;
    private int shading;
    public Card(Shape shape, Color color, int numberOfShapes, int shading, GreenfootImage cardImage, GreenfootImage selectedCardImage)
    {
        this.shape = shape;
        this.color = color;
        this.shading = shading;
        this.numberOfShapes = numberOfShapes;
        this.cardImage = cardImage;
        this.selectedCardImage = selectedCardImage;

        setImage(cardImage);
    }
    public boolean getIsSelected()
    {
        return isSelected;
    }
    public int getNumberOfShapes()
    {
        return numberOfShapes;
    }
    public Color getColor()
    {
        return color;
    }
    public int getShading()
    {
        return shading;
    }
    public GreenfootImage getSelectedCardImage()
    {
        return selectedCardImage;
    }
    public GreenfootImage getCardImage()
    {
        return cardImage;
    }
    public Shape getShape()
    {
        return shape;
    }
    public void setIsSelected(boolean selected)
    {
        this.isSelected = selected;
    }
}

