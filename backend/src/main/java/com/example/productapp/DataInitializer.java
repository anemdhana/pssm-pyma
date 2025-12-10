package com.example.productapp;

import com.example.productapp.model.Product;
import com.example.productapp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository repository;

    public DataInitializer(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            List<Product> seeds = List.of(
                    Product.builder().name("Widget").description("A useful widget").price(new BigDecimal("9.99")).build(),
                    Product.builder().name("Gadget").description("An awesome gadget").price(new BigDecimal("19.99")).build(),
                    Product.builder().name("Thingamajig").description("Handy thingamajig for everyday tasks").price(new BigDecimal("4.50")).build()
            );
            repository.saveAll(seeds);
            System.out.println("Seeded " + seeds.size() + " products into H2 database.");
        }
    }
}
