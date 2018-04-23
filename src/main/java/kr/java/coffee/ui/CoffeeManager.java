package kr.java.coffee.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import kr.java.coffee.dto.Product;
import kr.java.coffee.service.ProductService;
import kr.java.coffee.service.SaleService;
import kr.java.coffee.ui.content.ProductTablePanel;
import kr.java.coffee.ui.content.SaleDetailTablePanel;
import kr.java.coffee.ui.content.SaleTablePanel;
import kr.java.swinglibrary.component.AbstractTablePanel;

@SuppressWarnings("serial")
public class CoffeeManager extends JFrame implements ActionListener {
	private JPanel contentPane;
	private AbstractTablePanel pdtTable;

	public CoffeeManager() {
		initComponent();
	}

	private void initComponent() {
		setTitle("프랜차이즈 커피전문점 관리 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		createMenu();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel topTable = new JPanel();
		contentPane.add(topTable);
		topTable.setLayout(new GridLayout(0, 2, 0, 0));

		pdtTable = new ProductTablePanel();
		pdtTable.loadData(ProductService.getInstance().selectProductAll());
		pdtTable.setPopupMenu(createProductPopUpMenu());
		topTable.add(pdtTable);

		AbstractTablePanel saleTable = new SaleTablePanel();
		saleTable.loadData(SaleService.getInstance().selectSaleByAll());
		topTable.add(saleTable);

		AbstractTablePanel salePriceRankTable = new SaleDetailTablePanel(true);
		contentPane.add(salePriceRankTable);

		Map<String, Boolean> map = new HashMap<>();
		map.put("isSalePrice", true);
		salePriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));

		AbstractTablePanel marginPriceRankTable = new SaleDetailTablePanel(false);
		contentPane.add(marginPriceRankTable);
		map.put("isSalePrice", false);
		marginPriceRankTable.loadData(SaleService.getInstance().callSaleDetail(map));
	}

	private JPopupMenu createProductPopUpMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem delItem = new JMenuItem("삭제");
		delItem.addActionListener(popUpMenuListener);
		popMenu.add(delItem);

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popUpMenuListener);
		popMenu.add(updateItem);
		return popMenu;
	}

	ActionListener popUpMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("삭제")) {
				try{
					ProductService.getInstance().deleteProduct(new Product((String) pdtTable.getSelectedNo()));
					pdtTable.removeRow();
				}catch(RuntimeException e1) {
					JOptionPane.showMessageDialog(null, "해당 제품이 판매현황에 존재합니다.");
				}
			}
			if (e.getActionCommand().equals("수정")) {
				Product pdt = ProductService.getInstance().selectProductByNo(new Product((String) pdtTable.getSelectedNo()));
				RegProductUI regProductUi = RegProductUI.getInstance();
				regProductUi.setTitle("상품 수정");
				regProductUi.setProduct(pdt);
				regProductUi.enableCodeTf(false);
				regProductUi.setTable(pdtTable);
				regProductUi.setVisible(true);
			}
		}
	};
	
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("프로그램");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("종료");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);

		JMenu mnProduct = new JMenu("상품");
		menuBar.add(mnProduct);

		JMenuItem mntmPdtReg = new JMenuItem("상품 등록");
		mntmPdtReg.addActionListener(this);
		mnProduct.add(mntmPdtReg);

		JMenu mnSale = new JMenu("판매현황");
		menuBar.add(mnSale);

		JMenuItem mntmSaleReg = new JMenuItem("판매현황 등록");
		mntmSaleReg.addActionListener(this);
		mnSale.add(mntmSaleReg);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("종료")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("상품 등록")) {
			RegProductUI regProductUi = RegProductUI.getInstance();
			regProductUi.clearValue();
			regProductUi.setTable(pdtTable);
			regProductUi.setVisible(true);
		}
		if (e.getActionCommand().equals("판매현황 등록")) {
		}
	}
}
