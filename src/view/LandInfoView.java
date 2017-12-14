package view;
import model.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LandInfoView extends JPanel{
	//岛信息面板锁调用的岛
    private Land land;
    //造船按钮，每个岛都有
    private JButton buildBoat_jb;
    
    //岛信息面板大小设置
    private int LANDINFOV_W = 225;
    private int LANDINFOV_H = 300;
    
    //传进来不同的参数构造出不同的岛信息面板
    public LandInfoView(Object allPoints) {
    	this.setPreferredSize(new Dimension(LANDINFOV_W, LANDINFOV_H));
    	
    	this.setLayout(new FlowLayout());
    	this.setVisible(true);
    	this.setOpaque(false);
    	
//    	//造船按钮
//    	buildBoat_jb = new JButton("造船");
//    	buildBoat_jb.setFont(new Font("Font.PLAIN", Font.PLAIN, 20));
//    	buildBoat_jb.setBackground(Color.CYAN);
//    
    	
//    	if(land instanceof S) {
//    		JLabel land_name = new JLabel("海域");
//    		this.add(land_name);
    		
//    	}
//    	if(land instanceof Land_SimpleLand) {
//    		//向下转型---普通岛
//    		Land_SimpleLand land_SimpleLand = (Land_SimpleLand)land;
//    		
//    		JLabel land_name = new JLabel(land_SimpleLand.getLand_name() + ":      ");
//        	JLabel land_moneyV = new JLabel(Integer.toString(land_SimpleLand.getMoney()) + "$       ");
//        	JLabel possibility_goodV = new JLabel("渔民的祝福概率：" + Double.toString(land_SimpleLand.getPossibility_good()));
//        	JLabel possibility_badV = new JLabel("海盗的巢穴概率：" + Double.toString(land_SimpleLand.getPossibility_bad()));
//        	
//        	land_name.setFont(new Font("Inconsolata", Font.PLAIN, 30));
//        	land_moneyV.setFont(new Font("SansSerif", Font.PLAIN, 30));
//        	possibility_goodV.setFont(new Font("SansSerif", Font.PLAIN, 30));
//        	possibility_badV.setFont(new Font("lFont.PLAIN", Font.PLAIN, 30));
//        	
//        	this.add(land_name);
//        	this.add(land_moneyV);
//        	this.add(possibility_goodV);
//        	this.add(possibility_badV);
//        	this.add(buildBoat_jb);
//    	}
//    	
//    	if(land instanceof Land_TradeLand) {
//    		//向下转型----交易岛
//    		Land_TradeLand land_TradeLand = (Land_TradeLand)land;
//    		
//    		JLabel land_name = new JLabel(land_TradeLand.getLand_name() + "             ");
//    		JLabel message0 = new JLabel("可进行的交易：                             ");
//    		JLabel message1 = new JLabel("1.购买海盗保护费 ：         -8$");
//    		JLabel message2 = new JLabel("2.为船只加满耐久度 ：     -3$");
//    		JLabel message3 = new JLabel("3.将船只提升一个等级 ：  -4$");
//    		JLabel message4 = new JLabel("    4.购买使坏卡 ：               -5$     ");
//    		
//    		land_name.setFont(new Font("lFont.PLAIN", Font.PLAIN, 20));
//    		message1.setFont(new Font("lFont.PLAIN", Font.PLAIN, 20));
//    		message2.setFont(new Font("lFont.PLAIN", Font.PLAIN, 20));
//    		message3.setFont(new Font("lFont.PLAIN", Font.PLAIN, 20));
//    		message4.setFont(new Font("lFont.PLAIN", Font.PLAIN, 20));
//    		
//    		this.add(land_name);
//    		this.add(message1);
//    		this.add(message2);
//    		this.add(message3);
//    		this.add(message4);
//    		this.add(buildBoat_jb);
//    	}
//    	
//    	if(land instanceof Land_MysteryLand) {
//    		//向下转型---神秘岛
//    		Land_MysteryLand land_MysteryLand = (Land_MysteryLand)land;
//    		
//    		JLabel land_name = new JLabel(land_MysteryLand.getLand_name() + "              ");
//    		JLabel message0 = new JLabel("可能遇到的事件类型：");
//    		JLabel message1 = new JLabel("1.风暴的洗礼： 所有玩家右退一格");
//    		JLabel message2 = new JLabel("2.飞来的横祸：上岛玩家金币-5 ");
//    		JLabel message3 = new JLabel("3.海盗的宝藏： 上岛玩家金币+10");
//    		JLabel message4 = new JLabel("4.海神的祝福： 其他玩家滞后一轮");
//    		JLabel message5 = new JLabel("5.贪婪的横祸： 上岛玩家船只变为");
//    		JLabel message6 = new JLabel("小木船                                        ");
//    		
//    		land_name.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		message0.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		message1.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		message2.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		message3.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		message4.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		message5.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		message6.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
//    		
//    		this.add(land_name);
//    		this.add(message0);
//    		this.add(message1);
//    		this.add(message2);
//    		this.add(message3);
//    		this.add(message4);
//    		this.add(message5);
//    		this.add(message6);
//    		this.add(buildBoat_jb);
//    	}
    }

    
	public int getLANDINFOV_W() {
		return LANDINFOV_W;
	}

	public void setLANDINFOV_W(int lANDINFOV_W) {
		LANDINFOV_W = lANDINFOV_W;
	}

	public int getLANDINFOV_H() {
		return LANDINFOV_H;
	}

	public void setLANDINFOV_H(int lANDINFOV_H) {
		LANDINFOV_H = lANDINFOV_H;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public JButton getBuildBoat_jb() {
		return buildBoat_jb;
	}

	public void setBuildBoat_jb(JButton buildBoat_jb) {
		this.buildBoat_jb = buildBoat_jb;
	}

}