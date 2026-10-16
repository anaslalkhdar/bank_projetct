package net.youssfi.billingservice.feign;

import net.youssfi.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", fallback = ProductRestClientFallback.class)
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable Long id);

    @GetMapping("/products")
    PagedModel<Product> findAllProducts();
}
