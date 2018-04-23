package kr.java.coffee.dto;

import kr.java.swinglibrary.component.ToArray;

public class Product implements ToArray{
	private String code;
	private String name;
	private int price;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String code) {
		this.code = code;
	}

	public Product(String code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%s(%s - %s)", code, name, price);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public Object[] toArray() {
		return new Object[] {code, name, String.format("%,d", price)};
	}
	
}
