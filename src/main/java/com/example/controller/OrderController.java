@RestController
public class OrderController {
	@Autowired
    private OrderQuerySvc orderQuerySvc;

    @Autowired
    private OrderStatisticsSvc orderStatisticsSvc;
    
	@Autowired
    private OrderSvc orderSvc;
	
    @GetMapping("/order/statistics")
    public Map<String, Integer> getMemberOrderStatistics(@RequestParam int n) {
        return orderStatisticsSvc.getMemberOrderStatistics(n);
    }
	
	@GetMapping("/order/place")
    public Map<String, Integer> getMemberOrder(@RequestBody Order order) {
        return orderSvc.queryOrders(memberId, productIds);
    }
	
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderSvc.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderSvc.getOrderById(id);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderSvc.addOrder(order);
    }

    @PutMapping("/orders/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderSvc.updateOrder(id, order);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderSvc.deleteOrder(id);
    }
}