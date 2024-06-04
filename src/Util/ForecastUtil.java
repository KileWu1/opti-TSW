package Util;

import Mymtgame.fin;
import bean.MonsterBean;
import data.MonsterData;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

import static Mymtgame.fin.*;
import static Mymtgame.Main.*;
import static data.mapdata.LvMap;
public class ForecastUtil {
    public static JLayeredPane forecastLPane = new JLayeredPane();     // 预测信息面板

    static {
        // 初始化 预测面板
        forecastLPane.setLayout(null);
        forecastLPane.setBounds(6 * GAME_PIX_72, GAME_PIX_72, GAME_PIX_72 * 11, GAME_PIX_72 * 11);
        forecastLPane.setBackground(Color.BLACK);   // 黑色背景
        forecastLPane.setOpaque(true);
        forecastLPane.setVisible(false);
    }

    // 查看怪物功能
    public static void displayForecast() {
        inConversation = true;
        forecastLPane.setVisible(true);
        int cnt = 0;

        HashSet<Integer> forecastSet = new HashSet<>();
     // 遍歷當前樓層
        for (int x = 0; x < LvMap[currentFloor].length; x++) {
            for (int y = 0; y < LvMap[currentFloor][x].length; y++) {
                int id = LvMap[currentFloor][x][y];
                // 如果 id 對應 怪物 且 在 forecastSet 中不存在 id 值
                if ((id >= 40 && id <= 70) && !forecastSet.contains(id)) {
                    forecastSet.add(id);
                    MonsterBean m = MonsterData.monsterMap.get(id);

                    JLabel nameLabel = new JLabel("名稱：" + m.getName());
                    JLabel hpLabel = new JLabel("生命：" + m.getHp());
                    JLabel attackLabel = new JLabel("攻擊：" + m.getAttack());
                    JLabel defendLabel = new JLabel("防禦：" + m.getDefend());
                    JLabel moneyExpLabel = new JLabel("金幣·經驗：" + m.getMoney() + " · " + m.getExp());
                    JLabel loseLabel = new JLabel("損失：" + forecast(m));
                    
                    nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    nameLabel.setForeground(Color.WHITE);
                    nameLabel.setFont(new Font("Serif", 0, 25));

                    hpLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    hpLabel.setForeground(Color.WHITE);
                    hpLabel.setFont(new Font("Serif", 0, 25));

                    attackLabel.setBounds(339, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    attackLabel.setForeground(Color.WHITE);
                    attackLabel.setFont(new Font("Serif", 0, 25));

                    defendLabel.setBounds(339, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    defendLabel.setForeground(Color.WHITE);
                    defendLabel.setFont(new Font("Serif", 0, 25));

                    moneyExpLabel.setBounds(563, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    moneyExpLabel.setForeground(Color.WHITE);
                    moneyExpLabel.setFont(new Font("Serif", 0, 25));

                    loseLabel.setBounds(563, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    loseLabel.setForeground(Color.WHITE);
                    loseLabel.setFont(new Font("Serif", 0, 25));



                    JLabel headLabel = new JLabel();
                    headLabel.setIcon(new ImageIcon(fin.imgSource.get(id)));
                    headLabel.setBounds(25, 24 + 96 * cnt, GAME_PIX_72, GAME_PIX_72);
                    cnt++;

                    forecastLPane.add(nameLabel);
                    forecastLPane.add(hpLabel);
                    forecastLPane.add(attackLabel);
                    forecastLPane.add(defendLabel);
                    forecastLPane.add(moneyExpLabel);
                    forecastLPane.add(loseLabel);
                    forecastLPane.add(headLabel);
                }
            }
        }
    }

    // 预测功能
    public static int forecast(MonsterBean e) {
        if (fin.playerBean_1.getAttack() <= e.getDefend()) {
            return 0;//1;//1
        } else if (fin.playerBean_1.getDefend() >= e.getAttack()) {
            return 0;
        } else {
            return 0;//((e.getHp() / (fin.playerBean_1.getAttack() - e.getDefend())) * (e.getAttack() - fin.playerBean_1.getDefend()));
        }
    }
}
