package Util;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import Mymtgame.fin;
import static Mymtgame.fin.*;
import static Mymtgame.Main.*;
import static Util.ConversationUtil.*;

import static data.Imagedata.battleBgImg;
import static data.Imagedata.gameBgImg;

public class ShopUtil {
	public static boolean MagicianActive = false;
	public static boolean MarksManActive = false;
	public static boolean WariorActive = false;
	
	private static String[] choice = new String[4];

    // 商店事件
    public static void shop(int id) {
    	switch (id) {
        case 0: // 第 3 層轉職台
            choice = new String[]{"▶獲得遊俠之力 高攻低防", "▷獲得狂戰士之力 高防高血", "▷獲得法師之力 被動技能縮小術", "▷離開商店"};
            imgIco.setIcon(new ImageIcon(fin.imgSource.get(22)));
            break;
        case 1: // 第 5 層 神秘老人
            choice = new String[]{"▶提升一級（需要 30 金幣）", "▷增加攻擊12（需要 30 金幣） ", "▷增加防禦12（需要 30 金幣）", "▷離開商店"};
            imgIco.setIcon(new ImageIcon(fin.imgSource.get(26)));
            break;
       
    }

    Insets insets = dialogLPane.getInsets();
    imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);
    text.setBounds(100 + insets.left, 20 + insets.top, 550 - 50, 250);
    text.setText("請選擇一個: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3]);
    dialogBgImg.setBounds(0, 0, 550, 250);
    dialogLPane.setBounds(550, 230, 550, 250);
    dialogLPane.add(imgIco, 2, 0);
    dialogLPane.add(text, 3, 0);
    gamepane.add(dialogLPane);
    gamepane.repaint();

    frm.addKeyListener(new KeyListener() {
        int selection = 0;
        String message = "選擇一個: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (selection != 3 && e.getKeyCode() == e.VK_S) {
                choice[selection] = choice[selection].replaceAll("▶", "▷");
                selection = selection + 1;
                choice[selection] = choice[selection].replaceAll("▷", "▶");
                message = "選擇一個: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
                text.setText(message);
                frm.repaint();
            }
            if (selection != 0 && e.getKeyCode() == e.VK_W) {
                choice[selection] = choice[selection].replaceAll("▶", "▷");
                selection = selection - 1;
                choice[selection] = choice[selection].replaceAll("▷", "▶");
                message = "選擇一個: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
                text.setText(message);
                frm.repaint();
            }
                if (e.getKeyCode() == e.VK_SPACE) {
                    switch (id) {
                        case 0:     // 对应 3 楼的商店选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getExp() >= 65) //遊俠之力
                                    {
                                        playerBean_1.setExp(0);
                                        playerBean_1.setHp(playerBean_1.getHp() + +1500);
                                        playerBean_1.setAttack(playerBean_1.getAttack()+2000);
                                        playerBean_1.setDefend(playerBean_1.getDefend()+300);
                                        
                    		            try {
                    		            	gameBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/GameBg3.png"));
											battleBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/BattleBg5.png"));
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}//OR2
                                        MarksManActive = true;
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getExp() >= 65)//狂戰士之力 
                                    {
                                    	playerBean_1.setExp(0);
                                        playerBean_1.setHp(playerBean_1.getHp() + +3000);
                                        playerBean_1.setAttack(playerBean_1.getAttack()+600);
                                        playerBean_1.setDefend(playerBean_1.getDefend()+600);
                                        try {
											gameBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/GameBg1.png"));
											battleBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/BattleBg4.png"));//OR2
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
                    		            
                                        WariorActive = true;
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getExp() >= 65)//法師之力
                                    {
                                        playerBean_1.setExp(0);
                                        MagicianActive = true;
                                        try {
											gameBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/GameBg2.png"));
											battleBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/BattleBg3.png"));//OR2
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
                    		            
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamepane.remove(dialogLPane);
                                    frm.repaint();
                                    inConversation = false;
                                    frm.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 1:     // 对应 5 楼的老人选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getMoney() >= 30) {
                                        playerBean_1.setLevel(playerBean_1.getLevel() + 1);
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 30);
                                        playerBean_1.setHp(playerBean_1.getHp() + 120);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 3);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 3);
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getMoney() >= 30) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 30);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 12);
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getMoney() >= 30) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 30);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 12);
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamepane.remove(dialogLPane);
                                    frm.repaint();
                                    inConversation = false;
                                    frm.removeKeyListener(this);
                                    break;
                            }
                            break;
                       
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
