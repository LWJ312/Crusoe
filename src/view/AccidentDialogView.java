package view;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class AccidentDialogView extends JOptionPane{
	//错误类信息
	//边缘检测—————该方向不能前行
	private static String wrong_condition="所选方向不能行船，请重新选择";
	//购买服务金币不足错误提示
	private static String wrong_money_lacked = "您的金币不足以购买该服务┭┮﹏┭┮";
	//船只耐久度不支持再航行错误提示
	private static String wrong_drability_lacked = "抱歉，您额船只耐久度不支持航行了┭┮﹏┭┮";
	//玩家停留在海中船只耐久度变为0，该玩家被淘汰，错误提示弹框
	private static String wrong_gameover = "真遗憾，您被困海中，到达大陆失败，游戏结束┭┮﹏┭┮";
	
	//警示类信息
	//普通岛进入海盗的巢穴，金币值-5警示
	private static String warn_priate_inLand = "aaaa,进入了海盗的巢穴辣，金币-5$ oԾ‸Ծo";
	// 海中遇到海盗
	private static String warn_priate_inSea = "哎呀，真不幸oԾ‸Ծo，您在航行中遇到了海盗，金币-5$，换为小船";
	//神秘岛贪婪的横祸，上岛玩家提示船只变为小木船
	private static String warn_boatDown_inMysteryLand = "吖，贪婪的横祸事件被触发，ε=(´ο｀*)))唉丫，您的船只被降级为小木船了┭┮﹏┭┮，继续努力o";
	//海中遇到风暴，船只耐久度减少警示
	private static String warn_strom_inSea = "ε＝(#>д<)ﾉ，哇啊啊啊，在海域航行遇到了风暴，船只耐久度-1 oԾ‸Ծo";
	//神秘岛遭遇风暴的洗礼，所有玩家右退一格警示
	private static String warn_storm_inMysteryLand = "哎呀，风暴的洗礼被触发辣，所有玩家左退一格oԾ‸Ծo";
	//神秘岛遭遇飞来的横祸，上岛玩家提示金币-5
	private static String warn_disaster_inMysteryLand = "aaa,不小心触发 飞来的横祸 事件辣，您的金币值-5$oԾ‸Ծo";
	//被使坏或其他玩家海神祝福，造船不能移动警示
	private static String warn_harmed = "(。・＿・。)ﾉI’m sorry~，您此轮不能移动喔oԾ‸Ծo";
	
	//简单提示类信息
	//获胜弹窗
	private static String win = "恭喜您获得胜利ヾ(￣ー￣)ゞ，游戏结束了哦";
	//玩家被陷害，停留一轮
	private static String beHarmed="oh no,是有人偷偷陷害你呢？还是海神的祝福呢？停留一轮，休息一下吧";
	//玩家被陷害，停留一轮
	private static String boatDistoryAndStop="oh no,船只耐用度为0，不能前进，请停留一轮自动造船吧";
	//船只建造成功提示
	private static String tip_boatBulit = "(～￣▽￣)～船只建好咯，主人我们可以出航辣~";
	//普通岛捡钱
	private static String tip_getMoney="大发! get岛上金币";
	//普通岛渔民祝福，船只耐久度加满提示
	private static String tip_blessing_inLand = "哇哇哇，人品爆发，您在岛上受到了渔民的祝福喔~╰(*°▽°*)╯，船只耐久度加满喔，请放心起航吧！";
	//交易岛海岛保护费购买成功提示
	private static String tip_trade_protection = "嗯哼，保护费购买成功︿(￣︶￣)︿，客官您负责全地图浪，我负责看好他们~";
	//交易岛购买使船只耐久度加满，花费金币加耐久度提示
	private static String tip_trade_durability = "交易成功╰(*°▽°*)╯，金币-3$，船只已经为您耐久度加满辣，有钱在手，天下我有，客官放心起航~";
    //交易岛购买船只等级提升提示
	private static String tip_trade_boatUp = "交易成功╰(*°▽°*)╯，金币-4$，有钱在手，天下我有，客官放心起航，您的船只三年保修o~( 3$一次嘿嘿o(*￣︶￣*)o )";
	private static String tip_trade_boatUpFull="您的船是最高等级，无需升级";
	//交易岛购买使坏卡成功提示
	private static String tip_trade_doHarm = "交易成功╰(*°▽°*)╯，金币-5$，有钱在手，天下我有,客官您尽情做坏事，温馨提示我们有遭报应保险卖喔~o(*￣︶￣*)o ";
	//神秘岛触发海盗的宝藏提示
	private static String tip_truesure_inMysteryLand = "wowowo,您遇到了海盗埋藏的宝藏o╰(*°▽°*)╯，真幸运，金币+10";
	//神秘岛触发海神的祝福提示
	private static String tip_godBless_inMysteryLand = "wawa,您触发了神之青睐 海神的祝福，其他玩家滞后一轮，带着海神的祝福上路喔~(*^▽^*)";
	
	//选项弹窗类
	//交易岛事件选择弹窗选项数组
	private static String[] trade_goods = {"来人啊我要买保护费","小二快来给我的船只加满耐久度","店长我要给船升级！","嘿嘿嘿，快，给我来张使坏卡"};
	//针对不同玩家的不同使坏选项
	private static String[] harm_choices1 = {"Player1","Player2"};
	private static String[] harm_choices2 = {"Player2","Player3"};
	private static String[] harm_choices3 = {"Player1","Player3"};
	
	
	
	public AccidentDialogView() {
		
	}
	
	
	//各种事件提示窗口弹出方法

	//一     错误对话框--------1.金币值不足  2.船只耐久度不能航行
	//边缘检测—————该方向不能前行
	public static void wrongCondition() {
	    showMessageDialog(null,wrong_condition,"操作有错误哇！", JOptionPane.ERROR_MESSAGE);
    }
	//购买服务金币不足错误提示
	public static void wrongMoneyLacked() {
		showMessageDialog(null,wrong_money_lacked,"操作有错误哇！", JOptionPane.ERROR_MESSAGE);
	}
	//船只耐久度不支持再航行错误提示
	public static void wrongDrabilityLacked() {
		showMessageDialog(null,wrong_drability_lacked,"操作有错误哇！", JOptionPane.ERROR_MESSAGE);
	}
	//玩家停留在海中船只耐久度变为0，该玩家被淘汰，错误提示弹框
	public static void wrongGameover() {
		showMessageDialog(null,wrong_gameover,"操作有错误哇！", JOptionPane.ERROR_MESSAGE);
	}
	
	//二     简单消息对话框，提示用5户遇到的各种事件，显示由于事故而造成的各种事件信息-----1.金币值增减---2.耐久度增减---3.玩家位置被动改变。。。。。。
	
	//玩家被陷害，停留一轮
	public static void tip_beHarmed() {
		showMessageDialog(null, beHarmed);
	}
	//玩家被陷害，停留一轮
	public static void tip_boatDistoryAndStop() {
			showMessageDialog(null, boatDistoryAndStop);
		}
	//船只建造成功提示
	public static void tip_boatBulit() {
		showMessageDialog(null, tip_boatBulit);
	}
	//普通岛捡钱
	public static void tip_getMoney() {
		showMessageDialog(null, tip_getMoney);
	}
	//普通岛渔民祝福，船只耐久度加满提示
	public static void tip_blessing_inLand() {
		showMessageDialog(null, tip_blessing_inLand);
	}
	//交易岛海岛保护费购买成功提示
	public static void tip_trade_protection() {
		showMessageDialog(null, tip_trade_protection);
	}
	//交易岛购买使船只耐久度加满，花费金币加耐久度提示
	public static void tip_trade_durability() {
		showMessageDialog(null, tip_trade_durability);
	}
	//交易岛购买船只等级提升提示
	public static void tip_trade_boatUp() {
		showMessageDialog(null, tip_trade_boatUp);
	}
	public static void tip_trade_boatUpFull() {
		showMessageDialog(null, tip_trade_boatUpFull);
	}
	//交易岛购买使坏卡成功提示
	public static void tip_trade_doHarm() {
		showMessageDialog(null, tip_trade_doHarm);
	}
	//神秘岛触发海盗的宝藏提示
	public static void tip_truesure_inMysteryLand() {
		showMessageDialog(null, tip_truesure_inMysteryLand);
	}
	//神秘岛触发海神的祝福提示
	public static void tip_godBless_inMysteryLand() {
		showMessageDialog(null, tip_godBless_inMysteryLand);
	}
	//游戏胜利弹窗
	public static void tipWin() {
		showMessageDialog(null, win);
	}
	
	public static void tipBack() {
		showMessageDialog(null, "返回");
	}
	
	//三     简单警告对话框，提示用户遇到的各种坏事件信息-----1.海上遇到海盗----2.神秘岛各种坏事件
	//普通岛进入海盗的巢穴，金币值-5警示
	public static void warnPriateInLand() {
		showMessageDialog(null, warn_priate_inLand,"提醒提醒！",JOptionPane.WARNING_MESSAGE);
	}
	// 海中遇到海盗
	public static void warnPriateInSea() {
		showMessageDialog(null, warn_priate_inSea,"提醒提醒！",JOptionPane.WARNING_MESSAGE);
	}
	//海中遇到风暴，船只耐久度减少警示
	public static void warnStromInSea() {
		showMessageDialog(null, warn_strom_inSea,"提醒提醒！",JOptionPane.WARNING_MESSAGE);
	}
	//神秘岛遭遇风暴的洗礼，所有玩家左退一格警示
	public static void warnStormInMysteryLand() {
		showMessageDialog(null, warn_storm_inMysteryLand,"提醒提醒！",JOptionPane.WARNING_MESSAGE);
	}
	//神秘岛遭遇飞来的横祸，上岛玩家提示金币-5
	public static void warn_disaster_inMysteryLand() {
		showMessageDialog(null, warn_disaster_inMysteryLand,"提醒提醒！",JOptionPane.WARNING_MESSAGE);
	}
	//被使坏或其他玩家海神祝福，造船不能移动警示
	public static void warnHarmed() {
		showMessageDialog(null, warn_harmed,"提醒提醒！",JOptionPane.WARNING_MESSAGE);
	}
	//神秘岛贪婪的横祸，上岛玩家提示船只变为小木船
	public static void warnBoatDownInMesteryLand() {
			showMessageDialog(null, warn_boatDown_inMysteryLand,"提醒提醒！",JOptionPane.WARNING_MESSAGE);
		}
	
	//四     选择对话框，用户选择造船类型，为使坏卡选择使坏对象，交易岛购买服务选择。。。。。。
	//交易岛购买服务选择
	public static String chooseServe() {
		Object[] choices = {trade_goods[0],trade_goods[1],trade_goods[2],trade_goods[3]};
		Object selected = JOptionPane.showInputDialog(null,"请选择服务:","TradeLand",JOptionPane.DEFAULT_OPTION,null,choices,choices[3]);
//		if(JOptionPane.DEFAULT_OPTION == -1) {
//			return ("0");
//		}
	    return (String)selected;
	}
	
	//玩家一的选择弹窗：
	public static String choosePerson_1() {
		Object[] choices = {harm_choices2[0],harm_choices2[1]};
		Object selected = JOptionPane.showInputDialog(null, "请选择使坏对象o(￣ヘ￣o＃：", "DoHarm", JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
	    return (String)selected;
	}
	//玩家二的选择弹窗：
	public static String choosePerson_2() {
		Object[] choices = {harm_choices3[0],harm_choices3[1]};
		Object selected = JOptionPane.showInputDialog(null, "请选择使坏对象o(￣ヘ￣o＃：", "DoHarm", JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
		return (String)selected;
	}
	//玩家三的选择弹窗：
	public static String choosePerson_3() {
		Object[] choices = {harm_choices1[0],harm_choices1[1]};
		Object selected = JOptionPane.showInputDialog(null, "请选择使坏对象o(￣ヘ￣o＃：", "DoHarm", JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
		return (String)selected;
	}


	public static String[] getTrade_goods() {
		return trade_goods;
	}


	public static void setTrade_goods(String[] trade_goods) {
		AccidentDialogView.trade_goods = trade_goods;
	}
	
	
}
