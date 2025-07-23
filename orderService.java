public class OrderService {
   
    public void processOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getCustomer() == null || order.getItems() == null || order.getItems().isEmpty()) {
            System.out.println("Invalid order details.");
            return;
        }
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        double discount = 0;
        if (order.getPromoCode() != null && !order.getPromoCode().isEmpty()) {
            if (order.getPromoCode().equals("FIRST10")) {
                discount = total * 0.10;
            } else if (order.getPromoCode().equals("FREESHIP")) {
                discount = 5.00;
            }
        }
        total -= discount;
        boolean paid = false;
        if (order.getPaymentMethod().equals("card")) {
            
            System.out.println("Processing card payment for: " + total);
            paid = true;
        } else if (order.getPaymentMethod().equals("paypal")) {
            
            System.out.println("Processing PayPal payment for: " + total);
            paid = true;
        } else {
            System.out.println("Unsupported payment method.");
        }
        if (paid) {
           
            System.out.println("Order placed successfully for customer: " + order.getCustomer().getName());
            System.out.println("Total charged: " + total);
        } else {
            System.out.println("Order failed to process payment.");
        }
    }
}
