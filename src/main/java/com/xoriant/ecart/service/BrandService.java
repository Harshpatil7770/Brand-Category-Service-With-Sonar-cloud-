package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.model.Brand;

public interface BrandService {

	String addNewBrand(ProductDTO productDTO);

	String addNewListsBrands(List<ProductDTO> productDTO);

	String updateBrand(ProductDTO productDTO);

	String updateListsBrand(List<ProductDTO> productDTO);

	void deleteBrand(int brandId);

	Optional<Brand> findByBrandName(String brandName);

	Optional<Brand> findByBrandId(int brandId);

	List<Brand> findAllBrands();
}
