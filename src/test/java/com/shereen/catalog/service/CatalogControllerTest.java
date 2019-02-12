package com.shereen.catalog.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.shereen.catalog.controller.CatalogController;
import com.shereen.catalog.model.Catalog;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatalogControllerTest {

	@Autowired
	CatalogController catalogController;
	
	@Test
	public void testGetCatalog() {
		List<Catalog> outcome =  catalogController.getAllCatalogJson(10);
		assertThat(outcome.size(), is(equalTo(10)));
	}

	

}
