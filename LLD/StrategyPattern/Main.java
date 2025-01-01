public class Main {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setPaymentStrategy(new CreditCard("1234567890", "HDFC"));
        paymentContext.processPayment(200);

        paymentContext.setPaymentStrategy(new PayPal("ckatore82gmail.com"));
        paymentContext.processPayment(200);

        paymentContext.setPaymentStrategy(new UPI("ckatore82gmail.com"));
        paymentContext.processPayment(200);
    }
}
