package net.youssfi.billingservice.feign;

import net.youssfi.billingservice.model.Customer;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestClientFallback implements CustomerRestClient {
    @Override
    public Customer findCustomerById(Long id) {
        return Customer.builder().id(id).name("Not available").email("Not available").build();
    }

    @Override
    public PagedModel<Customer> findAllCustomers() {
        return PagedModel.empty();
    }
}
