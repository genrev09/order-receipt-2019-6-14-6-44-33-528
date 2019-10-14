package org.katas.refactoring;

import java.util.List;

public class Order {
    String name;
    String address;
    List<OrderedItem> orderedItems;

    public Order(String name, String address, List<OrderedItem> orderedItems) {
        this.name = name;
        this.address = address;
        this.orderedItems = orderedItems;
    }

    public String getCustomerName() {
        return name;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }
}
