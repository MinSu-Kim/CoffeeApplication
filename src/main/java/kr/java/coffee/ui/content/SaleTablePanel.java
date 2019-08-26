package kr.java.coffee.ui.content;

import javax.swing.SwingConstants;

import kr.java.swinglibrary.component.AbstractTablePanel;

@SuppressWarnings("serial")
public class SaleTablePanel extends AbstractTablePanel {
	
	public SaleTablePanel() {
		super("판매현황");
	}

	@Override
	protected void setAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 3, 4);
		tableSetWidth(100, 200, 100, 100, 50);	
	}

	@Override
	public void setColumnNames() {
		colNames = new String[] { "번호", "제품코드", "제품 단가", "판매수량", "마진율" };		
	}

}
