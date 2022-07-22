package com.xoriant.ecart.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xoriant.ecart.configuration.Constant;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.model.Category;
import com.xoriant.ecart.service.CategoryService;

@ExtendWith(MockitoExtension.class)
class CategoryResourceTest {

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryResource categoryResource;

	private ProductDTO productDTO;

	private ProductDTO productDTO1;

	private List<ProductDTO> productDTOLists;

	private Category category;

	@BeforeEach
	void setUp() {
		productDTOLists = new ArrayList<>();
		productDTO = new ProductDTO();
		productDTO.setCategoryId(101);
		productDTO.setProductName("Mobile");
		productDTOLists.add(productDTO);

		productDTO1 = new ProductDTO();
		productDTO1.setCategoryId(102);
		productDTO1.setProductName("Laptop");
		productDTOLists.add(productDTO1);

		category = new Category();
		category.setCategoryId(productDTO.getCategoryId());
		category.setCategoryName(productDTO.getCategoryName());
	}

	@Test
	void addNewCategory() {
		when(categoryService.addNewCategory(productDTO)).thenReturn(Constant.NEW_CATEGORY_ADDED);
		String result = categoryService.addNewCategory(productDTO);
		assertEquals(new ResponseEntity<String>(result, HttpStatus.OK), categoryResource.addNewCategory(productDTO));
	}

	@Test
	void addNewListsCategories() {
		when(categoryService.addNewListsCategories(productDTOLists)).thenReturn(Constant.NEW_LISTS_OF_CATEGORY_ADDED);
		String result = categoryService.addNewListsCategories(productDTOLists);
		assertEquals(new ResponseEntity<>(result, HttpStatus.OK),
				categoryResource.addNewListsCategories(productDTOLists));
	}

	@Test
	void updateCategory() {
		when(categoryService.updateCategory(productDTO)).thenReturn(Constant.UPDATE_EXISTING_CATEGORY);
		String result = categoryService.updateCategory(productDTO);
		assertEquals(new ResponseEntity<>(result, HttpStatus.OK), categoryResource.updateCategory(productDTO));
	}

	@Test
	void updateListsCategory() {
		when(categoryService.updateListsCategory(productDTOLists))
				.thenReturn(Constant.UPDATE_LISTS_OF_EXISTING_CATEGORY);
		String result = categoryService.updateListsCategory(productDTOLists);
		assertEquals(new ResponseEntity<>(result, HttpStatus.OK),
				categoryResource.updateListsCategory(productDTOLists));
	}

	@Test
	void deleteCategory() {
		int categoryId = 101;
		categoryService.deleteCategory(categoryId);
		verify(categoryService, times(1)).deleteCategory(categoryId);
	}

	@Test
	void findByCategoryName() {
		String categoryName = "Mobile";

		Optional<Category> existingCategory = Optional.of(category);
		when(categoryService.findByCategoryName(categoryName)).thenReturn(existingCategory);
		assertEquals(existingCategory, categoryResource.findByCategoryName(categoryName));
	}

	@Test
	void findByCategoryId() {
		int categoryId = 101;
		Optional<Category> existingCategory = Optional.of(category);
		when(categoryService.findByCategoryId(categoryId)).thenReturn(existingCategory);
		assertEquals(existingCategory, categoryResource.findByCategoryId(categoryId));
	}

	@Test
	void findAllCategories() {
		List<Category> catLists = new ArrayList<>();
		catLists.add(category);
		when(categoryService.findAllCategories()).thenReturn(catLists);
		assertEquals(catLists, categoryResource.findAllCategories());
	}
}
