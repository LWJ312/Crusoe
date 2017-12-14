package model;

public class Land {
	//父类岛
	private String land_name;
	private int landID;
    private int pos_X;//横坐标
    private int pos_Y;//纵坐标
    
    Player aPlayer;//岛上暂时停留的玩家 
    Accident accidents;
	public Land(int landId,int pos_X, int pos_Y,Accident accident) {
		this.landID=landId;
		this.pos_X = pos_X;
		this.pos_Y = pos_Y;
		this.setAccidents(accident);
	}
	
    //停留者造船
	public void offerTreesForBuildASmallBoat() {
		aPlayer.whetherNeedRebuild();
    }
	
	//getters and setters
	
	 public String getLand_name() {
			return land_name;
	}

	public Accident getAccidents() {
		return accidents;
	}

	public void setAccidents(Accident accidents) {
		this.accidents = accidents;
	}

	public void setLand_name(String land_name) {
			this.land_name = land_name;
	}
		
    public int getPos_X() {
		return pos_X;
	}


	public int getLandID() {
		return landID;
	}

	public void setLandID(int landID) {
		this.landID = landID;
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

    /*获得/设置岛上暂时停留的玩家信息*/
	public Player getaPlayer() {
		return aPlayer;
	}


	public void setaPlayer(Player aPlayer) {
		this.aPlayer = aPlayer;
	}

}