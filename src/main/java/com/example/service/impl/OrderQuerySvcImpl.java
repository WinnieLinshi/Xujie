public class OrderQuerySvcImpl implement OrderQuerySvc{

    private OrderDAO orderDAO;
    
	@override 
    public List<Order> queryOrders(Order order) {
        List<Order> orders = orderDAO.getOrders(order);
        return orders;
    }
}