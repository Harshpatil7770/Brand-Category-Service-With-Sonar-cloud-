package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.configuration.Constant;
import com.xoriant.ecart.dao.CategoryRepo;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.exception.ElementNotFound;
import com.xoriant.ecart.exception.InputUserException;
import com.xoriant.ecart.model.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	private Category category;

	@Override
	public String addNewCategory(ProductDTO productDTO) {

		if (productDTO.getCategoryName().isBlank() || productDTO.getCategoryName().isEmpty()) {
			throw new InputUserException();
		}
		category = new Category();
		category.setCategoryName(productDTO.getCategoryName().toUpperCase());
		categoryRepo.save(category);
		log.info("addNewCategory() called");
		return Constant.NEW_CATEGORY_ADDED;
	}

	@Override
	public String addNewListsCategories(List<ProductDTO> productDTO) {
		for (ProductDTO newProduct : productDTO) {
			if (newProduct.getCategoryName().isBlank() || newProduct.getCategoryName().isEmpty()) {
				throw new InputUserException();
			}
		}

		for (ProductDTO newProduct : productDTO) {
			category = new Category();
			category.setCategoryName(newProduct.getCategoryName().toUpperCase());
			categoryRepo.save(category);
		}
		log.info("addNewListsCategories() called");
		return Constant.NEW_LISTS_OF_CATEGORY_ADDED;
	}

	@Override
	public String updateCategory(ProductDTO productDTO) {

		if (productDTO.getCategoryName().isBlank() || productDTO.getCategoryName().isEmpty()) {
			throw new InputUserException();
		}
		Optional<Category> existingCategory = categoryRepo.findById(productDTO.getCategoryId());
		if (!existingCategory.isPresent()) {
			throw new ElementNotFound();
		}
		Category updateCategory = categoryRepo.findById(productDTO.getCategoryId()).orElse(null);
		updateCategory.setCategoryId(productDTO.getCategoryId());
		updateCategory.setCategoryName(productDTO.getCategoryName().toUpperCase());
		categoryRepo.save(updateCategory);
		log.info("updateCategory() called");
		return Constant.UPDATE_EXISTING_CATEGORY;
	}

	@Override
	public String updateListsCategory(List<ProductDTO> productDTO) {
		for (ProductDTO newProduct : productDTO) {
			Optional<Category> existingCategory = categoryRepo.findById(newProduct.getCategoryId());
			if (!existingCategory.isPresent()) {
				throw new ElementNotFound();
			}
			if (newProduct.getCategoryName().isBlank() || newProduct.getCategoryName().isEmpty()) {
				throw new InputUserException();
			}
		}
		for (ProductDTO newProduct : productDTO) {
			Category updateCategory = categoryRepo.findById(newProduct.getCategoryId()).orElse(null);
			updateCategory.setCategoryId(newProduct.getCategoryId());
			updateCategory.setCategoryName(newProduct.getCategoryName().toUpperCase());
			categoryRepo.save(updateCategory);
		}
		log.info("updateListsCategory() called");
		return Constant.UPDATE_LISTS_OF_EXISTING_CATEGORY;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Optional<Category> existingCategory = categoryRepo.findById(categoryId);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFound();
		}
		log.info("deleteCategory() called");
		categoryRepo.deleteById(categoryId);
	}

	@Override
	public Optional<Category> findByCategoryName(String categoryName) {
		Optional<Category> existingCategory = categoryRepo.findByCategoryName(categoryName);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFound();
		}
		log.info("findByCategoryName() called");
		return existingCategory;
	}

	@Override
	public Optional<Category> findByCategoryId(int categoryId) {

		Optional<Category> existingCategory = categoryRepo.findById(categoryId);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFound();
		}
		return existingCategory;
	}

	@Override
	public List<Category> findAllCategories() {
		List<Category> categoryLists = categoryRepo.findAllExistingCategories();
		if (categoryLists.isEmpty()) {
			throw new ElementNotFound();
		}
		return categoryLists;
	}

}