public class UPI implements PaymentStrategy{
    private String upiID;

    public UPI(String upiID){
        this.upiID = upiID;
    }

    public void payment(int amount){
        System.out.println("payment of amount "+ amount + " made using the " + upiID);
    }

}
