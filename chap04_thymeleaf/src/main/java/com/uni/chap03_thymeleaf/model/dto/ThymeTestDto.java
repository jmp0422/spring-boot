package com.uni.chap03_thymeleaf.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder(toBuilder = true)
public class ThymeTestDto {

    private long id;

    private String name;

    private String phone;

    private LocalDateTime createDate;
}
