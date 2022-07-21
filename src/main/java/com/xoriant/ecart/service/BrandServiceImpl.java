package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.configuration.Constant;
import com.xoriant.ecart.dao.BrandRepo;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.exception.ElementNotFound;
import com.xoriant.ecart.exception.InputUserException;
import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.model.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepo brandRepo;

	private Brand brand;


	@Override
	public  String addNewBrand(ProductDTO productDTO) {
		if (productDTO.getBrandName().isBlank() || productDTO.getBrandName().isEmpty()) {
			throw new InputUserException();
		}
		brand = new Brand();
		brand.setBrandName(productDTO.getBrandName().toUpperCase());
		brandRepo.save(brand);
		log.info("addNewBrand() called");
		return Constant.NEW_BRAND_ADDED;
	}

	@Override
	public String addNewListsBrands(List<ProductDTO> productDTO) {
		for (ProductDTO newProduct : productDTO) {
			if (newProduct.getBrandName().isBlank() || newProduct.getBrandName().isEmpty()) {
				throw new InputUserException();
			}
		}

		for (ProductDTO newProduct : productDTO) {
			brand = new Brand();
			brand.setBrandName(newProduct.getBrandName().toUpperCase());
			brandRepo.save(brand);
		}
		log.info("addNewListsBrands() called");
		return Constant.NEW_LISTS_OF_BRAND_ADDED;
	}

	@Override
	public String updateBrand(ProductDTO productDTO) {
		if (productDTO.getBrandName().isBlank() || productDTO.getBrandName().isEmpty()) {
			throw new InputUserException();
		}
		Optional<Brand> existingBrands = brandRepo.findById(productDTO.getBrandId());
		if (!existingBrands.isPresent()) {
			throw new ElementNotFound();
		}
		Brand updateBrand = brandRepo.findById(productDTO.getBrandId()).orElse(null);
		updateBrand.setBrandId(productDTO.getBrandId());
		updateBrand.setBrandName(productDTO.getBrandName().toUpperCase());
		brandRepo.save(updateBrand);
		log.info("updateBrand() called");
		return Constant.UPDATE_EXISTING_BRAND;
	}

	@Override
	public String updateListsBrand(List<ProductDTO> productDTO) {
		for (ProductDTO newProduct : productDTO) {
			Optional<Brand> existingBrands = brandRepo.findById(newProduct.getBrandId());
			if (!existingBrands.isPresent()) {
				throw new ElementNotFound();
			}
			if (newProduct.getBrandName().isBlank() || newProduct.getBrandName().isEmpty()) {
				throw new InputUserException();
			}
		}
		for (ProductDTO newProduct : productDTO) {
			Brand updateBrand = brandRepo.findById(newProduct.getBrandId()).orElse(null);
			updateBrand.setBrandId(newProduct.getBrandId());
			updateBrand.setBrandName(newProduct.getBrandName().toUpperCase());
			brandRepo.save(updateBrand);
		}
		log.info("updateListsBrand() called");
		return Constant.UPDATE_LISTS_OF_EXISTING_BRANDS;
	}

	@Override
	public void deleteBrand(int brandId) {
		Optional<Brand> existingBrands = brandRepo.findById(brandId);
		if (!existingBrands.isPresent()) {
			throw new ElementNotFound();
		}
		log.info("deleteBrand() called");
		brandRepo.deleteById(brandId);
	}

	@Override
	public Optional<Brand> findByBrandName(String brandName) {
		Optional<Brand> existingBrands = brandRepo.findByBrandName(brandName);
		if (!existingBrands.isPresent()) {
			throw new ElementNotFound();
		}
		log.info("findByBrandName() called");
		return existingBrands;
	}

	@Override
	public  Optional<Brand> findByBrandId(int brandId) {
		Optional<Brand> existingBrands = brandRepo.findById(brandId);
		if (!existingBrands.isPresent()) {
			throw new ElementNotFound();
		}
		return existingBrands;
	}

	@Override
	public List<Brand> findAllBrands() {
		List<Brand> brandLists = brandRepo.findAll();
		if (brandLists.isEmpty()) {
			throw new ElementNotFound();
		}
		return brandLists;
	}

}
