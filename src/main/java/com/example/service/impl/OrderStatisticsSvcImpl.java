public class OrderStatisticsSvc implement OrderStatisticsSvc{

    private Order order;

	@override 
    public OrderStatisticsAPI(Order order) {
        this.orderDAO = orderDAO;
    }

    public Map<String, Integer> getMemberOrderStatistics(int n) {
        List<Order> allOrders = orderDAO.getAllOrders();
        Map<String, Integer> memberOrderCountMap = new HashMap<>();

        for (Order order : allOrders) {
            String memberId = order.getMemberId();
            memberOrderCountMap.put(memberId, memberOrderCountMap.getOrDefault(memberId, 0) + 1);
        }

        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : memberOrderCountMap.entrySet()) {
            if (entry.getValue() > n) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}