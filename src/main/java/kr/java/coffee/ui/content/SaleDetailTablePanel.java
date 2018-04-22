package kr.java.coffee.ui.content;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;

import kr.java.coffee.service.SaleService;
import kr.java.swinglibrary.component.AbstractTablePanel;
import kr.java.swinglibrary.component.ToArray;

@SuppressWarnings("serial")
public class SaleDetailTablePanel extends AbstractTablePanel {
	
	public SaleDetailTablePanel(boolean isSalePrice) {
		super(isSalePrice?"판매금액 순위":"마진액 순위");
		
	}

	@Override
	protected void setAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 8);
		tableCellAlignment(SwingConstants.RIGHT, 3, 4, 5, 6, 7, 9);
		tableSetWidth(100, 100, 150, 100, 100, 150, 150,150, 100, 150);	
	}

	@Override
	protected Object[][] toArray(List<? extends ToArray> items) {
		Object[][] results = new Object[items.size()+1][];
		for (int i = 0; i < items.size(); i++) {
			results[i] = items.get(i).toArray();
		}
		results[items.size()] = getTotal();
		return results;
	}

	private Object[] getTotal() {
		List<Map<String,Object>> res = SaleService.getInstance().getTotal();
		Map<String, Object> maps = res.get(0);

		String[] total = new String[10];
		total[0]="합계";
		Arrays.fill(total, 1, 4, "");
		total[5]=String.format("%,.0f", maps.get("supply_price")); // maps.get("supply_price") double 타입
		total[6]=String.format("%,.0f", maps.get("addtax_price"));
		total[7]=String.format("%,.0f", maps.get("sale_price"));
		total[8]="";
		total[9]=String.format("%,.0f", maps.get("margin_price"));
		return total;
	}

	@Override
	public void setColumnNames() {
		colNames = new String[] {"순위", "제품코드", "제품명", "제품단가", "판매수량", "공급가액", "부가세액", "판매금액", "마진율", "마진액"};		
	}

}
