package com.xoriant.ecart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xoriant.ecart.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	Optional<Category> findByCategoryName(String categoryName);

	@Query(value = "select * from categories", nativeQuery = true)
	List<Category> findAllExistingCategories();

}
