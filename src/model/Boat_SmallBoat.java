package model;

public class Boat_SmallBoat extends Boat {

	//小船的代码

	private static int SMALLBOAT_DURABILITY=2;//小船耐久度的常量
	private static int SMALLBOAT_SPEED=1;//小船速度常量
	private static int SMALLBOAT_BUILDTIME=1;//小船建造时间常量

	//重写父类构造方法
	
	//无参默认构造方法，构造一个满耐久度的船
    public Boat_SmallBoat() {
    	super(SMALLBOAT_DURABILITY, SMALLBOAT_SPEED, SMALLBOAT_BUILDTIME, 0, 0,"smallBoat");
    	//船的位置在重建时由玩家重建方法传给他的船只
    	this.setFULLDURABILITY(SMALLBOAT_DURABILITY);
    	this.setRank(Boat.getSMALLBOAT_RANK());
    }
    

	//有参构造函数，构造一个被设定了耐久度为n的船
    public Boat_SmallBoat(int durability) {
    	super(durability, SMALLBOAT_SPEED, SMALLBOAT_BUILDTIME, 0, 0,"smallBoat");
    }

}