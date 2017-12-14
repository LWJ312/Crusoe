package model;

import java.util.Random;

import com.sun.accessibility.internal.resources.accessibility;

public class Land_SimpleLand extends Land{
	//普通岛
    private int money;
    private double possibility_good;//岛屿好事件的概率
    private double possibility_bad;//岛屿坏事件的概率

   
    Random random=new Random();
     
	public Land_SimpleLand(int landId,int pos_X, int pos_Y,double possibility_good,double possibility_bad,Accident accident) {
		super(landId,pos_X, pos_Y,accident);
		setMoney(random.nextInt(3));//初始化岛屿随机藏有金币0，1，2
	    setPossibility_good(possibility_good);/*初始化岛屿 设定“渔民的祝福————船只耐用度+2”的概率*/
	    setPossibility_bad(possibility_bad);/*初始化岛屿 设定“海盗的老窝————金币减少至0”的概率*/
	    setLand_name("SimpleLand");
	   
	}
	
    //玩家上岛，玩家捡钱，岛屿获得玩家此时刻的好运气值 并执行事件
	public String givePlayerGoodOrBad(Player aPlayer) {
		aPlayer.setMoney(aPlayer.getMoney()+this.getMoney());
		if((aPlayer.getLuckyChance()+getPossibility_good())>=1) {
			putGood(aPlayer);
			return("lucky");
		}
		else{
			putBad(aPlayer);
			return("bad");
		}
	}
	
	//好事件！渔民的祝福————船只耐用度+2
	public void putGood(Player aPlayer) {
	    getAccidents().fishermanBless(aPlayer.getPlayerID());
	}
	
	//坏事件！海盗的老窝————金币减少至0
	public void putBad(Player aPlayer) {
		getAccidents().meetPirateDen(aPlayer.getPlayerID());
	}
	//getters and setters
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public double getPossibility_good() {
		return possibility_good;
	}

	public void setPossibility_good(double possibility_good) {
		this.possibility_good = possibility_good;
	}

	public double getPossibility_bad() {
		return possibility_bad;
	}

	public void setPossibility_bad(double possibility_bad) {
		this.possibility_bad = possibility_bad;
	}

	
    
}