package model;

public class Accident {
	//交易岛的各种服务的购买费用
	int PROTECT_FEE = -3;                   //海盗保护费费用
    int FULLBOATDURABILITY_FEE = -3;        //为船只加满耐久度的费用
    int UPDATEBOAT_FEE = -4;                //为船只提升一个等级的费用
    int DOHARM_FEE = -5;                    //使坏卡的费用
    //在海域上的各种事件的各种数值
    int STORM_CHANGE_DURABILITY = -1;       //在海上遇到风暴减少的船只的耐久度
    int PIRATE_CHANGE_MONEY = -5;           //在海上遇到海盗减少的金币值
    //在神秘岛上的各种事件的相关数值
    int POS_BACK = -1;                      //风暴的洗礼水平后退的格数
    int ACCIDENT_DECRISE_MONEY = -5;        //飞来的横祸减少的玩家的金币值
    int TRUESURE_INCRISE_MONEY = 10;        //海盗的宝藏增加的玩家的金币值
    //普通岛屿上的各种事件的相关数值
    int BESSING_INCRISE_DURABILITY = 4;     //渔民的祝福增加的船只的耐久度
    int PIRATE_BEN_CHANGE_MONEY = -5;       //海盗的巢穴减少的玩家的金币值
	
    private Player[] players;
    
    public Accident() {
    	
    }
    public Accident(Player[] players) {
		this.players=players;
	}
	//基本改变的方法
	//改变玩家的船只耐久度
	public void changeBoatDurabilityByAccident(int playerID,int durability_change) {
		//传入船的耐久度以及耐久度变化量（增加为正值，减少为负值），返回变化后船的耐久度
		for(Player aPlayer : players) {
			//便利Players数组找到PlayerID对应的玩家的船只，改变该船只的耐久度
			if(aPlayer.getPlayerID() == playerID) {
				int re_durability = aPlayer.getTheBoat().getDurability();
				aPlayer.getTheBoat().setDurability(re_durability + durability_change);
				
				if(aPlayer.getTheBoat().getDurability() > aPlayer.getTheBoat().getFULLDURABILITY()) {
				//当船只耐久度超过该类型船只耐久度上限时，将船只耐久度设定为上限（满）耐久度
					aPlayer.getTheBoat().setDurability(aPlayer.getTheBoat().getFULLDURABILITY());
				}
			}
		}
	}
	
	//改变一个玩家的金币
	public void changePlayerMoneyByAccident(int playerID,int money_change) {
		//传入玩家的金币值以及金币值得变化（增加为正值，减少为负值），返回变化后玩家的金币值
		for(Player aPlayer : players) {
			//遍历Players数组找到PlayerID对应的玩家，改变它的金币值
			if(aPlayer.getPlayerID() == playerID) {
				int re_money = aPlayer.getMoney();
				aPlayer.setMoney(re_money + money_change);
				if(aPlayer.getMoney() < 0) {
				//判断玩家的金币是否为负值，若是负值则将其设为0
					aPlayer.setMoney(0);
				}
			}
		}
	}
	
	//普通岛屿上的事件
		//渔民的祝福-------船只耐久度加满
		public void fishermanBless(int playerID) {
			changeBoatDurabilityByAccident(playerID,BESSING_INCRISE_DURABILITY);
	    }
		//海盗的巢穴-------该玩家金币 -5
		public void meetPirateDen(int playerID) {
			changePlayerMoneyByAccident(playerID,PIRATE_BEN_CHANGE_MONEY);
		}
		
	
	//神秘岛的各种事件
	    //贪婪的横祸-------上岛玩家船只变为小木船
		public void meetAccidentOfGreed(int playerID) {
			for(Player aPlayer : players) {
				if(aPlayer.getPlayerID() == playerID) {
					//上岛玩家
					aPlayer.rebuildBoat(Boat.getSMALLBOAT_RANK());   //---------???????船类型？？？解决办法，直接设为小船
				}
			}
		}
	   //海神的祝福————除上岛玩家，其他玩家停滞一轮
	   public void stopOtherPlayers(int playerID) {
		for(Player aPlayer : players) {
			if(aPlayer.getPlayerID()!=playerID) {
				aPlayer.setIsStopped(true);
			}
		}
	   }
	   //风暴的洗礼-------左退一格
		public void meetAccidentOfStorm() {
			for(Player aPlayer : players) {
			//所有玩家pos_X减一(右退一格)
				int re_pos_X = aPlayer.getPos_X();//玩家事件前水平位置
				aPlayer.setPos_X(re_pos_X + POS_BACK);
			}
		}

	   //海盗的宝藏-------上岛玩家金币 +10
       public void meetPirateTreature(int playerID) {
	     changePlayerMoneyByAccident(playerID,TRUESURE_INCRISE_MONEY);
	   }
	   //飞来的横祸-------上岛玩家金币 -5
	    public void meetUnespectedAccident(int playerID) {
		changePlayerMoneyByAccident(playerID,ACCIDENT_DECRISE_MONEY);
	    }
	    
	//交易岛事件
		//购买海盗保护费
		public void tradeForProtection(int playerID) {
			for(Player aPlayer : players) {
				if(aPlayer.getPlayerID() == playerID) {
					changePlayerMoneyByAccident(playerID,PROTECT_FEE);
					aPlayer.setIsProtected(true);
				}
			}
		}
		//购买*满*船只耐久度
		public void tradeDurablity(int playerID) {
			changePlayerMoneyByAccident(playerID,FULLBOATDURABILITY_FEE);
			changeBoatDurabilityByAccident(playerID,4);
		}
		//购买船只提升一个等级
		public void tradeForUpDateBoat(int playerID) {
			for(Player aPlayer : players) {
				if(aPlayer.getPlayerID() == playerID) {
					//上岛玩家
					changePlayerMoneyByAccident(playerID,UPDATEBOAT_FEE);
					int targetRank=aPlayer.getTheBoat().getRank()+1;
					aPlayer.rebuildBoat(targetRank);   //---------???????升级后的船类型？？？解决办法，给船设置等级
		           
				}
			}
		}
	    //使坏
		public void tradeForDoHarm(Player player,int targetPlayerID) {
			changePlayerMoneyByAccident(player.getPlayerID(),DOHARM_FEE);
			for(Player aPlayer: players) {
				if(aPlayer.getPlayerID()==targetPlayerID) {
					aPlayer.setIsHarmed(true);
				}
			}
		}
	
	
	//海域中发生的事件
	   //海中遇到风暴-------所有玩家船只耐久度 -1
	   public void meetStormInSea(Player aPlayer) {
		
			changeBoatDurabilityByAccident(aPlayer.getPlayerID(),STORM_CHANGE_DURABILITY);
			if(aPlayer.getTheBoat().getDurability() <= 0) {
				aPlayer.rebuildBoat(Boat.getSMALLBOAT_RANK());     //-------???????新建耐久度为 1 的小木船
				aPlayer.getTheBoat().setDurability(1);
			}
		
	}
	    //海中遇到海盗-------该玩家金币 -5 -------该玩家船只变为小木船（耐久度满）
	   public void meetPirateInSea(Player aPlayer) {
			    if(aPlayer.getIsProtected() == false) {
			    	changePlayerMoneyByAccident(aPlayer.getPlayerID(),PIRATE_CHANGE_MONEY);
				    aPlayer.rebuildBoat(Boat.getSMALLBOAT_RANK());     //-------???????新建 的小木船(耐久度满)    
                }
		
	}
	
	
}
