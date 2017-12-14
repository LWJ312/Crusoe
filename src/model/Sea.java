package model;

import java.util.Random;

import sun.net.www.content.text.plain;

public class Sea {
    //海域类
	//位置，ID，信息栏名称属性
	private String seaName="海域";
	private int seaID;
	private int pos_x;
	private int pos_Y;
	//岛上海盗与风暴的概率属性
	private double priate_chance = 0.1;
	private double storm_chance = 0.1;
	
	//海域的事件属性，发生海域上的各种事件
	Accident anAccident;
	//海域上停留的玩家
	Player aPlayer;
	
	//海域构造函数
	public Sea(int seaID,int pos_X,int pos_Y,Accident accident) {
		this.setAnAccident(accident);
		this.setSeaID(seaID);
		this.setPos_x(pos_X);
		this.setPos_Y(pos_Y);
	}


	//产生海盗事件和风暴事件的概率
	//产生一个0或一的随机数
	public int generateAccidenNum() {
		Random aRandom=new Random();
		return (aRandom.nextInt(2));
	}
	//根据所产生的随机数分别发生     海盗---0     风暴---1
	public String meetAccidentInSea() {
		if(generateAccidenNum() == 0) {
			anAccident.meetPirateInSea(getaPlayer());
			return ("pirate");
		}
		else{
			anAccident.meetStormInSea(getaPlayer());
			return("storm");
		}
	}
	public String givePlayerSeaAccident() {
		if((getaPlayer().getBadChance()+getPriate_chance())>=1) {
			return meetAccidentInSea();
		}
		else return("safe");
		
	}
	

	public double getPriate_chance() {
		return priate_chance;
	}

	public void setPriate_chance(double priate_chance) {
		this.priate_chance = priate_chance;
	}

	public double getStorm_chance() {
		return storm_chance;
	}

	public void setStorm_chance(double storm_chance) {
		this.storm_chance = storm_chance;
	}

	public Accident getAnAccident() {
		return anAccident;
	}

	public void setAnAccident(Accident anAccident) {
		this.anAccident = anAccident;
	}


	public String getSeaName() {
		return seaName;
	}

	public void setSeaName(String seaName) {
		this.seaName = seaName;
	}

	public int getSeaID() {
		return seaID;
	}

	public void setSeaID(int seaID) {
		this.seaID = seaID;
	}

	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_Y() {
		return pos_Y;
	}

	public void setPos_Y(int pos_Y) {
		this.pos_Y = pos_Y;
	}

	public Player getaPlayer() {
		return aPlayer;
	}

	public void setaPlayer(Player aPlayer) {
		this.aPlayer = aPlayer;
	}
}
