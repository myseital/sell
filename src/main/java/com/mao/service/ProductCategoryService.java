package com.mao.service;

import com.mao.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIds);

    ProductCategory save(ProductCategory productCategory);
}
