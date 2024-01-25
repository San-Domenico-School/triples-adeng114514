/**
 * Write a description of class Scorekeeper here.
 * 
 * @Aivn Deng
 * @version (a version number or a date)
 */
public class Scorekeeper  
{
    private static int deckSize;
    private static int score;
    private static long startTime = System.currentTimeMillis();
    public static void setDeckSize(int howBig)
    {
        deckSize = howBig;
    }
    public static void updateScore()
    {
        score += 1;
    }
    public static int getScore()
    {
        return score;
    }
}
