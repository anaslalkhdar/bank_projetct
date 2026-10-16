package net.youssfi.inventoryservice;

import net.youssfi.inventoryservice.entities.Product;
import net.youssfi.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("Computer").price(3500).quantity(12).build());
            productRepository.save(Product.builder().name("Printer").price(1200).quantity(10).build());
            productRepository.save(Product.builder().name("Smartphone").price(8000).quantity(25).build());
            productRepository.save(Product.builder().name("Mouse").price(150).quantity(40).build());
            productRepository.save(Product.builder().name("Keyboard").price(300).quantity(30).build());
        };
    }
}
