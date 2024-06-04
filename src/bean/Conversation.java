package bean;

import Mymtgame.fin;
import data.mapdata;
import data.Imagedata;
import Util.ConversationUtil;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import static Mymtgame.Main.currentFloor;

public class Conversation {
	private static int id;
	
	public Conversation(int id) {
		   this.id = id;

	        String[] messages;
	        BufferedImage[] characters = new BufferedImage[50];
	        int[] w = new int[50];
	        int[] h = new int[50];
	        for (int x = 0; x < 50; x++) {
	            w[x] = 540;
	            h[x] = 170;
	        }
	        //對話框人物
	        for (int x = 0; x < characters.length; x++) {
	            if (x % 2 == 0) {
	                characters[x] = Imagedata.playerMap.get(-2);
	            } else {
	                characters[x] = fin.imgSource.get(id);
	            }
	        }
	        switch (this.id) {
	            case 24:     // 第 0 层 仙子 第一次对话
	                messages = new String[]{
	                		" ······(輸入法改至英文 按空白鍵以繼續)",
	                		" 你醒了!",
	                		" ······\n 你是誰？我在哪裡？",
	                		" 我是這裡的仙子，剛才你被這裡的小怪打昏了。",
	                		" ······\n 劍，劍，我的劍呢？",
	                		" 你的劍被他們搶走了，我只來得及將你救出來。",
	                		" 那，公主呢？我是來救公主的。",
	                		" 公主還在裡面，你這樣進去是打不過裡面的小怪的。",
	                		" 那我怎麼辦，我答應了國王一定要把公主救出來" +
	                		"的，那我現在應該怎麼辦呢？",
	                		" 放心吧，我把我的力量借給你，你就可以打贏那些小怪了。",
	                		" ······\n 可是，我怎麼進去呢?",
	                		" 我這裡有三把鑰匙，你先拿去，在塔里面還有很多這" +
	                		"樣的鑰匙，珍惜使用。加油吧，勇士！"
	                };
	                //對話窗高度
	                h[9] = 220;
	                h[13] = 400;
	                h[15] = 380;
	                h[17] = 200;
	                ConversationUtil.talk(messages, characters, w, h);
	               
	               fin.playerBean_1.setYkey(fin.playerBean_1.getYkey() + 1);
	               fin.playerBean_1.setBkey(fin.playerBean_1.getBkey() + 1);
	               fin.playerBean_1.setRkey(fin.playerBean_1.getRkey() + 1);
	                break;

	        }
	}

}
