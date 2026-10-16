package net.youssfi.billingservice;

import net.youssfi.billingservice.entities.Bill;
import net.youssfi.billingservice.entities.ProductItem;
import net.youssfi.billingservice.feign.CustomerRestClient;
import net.youssfi.billingservice.feign.ProductRestClient;
import net.youssfi.billingservice.model.Customer;
import net.youssfi.billingservice.model.Product;
import net.youssfi.billingservice.repository.BillRepository;
import net.youssfi.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient) {
        return args -> {
            Random random = new Random();
            for (Customer customer : customerRestClient.findAllCustomers().getContent()) {
                Bill bill = billRepository.save(Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer.getId())
                        .build());
                for (Product product : productRestClient.findAllProducts().getContent()) {
                    if (random.nextBoolean()) {
                        productItemRepository.save(ProductItem.builder()
                                .bill(bill)
                                .productId(product.getId())
                                .price(product.getPrice())
                                .quantity(1 + random.nextInt(10))
                                .build());
                    }
                }
            }
        };
    }
}
