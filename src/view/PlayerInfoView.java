package view;
import model.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayerInfoView extends JPanel {
	//该面板的大小设置
	//一个玩家的信息面板的大小
	private static int PLAYERINFOVIEW_W = 225;//宽度
	private static int PLAYERINFOVIEW_H = 100;//长度
	
	//提示某个玩家进行游戏的指示小色块的大小
	private static int tip_color_size = 40;
	private static int protect_color_size = 40;
	private static int boat_color_size = 50;
	//是否显示玩家游戏进行的小色块面板
	private boolean show_tip_colorV;
	
	//该界面对应的玩家引用
	private Player player;
	//玩家是否受保护isProtected图表
	private JPanel isProtectedV;
	//玩家轮船图标
	private JLabel boatTip;
//	private JPanel boatIm;
	//玩家面板中包含的玩家信息
	//轮到玩家提示颜色块,板块
	private JPanel tip_colorV;
	//是否受保护色块，板块
	private JPanel protect_colorV;
	//玩家姓名标签
	private JLabel nameV;
	//玩家金币标签
	private JLabel moneyV;
	//玩家小船类型标签（名称）
	private JLabel boatV;
	//玩家小船耐久度标签
	private JLabel boat_durabilityV;
	
	private JPanel top;
	private JPanel bottom;
	
	
    public PlayerInfoView(Player player) {
    	this.setLayout(GridLayout(2,1));
    	this.setTop(new JPanel());
    	this.getTop().setLayout(new FlowLayout());
    	this.getTop().setBackground(null);
    	this.setBottom(new JPanel());
    	this.getBottom().setBackground(null);
    	this.setOpaque(false);
    	top.setOpaque(false);
    	bottom.setOpaque(false);
    	//初始化
    	//初始色块为不可见，切换至某玩家进行操作的时候刷新为该玩家
    	this.show_tip_colorV = false;
    	this.setPlayer(player);
    	this.setNameV(new JLabel("Player" + player.getPlayerID() + " : "));
    	this.setMoneyV(new JLabel(player.getMoney() + "$"));
//    	JLabel tab = new JLabel("          ");//这段空格将轮船放在下一行头去
    	this.setBoatV(new JLabel("剩余耐用度" + " : "));
    	System.out.println(player.getTheBoat().getDurability());
    	this.setBoat_durabilityV(new JLabel(Integer.toString(player.getTheBoat().getDurability())));
    	
    	//游戏玩家提示图标
    	tip_colorV = new JPanel();
    	this.tip_colorV.setPreferredSize(new Dimension(tip_color_size, tip_color_size));
//    	tip_colorV.setBackground(Color.GRAY);
    	ImageIcon pointImage = new ImageIcon("./src/pic/箭头.png");
    	JLabel pointTip = new JLabel(pointImage);
    	this.tip_colorV.add(pointTip);
    	this.tip_colorV.setOpaque(false);
    	//玩家是否受保护提示图标
    	protect_colorV = new JPanel();
    	this.protect_colorV.setPreferredSize(new Dimension(protect_color_size, protect_color_size));
//    	protect_colorV.setBackground(Color.GRAY);
    	ImageIcon protectImage = new ImageIcon("./src/pic/盾牌.png");
    	JLabel protectTip = new JLabel(protectImage);
    	this.protect_colorV.add(protectTip);
    	this.protect_colorV.setOpaque(false);
    	//玩家轮船图标
        ImageIcon boatImage = new ImageIcon("./src/pic/"+player.getTheBoat().getBoatName()+player.getPlayerID()+".png");
		boatTip = new JLabel(boatImage);
		this.boatTip.setPreferredSize(new Dimension(boat_color_size, boat_color_size));
		this.boatTip.setOpaque(false);
//        this.boatTip.setBackground(Color.GRAY);
//		this.boatIm.add(boatTip);
		
//        if(boat instanceof Boat_SmallBoat) {
//        	if(this.player.getPlayerID() == 1) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/smallBoat1_pink.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        	if(this.player.getPlayerID() == 2) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/smallBoat2_white.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        	if(this.player.getPlayerID() == 3) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/smallBoat3_yellow.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        }
//        if(boat instanceof Boat_MiddleBoat) {
//        	if(this.player.getPlayerID() == 1) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/middleBoat1_pink.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        	if(this.player.getPlayerID() == 2) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/middleBoat2_white.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        	if(this.player.getPlayerID() == 3) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/middleBoat3_yellow.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        }
//        if(boat instanceof Boat_BigBoat) {
//        	if(this.player.getPlayerID() == 1) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/bigBoat1_pink.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        	if(this.player.getPlayerID() == 2) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/bigBoat2_white.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        	if(this.player.getPlayerID() == 3) {
//        		ImageIcon boatImage = new ImageIcon("./src/pic/bigBoat3_yellow.png");
//        		JLabel boatTip = new JLabel(boatImage);
//        		this.boatIm.add(boatTip);
//        	}
//        }
    	
	    this.setBackground(Color.GRAY);
	    this.setPreferredSize(new Dimension(PLAYERINFOVIEW_W, PLAYERINFOVIEW_H));
	    this.setLayout(new FlowLayout());
	    
	    this.nameV.setFont(new Font("lFont.PLAIN", Font.ITALIC, 20));
	    this.moneyV.setFont(new Font("lFont.PLAIN", Font.ITALIC, 20));
	    this.boatV.setFont(new Font("lFont.PLAIN", Font.ITALIC, 20));
	    this.boat_durabilityV.setFont(new Font("lFont.PLAIN", Font.ITALIC, 20));
	    
	    
	    this.add(tip_colorV);
	    tip_colorV.setVisible(false);
//	    if(player.getIsOperate()) {
//	    	tip_colorV.setVisible(true);
//	    }
	    this.add(protect_colorV);
	    protect_colorV.setVisible(false);
//        if(player.getIsProtected()) {
//	    	protect_colorV.setVisible(true);
//	    }
	    
	    this.getTop().add(nameV);
	    this.getTop().add(moneyV);
	   
	    this.getBottom().add(boatTip);
	    this.getBottom().add(boatV);
	    this.getBottom().add(boat_durabilityV);
	    this.add(this.getTop());
	    this.add(this.getBottom());
	    
    }
    
   
    
	private LayoutManager GridLayout(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

    

	public JPanel getTop() {
		return top;
	}

	public void setTop(JPanel top) {
		this.top = top;
	}


	public JPanel getBottom() {
		return bottom;
	}



	public void setBottom(JPanel bottom) {
		this.bottom = bottom;
	}



	public static int getProtect_color_size() {
		return protect_color_size;
	}


	public static void setProtect_color_size(int protect_color_size) {
		PlayerInfoView.protect_color_size = protect_color_size;
	}


	public static int getBoat_color_size() {
		return boat_color_size;
	}


	public static void setBoat_color_size(int boat_color_size) {
		PlayerInfoView.boat_color_size = boat_color_size;
	}


	public JLabel getBoatTip() {
		return boatTip;
	}



	public void setBoatTip(JLabel boatTip) {
		this.boatTip = boatTip;
	}



	public JPanel getProtect_colorV() {
		return protect_colorV;
	}


	public void setProtect_colorV(JPanel protect_colorV) {
		this.protect_colorV = protect_colorV;
	}


	public static int getPLAYERINFOVIEW_W() {
		return PLAYERINFOVIEW_W;
	}

	public static void setPLAYERINFOVIEW_W(int pLAYERINFOVIEW_W) {
		PLAYERINFOVIEW_W = pLAYERINFOVIEW_W;
	}

	public static int getPLAYERINFOVIEW_H() {
		return PLAYERINFOVIEW_H;
	}

	public static void setPLAYERINFOVIEW_H(int pLAYERINFOVIEW_H) {
		PLAYERINFOVIEW_H = pLAYERINFOVIEW_H;
	}

	public static int getTip_color_size() {
		return tip_color_size;
	}

	public static void setTip_color_size(int tip_color_size) {
		PlayerInfoView.tip_color_size = tip_color_size;
	}

	public boolean isShow_tip_colorV() {
		return show_tip_colorV;
	}


	public void setShow_tip_colorV(boolean show_tip_colorV) {
		this.show_tip_colorV = show_tip_colorV;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public JPanel getTip_colorV() {
		return tip_colorV;
	}

	public void setTip_colorV(JPanel tip_colorV) {
		this.tip_colorV = tip_colorV;
	}

	public JLabel getNameV() {
		return nameV;
	}

	public void setNameV(JLabel nameV) {
		this.nameV = nameV;
	}

	public JLabel getMoneyV() {
		return moneyV;
	}

	public void setMoneyV(JLabel moneyV) {
		this.moneyV = moneyV;
	}

	public JLabel getBoatV() {
		return boatV;
	}

	public void setBoatV(JLabel boatV) {
		this.boatV = boatV;
	}

	public JLabel getBoat_durabilityV() {
		return boat_durabilityV;
	}

	public void setBoat_durabilityV(JLabel boat_durabilityV) {
		this.boat_durabilityV = boat_durabilityV;
	}

	public JPanel getIsProtectedV() {
		return isProtectedV;
	}

	public void setIsProtectedV(JPanel isProtectedV) {
		this.isProtectedV = isProtectedV;
	}

}
