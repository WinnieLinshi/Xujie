public class OrderDAO{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders(Order orderQuery) {
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : orders) {
            if (matchesQuery(order, orderQuery)) {
                filteredOrders.add(order);
            }
        }

        return filteredOrders;
    }

    private boolean matchesQuery(Order orderQuery) {
        if (orderQuery.getOrderId() != 0 && this.order.getOrderId() != orderQuery.getOrderId()) {
            return false;
        }
        if (orderQuery.getProductName() != null && !order.getProductName().equals(orderQuery.getProductName())) {
            return false;
        }
        if (orderQuery.getPurchaseDate() != null && !order.getPurchaseDate().equals(orderQuery.getPurchaseDate())) {
            return false;
        }

        return true;
    }

    // Additional methods to add, update, delete orders in memory
    public void addOrder(Order order) {
        orders.add(order);
    }

    public void updateOrder(Order order) {
        for (Order m : orders) {
            if (m.getId() == order.getId()) {
                m.setMemberId(order.getMemberId());
                m.setProducts(order.getProducts());
                break;
            }
        }
	}

    public void deleteOrder(int orderId) {
        orders.removeIf(m -> m.getId() == orderId);
    }
}
