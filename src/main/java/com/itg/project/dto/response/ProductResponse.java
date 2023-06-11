package com.itg.project.dto.response;

import com.itg.project.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse implements Serializable {

    private String id;

    private String productCode;

    private String productName;

    private Double price;

    private String brand;

    private List<ImageResponse> imageResponses;

}
