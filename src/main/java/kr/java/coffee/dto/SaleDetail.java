package kr.java.coffee.dto;

public class SaleDetail {
	private int rank;
	private int supplyPrice;// 공급가액
	private int addTax; // 부가세액
	private int salePrice; // 판매금액
	private int marginPrice;// 마진액

	public SaleDetail() {
		// TODO Auto-generated constructor stub
	}

	public SaleDetail(int supplyPrice, int addTax, int salePrice, int marginPrice) {
		this.supplyPrice = supplyPrice;
		this.addTax = addTax;
		this.salePrice = salePrice;
		this.marginPrice = marginPrice;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	public int getAddTax() {
		return addTax;
	}

	public void setAddTax(int addTax) {
		this.addTax = addTax;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(int marginPrice) {
		this.marginPrice = marginPrice;
	}

	@Override
	public String toString() {
		return String.format("SaleDetail [%s, %s, %s, %s, %s]", rank, supplyPrice, addTax, salePrice, marginPrice);
	}
}
