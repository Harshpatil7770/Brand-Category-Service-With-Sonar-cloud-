package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.model.Category;

public interface CategoryService {

	String addNewCategory(ProductDTO productDTO);

	String addNewListsCategories(List<ProductDTO> productDTO);

	String updateCategory(ProductDTO productDTO);

	String updateListsCategory(List<ProductDTO> productDTO);

	void deleteCategory(int categoryId);

	Optional<Category> findByCategoryName(String categoryName);

	Optional<Category> findByCategoryId(int categoryId);

	List<Category> findAllCategories();
}
