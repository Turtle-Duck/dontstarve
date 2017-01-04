package main;

import javax.swing.JPanel;

public class UpdateThread extends Thread{
	
	JPanel mypan;

	public UpdateThread(JPanel panel) {
		mypan = panel;
	}

	public void run() {
		while(true){
			mypan.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
