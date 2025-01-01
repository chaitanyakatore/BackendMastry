public class PayPal implements PaymentStrategy{

    private String email;

    public PayPal(String email){
        this.email = email;
    }

    public void payment(int amount){
        System.out.println("Payment of amount " + amount + "made using the " + email);
    }
}
