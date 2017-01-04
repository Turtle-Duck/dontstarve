package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements normal {
	
	JPanel panel;
	class mypanel extends JPanel{
		public void paint(Graphics gra) {
			super.paint(gra);
			player.drawunder(gra);
			player.drawplayer(gra);
			player.drawobj(gra);
			player.drawstate(gra);
			//int count=0;
		}
	}
	class PanelListenner extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_UP:
				player.towards=1;
				player.up = true;
				break;
			case KeyEvent.VK_DOWN:
				player.towards=0;
				player.down = true;
				break;
			case KeyEvent.VK_LEFT:
				player.towards=2;
				player.left = true;
				break;
			case KeyEvent.VK_RIGHT:
				player.towards=3;
				player.right = true;
				break;
			case KeyEvent.VK_ESCAPE:
				player.reset();
				break;
			case KeyEvent.VK_S:
				player.save();
				break;
			case KeyEvent.VK_1:
				player.dosomething(1);
				break;
			case KeyEvent.VK_2:
				player.dosomething(2);
				break;
			case KeyEvent.VK_3:
				player.dosomething(3);
				break;
			case KeyEvent.VK_4:
				player.dosomething(4);
				break;
			case KeyEvent.VK_5:
				player.dosomething(5);
				break;
			case KeyEvent.VK_SPACE:
				player.dosomething(0);
				break;
			default:
				break;
			}
		}
		public void keyReleased(KeyEvent e){
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_UP:
				player.up = false;
				player.uptimes=0;
				break;
			case KeyEvent.VK_DOWN:
				player.down = false;
				player.downtimes=0;
				break;
			case KeyEvent.VK_LEFT:
				player.left = false;
				player.lefttimes=0;
				break;
			case KeyEvent.VK_RIGHT:
				player.right = false;
				player.righttimes=0;
				break;
			default:
				break;
			}
		}
	}
	public Frame(){
		this.setTitle(title);
		this.setSize(framex, framey);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(3);
		panel = setpanel();
		this.add(panel);
		this.setVisible(true);
		PanelListenner plis = new PanelListenner();
		this.addKeyListener(plis);
		player player1 = new player();
		player1.start();
		UpdateThread utthd = new UpdateThread(panel);
		utthd.start();
	}
	public JPanel setpanel(){
		JPanel panel = new mypanel();
		panel.setPreferredSize(new Dimension(gamex, gamey));
		panel.setLayout(null);
		panel.setBackground(Color.white);
		return panel;
	}
}
