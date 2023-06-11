package com.itg.project.dto.request;

import com.itg.project.entity.Image;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class ProductDto implements Serializable {

    @NotEmpty
    private String productCode;

    @NotEmpty
    private String productName;

    @NotNull
    private Double price;

    @NotEmpty
    private String brand;
}
