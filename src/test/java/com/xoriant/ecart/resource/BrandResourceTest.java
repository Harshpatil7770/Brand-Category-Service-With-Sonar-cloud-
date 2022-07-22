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
import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.service.BrandService;
import com.xoriant.ecart.service.CategoryService;

@ExtendWith(MockitoExtension.class)
class BrandResourceTest {

	@Mock
	private BrandService brandService;

	@InjectMocks
	private BrandResource brandResource;

	private Brand brand;

	private ProductDTO productDTO;

	private ProductDTO productDTO1;

	private List<ProductDTO> productDTOLists;

	@BeforeEach
	void setUp() {

		productDTOLists = new ArrayList<>();
		brand = new Brand();
		productDTO = new ProductDTO();
		productDTO.setBrandId(101);
		productDTO.setBrandName("Oppo");
		productDTOLists.add(productDTO);

		productDTO1 = new ProductDTO();
		productDTO1.setBrandId(102);
		productDTO1.setBrandName("Vivo");
		productDTOLists.add(productDTO1);
	}

	@Test
	void addNewBrand() {
		when(brandService.addNewBrand(productDTO)).thenReturn(Constant.NEW_BRAND_ADDED);
		String response = brandService.addNewBrand(productDTO);
		assertEquals(new ResponseEntity<>(response, HttpStatus.OK), brandResource.addNewBrand(productDTO));
	}

	@Test
	void addNewListsBrands() {
		when(brandService.addNewListsBrands(productDTOLists)).thenReturn(Constant.NEW_LISTS_OF_BRAND_ADDED);
		String response = brandService.addNewListsBrands(productDTOLists);
		assertEquals(new ResponseEntity<>(response, HttpStatus.OK), brandResource.addNewListsBrands(productDTOLists));
	}

	@Test
	void updateBrand() {
		when(brandService.updateBrand(productDTO)).thenReturn(Constant.UPDATE_EXISTING_BRAND);
		String response = brandService.updateBrand(productDTO);
		assertEquals(new ResponseEntity<>(response, HttpStatus.OK), brandResource.updateBrand(productDTO));
	}

	@Test
	void updateListsBrand() {
		when(brandService.updateListsBrand(productDTOLists)).thenReturn(Constant.UPDATE_LISTS_OF_EXISTING_BRANDS);
		String result = brandService.updateListsBrand(productDTOLists);
		assertEquals(new ResponseEntity<>(result, HttpStatus.OK), brandResource.updateListsBrand(productDTOLists));
	}

	@Test
	void deleteBrand() {
		int brandId = 101;
		brandService.deleteBrand(brandId);
		verify(brandService, times(1)).deleteBrand(brandId);
	}

	@Test
	void findByBrandName() {
		String brandName = "Oppo";
		brand.setBrandName(productDTO.getBrandName());
		Optional<Brand> existingBrand = Optional.of(brand);
		when(brandService.findByBrandName(brandName)).thenReturn(existingBrand);
		assertEquals(existingBrand, brandResource.findByBrandName(brandName));
	}

	@Test
	void findByBrandId() {
		int brandId = 101;
		brand.setBrandName(productDTO.getBrandName());
		Optional<Brand> existingBrand = Optional.of(brand);
		when(brandService.findByBrandId(brandId)).thenReturn(existingBrand);
		assertEquals(existingBrand, brandResource.findByBrandId(brandId));
	}

	@Test
	void findAllBrands() {
		List<Brand> brandLists = new ArrayList<>();
		brand.setBrandName(productDTO.getBrandName());
		brandLists.add(brand);
		when(brandService.findAllBrands()).thenReturn(brandLists);
		assertEquals(brandLists, brandResource.findAllBrands());
	}

}
