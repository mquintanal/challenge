package com.mql.nexu.cardealership;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mql.nexu.cardealership.models.Brand;
import com.mql.nexu.cardealership.services.BrandService;

@SpringBootTest
class CardealershipApplicationTests {

	@Autowired
    private BrandService brandService;

    @Test
    public void testCreateBrand() {
        Brand brand = brandService.createBrand("Test Brand");
        assertNotNull(brand.getId());
        assertEquals("Test Brand", brand.getName());
    }


	

}
