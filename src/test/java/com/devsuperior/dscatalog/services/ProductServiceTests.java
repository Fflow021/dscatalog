package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;
    private long existentId;
    private long nonExistentId;

    @BeforeEach
    void setUp() throws Exception {
        existentId = 1L;
        nonExistentId = 10000L;

        Mockito.doNothing().when(repository).deleteById(existentId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistentId);
    }

    @Test
    public void deleteByIdShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            service.delete(existentId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(existentId);
    }

}
