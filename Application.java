import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@RestController
public class Application {

    // 使用 ConcurrentHashMap 來確保線程安全
    private Map<Integer, Map<String, Object>> members = new ConcurrentHashMap<>();
    private List<Map<String, Object>> orders = Collections.synchronizedList(new ArrayList<>());

    // 新增會員
    @PostMapping("/members")
    public synchronized Map<String, Object> addMember(@RequestBody Map<String, Object> member) {
        int id = members.size();
        members.put(id, member);
        return member;
    }

    // 刪除會員
    @DeleteMapping("/members/{id}")
    public synchronized Map<String, Object> deleteMember(@PathVariable int id) {
        return members.remove(id);
    }

    // 修改會員
    @PutMapping("/members/{id}")
    public synchronized Map<String, Object> updateMember(@PathVariable int id, @RequestBody Map<String, Object> member) {
        return members.put(id, member);
    }

    // 查詢單一會員
    @GetMapping("/members/{id}")
    public Map<String, Object> getMember(@PathVariable int id) {
        return members.get(id);
    }

    // 分頁查詢會員
    @GetMapping("/members")
    public List<Map<String, Object>> getMembers(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        List<Map<String, Object>> result = new ArrayList<>();
        int start = page * size;
        int end = Math.min(start + size, members.size());
        for (int i = start; i < end; i++) {
            result.add(members.get(i));
        }
        return result;
    }

    // 訂單訂購API
    @PostMapping("/orders")
    public synchronized Map<String, Object> placeOrder(@RequestBody Map<String, Object> order) {
        orders.add(order);
        return order;
    }

    // 訂單查詢API
    @GetMapping("/orders")
    public synchronized List<Map<String, Object>> searchOrders(@RequestParam(required = false) String orderNumber,
                                                                @RequestParam(required = false) String productName,
                                                                @RequestParam(required = false) String purchaseDate,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        // 假設實作訂單查詢邏輯並返回結果
        return new ArrayList<>();
    }

    // 訂單統計API
    @GetMapping("/order-statistics")
    public synchronized List<Map<String, Object>> getOrderStatistics(@RequestParam int n) {
        // 假設實作訂單統計邏輯並返回結果
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
