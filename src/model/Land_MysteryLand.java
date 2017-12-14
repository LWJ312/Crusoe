package model;

import java.util.Random;

public class Land_MysteryLand extends Land{
	//神秘岛
	private int accidentType;
	
	Random aRandom=new Random();
	
    public Land_MysteryLand(int landId,int pos_X, int pos_Y,Accident accident) {
		super(landId,pos_X, pos_Y,accident);
		setLand_name("MysteryLand");
		
	}
    
    //玩家上岛，神秘岛占卜 自动生成事件编号，神秘岛事件初始化
    public void generateAccidentType() {
		setAccidentType(aRandom.nextInt(5));
	}
    //向玩家执行事件
    public String givePlayerGoodOrBad(Player aPlayer) {
		switch (getAccidentType()) {
		case 0:{
			//“贪婪的横祸”————船只被破坏，分配初始化小木船
			getAccidents().meetAccidentOfGreed(aPlayer.getPlayerID());
			return("greedy");
		}
		case 1:{
			//“海神的祝福”————除上岛玩家，其他玩家停滞一轮
			//解决办法，给玩家增加一个isStopped布尔值来判断是否滞留
			getAccidents().stopOtherPlayers(aPlayer.getPlayerID());
			return("bless");
		}
		case 2:{
			//“风暴的洗礼”————所有玩家左移一格
			getAccidents().meetAccidentOfStorm();
			return("storm");
		}
		case 3:{
			//“海盗的宝藏”————上岛玩家金币+10
			getAccidents().meetPirateTreature(aPlayer.getPlayerID());
		    return("treasure");
		}
		case 4:{
			//“飞来的横祸”————上岛玩家金币-5
			getAccidents().meetUnespectedAccident(aPlayer.getPlayerID());
		    return("disaster");
		}
		default:
			break;
		}
		return null;
	}

	public int getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(int accidentType) {
		this.accidentType = accidentType;
	}

   
}