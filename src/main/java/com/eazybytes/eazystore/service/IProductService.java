package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.PageResponseDto;
import com.eazybytes.eazystore.dto.ProductDto;
import com.eazybytes.eazystore.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    PageResponseDto getProducts(Pageable pageable);
}
