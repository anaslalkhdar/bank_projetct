package net.youssfi.billingservice.feign;

import net.youssfi.billingservice.model.Product;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

@Component
public class ProductRestClientFallback implements ProductRestClient {
    @Override
    public Product findProductById(Long id) {
        return Product.builder().id(id).name("Not available").price(0).quantity(0).build();
    }

    @Override
    public PagedModel<Product> findAllProducts() {
        return PagedModel.empty();
    }
}
