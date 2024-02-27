public class OrderSvcImpl implement OrderSvc{
    private MemberDAO memberDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

	@override 
    public void getOrder(Order order) {
        return orderDAO.getOrders(order);
    }

	@override 
    public void addOrder(Order order) {
        return orderDAO.addOrder(order);
    }
	
	@override 
    public void updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }	
	
	@override 
    public void deleteOrder(Order order) {
        return orderDAO.deleteOrder(order);
    }	
	
	@override 
    public void placeOrder(int memberId, List<Integer> productIds) {
        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }
        
        List<Product> products = new ArrayList<>();
        for (int productId : productIds) {
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                throw new IllegalArgumentException("Product not found");
            }
            if (product.getQuantity() <= 0) {
                throw new IllegalArgumentException("Product out of stock");
            }
            products.add(product);
        }
        
        // Deduct quantity from stock
        for (Product product : products) {
            product.setQuantity(product.getQuantity() - 1);
            productDAO.updateProduct(product);
        }
        
        Order order = new Order();
        order.setMemberId(memberId);
        order.setProducts(products);
        
        orderDAO.addOrder(order);
    }
}