package kr.java.coffee.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.ibatis.exceptions.PersistenceException;

import kr.java.coffee.dto.Product;
import kr.java.coffee.dto.Sale;
import kr.java.coffee.service.ProductService;
import kr.java.coffee.service.SaleService;
import kr.java.swinglibrary.component.AbstractTablePanel;
import kr.java.swinglibrary.component.InputCombo;
import kr.java.swinglibrary.component.InputTextField;

@SuppressWarnings("serial")
public class RegSaleUI extends JFrame implements ActionListener {
	private static final RegSaleUI instance = new RegSaleUI();

	public static RegSaleUI getInstance() {
		return instance;
	}

	private JPanel contentPane;
	private AbstractTablePanel saleTable;
	private JButton btnAdd;
	private InputCombo<Product> pCode;
	private InputTextField pSaleCnt;
	private InputTextField pMarginRate;
	private InputTextField pNo;

	private List<Observer>observers = new ArrayList<Observer>() ;
	
	public void attach(Observer observer) { // 옵서버 즉 통보 대상을 추가함
		observers.add(observer) ;
	}	
	
	public void detach(Observer observer) { // 옵서버 즉 통보 대상을 제거함
		observers.remove(observer) ;
	}
	
	// 통보 대상 목록, 즉 observers의 각 옵서버에게 변경을 통보함
	public void notifyObservers() {
		for ( Observer o : observers ) o.update() ;
	} 

	private RegSaleUI() {
		initComponents();
	}

	private void initComponents() {
		setTitle("판매현황 등록");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 10));
		
		pNo = new InputTextField();
		pNo.setLblValue("번호");
		pNo.setEditableTf(false);
		contentPane.add(pNo);

		pCode = new InputCombo<>();
		pCode.setLblValue("제품코드");
		contentPane.add(pCode);

		pSaleCnt = new InputTextField();
		pSaleCnt.setLblValue("판매수량");
		contentPane.add(pSaleCnt);

		pMarginRate = new InputTextField();
		pMarginRate.setLblValue("마진율");
		contentPane.add(pMarginRate);

		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		pBtn.setLayout(new GridLayout(0, 2, 0, 0));

		btnAdd = new JButton("등록");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("등록")) {
			Sale sale = null;
			try {
				sale = getSale();
				SaleService.getInstance().insertSale(sale);
				saleTable.addRow(sale);
			} catch(PersistenceException e1) {
				JOptionPane.showMessageDialog(null, "제품 코드 중복");
				return;
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"e1"+e1 +"\n"+ e1.getMessage());
				return;
			}
			
		}
		if (e.getActionCommand().equals("수정")) {
			Sale sale = null;
			try {
				sale = getSale();
				SaleService.getInstance().updateSale(sale);
				saleTable.updateRow(sale);
				dispose();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
		}
		if (e.getActionCommand().equals("취소")) {
			if (btnAdd.getText().equals("수정")) {
				dispose();
			}
		}
		clearValue();
		notifyObservers();
	}

	public void getProductLoad() {
		List<Product> list = ProductService.getInstance().selectProductAll();
		pCode.setItems(list);
		getNextNo();
	}

	private void getNextNo() {
		int nextNo = SaleService.getInstance().selectSaleByAll().size() + 1;
		pNo.setTfValue(String.valueOf(nextNo));
	}
	
	public void clearValue() {
		pCode.setSelectedItem(null);
		pSaleCnt.setTfValue("");
		pMarginRate.setTfValue("");
		btnAdd.setText("등록");
		setTitle("판매현황 등록");
		getNextNo();
	}

	public void setSale(Sale sale) {
		pNo.setTfValue(String.valueOf(sale.getNo()));
		pCode.setSelectedItem(sale.getProduct());
		pSaleCnt.setTfValue(String.valueOf(sale.getSaleCnt()));
		pMarginRate.setTfValue(String.valueOf(sale.getMarginRate()));
		btnAdd.setText("수정");
	}

	private Sale getSale() throws Exception {
		isEmptyValue();
		isValidCheck();
		int no = Integer.parseInt(pNo.getTfValue());
		Product product = (Product) pCode.getSelectedItem();
		int saleCnt = Integer.parseInt(pSaleCnt.getTfValue());
		int marginRate = Integer.parseInt(pMarginRate.getTfValue());
		return new Sale(no, product, saleCnt, marginRate);
	}

	private void isValidCheck() throws Exception {
		pSaleCnt.isValidCheck("[0-9]{1,8}", "정수 8자리이하만 가능" );
		pMarginRate.isValidCheck("[0-9]{1,2}", "정수 2자리 이하만  가능(예 : 마진율이 20%이면 20 입력)");
	}

	private void isEmptyValue() throws Exception {
		pSaleCnt.isEmptyCheck();
		pMarginRate.isEmptyCheck();
	}

	public void setTable(AbstractTablePanel saleTable) {
		this.saleTable = saleTable;
	}

	public void enableCodeTf(boolean isEnable) {
		throw new UnsupportedOperationException(); 
	}
}
