package net.youssfi.billingservice.web;

import net.youssfi.billingservice.entities.Bill;
import net.youssfi.billingservice.feign.CustomerRestClient;
import net.youssfi.billingservice.feign.ProductRestClient;
import net.youssfi.billingservice.repository.BillRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillRestController {
    private final BillRepository billRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillRestController(BillRepository billRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/bills")
    public List<Bill> bills() {
        List<Bill> bills = billRepository.findAll();
        bills.forEach(this::completeBill);
        return bills;
    }

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Bill %s not found", id)));
        completeBill(bill);
        return bill;
    }

    private void completeBill(Bill bill) {
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem ->
                productItem.setProduct(productRestClient.findProductById(productItem.getProductId())));
    }
}
