package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.omg.CORBA.PRIVATE_MEMBER;

import model.Boat;
import model.Game;
import model.Land;
import model.Land_MysteryLand;
import model.Land_SimpleLand;
import model.Land_TradeLand;
import model.Player;
import model.Sea;
import sun.launcher.resources.launcher;
import view.AccidentDialogView;
import view.GameView;
import view.LandInfoView;
import view.PlayGroundView;
import view.PlayerInfoView;

public class GameController implements KeyListener{
	static int LEFT=1;
	static int RIGHT=3;
	static int UP=5;
	static int DOWN=2;
	/** 当前是否处于玩家选方向的阶段 */
	private boolean choosing=false;
		
//游戏循环
	/*刚开始 金币最少的玩家开始游戏，玩家信息面板上排出顺序，
	 * 第一位玩家开始选择方向（方向判断）
	 * 
	 *{所有岛通用}
	 * 若海域发生事件，则玩家到岛后弹r窗提示
	 *  若船耐用度不够，弹窗提示，然后在岛信息面板有造船按钮；
	 *  第一位玩家到达岛后，游戏地图刷新打印，岛信息面板显示该岛信息。
	 *  触发事件弹窗，eg获得金币，
	 * 
	 * {神秘岛}
	 * 岛信息面板更改
	 * 事件弹窗
	 * 
	 * {交易岛}
	 * 弹窗里面有选项
	 * 若为使坏，则再弹窗出使坏对象（其他玩家）
	 * 
	 * 
	 * 每一位玩家操作完后，玩家面板刷新
	 * 
	 * 调用game类判断胜利
	 */
	private Game game;
	
	private GameView gameView;
	
	public GameController(Game game){
		this.game=game;
		this.gameView=new GameView(game);
		this.getGameView().getPlayGroundView().setaGameController(this);
			
	}
	
    public void runGame() {
//    	Scanner scanner=new Scanner(System.in);
		while(!getGame().isSomeoneWin()) {
			for(Player aPlayer:getGame().getPlayers()) {
					setChoosing(true);
					aPlayer.setIsOperate(true);
					updateLandInfoView(getGame().getThePlayer().getPlayerID());
					getGame().setThePlayer(aPlayer);
			       
					//显示正在操作的箭头图标
					PlayerInfoView thePlayer_playerInfoView=this.getGameView().getOtherInfoView().getPlayersInfoView()[aPlayer.getPlayerID()-1];
					thePlayer_playerInfoView.getTip_colorV().setVisible(true);
					/*玩家进行操作（方向选择）之前玩家面板信息同步！-----操作后的同步在keyRelease里*/
					updatePlayerInfoView(aPlayer.getPlayerID());
				    if(aPlayer.getIsStopped()) {
				    	if(aPlayer.getIsHarmed()) {
				    		AccidentDialogView.tip_beHarmed();
				    		aPlayer.setIsHarmed(false);
				    		aPlayer.setIsStopped(false);
				    	}
				    	else {
					    	AccidentDialogView.tip_boatDistoryAndStop();
					    	aPlayer.rebuildBoat(Boat.getSMALLBOAT_RANK());
					    	aPlayer.setIsHarmed(false);
				    		aPlayer.setIsStopped(false);
				    		
						}	
				    	setChoosing(false);
				    	thePlayer_playerInfoView.getTip_colorV().setVisible(false);
				        continue;
				    }
				    //暂时的写法————直到键盘响应了才进行下一步操作
					while(isChoosing()==true) {		
					 System.out.println("");
					 }	
					//更新岛信息面板		
					updateLandInfoView(getGame().getThePlayer().getPlayerID());
					this.getGameView().getOtherInfoView().getLandInfoView().repaint();
					//触发岛或者海域事件
					meetAccident(getGame().getThePlayer().getPlayerID());
					//更新玩家信息面板
					aPlayer.setIsOperate(false);
					updatePlayerInfoView(getGame().getThePlayer().getPlayerID());
					getGameView().repaint();
					thePlayer_playerInfoView.getTip_colorV().setVisible(false);
					
					
									
			}
			
		}
		AccidentDialogView.tipWin();
	}
    
    
    //更新玩家信息面板
    public void updatePlayerInfoView(int currentPlayerID){

    	//注：正在操作图标在键盘响应（玩家选择）前更新------在runGame的循环里面
    	//更新是否受保护————主要用于交易岛购买保护费后更新图标！
    	Player theCurrentPlayer=getGame().getPlayers()[currentPlayerID-1];
    	PlayerInfoView theCurrentPlayerInfoView=this.getGameView().getOtherInfoView().getPlayersInfoView()[currentPlayerID-1];
    	 if(theCurrentPlayer.getIsProtected()) {
		       theCurrentPlayerInfoView.getProtect_colorV().setVisible(true);
		    }
		 else {
			   theCurrentPlayerInfoView.getProtect_colorV().setVisible(false);	
		 }		
		//更新金币
    	String money=String.valueOf(getGame().getPlayers()[currentPlayerID-1].getMoney())+"$";
    	this.getGameView().getOtherInfoView().getPlayersInfoView()[currentPlayerID-1].getMoneyV().setText(money);
    	//更新船图标————主要用于船耐用度为0后重新造船，以及船升级	---?？？？？？？？？？???未测试
		updatePlayerInfoView_boatIcon(currentPlayerID);
    	//更新船的剩余耐用度
    	String boatDurability=String.valueOf(this.getGame().getPlayers()[currentPlayerID-1].getTheBoat().getDurability());
    	this.getGameView().getOtherInfoView().getPlayersInfoView()[currentPlayerID-1].getBoat_durabilityV().setText(boatDurability);
    }
    //更新玩家信息面板之船图标——————主要用于玩家操作前
    public void updatePlayerInfoView_boatIcon(int currentPlayerID) {
    	Player theCurrentPlayer=getGame().getPlayers()[currentPlayerID-1];
    	PlayerInfoView theCurrentPlayerInfoView=this.getGameView().getOtherInfoView().getPlayersInfoView()[currentPlayerID-1];
    	ImageIcon bImage;
	     switch (theCurrentPlayer.getTheBoat().getRank()) {
				case 1:
					 bImage=new ImageIcon("./src/pic/"+theCurrentPlayer.getTheBoat().getBoatName()+theCurrentPlayer.getPlayerID()+".png");
				     theCurrentPlayerInfoView.getBoatTip().setIcon(bImage);
					break;
				case 2:
					 bImage=new ImageIcon("./src/pic/"+theCurrentPlayer.getTheBoat().getBoatName()+theCurrentPlayer.getPlayerID()+".png");
					 theCurrentPlayerInfoView.getBoatTip().setIcon(bImage);
					break;
				case 3:
					 bImage=new ImageIcon("./src/pic/"+theCurrentPlayer.getTheBoat().getBoatName()+theCurrentPlayer.getPlayerID()+".png");
					 theCurrentPlayerInfoView.getBoatTip().setIcon(bImage);
					break;
				default:
					break;
				}
	}
    //更新岛信息面板
    public void updateLandInfoView(int currentPlayerID) {
    	Player theCurrentPlayer=getGame().getPlayers()[currentPlayerID-1];
    	LandInfoView theLandInfoView=this.getGameView().getOtherInfoView().getLandInfoView();
    	theLandInfoView.removeAll();
    	Object nowPosition=getGame().getAllPoints()[theCurrentPlayer.getPos_X()][theCurrentPlayer.getPos_Y()];		
		
    	//海域信息显示
    	if(nowPosition instanceof Sea) {
    		Sea aSea = (Sea)nowPosition;
    		JLabel sea_nameV = new JLabel(aSea.getSeaName() + "            ");
    		JLabel sea_message = new JLabel("可能遇到的事件概率：        ");
    		JLabel sea_priate_chanceV = new JLabel("1.被海盗袭击：      " + aSea.getPriate_chance());
    		JLabel sea_storm_chanceV = new JLabel("2.被风暴袭击：     " + aSea.getStorm_chance());
    		
    		sea_nameV.setFont(new Font("Inconsolata", Font.PLAIN, 18));
    		sea_message.setFont(new Font("SansSerif", Font.PLAIN, 18));
    		sea_priate_chanceV.setFont(new Font("SansSerif", Font.PLAIN, 18));
    		sea_storm_chanceV.setFont(new Font("lFont.PLAIN", Font.PLAIN, 18));
    		
    		theLandInfoView.add(sea_nameV);
        	theLandInfoView.add(sea_message);
        	theLandInfoView.add(sea_priate_chanceV);
        	theLandInfoView.add(sea_storm_chanceV);
    	}
    	else {
    		JButton buildBoat_jb;
    		//造船按钮
        	buildBoat_jb = new JButton("造船");
        	buildBoat_jb.setFont(new Font("Font.PLAIN", Font.PLAIN, 20));
        	buildBoat_jb.setBackground(Color.CYAN);
        
    	
    		if(nowPosition instanceof Land_SimpleLand) {
        		//向下转型---普通岛
        		Land_SimpleLand land_SimpleLand = (Land_SimpleLand)nowPosition;
        		
        		JLabel land_name = new JLabel(land_SimpleLand.getLand_name() + ":      ");
            	JLabel land_moneyV = new JLabel(Integer.toString(land_SimpleLand.getMoney()) + "$       ");
            	JLabel possibility_goodV = new JLabel("渔民的祝福概率：" + Double.toString(land_SimpleLand.getPossibility_good()));
            	JLabel possibility_badV = new JLabel("海盗的巢穴概率：" + Double.toString(land_SimpleLand.getPossibility_bad()));
            	
            	land_name.setFont(new Font("Inconsolata", Font.PLAIN, 22));
            	land_moneyV.setFont(new Font("SansSerif", Font.PLAIN, 22));
            	possibility_goodV.setFont(new Font("SansSerif", Font.PLAIN, 22));
            	possibility_badV.setFont(new Font("lFont.PLAIN", Font.PLAIN, 22));
            	
            	theLandInfoView.add(land_name);
            	theLandInfoView.add(land_moneyV);
            	theLandInfoView.add(possibility_goodV);
            	theLandInfoView.add(possibility_badV);
            	//theLandInfoView.add(buildBoat_jb);
        	}
        	
        	if(nowPosition instanceof Land_TradeLand) {
        		//向下转型----交易岛
        		Land_TradeLand land_TradeLand = (Land_TradeLand)nowPosition;
        		
        		JLabel land_name = new JLabel(land_TradeLand.getLand_name() + "             ");
        		JLabel message0 = new JLabel("可进行的交易：                             ");
        		JLabel message1 = new JLabel("1.购买海盗保护费 ：         -8$");
        		JLabel message2 = new JLabel("2.为船只加满耐久度 ：     -3$");
        		JLabel message3 = new JLabel("3.将船只提升一个等级 ：  -4$");
        		JLabel message4 = new JLabel("    4.购买使坏卡 ：               -5$     ");
        		
        		land_name.setFont(new Font("lFont.PLAIN", Font.PLAIN, 15));
        		message1.setFont(new Font("lFont.PLAIN", Font.PLAIN, 15));
        		message2.setFont(new Font("lFont.PLAIN", Font.PLAIN, 15));
        		message3.setFont(new Font("lFont.PLAIN", Font.PLAIN, 15));
        		message4.setFont(new Font("lFont.PLAIN", Font.PLAIN, 15));
        		
        		theLandInfoView.add(land_name);
        		theLandInfoView.add(message1);
        		theLandInfoView.add(message2);
        		theLandInfoView.add(message3);
        		theLandInfoView.add(message4);
        		//theLandInfoView.add(buildBoat_jb);
        	}
        	
        	if(nowPosition instanceof Land_MysteryLand) {
        		//向下转型---神秘岛
        		Land_MysteryLand land_MysteryLand = (Land_MysteryLand)nowPosition;
        		
        		JLabel land_name = new JLabel(land_MysteryLand.getLand_name() + "              ");
        		JLabel message0 = new JLabel("可能遇到的事件类型：");
        		JLabel message1 = new JLabel("1.风暴的洗礼： 所有玩家右退一格");
        		JLabel message2 = new JLabel("2.飞来的横祸：上岛玩家金币-5 ");
        		JLabel message3 = new JLabel("3.海盗的宝藏： 上岛玩家金币+10");
        		JLabel message4 = new JLabel("4.海神的祝福： 其他玩家滞后一轮");
        		JLabel message5 = new JLabel("5.贪婪的横祸： 上岛玩家船只变为");
        		JLabel message6 = new JLabel("小木船                                        ");
        		
        		land_name.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		message0.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		message1.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		message2.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		message3.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		message4.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		message5.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		message6.setFont(new Font("lFont.PLAIN", Font.PLAIN, 13));
        		
        		theLandInfoView.add(land_name);
        		theLandInfoView.add(message0);
        		theLandInfoView.add(message1);
        		theLandInfoView.add(message2);
        		theLandInfoView.add(message3);
        		theLandInfoView.add(message4);
        		theLandInfoView.add(message5);
        		theLandInfoView.add(message6);
        		//theLandInfoView.add(buildBoat_jb);
    	   }
    	} 	
	}

    //玩家选择方向船前进后 触发海域/岛事件
    public void meetAccident(int currentPlayerID) {
    	Player theCurrentPlayer=getGame().getPlayers()[currentPlayerID-1];
    	theCurrentPlayer.betTheChances();
    	Object nowPosition=getGame().getAllPoints()[theCurrentPlayer.getPos_X()][theCurrentPlayer.getPos_Y()];		
		
    	if(nowPosition instanceof Sea) {
    		((Sea) nowPosition).setaPlayer(theCurrentPlayer);
    		String result=new String();
    		result=((Sea) nowPosition).givePlayerSeaAccident();
    		if(result=="pirate") {
    			AccidentDialogView.warnPriateInSea();
    			//船若降为小木船，修改图标
        		updateBoatIcon(currentPlayerID);
    		}
    		if(result=="storm") {
    			AccidentDialogView.warnStromInSea();
    		}
    		
    		
    		
    	}
    	
    	if(nowPosition instanceof Land_SimpleLand) {
    		AccidentDialogView.tip_getMoney();
    		String result=new String();
    		result=((Land_SimpleLand) nowPosition).givePlayerGoodOrBad(theCurrentPlayer);
    		
    		if(result=="lucky") {
    			AccidentDialogView.tip_blessing_inLand();
    		}
    		if(result=="bad") {
    			AccidentDialogView.warnPriateInLand();
    		}
    	}
    	if(nowPosition instanceof Land_MysteryLand) {
    		((Land_MysteryLand) nowPosition).generateAccidentType();
    		String result=new String();
    		result=((Land_MysteryLand) nowPosition).givePlayerGoodOrBad(theCurrentPlayer);
    		
    		if(result=="greedy") {//？？？？？？？？？？？？
    			AccidentDialogView.warnBoatDownInMesteryLand();
    		}
    		if(result=="bless") {
    			AccidentDialogView.tip_godBless_inMysteryLand();
    		}
    		if(result=="storm") {
    			AccidentDialogView.warnStormInMysteryLand();
    		}
    		if(result=="treasure") {
    			AccidentDialogView.tip_truesure_inMysteryLand();
    		}
    		if(result=="disaster") {
    			AccidentDialogView.warn_disaster_inMysteryLand();
    		}	
    	}
    	if(nowPosition instanceof Land_TradeLand) {
    		((Land_TradeLand) nowPosition).setaPlayer(theCurrentPlayer);
    		String accidentS=new String();
    		accidentS = AccidentDialogView.chooseServe();

    		//购买保护费提示弹窗
    		if(accidentS instanceof String) {
    			if(accidentS.equals(AccidentDialogView.getTrade_goods()[0])) {
        			
    				if(theCurrentPlayer.getMoney() < 2) {
    					AccidentDialogView.wrongMoneyLacked();
    				}
    				else {
    					((Land_TradeLand) nowPosition).trade(1);
    						AccidentDialogView.tip_trade_protection();
    				}
    				
    		}
    		//购买船只耐久度提示弹窗
    		if(accidentS.equals(AccidentDialogView.getTrade_goods()[1])) {
    				if(theCurrentPlayer.getMoney() < 3) {
    						AccidentDialogView.wrongMoneyLacked();
    				}
    				else {
    						((Land_TradeLand) nowPosition).trade(2);
    						AccidentDialogView.tip_trade_durability();
    				}
    				
    		}
    	   //购买船只升级提示弹窗
    	   if(accidentS.equals(AccidentDialogView.getTrade_goods()[2])) {
    			
    			if(theCurrentPlayer.getMoney() < 4) {
    					AccidentDialogView.wrongMoneyLacked();
    			}
    			else {  
    				    boolean result=((Land_TradeLand) nowPosition).trade(3);
    					if(result) {
    						//升级
    						AccidentDialogView.tip_trade_boatUp();
    						//船升级，图标更新
    						updateBoatIcon(currentPlayerID);
    						}
    					else {
    						//已是最高级，无需升级
    						AccidentDialogView.tip_trade_boatUpFull();
						}
    			}
    		}
    	 //根据玩家ID弹出不同的使坏卡购买使用及提示界面
    	   if(accidentS.equals(AccidentDialogView.getTrade_goods()[3])) {
   					if(theCurrentPlayer.getMoney() < 5) {
   						AccidentDialogView.wrongMoneyLacked();
   					}
   					else {
   						int targetplayerID;
   						if(theCurrentPlayer.getPlayerID()== 1) {
   							targetplayerID = Integer.parseInt(AccidentDialogView.choosePerson_1().substring(6));
   							AccidentDialogView.tip_trade_doHarm();
   							getGame().getAccidents().tradeForDoHarm(theCurrentPlayer,targetplayerID);
   						}
   						if(theCurrentPlayer.getPlayerID()== 2) {
   							targetplayerID = Integer.parseInt(AccidentDialogView.choosePerson_2().substring(6));
   							AccidentDialogView.tip_trade_doHarm();
   							getGame().getAccidents().tradeForDoHarm(theCurrentPlayer,targetplayerID);
   						}
   						if(theCurrentPlayer.getPlayerID()== 3) {
   							targetplayerID = Integer.parseInt(AccidentDialogView.choosePerson_3().substring(6));
   							AccidentDialogView.tip_trade_doHarm();
   							getGame().getAccidents().tradeForDoHarm(theCurrentPlayer,targetplayerID);
   						}
   					}
   				}
   			}
    		else {
    			AccidentDialogView.tipBack();
    		}
    		}
    		
   		
	}
    //耐用度减到0，停一轮造船
    public void boatNoDurabilityAndRebuild(Player aPlayer) {
		aPlayer.setIsStopped(true);
	}
    //更新船的图标
    public void updateBoatIcon(int currentPlayerID) {
		ImageIcon imageIcon=new ImageIcon("./src/pic/"+getGame().getThePlayer().getTheBoat().getBoatName()+getGame().getThePlayer().getPlayerID()+".png");
		this.getGameView().getPlayGroundView().getBoatsPic()[currentPlayerID-1].setImage(imageIcon.getImage());
	}
    
    public static void main(String[] args) {
		JFrame jFrame=new JFrame("crusoe");
		Game game=new Game();
		GameController gameController=new GameController(game);
		//对游戏面板监听
		jFrame.addKeyListener(gameController);
		jFrame.setContentPane(gameController.getGameView());
		jFrame.setFocusable(true);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!很重要
		jFrame.setVisible(true);
		jFrame.pack();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameController.runGame();
//		JPanel imagePanel=(JPanel) jFrame.getContentPane();
//		imagePanel.setOpaque(false);
//				
//		jFrame.getLayeredPane().add(gv, new Integer(Integer.MIN_VALUE));
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {//????不知道有么有问题
	
	}

	@Override
	public void keyReleased(KeyEvent e) {//事件监听还没弄
		if(isChoosing()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(getGame().getThePlayer().getPos_X()!=0) {
				   getGame().getThePlayer().move_player(LEFT);
				   setChoosing(false);
				}
				else AccidentDialogView.wrongCondition();
				break;
			case KeyEvent.VK_RIGHT:
				if(getGame().getThePlayer().getPos_X()!=12) {
				   getGame().getThePlayer().move_player(RIGHT);
				   setChoosing(false);
				}
				else AccidentDialogView.wrongCondition();
				break;
			case KeyEvent.VK_UP:
				if(getGame().getThePlayer().getPos_Y()!=0) {
				   getGame().getThePlayer().move_player(UP);
				   setChoosing(false);
				}
				else AccidentDialogView.wrongCondition();
				break;
			case KeyEvent.VK_DOWN:
				if(getGame().getThePlayer().getPos_Y()!=9) {
				   getGame().getThePlayer().move_player(DOWN);
				   setChoosing(false);
				}
				else AccidentDialogView.wrongCondition();
				break;
			}
			
		}

	}

	public boolean isChoosing() {
		return choosing;
	}

	public void setChoosing(boolean choosing) {
		this.choosing = choosing;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
	
	
}
