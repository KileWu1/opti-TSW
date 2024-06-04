package Mymtgame;

import static Mymtgame.fin.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;

import static Util.BattleUtil.battleLPane;
import static data.mapdata.*;
import javax.swing.*;
import static java.awt.event.KeyEvent.*;
import bean.ItemsBean;
import static Util.ForecastUtil.displayForecast;
import static Util.ForecastUtil.forecastLPane;
import static Util.MsgUtil.msgLPane;

public class Main  {
	public static final int GAME_PIX_72 = 72;
	 
	static fin pan1 = new fin();
	static fin pan2 = new fin();
	static fin pan3 = new fin();
	static fin pan4 = new fin();
	
	static JButton btn_start	= new JButton("開始");
	static JButton btn_rule 	= new JButton("規則");
	static JButton btn_tobattle = new JButton("戰鬥");
	
	static JButton btn_returnStartScreemR = new JButton("返回");
	static JButton btn_returnStartScreemM = new JButton("返回");
	static JButton btn_returnMainScreem = new JButton("返回");
	
	static JLabel imglab = new JLabel();
	static JLabel battle = new JLabel();
	static JLabel map = new JLabel();
	
	static JLabel lab_rule = new JLabel();
	static JLabel text_rule = new JLabel("<html><h1>遊戲規則</h1>"
			+ "<br>角色移動控制:請使用方向鍵 "
			+ "<br>遇到怪物則與剪刀石頭布決勝"
			+ "<br>使用對應顏色鑰匙來開啟牆壁 </html>"
			);
	
		public static	Clip bgm;
	
	public static int currentFloor = 0;     // 当前楼层
    public static int maxFloor = 0;         // 最大楼层
    
	public static void main(String[] args) 
	{
		try {
			bgm = AudioSystem.getClip();
			File music = new File("res\\Black Desert Remastered OST.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(music);
			bgm.open(ais);
			bgm.start();
			bgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
			catch (LineUnavailableException |UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		gamepane.add(msgLPane);
		gamepane.add(forecastLPane);
		gamepane.add(pan1);
		gamepane.add(battleLPane);
		gamepane.add(pan3);
		
		gamepane.setPreferredSize(new Dimension(GAME_PIX_72 * 18, GAME_PIX_72 * 13));
		Icon page1 = new ImageIcon("pictrue\\2.jpg");
		Icon page2 = new ImageIcon("pictrue\\3.jpg");
		
		//Background
		imglab.setBounds(0,0,GAME_PIX_72*18,GAME_PIX_72*13);
		imglab.setIcon(page1);
		battle.setBounds(0,0,GAME_PIX_72*18,GAME_PIX_72*13);
		battle.setIcon(page2);
		
		text_rule.setBounds(200,100,600,400);	
		text_rule.setForeground(Color.white);
		text_rule.setFont(new Font(" ",Font.BOLD,20));
		
		//pan1
		pan1.add(btn_start);
		pan1.add(btn_rule);
		btn_rule.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				pan1.setVisible(false);
				pan3.setVisible(true);
			}
			
		});
		
		pan1.setLayout(null);
		pan1.setBounds(0,0,GAME_PIX_72*18,GAME_PIX_72*13);
		pan1.add(imglab);
		
		
		//pan3Rule
		pan3.setLayout(null);
		btn_returnStartScreemR.setForeground(Color.WHITE);
		pan3.add(btn_returnStartScreemR);
		pan3.add(text_rule);// !!! 先add text label會最先在最上層 與下列交換 文字則會消失
		pan3.add(lab_rule);
		pan3.add(battle);
		pan3.setBounds(0,0,GAME_PIX_72*18,GAME_PIX_72*13);
		
		pan3.setVisible(false);
		
		
		
		frm.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.close();
				frm.dispose();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frm.setContentPane(gamepane);
		frm.setLayout(null);
		frm.setFocusable(true);
		frm.pack();
		frm.setVisible(true);
		
		frm.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				 if (!fin.inConversation)
				switch (e.getKeyCode()) {

                case VK_DOWN:   // 键盘 ↓
                    if (playerBean_1.getPosY() + 1 < 11 && playerBean_1.getPosY() + 1 >= 0) {
                        playerBean_1.setToward(1);
                        interaction(playerBean_1.getPosX(), playerBean_1.getPosY() + 1);
                       frm.repaint();
                    }
                	
                    break;
                case VK_RIGHT:  // 键盘 →
                    if (playerBean_1.getPosX() + 1 < 11 && playerBean_1.getPosX() + 1 >= 0) {
                        playerBean_1.setToward(2);
                        interaction(playerBean_1.getPosX() + 1, playerBean_1.getPosY());
                        frm.repaint();
                    }
                    break;
                case VK_UP:     // 键盘 ↑
                    if (playerBean_1.getPosY() - 1 < 11 && playerBean_1.getPosY() - 1 >= 0) {
                        playerBean_1.setToward(3);
                        interaction(playerBean_1.getPosX(), playerBean_1.getPosY() - 1);
                        frm.repaint();
                    }
                    break;
                case VK_LEFT:   // 键盘 ←
                    if (playerBean_1.getPosX() - 1 < 11 && playerBean_1.getPosX() - 1 >= 0) {
                        playerBean_1.setToward(0);
                        interaction(playerBean_1.getPosX() - 1, playerBean_1.getPosY());
                        frm.repaint();
                    }
                    break;
                case VK_L:      // 键盘 L
                    if (ItemsBean.isHasForecast) {
                        displayForecast();
                    }
                    break;
                	
				
				 }
				 else if (e.getKeyCode() == VK_L)//bug
	                {
	                    inConversation = false;
	                    forecastLPane.removeAll();
	                    forecastLPane.setVisible(false);
	                }
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			

			

			
			
			
			
			
		});
		
		
		
		
		//Button Setting
		btn_start.setBounds(950,500,200,100);
		btn_start.setBorder(null);
		btn_start.setContentAreaFilled(false);
		btn_start.setFont(new Font("標楷體",Font.BOLD,50));
		btn_start.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		btn_start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					pan1.setVisible(false);
					pan2.setVisible(true);
					
			}
			
			
		});
				
					
		btn_rule.setBounds(950,600,200,100);
		btn_rule.setBorder(null);
		btn_rule.setContentAreaFilled(false);
		btn_rule.setFont(new Font("標楷體",Font.BOLD,50));
		btn_rule.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				pan1.setVisible(false);
				pan3.setVisible(true);
			}
			
		});
			
		btn_tobattle.setBounds(550,500,200,100);
		btn_tobattle.setBorder(null);
		btn_tobattle.setContentAreaFilled(false);
		btn_tobattle.setFont(new Font("標楷體",Font.BOLD,50));
		btn_tobattle.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				pan2.setVisible(false);
				pan4.setVisible(true);
			}
		});
				
		btn_returnStartScreemR.setBounds(550,600,200,100);
		btn_returnStartScreemR.setBorder(null);
		btn_returnStartScreemR.setContentAreaFilled(false);
		btn_returnStartScreemR.setFont(new Font("標楷體",Font.BOLD,50));
		btn_returnStartScreemR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				pan3.setVisible(false);
				pan1.setVisible(true);
			}
			
		});
				
		btn_returnStartScreemM.setBounds(120,800,200,100);
		btn_returnStartScreemM.setBorder(null);
		btn_returnStartScreemM.setContentAreaFilled(false);
		btn_returnStartScreemM.setFont(new Font("標楷體",Font.BOLD,50));
		btn_returnStartScreemM.setForeground(Color.white);
		btn_returnStartScreemM.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				pan2.setVisible(false);
				pan1.setVisible(true);
			}
				
		});	
		
		btn_returnMainScreem.setBounds(550,600,200,100);
		btn_returnMainScreem.setBorder(null);
		btn_returnMainScreem.setContentAreaFilled(false);
		btn_returnMainScreem.setFont(new Font("標楷體",Font.BOLD,50));
		btn_returnMainScreem.setForeground(Color.RED);
		btn_returnMainScreem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				pan4.setVisible(false);
				pan2.setVisible(true);
			}
				
		});		
	}

	
}
