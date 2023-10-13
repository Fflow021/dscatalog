package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceIT {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repository;
    private long existentId;
    private long nonExistentId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception {
        existentId = 1L;
        nonExistentId = 2L;
        countTotalProducts = 25L;

    }
    @Test
    public void deleteShouldDeleteResourceWhenIdExists() {

        service.delete(existentId);

        Assertions.assertEquals(countTotalProducts-1, repository.count());
    }

    @Test
    public void deleteShouldThrowsResourceNotFoundExceptionWhenIdDoesntExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistentId);
        });
    }
}
