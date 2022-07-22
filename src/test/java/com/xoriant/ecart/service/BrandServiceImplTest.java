package com.xoriant.ecart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xoriant.ecart.configuration.Constant;
import com.xoriant.ecart.dao.BrandRepo;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.exception.ElementNotFound;
import com.xoriant.ecart.exception.InputUserException;
import com.xoriant.ecart.model.Brand;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

	@Mock
	private BrandRepo brandRepo;

	@InjectMocks
	private BrandServiceImpl brandServiceImpl;

	private Brand brand1;
	private Brand brand2;

	private ProductDTO productDTO1;
	private ProductDTO productDTO2;

	private List<ProductDTO> productLists;

	@BeforeEach
	void setUp() {
		productLists = new ArrayList<>();
		productDTO1 = new ProductDTO();
		productDTO1.setBrandId(101);
		productDTO1.setBrandName("Oppo");
		productLists.add(productDTO1);

		productDTO2 = new ProductDTO();
		productDTO2.setBrandId(102);
		productDTO2.setBrandName("Vivo");
		productLists.add(productDTO2);

		brand1 = new Brand();
		brand2 = new Brand();

	}

	@Test
	void addNewBrand() {
		brand1.setBrandName(productDTO1.getBrandName().toUpperCase());
		when(brandRepo.save(brand1)).thenReturn(brand1);
		assertEquals(Constant.NEW_BRAND_ADDED, brandServiceImpl.addNewBrand(productDTO1));
	}

	@Test
	void addNewBrand_throwException_if_brandNameIsEmpty() {
		doThrow(InputUserException.class).when(brandRepo).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandRepo.save(brand1);
		});
	}

	@Test
	void addNewBrand_throwException_if_brandNameIsBlank() {
		brand1.setBrandName(" ");
		doThrow(InputUserException.class).when(brandRepo).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandRepo.save(brand1);
		});
	}

	@Test
	void addNewListsBrands() {
		List<Brand> brandLists = new ArrayList<>();
		brand1.setBrandName(productDTO1.getBrandName().toUpperCase());
		brandLists.add(brand1);
		brand2.setBrandName(productDTO2.getBrandName().toUpperCase());
		brandLists.add(brand2);

		for (Brand newProduct : brandLists) {
			when(brandRepo.save(newProduct)).thenReturn(newProduct);
		}
		assertEquals(Constant.NEW_LISTS_OF_BRAND_ADDED, brandServiceImpl.addNewListsBrands(productLists));
	}

	@Test
	void addNewListsBrands_throwsException_if_brandNameIsNull() {
		List<Brand> brandLists = new ArrayList<>();
		brandLists.add(brand1);
		for (Brand newBrand : brandLists) {
			System.out.println(newBrand.getBrandName());
			if (newBrand.getBrandName() == null) {
				doThrow(InputUserException.class).when(brandRepo).save(newBrand);
				assertThrows(InputUserException.class, () -> {
					brandRepo.save(newBrand);
				});
			}
		}
	}

	@Test
	void addNewListsBrands_throwsException_if_brandNameIsBlank() {
		List<Brand> brandLists = new ArrayList<>();
		brand1.setBrandName("  ");
		brandLists.add(brand1);
		for (Brand newBrand : brandLists) {
			System.out.println(newBrand.getBrandName());
			if (newBrand.getBrandName().isBlank()) {
				doThrow(InputUserException.class).when(brandRepo).save(newBrand);
				assertThrows(InputUserException.class, () -> {
					brandRepo.save(newBrand);
				});
			}
		}
	}

	@Test
	void updateBrand() {
		int brandId = 101;
		Optional<Brand> existingBrand = Optional.of(brand1);
		when(brandRepo.findById(brandId)).thenReturn(existingBrand);
		Optional<Brand> result = brandRepo.findById(brandId);
		assertNotNull(result);
		productDTO1.setBrandName("Samsung");
		brand1.setBrandName(productDTO1.getBrandName().toUpperCase());
		when(brandRepo.save(brand1)).thenReturn(brand1);
		assertEquals(Constant.UPDATE_EXISTING_BRAND, brandServiceImpl.updateBrand(productDTO1));
	}

	@Test
	void updateBrand_throwsException_if_brandNameIsEmpty() {
		int brandId = 101;
		Optional<Brand> existingBrand = Optional.of(brand1);
		when(brandRepo.findById(brandId)).thenReturn(existingBrand);
		Optional<Brand> result = brandRepo.findById(brandId);
		assertNotNull(result);
		productDTO1.setBrandName(null);
		brand1.setBrandName(productDTO1.getBrandName());
		doThrow(InputUserException.class).when(brandRepo).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandRepo.save(brand1);

		});
	}

	@Test
	void updateBrand_throwsException_if_brandNameIsBlank() {
		int brandId = 101;
		Optional<Brand> existingBrand = Optional.of(brand1);
		when(brandRepo.findById(brandId)).thenReturn(existingBrand);
		Optional<Brand> result = brandRepo.findById(brandId);
		assertNotNull(result);
		productDTO1.setBrandName("  ");
		brand1.setBrandName(productDTO1.getBrandName());
		doThrow(InputUserException.class).when(brandRepo).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandRepo.save(brand1);

		});
	}

	@Test
	void updateListsBrand() {
		List<Brand> brandLists = new ArrayList<>();

		int brandId = 101;
		Optional<Brand> existingBrand = Optional.of(brand1);
		when(brandRepo.findById(brandId)).thenReturn(existingBrand);
		Optional<Brand> result = brandRepo.findById(brandId);
		assertNotNull(result);
		productDTO1.setBrandName("Samsung");
		brand1.setBrandName(productDTO1.getBrandName().toUpperCase());
		brandLists.add(brand1);

		int brandId1 = 102;
		Optional<Brand> existingBrand1 = Optional.of(brand2);
		when(brandRepo.findById(brandId1)).thenReturn(existingBrand1);
		Optional<Brand> result1 = brandRepo.findById(brandId);
		assertNotNull(result1);
		productDTO2.setBrandName("I Phone");
		brand2.setBrandName(productDTO2.getBrandName().toUpperCase());
		brandLists.add(brand2);

		for (Brand newbrand : brandLists) {
			when(brandRepo.save(newbrand)).thenReturn(newbrand);
		}
		assertEquals(Constant.UPDATE_LISTS_OF_EXISTING_BRANDS, brandServiceImpl.updateListsBrand(productLists));

	}

	@Test
	void updateListsBrand_throwsException_if_brandNameIsNull() {
		doThrow(InputUserException.class).when(brandRepo).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandRepo.save(brand1);
		});
	}

	@Test
	void updateListsBrand_throwsException_if_brandNameIsBlank() {
		brand1.setBrandName("  ");
		doThrow(InputUserException.class).when(brandRepo).save(brand1);
		assertThrows(InputUserException.class, () -> {
			brandRepo.save(brand1);
		});
	}

	@Test
	void deleteBrand() {
		int brandId = 101;
		brandRepo.deleteById(brandId);
		verify(brandRepo, times(1)).deleteById(101);
	}

	void deleteBrand_throwsException_if_brandIdIsNotFound() {
		int brandId = 501;
		doThrow(ElementNotFound.class).when(brandRepo.findById(brandId));
		assertThrows(ElementNotFound.class, () -> {
			brandRepo.findById(brandId);
		});
	}

	@Test
	void findAllBrands() {
		List<Brand> brandLists = new ArrayList<Brand>();
		brand1.setBrandName(productDTO1.getBrandName());
		brand2.setBrandName(productDTO2.getBrandName());
		brandLists.add(brand1);
		brandLists.add(brand2);
		when(brandRepo.findAll()).thenReturn(brandLists);
		assertEquals(2, brandRepo.findAll().size());
	}

	void findAllBrands_throwsException_ifBrandListsNotPresent() {
		List<Brand> brandLists = new ArrayList<Brand>();
		doThrow(ElementNotFound.class).when(brandRepo).findAll();
		assertThrows(ElementNotFound.class, () -> {
			brandRepo.findAll();
		});
	}
}
