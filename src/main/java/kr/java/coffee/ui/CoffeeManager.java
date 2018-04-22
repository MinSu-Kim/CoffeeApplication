package kr.java.coffee.ui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.java.coffee.service.ProductService;
import kr.java.coffee.service.SaleService;
import kr.java.coffee.ui.content.ProductTablePanel;
import kr.java.coffee.ui.content.SaleTablePanel;
import kr.java.swinglibrary.component.AbstractTablePanel;

@SuppressWarnings("serial")
public class CoffeeManager extends JFrame{
	private JPanel contentPane;
	
	public CoffeeManager() {
		initComponent();
	}

	private void initComponent() {
		setTitle("프랜차이즈 커피전문점 관리 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel topTable = new JPanel();
		contentPane.add(topTable);
		topTable.setLayout(new GridLayout(0, 2, 0, 0));
		
		AbstractTablePanel pdtTable = new ProductTablePanel();
		pdtTable.loadData(ProductService.getInstance().selectProductAll());
		topTable.add(pdtTable);
		
		AbstractTablePanel saleTable = new SaleTablePanel();
		saleTable.loadData(SaleService.getInstance().selectSaleByAll());
		topTable.add(saleTable);
		
		JPanel salePriceRankTable = new JPanel();
		contentPane.add(salePriceRankTable);
		
		JPanel marginPriceRankTable = new JPanel();
		contentPane.add(marginPriceRankTable);
	}

}
