package com.itg.project.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itg.project.business.service.IProductService;
import com.itg.project.dto.response.ProductResponse;
import com.itg.project.entity.Image;
import com.itg.project.entity.Product;
import com.itg.project.exception.BadRequestException;
import com.itg.project.exception.InternalServerException;
import com.itg.project.exception.NotFoundException;
import com.itg.project.repository.ProductRepository;
import com.itg.project.dto.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final ImageServiceImpl imageServiceImpl;


    @Transactional
    public ProductResponse save(String productDto, MultipartFile [] images){
        try {
            Product product = convertDtoToEntity(productDto);
            log.info("Product {}",product);

            if(productRepository.existsByProductCode(product.getProductCode())){
                log.warn("There is a product with this product code {}",product.getProductCode());
                throw new BadRequestException("There is a product with this product code");
            }

            product.setImages(createImageEntity(images));
            productRepository.saveAndFlush(product); //save'e döndürülebilir
            return createProductResponse(product);
        }
        catch (Exception e){
            log.error("ERROR {}",e.toString());
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional
    public ProductResponse getProductById(String id){
        Optional<Product> product = productRepository.getProductByIdEqualsIgnoreCase(id);
        if(!product.isPresent()){
             log.warn("Product not found with this id {}",id);
             throw new NotFoundException("Product not found");
        }

        return createProductResponse(product.get());
    }

    @Transactional
    public ResponseMessage deleteProductById(String id){
        Optional<Product> product = productRepository.getProductByIdEqualsIgnoreCase(id);
        if(!product.isPresent()){
            throw new NotFoundException("Product not found");
        }

        productRepository.deleteById(id);
        log.info("The product with this id {} has been successfully deleted",id);
        return ResponseMessage.builder().message("The product has been successfully deleted").httpStatus(HttpStatus.OK).build();
    }

    @Transactional
    public List<ProductResponse> getAllProduct(){
        return productRepository.findAll().stream().map(this::createProductResponse).collect(Collectors.toList());
    }

    @Transactional
    public List<ProductResponse> getAllProductsByProductCode(String productCode){
        return productRepository.searchByProductCodeLike(productCode).map(this::createProductResponse).collect(Collectors.toList());
    }

    public Product convertDtoToEntity(String productDto) throws JsonProcessingException {
        log.info(productDto);
        return objectMapper.readValue(productDto,Product.class);
    }

    public List<Image> createImageEntity(MultipartFile [] images) throws IOException {
        return Arrays.stream(images).map(image ->{
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            try {
                return Image.builder().name(fileName).type(image.getContentType()).data(image.getBytes()).build();
            } catch (IOException e) {
                log.error("ERROR {}",e.toString());
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public ProductResponse createProductResponse(Product product){
        return ProductResponse.builder().id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .productCode(product.getProductCode())
                .imageResponses(imageServiceImpl.getImageById(product.getImages().stream().map(Image::getId).collect(Collectors.toList())))
                .build();
    }
}
