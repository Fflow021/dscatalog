package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;
    private long existentId;
    private long nonExistentId;
    private long countTotalProducts;
    @BeforeEach
    void setUp() throws Exception {
        existentId = 1L;
        nonExistentId = 10000L;
        countTotalProducts = 25L;
    }

    @Test
    public void findByIdShouldReturnOptionalNonEmptyWhenIdExists() {

        Optional<Product> result = repository.findById(existentId);
        Assertions.assertTrue(result.isPresent());

    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesntExist() {

        Optional<Product> result = repository.findById(nonExistentId);
        Assertions.assertTrue(result.isEmpty());

    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {

        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts+1, product.getId());

    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existentId);

        Optional<Product> result = repository.findById(existentId);
        Assertions.assertFalse(result.isPresent());
    }

//delete by id vai sempre ignorar caso um ID que não existe seja imputado
//    @Test
//    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
//
//        long nonExistingId = 1000L;
//
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->  {
//            repository.deleteById(nonExistingId);
//        });
//    }


}
