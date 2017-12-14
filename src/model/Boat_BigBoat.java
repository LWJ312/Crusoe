package model;

public class Boat_BigBoat extends Boat {

	//大船的代码
		
	private static int BIGBOAT_DURABILITY=4;
	private static int BIGBOAT_SPEED=3;
	private static int BIGBOAT_BUILDTIME=3;
		
	//重写父类构造方法
		
	//无参默认构造方法，构造一个满耐久度的船
	public Boat_BigBoat() {
	    super(BIGBOAT_DURABILITY, BIGBOAT_SPEED, BIGBOAT_BUILDTIME, 0, 0,"bigBoat");
	  //船的位置在重建时由玩家重建方法传给他的船只
	    this.setFULLDURABILITY(BIGBOAT_DURABILITY);
	    this.setRank(Boat.getBIGBOAT_RANK());
	}
	    
	//有参构造函数，构造一个被设定了耐久度为n的船
	public Boat_BigBoat(int durability) {
	    super(durability, BIGBOAT_SPEED, BIGBOAT_BUILDTIME, 0, 0,"bigBoat");
	}
	
}