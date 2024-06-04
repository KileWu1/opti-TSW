package Util;

import data.Imagedata;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mymtgame.Main.GAME_PIX_72;
import static Mymtgame.fin.frm;
import static Mymtgame.fin.inConversation;

public class MsgUtil {
	 public static JLayeredPane msgLPane = new JLayeredPane();           // 提示信息面板
	    public static JLabel msgLabel = new JLabel();

	    static {
	        // 初始化 信息面板
	        msgLPane.setLayout(null);
	        msgLPane.setBounds(10, 270, GAME_PIX_72 * 18 - 20, 150);
	        msgLabel.setBounds(0, 0, GAME_PIX_72 * 18 - 20, 150);
	        msgLabel.setForeground(Color.WHITE);
	        msgLabel.setFont(new Font("Serif", 0, 50));
	        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        JLabel msgBackground = new JLabel(new ImageIcon(Imagedata.blankBgImg));
	        msgBackground.setLayout(null);
	        msgBackground.setBounds(0, 0, GAME_PIX_72 * 18 - 20, 150);
	        msgBackground.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
	        msgLPane.add(msgBackground, 1, 0);
	        msgLPane.add(msgLabel, 2, 0);
	        msgLPane.setOpaque(true);
	        msgLPane.setVisible(false);
	    }

	    public static void displayMessage(String message) {
	        msgLPane.setVisible(true);
	        inConversation = true;
	        Timer animat = new Timer(450, new ActionListener() {
	            int count = 0;

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                count++;
	                if (count == 3) {
	                    msgLPane.setVisible(false);
	                    inConversation = false;
	                    ((Timer) e.getSource()).stop();
	                }
	                msgLabel.setText(message);
	                frm.repaint();
	            }
	        });
	        animat.setInitialDelay(0);
	        animat.start();
	    }
}
