package Mymtgame;
import java.awt.*;
import java.awt.event.*;
import static data.mapdata.*;

import javax.swing.*;
import static Mymtgame.Main.*;

import bean.PlayerBean;
import data.Imagedata;
import data.mapdata;
import data.MonsterData;
import bean.Conversation;
import bean.ItemsBean;
import Util.*;


import java.awt.image.*;
import java.util.HashMap;


public class fin extends JPanel

{	
	public static JFrame frm = new JFrame();
	public static HashMap<Integer, BufferedImage> imgSource = Imagedata.imagesMap0;
	
	public static PlayerBean playerBean_1 = new PlayerBean();
	public static JLabel timeLabel;
	public static int gameMin = 0;
	public static double gameSec = 0;
	public static fin gamepane = new fin();
	public static boolean inConversation = false;
	
	public fin() {
		setLayout(null);
        frm = new JFrame("魔塔");

        // 畫面跳動
        
        
        new Timer(500, new ActionListener() {
            boolean change = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (change) {
                    change = false;
                    imgSource = Imagedata.imagesMap0;
                } else {
                    change = true;
                    imgSource = Imagedata.imagesMap1;
                }
                repaint();
            }
        }).start();
	}
	
	
	
	
	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 主背景繪製
        g2.drawImage(Imagedata.gameBgImg, 0, 0, null);

        // 地圖數據繪製
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                int id = LvMap[currentFloor][x][y];
                BufferedImage a = imgSource.get(id);
                g2.drawImage(a, GAME_PIX_72 * y + GAME_PIX_72 * 6, GAME_PIX_72 * x + GAME_PIX_72, null);
            }
        }
        g2.drawImage(playerBean_1.draw(), (playerBean_1.getPosX() + 6) * GAME_PIX_72, (playerBean_1.getPosY() + 1) * GAME_PIX_72, null);
        g2.setFont(new Font("Arial", 0, 30));
        g2.setColor(Color.WHITE);
        g2.drawString(playerBean_1.getLevel() + "", 230, 150);
        g2.drawString(playerBean_1.getHp() + "", 220, 215);
        g2.drawString(playerBean_1.getAttack() + "", 220, 270);
        g2.drawString(playerBean_1.getDefend() + "", 220, 325);
        g2.drawString(playerBean_1.getMoney() + "", 220, 385);
        g2.drawString(playerBean_1.getExp() + "", 220, 440);

        // 繪製鑰匙數目
        g2.drawString(playerBean_1.getYkey() + "", 240, 530);
        g2.drawString(playerBean_1.getBkey() + "", 240, 605);
        g2.drawString(playerBean_1.getRkey() + "", 240, 680);

        // 繪製當前樓層
        g2.drawString(currentFloor + "", 200, 750);
        
    }
	 public static void interaction(int x, int y) {
		 int id = LvMap[currentFloor][y][x];
			 switch (id) {
				 case 0: // 玩家移動
					 playerBean_1.move(x, y);
					 break;
				 case 1: // 磚牆
					 break;
				 case 2: // 黃門
					 if (playerBean_1.getYkey() > 0) {
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.setYkey(playerBean_1.getYkey() - 1);
					 playerBean_1.move(x, y);
					 }
				 break;
				 case 3: // 藍門
					 if (playerBean_1.getBkey() > 0) {
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.setBkey(playerBean_1.getBkey() - 1);
					 playerBean_1.move(x, y);
					 }
				 break;
				 case 4: // 紅門
					 if (playerBean_1.getRkey() > 0) {
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.setRkey(playerBean_1.getRkey() - 1);
					 playerBean_1.move(x, y);
					 }
					 break;
				 case 5: // 石塊
					 break;
				 case 6: // [道具] 黃鑰匙
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 playerBean_1.setYkey(playerBean_1.getYkey() + 1);
					 MsgUtil.displayMessage("得到一把 黃鑰匙 ！");
				 break;
				 case 7: // [道具] 藍鑰匙
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 playerBean_1.setBkey(playerBean_1.getBkey() + 1);
					 MsgUtil.displayMessage("得到一把 藍鑰匙 ！");
					 break;
				 case 8: // [道具] 紅鑰匙
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 playerBean_1.setRkey(playerBean_1.getRkey() + 1);
					 MsgUtil.displayMessage("得到一把 紅鑰匙 ！");
					 break;
				 case 9: // [道具] 藍寶石
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 playerBean_1.setDefend(playerBean_1.getDefend() + 3);
					 MsgUtil.displayMessage("得到一個藍寶石 防禦力加 3 ！");
					 break;
				 case 10: // [道具] 紅寶石
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 playerBean_1.setAttack(playerBean_1.getAttack() + 3);
					 MsgUtil.displayMessage("得到一個紅寶石 攻擊力加 3 ！");
					 break;
				 case 11: // [道具] 紅藥水
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 playerBean_1.setHp(playerBean_1.getHp() + 120);
					 MsgUtil.displayMessage("得到一個小血瓶 生命加 120 ！");
					 break;
				 case 12: // [道具] 藍藥水
					 LvMap[currentFloor][y][x] = 0;
					 inConversation=true;
					 playerBean_1.move(x, y);
					 playerBean_1.setHp(playerBean_1.getHp() + 500);
					 MsgUtil.displayMessage("得到一個大血瓶 生命加 500 ！");
					 break;
				 case 13: // 上樓
					 currentFloor++;
					 maxFloor = Math.max(maxFloor, currentFloor);
					 playerBean_1.move(initPos[currentFloor][0], initPos[currentFloor][1]);
					 break;
				 case 14: // 下樓
					 currentFloor--;
					 playerBean_1.move(finPos[currentFloor][0], finPos[currentFloor][1]);
					 break;
				 case 15: // 不可通過的護欄
					 break;
				 case 19: // 火海
					 break;
				 case 20: // 星空
					 break;
				 case 22: // 商店
					 inConversation=true;
					 if (currentFloor == 2) {
					 ShopUtil.shop(0);
					 }
					 if (currentFloor == 3) {
						 ShopUtil.shop(1);
						 }
					 break;
				 case 24: // [對話] 仙子
					 inConversation=true;
					 new Conversation(id);
					 break;
				 case 26: // [對話] 老人
					 inConversation=true;
					 ShopUtil.shop(1);
					 break;
				 case 27: // [對話] 商人
					 inConversation=true;
					 ShopUtil.shop(2);
					 break;
				 case 28: // [對話] 公主
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 break;
				 case 34: // [寶物] 聖光徽
					 LvMap[currentFloor][y][x] = 0;
					 playerBean_1.move(x, y);
					 ItemsBean.isHasForecast = true;
					 MsgUtil.displayMessage("【聖光徽】 按 L 鍵使用 查看怪物的基本情況。");
					 break;
	            case 40:    // [怪物 monster]
	            case 41:    // [怪物 monster]
	            case 42:    // [怪物 monster]
	            case 43:    // [怪物 monster]
	            case 44:    // [怪物 monster]
	            case 45:    // [怪物 monster]
	            case 46:    // [怪物 monster]
	            case 47:    // [怪物 monster]
	            case 48:    // [怪物 monster]
	            case 49:    // [怪物 monster]
	            case 50:    // [怪物 monster]
	            case 51:    // [怪物 monster]
	            case 52:    // [怪物 monster]
	            case 53:    // [怪物 monster]
	            case 54:    // [怪物 monster]
	            case 55:    // [怪物 monster]
	            case 56:    // [怪物 monster]
	            case 57:    // [怪物 monster]
	            case 58:    // [怪物 monster]
	            case 59:    // [怪物 monster]
	            case 60:    // [怪物 monster]
	            case 61:    // [怪物 monster]
	            case 62:    // [怪物 monster]
	            case 63:    // [怪物 monster]
	            case 64:    // [怪物 monster]
	            case 65:    // [怪物 monster]
	            case 66:    // [怪物 monster]
	            case 67:    // [怪物 monster]
	            case 68:    // [怪物 monster]
	            case 69:    // [怪物 monster]
	            case 70:    // [怪物 monster]
	                if (ForecastUtil.forecast(MonsterData.monsterMap.get(id))==1
	                        ||ForecastUtil.forecast(MonsterData.monsterMap.get(id)) >= playerBean_1.getHp()) {
	                    return;
	                } else {
	                    new BattleUtil(id, x, y);
	                }
	                break;
	            case 71:    // [宝物] 铁剑
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                playerBean_1.setAttack(playerBean_1.getAttack() + 30);
	                MsgUtil.displayMessage("得到 铁剑 攻击加 30 ！");
	                break;
	            case 73:    // [宝物] 钢剑
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                playerBean_1.setAttack(playerBean_1.getAttack() + 50);
	                MsgUtil.displayMessage("得到 钢剑 攻击加 50 ！");
	                break;
	            /*case 75:    // [宝物] 圣光剑
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 120);
	                MsgUtil.displayMessage("得到 圣光剑 攻击加 120 ！");
	                break;*/
	            case 76:    // [宝物]
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                fin.playerBean_1.setDefend(fin.playerBean_1.getDefend() + 10);
	                MsgUtil.displayMessage("得到 铁盾 防御加 10 ！");
	                break;
	            case 78:    // [宝物] 钢盾
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                playerBean_1.setDefend(playerBean_1.getDefend() + 30);
	                MsgUtil.displayMessage("得到 钢盾 防御加 30 ！");
	                break;
	            /*case 80:    // [宝物] 星光盾
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 120);
	                MsgUtil.displayMessage("得到 星光盾 防御加 120 ！");
	                break;*/
	            case 115:   // 可通过的护栏
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                break;
	            case 119:   // 同 case 1:
	            case 129:   // 同 case 1:
	                LvMap[currentFloor][y][x] = 0;
	                playerBean_1.move(x, y);
	                break;
	            case 188:
	            	LvMap[currentFloor][y][x] = 0;
	            	LvMap[currentFloor][y-1][x] = 0;
	            	LvMap[currentFloor][y-2][x] = 0;
	            	LvMap[currentFloor][y][x-1] = 0;
	            	LvMap[currentFloor][y-1][x-1] = 0;
	            	LvMap[currentFloor][y-2][x-1] = 0;
	            	LvMap[currentFloor][y-1][x+1] = 0;
	            	LvMap[currentFloor][y-2][x+1] = 0;
	            	LvMap[currentFloor][y][x+1] = 0;
	            	 if (ForecastUtil.forecast(MonsterData.monsterMap.get(id))==1
                     ||ForecastUtil.forecast(MonsterData.monsterMap.get(id)) >= playerBean_1.getHp()) {
                 return;
             } else {
                 new BattleUtil(id, x, y);
             }
	                break;
	            
	        }
	    }

	

}