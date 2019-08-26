package kr.java.coffee.dto;

import kr.java.swinglibrary.component.ToArray;

public class Sale implements ToArray{
	private int no;
	private int price;
	private Product product; // 제품
	private int saleCnt; // 판매수량
	private int marginRate; // 마진율
	private SaleDetail saleDetail;
	
	public Sale() {
	}

	public Sale(int no) {
		this.no = no;
	}

	public Sale(int no, int price, Product product, int saleCnt, int marginRate) {
		this.no = no;
		this.price = price;
		this.product = product;
		this.saleCnt = saleCnt;
		this.marginRate = marginRate;
	}

	public Sale(int no, int price, Product product, int saleCnt, int marginRate, SaleDetail saleDetail) {
		this.no = no;
		this.price = price;
		this.product = product;
		this.saleCnt = saleCnt;
		this.marginRate = marginRate;
		this.saleDetail = saleDetail;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getSaleCnt() {
		return saleCnt;
	}

	public void setSaleCnt(int saleCnt) {
		this.saleCnt = saleCnt;
	}

	public int getMarginRate() {
		return marginRate;
	}

	public void setMarginRate(int marginRate) {
		this.marginRate = marginRate;
	}

	public SaleDetail getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}

	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Sale [%s, %s, %s, %s, %s, %s]", no, price, product, saleCnt, marginRate, saleDetail);
	}

	@Override
	public Object[] toArray() {
		if (saleDetail == null) {
			return new Object[] { no, product.getCode(), saleCnt, marginRate+"%" };
		}else {
			return new Object[] { saleDetail.getRank(), product.getCode(), product.getName(), 
					String.format("%,d", price), saleCnt, 
					String.format("%,d", saleDetail.getSupplyPrice()), 
					String.format("%,d", saleDetail.getAddTax()),
					String.format("%,d", saleDetail.getSalePrice()), 
					marginRate+"%", 
					String.format("%,d", saleDetail.getMarginPrice()) };
		}
	}

}
