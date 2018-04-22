package kr.java.coffee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.java.coffee.dto.Product;
import kr.java.coffee.dto.Sale;
import kr.java.coffee.service.SaleService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SaleDaoTest {
	private static SaleService saleService;

	@Before
	public void setUp() throws Exception {
		testAselectSaleByAll();
	}

	@After
	public void tearDown() throws Exception {
		testAselectSaleByAll();
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		saleService = SaleService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		saleService = null;
	}

	@Test
	public void testAselectSaleByAll() {
		List<Sale> saleLists = saleService.selectSaleByAll();
		Assert.assertNotEquals(0, saleLists.size());
	}

	@Test
	public void testBselectSaleByNo() {
		Sale sale = saleService.selectSaleByNo(new Sale(1));
		Assert.assertNotEquals(0, sale.getSaleCnt());
	}

	@Test
	public void testCinsertProduct() {
		Sale newSale = new Sale(5, new Product("A001"), 10, 10);
		int res = saleService.insertSale(newSale);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testDupdateProduct() {
		Sale newSale = new Sale(5, new Product("A001"), 1000, 20);
		int res = saleService.updateSale(newSale);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testEdeleteProduct() {
		Sale newSale = new Sale(5);
		int res = saleService.deleteSale(newSale);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void testFcallSaleDetail() {
		Map<String, Boolean> map = new HashMap<>();
		map.put("isSalePrice", true);
		List<Sale> list = saleService.callSaleDetail(map);
		Assert.assertNotEquals(0, list.size());
	}
	
	@Test
	public void testGcallgetTotal() {
		List<Map<String, Object>> maps = saleService.getTotal();
		Assert.assertNotEquals(0, maps.get(0).size());
	}
}
