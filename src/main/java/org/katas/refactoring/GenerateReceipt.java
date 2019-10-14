package org.katas.refactoring;

import java.util.List;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class GenerateReceipt {
    private static final String TAB = "\t";
    private static final String NEWLINE = "\n";
    public static final double RATE = .10;
    private Order order;

    public GenerateReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder receipt = new StringBuilder();
        String receiptHeader = "======Printing Orders======\n";
        List<OrderedItem> orderedItems = order.getOrderedItems();
        double totalSalesTax = computeSalesTax(orderedItems);
        double totalAmount = computeTotalAmount(orderedItems, totalSalesTax);

        receipt.append(receiptHeader);
        receipt.append(order.getCustomerName());
        receipt.append(order.getCustomerAddress());

        printItem(receipt, orderedItems);

        receipt.append(addDelimiter("Sales Tax",TAB)).append(totalSalesTax);
        receipt.append(addDelimiter("Total Amount",TAB)).append(totalAmount);
        return receipt.toString();
    }

    private void printItem(StringBuilder receipt, List<OrderedItem> orderedItems) {
        for (OrderedItem orderedItem : orderedItems) {
            receipt.append(addDelimiter(orderedItem.getDescription(),TAB));
            receipt.append(addDelimiter(orderedItem.getPrice(),TAB));
            receipt.append(addDelimiter(orderedItem.getQuantity(),TAB));
            receipt.append(addDelimiter(orderedItem.totalAmount(),NEWLINE));
        }
    }

    public String addDelimiter(Object content, String delimiter){
        return content.toString() + delimiter;
    }

    public double computeSalesTax (List<OrderedItem> orderedItemList){
        return orderedItemList.stream().map(orderedItem -> orderedItem.totalAmount() * RATE).reduce((a, b) -> a+b).orElse(0.0);
    }

    public double computeTotalAmount(List<OrderedItem> orderedItemList, double totalSalesTax){
        double totalAmount = orderedItemList.stream().map(orderedItem -> orderedItem.totalAmount())
                .reduce((a,b) -> a+b).orElse(0.0);
        return totalAmount + totalSalesTax;
    }
}