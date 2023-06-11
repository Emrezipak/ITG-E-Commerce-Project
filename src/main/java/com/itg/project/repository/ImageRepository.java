package com.itg.project.repository;

import com.itg.project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,String> {

    @Query("select a from Image a where a.id IN :idList")
    List<Image> getByIdIn(@Param("idList") List<String> ids);
}
