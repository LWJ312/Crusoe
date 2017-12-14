package model;

import java.util.Scanner;

public class Land_TradeLand extends Land{
    int PROTECT_FEE=8;
    int FULLBOATDURABILITY_FEE=3;
    int UPDATEBOAT_FEE=4;
    int DOHARM_FEE=5;
   
    
	public Land_TradeLand(int landId, int pos_X, int pos_Y,Accident anAccident) {
		super(landId, pos_X, pos_Y,anAccident);
		setLand_name("TradeLand");
	}
    
	public boolean trade(int playerChoice) {
		switch (playerChoice) {
		case 1:{//海盗保护费
			getAccidents().tradeForProtection(getaPlayer().getPlayerID());
			return true;
		}
		case 2:{//加满船只耐用度
			getAccidents().tradeDurablity(getaPlayer().getPlayerID());
			return true;
		}
		case 3:{//升级船只
			System.out.println(getaPlayer().getTheBoat().getRank());
			if(getaPlayer().getTheBoat().getRank()<3) {
				getAccidents().tradeForUpDateBoat(getaPlayer().getPlayerID());
				return true;
			}
			else 
				return false;
		}
//		case 4:{//？？？？？？使坏卡.不方便穿参数
//			getAccidents().tradeForDoHarm(int targetID);
//			return true;
//		}

		default:
			break;
		}
		return false;
	}

}