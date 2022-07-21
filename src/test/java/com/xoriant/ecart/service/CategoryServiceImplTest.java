package com.xoriant.ecart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xoriant.ecart.configuration.Constant;
import com.xoriant.ecart.dao.CategoryRepo;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.model.Category;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	private CategoryRepo categoryRepo;

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	private ProductDTO productDTO1;

	private ProductDTO productDTO2;

	private Category category1;

	private Category category2;

	private List<ProductDTO> productDTOLists;

	@BeforeEach
	void setUp() {
		productDTO1 = new ProductDTO();
		productDTO1.setCategoryId(101);
		productDTO1.setCategoryName("Mobile");

		productDTO2 = new ProductDTO();
		productDTO2.setCategoryId(102);
		productDTO2.setCategoryName("Laptop");

		category1 = new Category();
		category2 = new Category();

		productDTOLists = new ArrayList<>();
		productDTOLists.add(productDTO1);
		productDTOLists.add(productDTO2);
	}

	@Test
	void addNewCategory() {
		category1.setCategoryName(productDTO1.getCategoryName().toUpperCase());
		when(categoryRepo.save(category1)).thenReturn(category1);
		assertEquals(Constant.NEW_CATEGORY_ADDED, categoryServiceImpl.addNewCategory(productDTO1));
	}

	@Test
	void addNewListsCategories() {
		List<Category> lists = new ArrayList<>();
		category1.setCategoryName(productDTO1.getCategoryName().toUpperCase());
		category2.setCategoryName(productDTO2.getCategoryName().toUpperCase());
		lists.add(category1);
		lists.add(category2);
		for (Category newCats : lists) {
			when(categoryRepo.save(newCats)).thenReturn(newCats);
		}
		assertEquals(Constant.NEW_LISTS_OF_CATEGORY_ADDED, categoryServiceImpl.addNewListsCategories(productDTOLists));
	}
}
