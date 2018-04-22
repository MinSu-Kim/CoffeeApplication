package kr.java.coffee;

import java.awt.EventQueue;

import kr.java.coffee.ui.CoffeeManager;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeeManager frame = new CoffeeManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}











