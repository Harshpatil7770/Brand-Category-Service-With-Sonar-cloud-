package com.xoriant.ecart.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.model.Category;
import com.xoriant.ecart.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save")
	public ResponseEntity<String> addNewCategory(@RequestBody ProductDTO productDTO) {
		String result = categoryService.addNewCategory(productDTO);
		log.info("addNewCategory() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/saveAll")
	public ResponseEntity<String> addNewListsCategories(@RequestBody List<ProductDTO> productDTO) {
		String result = categoryService.addNewListsCategories(productDTO);
		log.info("addNewListsCategories() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateCategory(@RequestBody ProductDTO productDTO) {
		String result = categoryService.updateCategory(productDTO);
		log.info("updateCategory() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/updateAll")
	public ResponseEntity<String> updateListsCategory(@RequestBody List<ProductDTO> productDTO) {
		String result = categoryService.updateListsCategory(productDTO);
		log.info("updateListsCategory() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable int categoryId) {
		log.info("updateListsCategory() called");
		categoryService.deleteCategory(categoryId);
	}

	@GetMapping("/find/{categoryName}")
	public Optional<Category> findByCategoryName(@PathVariable String categoryName) {
		log.info("findByCategoryName() called");
		return categoryService.findByCategoryName(categoryName);
	}

	@GetMapping("/find-category/{categoryId}")
	public Optional<Category> findByCategoryId(@PathVariable int categoryId) {
		log.info("findByCategoryId() called");
		return categoryService.findByCategoryId(categoryId);
	}

	//Not working
	@GetMapping("/findAll/categories")
	public List<Category> findAllCategories() {
		log.info("findAllCategories() called");
		return categoryService.findAllCategories();
	}
}
