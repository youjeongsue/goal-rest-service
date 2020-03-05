package com.goal.restservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryDto {

  @NotBlank
  @Size(min = 5, max = 50)
  String name;

  @Builder
  public CategoryDto(String name) {
    this.name = name;
  }
}
