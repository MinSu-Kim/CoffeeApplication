package kr.java.coffee.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.java.coffee.dto.Product;
import kr.java.coffee.service.ProductService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {
	private static ProductService productService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		productService = ProductService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		productService = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}

	@After
	public void tearDown() throws Exception {
		testAselectProductByAll();
	}
	
	@Test
	public void testAselectProductByAll() {
		List<Product> pdtLists = productService.selectProductAll();
		Assert.assertNotEquals(0, pdtLists.size());
	}

	@Test
	public void testBselectProductByNo() {
		Product product = productService.selectProductByNo(new Product("A001"));
		Product comparePdt = new Product("A001", "아메리카노");
		Assert.assertEquals(comparePdt.getName(), product.getName());
	}

	@Test
	public void testCinsertProduct() {
		Product newProduct = new Product("A005", "캬라멜마키아또");
		int res = productService.insertProduct(newProduct);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testDupdateProduct() {
		Product updateProduct = new Product("A005", "홍차");
		int res = productService.updateProduct(updateProduct);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testEdeleteProduct() {
		Product delProduct = new Product("A005");
		int res = productService.deleteProduct(delProduct);
		Assert.assertEquals(1, res);
	}
}
