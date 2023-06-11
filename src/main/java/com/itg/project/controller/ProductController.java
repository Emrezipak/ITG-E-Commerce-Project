package com.itg.project.controller;

import com.itg.project.business.service.IProductService;
import com.itg.project.dto.response.ProductResponse;
import com.itg.project.dto.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> save(@RequestPart(value = "product") String productDto, @RequestPart(value = "images") MultipartFile[] images) {
        return ResponseEntity.ok(productService.save(productDto, images));
    }

    @GetMapping("/getProductById")
    public ResponseEntity<ProductResponse> getProductById(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/deleteProductById")
    public ResponseEntity<ResponseMessage> deleteProductById(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/searchByProductCodeLike")
    public ResponseEntity<List<ProductResponse>> getAllProductsByProductCode(@RequestParam(name = "productCode") String productCode) {
        return ResponseEntity.ok(productService.getAllProductsByProductCode(productCode));
    }
}
