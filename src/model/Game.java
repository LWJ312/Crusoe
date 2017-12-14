package model;

import java.util.Random;

public class Game {
    /*属性*/
    //玩家数组
	private static Player[] players;
	
    //岛数组[11][8]--共44，神秘岛6，交易岛4，普通岛34
	private int MYSTERYLAND_NUM = 6;
	private int TRADELAND_NUM = 4;
	private int SIMPLELAND_NUM=34;
	
	//每行点的数量
	private int ALL_NUM_X = 13;
	private int ALL_NUM_Y = 10;
	
	//所有数组
	private Object[][] allPoints = new Object[ALL_NUM_X][ALL_NUM_Y];
		
    //当前正在操作的玩家
	private Player thePlayer;
	//事件类
	private Accident accidents;
	
	//神秘岛位置二维数组
	int[][] mystaryLand_position = {{1,5},{3,2},{5,4},{8,3},{8,7},{10,6}};
	//交易岛位置二维数组
	int[][] tradeLand_position =  {{2,7},{3,3},{6,2},{7,5}};
	//普通小岛
	int[][] simpleland_position = {{1,1},{1,3},{1,7},{2,2},{2,5},{2,8},{3,1},{3,4},{3,6},{4,3},
				{4,5},{4,8},{5,2},{5,3},{5,6},{6,1},{6,3},{6,4},{6,5},{6,7},{7,2},{7,4},{7,6},
				{8,2},{8,5},{9,1},{9,4},{9,6},{9,8},{10,2},{10,5},{10,7},{11,3},{11,6}};
	//初始化玩家金币，随机分配2,3,4
	int[] money = {12,10,8};
	
    /*方法*/	
    //初始化玩家，给玩家分配小船，金币
	public Game() {
		
		players=new Player[3];
		//对三个玩家进行初始化，编号1,2,3---初始金币0---初始都分配满耐久度的小木船
		players[0] = new Player(1, 0, new Boat_SmallBoat());
		players[1] = new Player(2, 0, new Boat_SmallBoat());
		players[2] = new Player(3, 0, new Boat_SmallBoat());
		accidents=new Accident(players);
		
		Random aRandom=new Random();
		int num = aRandom.nextInt(3);//随机产生一个0~2的整数
		players[0].setMoney(money[num]);
		num = aRandom.nextInt(3);
		while(money[num] == players[0].getMoney()) {
			num = aRandom.nextInt(3);
		}
		players[1].setMoney(money[num]);
		while((money[num] == players[0].getMoney())||(money[num] == players[1].getMoney())) {
			num = aRandom.nextInt(3);
		}
		players[2].setMoney(money[num]);
		
		//根据金币重新分配玩家ID（开始顺序）
		int temp;
		for(int i=0;i<players.length;i++) {
			for(int j=i+1;j<players.length;j++) {
				if((players[i].getMoney()>players[j].getMoney())) {
					temp=players[i].getPlayerID();
					players[i].setPlayerID(players[j].getPlayerID());
					players[j].setPlayerID(temp);
				}
			}
			
		}
				
		//重置玩家在数组中的顺序
		Player minPlayer;
		for(int i=0; i<players.length; i++) {	
			for(int j=i+1; j<players.length; j++) {
				if(players[i].getPlayerID()>players[j].getPlayerID()) {					
					minPlayer = players[i];
					players[i] = players[j];
					players[j] = minPlayer;
				}
			}
		}
		//初始化玩家位置和船的位置
		for(Player player:players) {
		   player.setPos_X(0);
		   player.setPos_Y(0);
		   player.getTheBoat().setPos_X(player.getPos_X());
		   player.getTheBoat().setPos_Y(player.getPos_Y());
		}
		//刚开始正在游戏的玩家，初始化为排序后的玩家1,数组0元素
		thePlayer = players[0];
		
		//岛数组初始化
		//加入神秘岛，6元素,岛ID从1---6
		for(int i=0; i<MYSTERYLAND_NUM; i++) {
			int x = mystaryLand_position[i][0];
			int y = mystaryLand_position[i][1];
			allPoints[x][y] = createMysteryLands()[i];
		}
		//初始化交易岛，4元素,岛ID从7---10
		for(int i=0; i<TRADELAND_NUM; i++) {
			int x = tradeLand_position[i][0];
			int y = tradeLand_position[i][1];
			allPoints[x][y] = createTradeLands()[i];
		}

		//初始化普通小岛,岛ID从11----44
		for(int i=0; i<SIMPLELAND_NUM; i++) {
			int x = simpleland_position[i][0];
			int y = simpleland_position[i][1];
		    allPoints[x][y] = createSimpleLands()[i];
		}
		
		//剩余（海域）
	    for(int x=0;x<ALL_NUM_X;x++) {
			for(int y=0;y<ALL_NUM_Y;y++) {
			  int count = 0;
			   if(allPoints[x][y] == null) {
		           allPoints[x][y] = new Sea(MYSTERYLAND_NUM + TRADELAND_NUM + SIMPLELAND_NUM + count +1, x, y,this.getAccidents());
							count++;
						}
					}
				}
	}
	
	
	//判断胜利
	public boolean isSomeoneWin() {
		for(Player player : players) {
			if((player.getPos_X() >11)&&(player.getPos_Y() >8)) {
				player.setIsWin(true);
				return true;
			}
		}
		return false;
	}
	
	//初始化一个神秘岛数组，6元素,岛ID从1---6
	public Land_MysteryLand[] createMysteryLands() {
		Land_MysteryLand[] mysteryLands = new Land_MysteryLand[MYSTERYLAND_NUM];
		for(int i=0; i<MYSTERYLAND_NUM; i++) {
			mysteryLands[i] = new Land_MysteryLand(i+1, mystaryLand_position[i][0], mystaryLand_position[i][1],this.getAccidents());
		}
		return mysteryLands;
	}
	
	//初始化一个交易岛数组，4元素,岛ID从7---10
	public Land_TradeLand[] createTradeLands() {
		Land_TradeLand[] tradeLands = new Land_TradeLand[TRADELAND_NUM];
		for(int i=0; i<TRADELAND_NUM; i++) {
			tradeLands[i] = new Land_TradeLand(MYSTERYLAND_NUM+i+1, tradeLand_position[i][0], tradeLand_position[i][1],this.getAccidents());
		}
		return tradeLands;
	}

	//初始化一个普通岛数组，34元素，岛ID从11---44
	public Land_SimpleLand[] createSimpleLands() {
		Land_SimpleLand[] simpleLands = new Land_SimpleLand[SIMPLELAND_NUM];
		for(int i=0; i<SIMPLELAND_NUM; i++) {
			double goodPossibilty=getChanceOfAccidentInLand();
			simpleLands[i] = new Land_SimpleLand(MYSTERYLAND_NUM + TRADELAND_NUM + i +1, simpleland_position[i][0], simpleland_position[i][1], goodPossibilty, 1-goodPossibilty,this.getAccidents());
		}
		return simpleLands;
	}
	//产生岛上事件概率，“渔民的祝福”，“海盗的巢穴”
	public double getChanceOfAccidentInLand() {
		int num = (int)(Math.random()*4);
		double chance = 0;
		if(num == 0) {
			chance = 0;
		}
		if(num == 1) {
			chance = 0.2;
		}
		if(num == 2) {
			chance = 0.3;
		}
		if(num == 3) {
			chance = 0.5;
		}
		return chance;
	}

	
    //getters,setters
	
	
	public Player[] getPlayers() {
		return players;
	}

	public Accident getAccidents() {
		return accidents;
	}


	public void setAccidents(Accident accidents) {
		this.accidents = accidents;
	}


	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public int getMYSTERYLAND_NUM() {
		return MYSTERYLAND_NUM;
	}

	public void setMYSTERYLAND_NUM(int mYSTERYLAND_NUM) {
		MYSTERYLAND_NUM = mYSTERYLAND_NUM;
	}

	public int getTRADELAND_NUM() {
		return TRADELAND_NUM;
	}

	public void setTRADELAND_NUM(int tRADELAND_NUM) {
		TRADELAND_NUM = tRADELAND_NUM;
	}


	public int getALL_NUM_X() {
		return ALL_NUM_X;
	}


	public void setALL_NUM_X(int aLL_NUM_X) {
		ALL_NUM_X = aLL_NUM_X;
	}


	public int getALL_NUM_Y() {
		return ALL_NUM_Y;
	}


	public void setALL_NUM_Y(int aLL_NUM_Y) {
		ALL_NUM_Y = aLL_NUM_Y;
	}


	public Object[][] getAllPoints() {
		return allPoints;
	}


	public void setAllPoints(Object[][] allPoints) {
		this.allPoints = allPoints;
	}


	public Player getThePlayer() {
		return thePlayer;
	}

	public void setThePlayer(Player thePlayer) {
		this.thePlayer = thePlayer;
	}

	public int[][] getMystaryLand_position() {
		return mystaryLand_position;
	}

	public void setMystaryLand_position(int[][] mystaryLand_position) {
		this.mystaryLand_position = mystaryLand_position;
	}
	
	public int[][] getTradeLand_position() {
		return tradeLand_position;
	}

	public void setTradeLand_position(int[][] tradeLand_position) {
		this.tradeLand_position = tradeLand_position;
	}

}
