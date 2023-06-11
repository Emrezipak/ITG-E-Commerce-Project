package com.itg.project.business.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itg.project.dto.response.ProductResponse;
import com.itg.project.entity.Image;
import com.itg.project.entity.Product;
import com.itg.project.dto.response.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductResponse save(String productDto, MultipartFile[] images);
    ProductResponse getProductById(String id);
    ResponseMessage deleteProductById(String id);
    List<ProductResponse> getAllProduct();
    List<ProductResponse> getAllProductsByProductCode(String productCode);
    Product convertDtoToEntity(String productDto) throws JsonProcessingException;
    List<Image> createImageEntity(MultipartFile [] images) throws IOException;
    ProductResponse createProductResponse(Product product);


}
