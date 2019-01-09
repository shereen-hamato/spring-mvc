package com.shereen.catalog.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CatalogServiceTest {

	@Autowired
	CatalogService cataLogService;

	@Test
	public void testGetAllCatalogs() {
		assertEquals(cataLogService.getAllCatalogs().size(), 10);
	}

}
