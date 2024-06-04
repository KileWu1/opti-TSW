package Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Mymtgame.fin;
import bean.MonsterBean;
import data.Imagedata;
import data.MonsterData;

import static Mymtgame.Main.GAME_PIX_72;
import static Mymtgame.Main.bgm;
import static Mymtgame.Main.currentFloor;
import static data.mapdata.LvMap;
import static Util.ShopUtil.MagicianActive;

public class BattleUtil
{
	 	private static final Font BATTLE_FONT = new Font("Serif", 0, 35);

	    public static JLayeredPane battleLPane = new JLayeredPane();       // 戰鬥訊息面板

	    private JLabel battleBgLabel;
	    private JLabel monsterImg;                      // 怪物圖片
	    private JLabel monster_hp = new JLabel();       // 怪物生命值
	    private JLabel monster_attack = new JLabel();   // 怪物攻擊力
	    private JLabel monster_defend = new JLabel();   // 怪物防御力
	    private JLabel player_hp = new JLabel();        // 玩家生命值
	    private JLabel player_attack = new JLabel();    // 玩家攻擊力
	    private JLabel player_defend = new JLabel();    // 玩家防御力

	    private MonsterBean monsterBean;
	    private static int hp;
	    private int attack;
	    private int defend;
	    
		static JButton paper = new JButton("布");//0
		static JButton scissor = new JButton("剪刀");//1
		static JButton stone =new JButton("石頭");//2

		
		static JLabel lab1 = new JLabel("戰鬥開始",JLabel.CENTER);
		static JLabel lab2 = new JLabel("",JLabel.CENTER);
		static JLabel lab3 = new JLabel("",JLabel.CENTER);
		
		private JLabel enemyL = new JLabel();
		
		static int random ;
		
		
		
		private int check;

	    /**
	     * @param id 怪物id
	     * @param x  怪物x坐标
	     * @param y  怪物y坐标
	     */
	    public BattleUtil(int id, int x, int y) {
	    	bgm.stop();
	    	
	        monsterBean = MonsterData.monsterMap.get(id);
	        
	        hp = monsterBean.getHp();
	        attack = monsterBean.getAttack();
	        defend = monsterBean.getDefend();
	        
	        if(MagicianActive == true)
	        {
	        	hp/=10;
	        	attack/=10;
	        	defend/=10;
	        }
	        
	        // 取圖
	        battleBgLabel = new JLabel(new ImageIcon(Imagedata.battleBgImg));
	        monsterImg = new JLabel(new ImageIcon(fin.imgSource.get(id)));
	        paper = new JButton(new ImageIcon(Imagedata.battlepImg));
	        scissor = new JButton(new ImageIcon(Imagedata.battlesImg));
	        stone =new JButton(new ImageIcon(Imagedata.battlestImg));

	        // 初始化 戰鬥訊息面板
	        battleLPane.setLayout(null);
	        battleLPane.setBounds(27, GAME_PIX_72 * 2, 1242, 541);
	        battleBgLabel.setBounds(0, 0, 1242, 541);
	        battleLPane.add(battleBgLabel, 1, 0);
	        battleLPane.setOpaque(true);
	        battleLPane.setVisible(false);

	       
	        
	        int tmp = -50;
	        monster_hp.setBounds(150, 460 + tmp, 100, 50);
	        monster_hp.setFont(BATTLE_FONT);
	        monster_hp.setForeground(Color.WHITE);

	        monster_attack.setBounds(150, 492 + tmp, 100, 50);
	        monster_attack.setFont(BATTLE_FONT);
	        monster_attack.setForeground(Color.WHITE);

	        monster_defend.setBounds(150, 524 + tmp, 100, 50);
	        monster_defend.setFont(BATTLE_FONT);
	        monster_defend.setForeground(Color.WHITE);

	        player_hp.setBounds(1030, 465 + tmp, 100, 50);
	        player_hp.setFont(BATTLE_FONT);
	        player_hp.setForeground(Color.WHITE);

	        player_attack.setBounds(1030, 497 + tmp, 100, 50);
	        player_attack.setFont(BATTLE_FONT);
	        player_attack.setForeground(Color.WHITE);

	        player_defend.setBounds(1030, 529 + tmp, 100, 50);
	        player_defend.setFont(BATTLE_FONT);
	        player_defend.setForeground(Color.WHITE);
	        
	        enemyL.setBounds(590,195,100,100);
			lab1.setBounds(390,40,500,50);
			lab1.setFont(BATTLE_FONT);
			lab1.setForeground(Color.WHITE);
			lab2.setBounds(390,75,500,50);
			lab2.setFont(BATTLE_FONT);
			lab2.setForeground(Color.WHITE);
			lab3.setBounds(390,110,500,50);
			lab3.setFont(BATTLE_FONT);
			lab3.setForeground(Color.WHITE);
			
			paper.setBounds(490,350,90,90);
			paper.setContentAreaFilled(false);
			
			scissor.setBounds(595,350,90,90);
			scissor.setContentAreaFilled(false);
			
			stone.setBounds(700,350,90,90);
			stone.setContentAreaFilled(false);
	        
			battleLPane.add(enemyL,8,0);
			battleLPane.add(lab1,9,0);
			battleLPane.add(lab2,10,0);
			battleLPane.add(lab3,11,0);
			battleLPane.add(stone,12,0);
			battleLPane.add(paper,13,0);
			battleLPane.add(scissor,14,0);
			
	        battleLPane.add(monster_hp, 2, 0);
	        battleLPane.add(monster_attack, 3, 0);
	        battleLPane.add(monster_defend, 4, 0);
	        battleLPane.add(player_hp, 5, 0);
	        battleLPane.add(player_attack, 6, 0);
	        battleLPane.add(player_defend, 7, 0);

	        monsterImg.setBounds(100, 120, 72, 72);
	        battleLPane.add(monsterImg, 8, 0);

	        monster_hp.setText(hp + "");
	        monster_attack.setText(attack + "");
	        monster_defend.setText(defend + "");

	        player_hp.setText(fin.playerBean_1.getHp() + "");
	        player_attack.setText(fin.playerBean_1.getAttack() + "");
	        player_defend.setText(fin.playerBean_1.getDefend() + "");
	        battleLPane.setVisible(true);
	        fin.inConversation = true;
	        
	        Timer bFrame = new Timer(1500, new ActionListener() {

	        	@Override
	        	public void actionPerformed(ActionEvent ex) {
	        		//attack();
	        	    monster_hp.setText(hp + "");
	        	    player_hp.setText(fin.playerBean_1.getHp() + "");
	        		paper.setBorder(BorderFactory.createEmptyBorder());
					scissor.setBorder(BorderFactory.createEmptyBorder());
					stone.setBorder(BorderFactory.createEmptyBorder());
	        	    fin.frm.repaint();
	        	    if (hp <= 0)
	        	    {
	        	        battleLPane.setVisible(false);
	        	        fin.inConversation = false;

	        	        fin.playerBean_1.setExp(fin.playerBean_1.getExp() + monsterBean.getExp());//exp += monsterBean.getExp();
	        	        fin.playerBean_1.setMoney(fin.playerBean_1.getMoney() + monsterBean.getMoney());// += monsterBean.getMoney();
	        	        MsgUtil.displayMessage("獲得金幣 " + monsterBean.getExp() + " 經驗值 " + monsterBean.getMoney() + " ！");
	        	        battleLPane.remove(monsterImg);
	        	        battleLPane.remove(monster_hp);
	        	        battleLPane.remove(monster_attack);
	        	        battleLPane.remove(monster_defend);
	        	        battleLPane.remove(player_hp);
	        	        battleLPane.remove(player_attack);
	        	        battleLPane.remove(player_defend);
	        	        enemyL.setIcon(null);
	        	        lab1.setText(null);
	        	        lab2.setText(null);
	        	        lab3.setText(null);

	        	        LvMap[currentFloor][y][x] = 0;
	        	        fin.playerBean_1.move(x, y);
	        	        bgm.start();
	        	        ((Timer) ex.getSource()).stop();
	        	    }
	        	}
	        	});
	        	bFrame.start();
	        
	        paper.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					
					if(fin.playerBean_1.getHp()>0 && hp>0)
					{
						lab1.setText(null);
	        	        lab2.setText(null);
	        	        lab3.setText(null);
						paper.setBorder(BorderFactory.createLineBorder(Color.RED,3));
						random = (int)(Math.random()*100%3);//0 1 2 
						if(random!=0)
						{
							if(random==1)
							{
								enemyL.setIcon(new ImageIcon(Imagedata.battlesImg));
								check=1;
								attack();
								if((attack-fin.playerBean_1.getDefend())<=0) lab1.setText("我方成功防禦");
								else lab1.setText("我方受到"+(attack - fin.playerBean_1.getDefend())+"點傷害");
								monster_hp.setText(hp + "");
				                player_hp.setText(fin.playerBean_1.getHp() + "");
				                fin.frm.repaint();
								if(fin.playerBean_1.getHp()>0)	lab2.setText("我方還剩"+fin.playerBean_1.getHp()+"點生命值");
								else					lab2.setText("我方陣亡!!");
							}
							else
							{
								enemyL.setIcon(new ImageIcon(Imagedata.battlestImg));
								check=2;
								attack();
								lab1.setText("敵方受到"+(fin.playerBean_1.getAttack()-defend)+"點傷害");
								monster_hp.setText(hp + "");
				                player_hp.setText(fin.playerBean_1.getHp() + "");
				                fin.frm.repaint();
								if(hp>0)		lab3.setText("敵方還剩"+hp+"點生命值");
								else			lab3.setText("敵方陣亡!!");
							}
						}
						else
						{
							enemyL.setIcon(new ImageIcon(Imagedata.battlepImg));
							lab1.setText("平手");
						}
					}
					else
					{
						
	                    
					}
				}
		
			});		
			scissor.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					monster_hp.setText(hp + "");
	                player_hp.setText(fin.playerBean_1.getHp() + "");
	                fin.frm.repaint();
					if(fin.playerBean_1.getHp()>0 && hp>0)
					{
						lab1.setText(null);
	        	        lab2.setText(null);
	        	        lab3.setText(null);
						scissor.setBorder(BorderFactory.createLineBorder(Color.RED,3));
						random = (int)(Math.random()*100%3);//0 1 2 
						if(random!=1)
						{
							if(random==2)
							{
								enemyL.setIcon(new ImageIcon(Imagedata.battlestImg));
								check=1;
								attack();
								if((attack-fin.playerBean_1.getDefend())<=0) lab1.setText("我方成功防禦");
								else lab1.setText("我方受到"+(attack - fin.playerBean_1.getDefend())+"點傷害");
								monster_hp.setText(hp + "");
				                player_hp.setText(fin.playerBean_1.getHp() + "");
				                fin.frm.repaint();
								if(fin.playerBean_1.getHp()>0)	lab2.setText("我方還剩"+fin.playerBean_1.getHp()+"點生命值");
								else					lab2.setText("我方陣亡!!");
							}
							else
							{
								enemyL.setIcon(new ImageIcon(Imagedata.battlepImg));
								check=2;
								attack();
								lab1.setText("敵方受到"+(fin.playerBean_1.getAttack()-defend)+"點傷害");
								monster_hp.setText(hp + "");
				                player_hp.setText(fin.playerBean_1.getHp() + "");
				                fin.frm.repaint();
								if(hp>0)		lab3.setText("敵方還剩"+hp+"點生命值");
								else			lab3.setText("敵方陣亡!!");
							}
						}
						else
						{
							enemyL.setIcon(new ImageIcon(Imagedata.battlesImg));
							lab1.setText("平手");
						}
					}
					else
					{
	                    
					}
					
				}
		
			});		
			stone.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					monster_hp.setText(hp + "");
	                player_hp.setText(fin.playerBean_1.getHp() + "");
	                fin.frm.repaint();
	               
					if(fin.playerBean_1.getHp()>0 && hp>0)
					{
						lab1.setText(null);
	        	        lab2.setText(null);
	        	        lab3.setText(null);
						stone.setBorder(BorderFactory.createLineBorder(Color.RED,3));
						random = (int)(Math.random()*100%3);//0 1 2 
						if(random!=2)
						{
							if(random==0)
							{
								enemyL.setIcon(new ImageIcon(Imagedata.battlepImg));
								check=1;
								attack();
								if((attack-fin.playerBean_1.getDefend())<=0) lab1.setText("我方成功防禦");
								else lab1.setText("我方受到"+(attack - fin.playerBean_1.getDefend())+"點傷害");
								monster_hp.setText(hp + "");
				                player_hp.setText(fin.playerBean_1.getHp() + "");
				                fin.frm.repaint();
								if(fin.playerBean_1.getHp()>0)	lab2.setText("我方還剩"+fin.playerBean_1.getHp()+"點生命值");
								else					lab2.setText("我方陣亡!!");
							}
							else
							{
								enemyL.setIcon(new ImageIcon(Imagedata.battlesImg));
								check=2;
								attack();
								lab1.setText("敵方受到"+(fin.playerBean_1.getAttack()-defend)+"點傷害");
								monster_hp.setText(hp + "");
				                player_hp.setText(fin.playerBean_1.getHp() + "");
				                fin.frm.repaint();
								if(hp>0)		lab3.setText("敵方還剩"+hp+"點生命值");
								else			lab3.setText("敵方陣亡!!");
							}
						}
						else
						{
							enemyL.setIcon(new ImageIcon(Imagedata.battlestImg));
							lab1.setText("平手");
						}
					}
					else
					{  
						
					}
				}
			});		
	        
	      
	    }

	  
	    private void attack()
	    {
	        if (fin.playerBean_1.getAttack() > defend && check==2)
	        {
	            hp = hp - fin.playerBean_1.getAttack() + defend;
	        }
	        if (hp <= 0) return;
	        if (attack > fin.playerBean_1.getDefend() && check == 1) {
	            fin.playerBean_1.setHp(fin.playerBean_1.getHp() - attack + fin.playerBean_1.getDefend()); //= hp - e.getAttack() + defend;
	        }
	        if (fin.playerBean_1.getAttack() < defend && attack <fin.playerBean_1.getDefend()) return;
	    }
}

//
/*
int tmp = -50;
monster_hp.setBounds(400, 37 + tmp, 300, 300);
monster_hp.setFont(BATTLE_FONT);
monster_hp.setForeground(Color.WHITE);

monster_attack.setBounds(400, 157 + tmp, 300, 300);
monster_attack.setFont(BATTLE_FONT);
monster_attack.setForeground(Color.WHITE);

monster_defend.setBounds(400, 291 + tmp, 300, 300);
monster_defend.setFont(BATTLE_FONT);
monster_defend.setForeground(Color.WHITE);

player_hp.setBounds(785, 37 + tmp, 300, 300);
player_hp.setFont(BATTLE_FONT);
player_hp.setForeground(Color.WHITE);

player_attack.setBounds(785, 157 + tmp, 300, 300);
player_attack.setFont(BATTLE_FONT);
player_attack.setForeground(Color.WHITE);

player_defend.setBounds(785, 291 + tmp, 300, 300);
player_defend.setFont(BATTLE_FONT);
player_defend.setForeground(Color.WHITE);

*/

/* Timer bFrame = new Timer(500, new ActionListener() {

@Override
public void actionPerformed(ActionEvent ex) {
    attack();
    monster_hp.setText(hp + "");
    player_hp.setText(fin.playerBean_1.getHp() + "");
    fin.frm.repaint();
    if (hp <= 0) {
        battleLPane.setVisible(false);
        fin.inConversation = false;

        fin.playerBean_1.setExp(fin.playerBean_1.getExp() + monsterBean.getExp());//exp += monsterBean.getExp();
        fin.playerBean_1.setMoney(fin.playerBean_1.getMoney() + monsterBean.getMoney());// += monsterBean.getMoney();
        //MsgUtil.displayMessage("获得金币数 " + monsterBean.getExp() + " 经验值 " + monsterBean.getMoney() + " ！");
        battleLPane.remove(monsterImg);
        battleLPane.remove(monster_hp);
        battleLPane.remove(monster_attack);
        battleLPane.remove(monster_defend);
        battleLPane.remove(player_hp);
        battleLPane.remove(player_attack);
        battleLPane.remove(player_defend);

        LvMap[currentFloor][y][x] = 0;
        fin.playerBean_1.move(x, y);
        ((Timer) ex.getSource()).stop();
    }
}
});
bFrame.start();*/