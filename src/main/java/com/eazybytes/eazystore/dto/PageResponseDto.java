package com.eazybytes.eazystore.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto {
    private  List<ProductDto> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private  int pageSize;


}
