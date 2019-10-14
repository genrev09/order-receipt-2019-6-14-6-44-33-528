package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final String TAB = "\t";
    private static final String NEWLINE = "\n";
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        String receiptHeader = "======Printing Orders======\n";

        output.append(receiptHeader);

        // print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
//        output.append(order.getCustomerLoyaltyNumber());

        // prints lineItems
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            output.append(addDelimiter(lineItem.getDescription(),TAB));
            output.append(addDelimiter(lineItem.getPrice(),TAB));
            output.append(addDelimiter(lineItem.getQuantity(),TAB));
            output.append(addDelimiter(lineItem.totalAmount(),NEWLINE));

            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        output.append(addDelimiter("Sales Tax",TAB)).append(totalSalesTax);

        // print total amount
        output.append(addDelimiter("Total Amount",TAB)).append(totalAmount);
        return output.toString();
    }

    public String addDelimiter(Object content, String delimiter){
        return content.toString() + delimiter;
    }
}