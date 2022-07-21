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
import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.service.BrandService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/brands")
@Slf4j
public class BrandResource {

	@Autowired
	private BrandService brandService;

	@PostMapping("/save")
	public ResponseEntity<String> addNewBrand(@RequestBody ProductDTO productDTO) {
		String result = brandService.addNewBrand(productDTO);
		log.info("addNewBrand() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/saveAll")
	public ResponseEntity<String> addNewListsBrands(@RequestBody List<ProductDTO> productDTO) {
		String result = brandService.addNewListsBrands(productDTO);
		log.info("addNewListsBrands() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateBrand(@RequestBody ProductDTO productDTO) {
		String result = brandService.updateBrand(productDTO);
		log.info("updateBrand() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/updateAll")
	public ResponseEntity<String> updateListsBrand(@RequestBody List<ProductDTO> productDTO) {
		String result = brandService.updateListsBrand(productDTO);
		log.info("updateListsBrand() called");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{brandId}")
	public void deleteBrand(@PathVariable int brandId) {
		log.info("deleteBrand() called");
		brandService.deleteBrand(brandId);
	}

	@GetMapping("/find/{brandName}")
	public Optional<Brand> findByBrandName(@PathVariable String brandName) {
		log.info("findByBrandName() called");
		return brandService.findByBrandName(brandName);
	}

	@GetMapping("/find-brand/{brandId}")
	public Optional<Brand> findByBrandId(@PathVariable int brandId) {
		log.info("findByBrandId() called");
		return brandService.findByBrandId(brandId);
	}

	// Not working
	@GetMapping("/findAll/brands")
	public List<Brand> findAllBrands() {
		log.info("findAllBrands() called");
		return brandService.findAllBrands();
	}
}
