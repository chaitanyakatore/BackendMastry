public class CreditCard implements PaymentStrategy{

    private String CrediCard;
    private String CardName;

    public CreditCard(String CrediCard, String CardName){
        this.CardName = CardName;
        this.CrediCard = CrediCard;
    }

    public void payment(int amount){
        System.out.println("Payment of " + amount + " made using the "+ CardName + " with Card number ending with "+ CrediCard);
    }
}
