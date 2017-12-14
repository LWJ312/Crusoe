package model;

//船的父类代码
public class Boat {
	
	private String boatName;//船名
	private int durability;//耐久力
	private int speed;     //船速度
	private int buildTime; //新建船需要跳过的轮数
	private int pos_X;     //船的坐标（列）
	private int pos_Y;     //船的坐标（行）
	
	private int FULLDURABILITY;
	private static int  SMALLBOAT_RANK=1;
	private static int  MIDDLEBOAT_RANK=2;
	private static int  BIGBOAT_RANK=3;
	
	private static int rank;  //船等级，小船1，中船 2，大船3
	
	
    //自定义构造方法
  	public Boat(int durability,int speed,int buildTime,int pos_X,int pos_Y,String boatname) {
		this.setDurability(durability);
		this.setSpeed(speed);
		this.setBuildTime(buildTime);
		this.setPos_X(pos_X);
		this.setPos_Y(pos_Y);
		this.setBoatName(boatname);
	}
	

	//船移动的方法  返回船的位置数组 船的耐久度减一
	public int[] move(int n) {
		
		int[] boatpos=new int[2];//船的位置  数组
		boatpos[0]=this.pos_X;//数组第一个值是船的列坐标
		boatpos[1]=this.pos_Y;//数组第二个值是船的行坐标
		
		if(1==n) {//输入1使船向左运动
			boatpos[0]=boatpos[0]-1;
		}
		
		if(2==n) {//输入2使船向下运动
			boatpos[1]=boatpos[1]+1;
		}
		
		if(3==n) {//输入3使船向右运动
			boatpos[0]=boatpos[0]+1;
		}
		
		if(5==n) {//输入5使船向上运动
			boatpos[1]=boatpos[1]-1;
		}
		
		this.pos_X=boatpos[0];//更新船的位置坐标
		this.pos_Y=boatpos[1];
		this.durability=this.changeDurability(1);
		
		return boatpos;
	}
	
	public int changeDurability(int n) {//改变船的耐久度
		this.durability-=n;
		
		return this.durability;
	}
	
	
	public int getFULLDURABILITY() {
		return FULLDURABILITY;
	}


	public void setFULLDURABILITY(int fULLDURABILITY) {
		FULLDURABILITY = fULLDURABILITY;
	}


	public String getBoatName() {
		return boatName;
	}

	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}
	
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(int buildTime) {
		this.buildTime = buildTime;
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


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}


	public static int getSMALLBOAT_RANK() {
		return SMALLBOAT_RANK;
	}


	public static void setSMALLBOAT_RANK(int sMALLBOAT_RANK) {
		SMALLBOAT_RANK = sMALLBOAT_RANK;
	}


	public static int getMIDDLEBOAT_RANK() {
		return MIDDLEBOAT_RANK;
	}


	public static void setMIDDLEBOAT_RANK(int mIDDLEBOAT_RANK) {
		MIDDLEBOAT_RANK = mIDDLEBOAT_RANK;
	}


	public static int getBIGBOAT_RANK() {
		return BIGBOAT_RANK;
	}


	public static void setBIGBOAT_RANK(int bIGBOAT_RANK) {
		BIGBOAT_RANK = bIGBOAT_RANK;
	}
	

}