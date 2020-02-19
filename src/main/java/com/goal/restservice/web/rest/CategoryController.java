package com.goal.restservice.web.rest;

import com.goal.restservice.domain.ResponseVO;
import com.goal.restservice.domain.categories.Category;
import com.goal.restservice.repository.CategoryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

  private final CategoryRepository categoryRepository;

}
