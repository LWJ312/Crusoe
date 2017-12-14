package model;

public class Boat_MiddleBoat extends Boat {
	//中船的代码

	private static int MIDDLEBOAT_DURABILITY=3;
	private static int MIDDLEBOAT_SPEED=2;
	private static int MIDDLEBOAT_BUILDTIME=2;

	//重写父类构造方法
	
	//无参默认构造方法，构造一个满耐久度的船
	public Boat_MiddleBoat() {
	    super(MIDDLEBOAT_DURABILITY, MIDDLEBOAT_SPEED, MIDDLEBOAT_BUILDTIME, 0, 0,"middleBoat");
	    //船的位置在重建时由玩家重建方法传给他的船只
	    this.setFULLDURABILITY(MIDDLEBOAT_DURABILITY);
	    this.setRank(Boat.getMIDDLEBOAT_RANK());
	}
		    
	//有参构造函数，构造一个被设定了耐久度为n的船
	public Boat_MiddleBoat(int durability) {
		super(durability, MIDDLEBOAT_SPEED, MIDDLEBOAT_BUILDTIME, 0, 0,"middleBoat");
	}

}