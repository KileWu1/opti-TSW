package data;
import bean.MonsterBean;
import java.util.HashMap;

public class MonsterData {
	public static HashMap<Integer, MonsterBean> monsterMap = new HashMap<>();

	static {
		monsterMap.clear();
		monsterMap.put(40, new MonsterBean(0, 50, 20, 1, 1, 1, "綠頭怪"));
		monsterMap.put(41, new MonsterBean(1, 70, 15, 2, 2, 2, "紅頭怪"));
		monsterMap.put(42, new MonsterBean(2, 100, 20, 5, 3, 3, "小蝙蝠"));
		monsterMap.put(43, new MonsterBean(3, 200, 35, 10, 5, 5, "青頭怪"));
		monsterMap.put(44, new MonsterBean(4, 300, 30, 5, 5, 4, "骷髏人"));
		monsterMap.put(45, new MonsterBean(5, 150, 40, 20, 8, 6, "骷髏士兵"));
		monsterMap.put(46, new MonsterBean(6, 300, 75, 45, 13, 10, "獸面人"));
		monsterMap.put(47, new MonsterBean(7, 450, 150, 90, 22, 19, "初級衛兵"));
		monsterMap.put(48, new MonsterBean(8, 250, 70, 30, 10, 8, "大蝙蝠"));
		monsterMap.put(49, new MonsterBean(9, 550, 160, 90, 25, 20, "紅蝙蝠"));
		monsterMap.put(50, new MonsterBean(10, 1300, 300, 150, 40, 35, "白衣武士"));
		monsterMap.put(51, new MonsterBean(11, 700, 250, 125, 32, 30, "怪王"));
		monsterMap.put(52, new MonsterBean(12, 500, 400, 260, 47, 45, "紅衣法師"));
		monsterMap.put(53, new MonsterBean(13, 15000, 1000, 1000, 100, 100, "紅衣魔王"));
		monsterMap.put(54, new MonsterBean(14, 850, 350, 200, 45, 40, "金甲衛士"));
		monsterMap.put(55, new MonsterBean(15, 900, 750, 650, 77, 70, "金甲隊長"));
		monsterMap.put(56, new MonsterBean(16, 400, 90, 50, 15, 12, "骷髏隊長"));
		monsterMap.put(57, new MonsterBean(17, 1500, 830, 730, 80, 70, "靈法師"));
		monsterMap.put(58, new MonsterBean(18, 1200, 980, 900, 88, 75, "靈武士"));
		monsterMap.put(59, new MonsterBean(19, 30000, 1700, 1500, 250, 220, "冥靈魔王"));
		monsterMap.put(60, new MonsterBean(20, 250, 120, 70, 20, 17, "麻衣法師"));
		monsterMap.put(61, new MonsterBean(21, 2000, 680, 590, 70, 65, "冥戰士"));
		monsterMap.put(62, new MonsterBean(22, 2500, 900, 850, 84, 75, "冥隊長"));
		monsterMap.put(63, new MonsterBean(23, 125, 40,0, 10, 7, "初級法師"));
		monsterMap.put(64, new MonsterBean(24, 100, 200, 110, 30, 25, "高級法師"));
		monsterMap.put(65, new MonsterBean(25, 500, 115, 65, 15, 15, "石頭怪人"));
		monsterMap.put(66, new MonsterBean(26, 900, 450, 330, 50, 50, "獸面戰士"));
		monsterMap.put(67, new MonsterBean(27, 1200, 620, 520, 65, 75, "雙手劍士"));
		monsterMap.put(68, new MonsterBean(28, 1250, 500, 400, 55, 55, "冥衛兵"));
		monsterMap.put(69, new MonsterBean(29, 1500, 560, 460, 60, 60, "高級衛兵"));
		monsterMap.put(70, new MonsterBean(30, 3100, 1150, 1050, 92, 80, "影子戰士"));
		monsterMap.put(188, new MonsterBean(31, 9999, 666, 50, 0, 0, "魔龍"));
		
	}
}