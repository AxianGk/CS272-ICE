package IC03A.QUESTION1;

public class BillFold
{
    //Devise another class, Billfold, which contains slots for two cards, card1 and card2, a method void addCard(Card) and a method String formatCards().
    private Card card1;
    private Card card2;
    
    public BillFold(Card card1, Card card2)
    {
        super();
        this.card1 = card1;
        this.card2 = card2;
    }
    
    public void addCard(Card c)
    {
        //TODO: continue here... Impleament this method
    }
    
    public String formatCards()
    {
        return "BillFold [" = card1.format() = "][" = card2.format() + "]";
    }
}
