
package diehard;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class molecrush extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	Timer timer = new Timer(1000 , this);
	JButton[] button = new JButton[9];
	JButton[] bt = {new JButton("スタート"),new JButton("ストップ")};
	JLabel label1;
	JPanel p = new JPanel();
	int i = 0, a = 0, x =0, s=0, win=0;
	ArrayList<Integer> number = new ArrayList<Integer>();
	
	public molecrush(String title) {
		super(title);
		
		timer.setActionCommand("timer");
		
		setBounds(400, 200, 1380, 1500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label1 = new JLabel("もぐらを叩いてください",JLabel.CENTER);
		label1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 56));
		add("North",label1);
		
		for(i=0;i<9;i++){
			button[i] = new JButton(new ImageIcon("shibafu.png"));
			button[i].addActionListener(this);
			p.add(button[i]);}
		add("Center",p);
		
		for (int i = 0; i < 2;i++) {
			bt[i].addActionListener(this);
			bt[i].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 56));
			p.add(bt[i]);
		}
		add("Center",p);
		
		bt[0].setEnabled(true);
		bt[1].setEnabled(false);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new molecrush("じゃんけんゲーム");
	}
	
	public void actionPerformed(ActionEvent e) {
		for(i=0;i<9;i++){
			button[i].setIcon(new ImageIcon("shibafu.png"));
		}
		String cmd = e.getActionCommand();
		
		int x = new Random().nextInt(9);
		
		if(cmd.equals("timer")) {
			
			label1.setText("あと " + (30-s) + " 秒    " + win + "パンチ！");
			s += 1;
			button[x].setIcon(new ImageIcon("mogura.png"));	
		}
		
		number.add(x);
		
		//System.out.println(a);

		if(e.getSource() == bt[0] && !timer.isRunning()) {
			timer.start();
			bt[0].setEnabled(false);
			bt[1].setEnabled(true);
		}
		
		else if(e.getSource() != button && !timer.isRunning()) { // スタートボタンを押されていない時に芝生ボタンが押されたらそれを表示
			label1.setText("スタートボタンを押してね");
		}
		
		else if(e.getSource() == bt[1] && timer.isRunning()||s >= 31) { //ストップボタンが押されるか31秒経つとゲームを終了
			timer.stop();
			for(i=0;i<9;i++){
				button[i].setIcon(new ImageIcon("shibafu.png"));
			}
			bt[0].setEnabled(true);
			bt[1].setEnabled(false);
			label1.setText(win + " 匹のモグラを叩きましたね！");
			s = 0;
			win = 0;
		}
		
		else if(e.getSource() == button[number.get(a-1)]) { //モグラが表示されたボタンが押されると、それをカウント
			win += 1;
		}
		
		a += 1;
	}
}

//https://www.javadrive.jp/tutorial/jlabel/index3.html
//https://www.javadrive.jp/tutorial/jbutton/index4.html
//https://www.irasutoya.com/2015/07/blog-post_732.html
//https://www.irasutoya.com/2013/02/blog-post_9399.html