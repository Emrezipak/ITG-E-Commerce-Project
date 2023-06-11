package com.itg.project.business.impl;

import com.itg.project.dto.response.ImageResponse;
import com.itg.project.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ImageServiceImpl {

    private final ImageRepository imageRepository;

    public List<ImageResponse> getImageById(List<String> ids) {
        return imageRepository.getByIdIn(ids).stream().map(image -> ImageResponse.builder()
                .size(image.getData().length)
                .url(downloadUri(image.getId()))
                .type(image.getType())
                .name(image.getName()).build()).collect(Collectors.toList());
    }

    private String downloadUri(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(id)
                .toUriString();
    }
}
