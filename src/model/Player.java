package model;

import java.util.Random;
import java.util.Scanner;

public class Player {
	private int  PlayerID;         //玩家ID
	private int  Money;          //玩家所拥有的金币
	private int pos_X;           //玩家位置横坐标
	private int  pos_Y;           //玩家位置纵坐标
	
	private Boolean  isProtected;           //判断玩家是否被保护(海盗)
	private Boolean  isHarmed;             //判断玩家是否被陷害(使坏卡)
	private Boolean  isWin;              //判断玩家是否胜利
	private Boolean  isStopped;      //是否被滞留
	  
    private double luckyChance;  //玩家每到达一个岛屿，会有一个随机的好运值
    private double badChance;   //玩家每到达一个岛屿，会有一个随机的坏运值
	
	private Boat theBoat;                //玩家的小船
	private boolean isOperate;     //是否轮到该玩家进行操作
   
    public Player(int  playerID, int  money , Boat aBoat){
		this.setPlayerID(playerID);
		this.setMoney(money);
		this.setTheBoat(new Boat_SmallBoat());//初始化时统一为玩家给**小木船**
		this.setIsProtected(false);
		this.setIsHarmed(false);
		this.setIsWin(false);
		this.setIsStopped(false);
		this.setIsOperate(false);
	}
    
    //占卜运气，随机生成好运值，两者和为1；
    public void betTheChances() {
    	Random aRandom=new Random();
    	this.setLuckyChance(aRandom.nextDouble());
    	this.setBadChance(1-this.getLuckyChance());
    }
	
	//玩家上岛，船只耐久度为0重造船，选择重造船 : 1--小木船，2--帆船，3--轮船
	public void whetherNeedRebuild(){
		   if(theBoat.getDurability() <= 0){
			   System.out.println("请输入造船种类：");
			   Scanner scanner = new Scanner(System.in);
			   int boatNum = scanner.nextInt(3)+1;
			   this.rebuildBoat(boatNum);
			   scanner.close();
		   }
	   }
	//移动
	public void move_player(int n) {
		getTheBoat().move(n);
		setPos_X(getTheBoat().getPos_X());
		setPos_Y(getTheBoat().getPos_Y());
		if(getTheBoat().getDurability()==0) {
			setIsStopped(true);
		}
	}
	//玩家新建船，供其他方法调用
	public void rebuildBoat(int boatRank) {
		this.theBoat = null;//玩家船只毁灭
		//通过输入选择造何种种类的船
		switch(boatRank) {
		    case 1:
		    	this.setTheBoat(new Boat_SmallBoat());
		    	break;
		    case 2:
		    	this.setTheBoat(new Boat_MiddleBoat());
		    	break;
		    case 3:
		    	this.setTheBoat(new Boat_BigBoat());
		    	break;
		}
//		this.getTheBoat().setRank(boatRank);
		//将玩家的坐标传给船
		this.theBoat.setPos_X(this.pos_X);
		this.theBoat.setPos_Y(this.pos_Y);
	}

    //getters and setters
	 public boolean getIsOperate() {
			return isOperate;
		}

	public void setIsOperate(boolean isOperate) {
			this.isOperate = isOperate;
	}
		
	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}

	public int getMoney() {
		return Money;
	}

	public void setMoney(int money) {
		Money = money;
	}

	public int getPos_X() {
		return pos_X;
	}

	public void setPos_X(int pos_X) {
		this.pos_X = pos_X;
	}

	public int getPos_Y() {
		return pos_Y;
	}

	public void setPos_Y(int pos_Y) {
		this.pos_Y = pos_Y;
	}

	public Boolean getIsProtected() {
		return isProtected;
	}

	public void setIsProtected(Boolean isProtected) {
		this.isProtected = isProtected;
	}

	public Boolean getIsHarmed() {
		return isHarmed;
	}

	public void setIsHarmed(Boolean isHarmed) {
		this.isHarmed = isHarmed;
	}

	public Boolean getIsWin() {
		return isWin;
	}

	public void setIsWin(Boolean isWin) {
		this.isWin = isWin;
	}
	
	public Boolean getIsStopped() {
		return isStopped;
	}

	public void setIsStopped(Boolean isStopped) {
		this.isStopped = isStopped;
	}
	
    public double getLuckyChance() {
		return luckyChance;
	}

	public void setLuckyChance(double luckyChance) {
		this.luckyChance = luckyChance;
	}

	public double getBadChance() {
		return badChance;
	}

	public void setBadChance(double badChance) {
		this.badChance = badChance;
	}

   public Boat getTheBoat() {
	return theBoat;
    }

   public void setTheBoat(Boat aBoat) {
	this.theBoat = aBoat;
    }
   
}
