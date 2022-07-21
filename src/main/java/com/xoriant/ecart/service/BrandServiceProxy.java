package com.xoriant.ecart.service;

import java.util.Optional;

import com.xoriant.ecart.model.Brand;
public interface BrandServiceProxy {

	Optional<Brand> findByBrandId(int brandId);
}
