package kr.java.coffee.ui.content;

import javax.swing.SwingConstants;

import kr.java.swinglibrary.component.AbstractTablePanel;

@SuppressWarnings("serial")
public class ProductTablePanel extends AbstractTablePanel {
	
	public ProductTablePanel() {
		super("제품");
	}

	@Override
	protected void setAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableSetWidth(100, 200, 100);			
	}

	@Override
	public void setColumnNames() {
		colNames = new String[] { "제품번호", "제품명", "제품 단가" };		
	}
	
}
