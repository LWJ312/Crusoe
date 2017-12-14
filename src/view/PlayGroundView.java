package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import controller.GameController;
import model.Game;
import model.Land;
import model.Land_MysteryLand;
import model.Land_SimpleLand;
import model.Land_TradeLand;


public class PlayGroundView extends JPanel {
	private int IMAGESIZE=32;
	private ImageIcon[] boatsPic=new ImageIcon[3];
	private ImageIcon[] landsPic=new ImageIcon[3];
	
	private GameController aGameController;
	
	static int PLAYGROUNDVIEW_W=900;
	static int PLAYGROUNDVIEW_H=600;
	
    public PlayGroundView() {   
    	//设置游戏场景的背景图片
		this.setPreferredSize(new Dimension(PLAYGROUNDVIEW_W, PLAYGROUNDVIEW_H));
		ImageIcon backgroundImage=new ImageIcon("./src/pic/sea.png");
		Image image=backgroundImage.getImage().getScaledInstance(PLAYGROUNDVIEW_W, PLAYGROUNDVIEW_H, Image.SCALE_FAST);
		JLabel label=new JLabel();
		label.setIcon(new ImageIcon(image));
		this.add(label);
		//
	    aGameController=null;
	    //
	    boatsPic[0]=new ImageIcon("./src/pic/smallBoat1.png");
	    boatsPic[1]=new ImageIcon("./src/pic/smallBoat2.png");
	    boatsPic[2]=new ImageIcon("./src/pic/smallBoat3.png");
	    
	    landsPic[0]=new ImageIcon("./src/pic/land_simple.png");
	    landsPic[1]=new ImageIcon("./src/pic/land_mystery.png");
	    landsPic[2]=new ImageIcon("./src/pic/land_trade.png");
	    
	    
		
    }
    
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
	
		super.paint(g);
		for(int i=0;i<boatsPic.length;i++) {
			if(this.getaGameController().getGame().getPlayers()[i].getTheBoat().getPos_X()==12) {
			g.drawImage(boatsPic[i].getImage(),(int)((getaGameController().getGame().getPlayers()[i].getPos_X()-(4-i)*0.1)*75),(int)(getaGameController().getGame().getPlayers()[i].getPos_Y()*66), this);
				
			}
			else g.drawImage(boatsPic[i].getImage(),(int)((getaGameController().getGame().getPlayers()[i].getPos_X()+i*0.1)*75),(int)(getaGameController().getGame().getPlayers()[i].getPos_Y()*66)-10, this);
			
		}
	
//		for(Land[] Lands:getaGameController().getGame().getLands()) {
//			for(Land aLand:Lands) {
////				if(aLand.getClass()==Land_SimpleLand.class) {
////					g.drawImage(landsPic[0].getImage(),aLand.getPos_X()*100,aLand.getPos_Y()*100, this);
////				}
//				if(aLand.getClass()==Land_MysteryLand.class) {
//					System.out.println(1);
//					g.drawImage(landsPic[1].getImage(),aLand.getPos_X()*100,aLand.getPos_Y()*100, this);
//				}
////				if(aLand.getClass()==Land_TradeLand.class) {
////					g.drawImage(landsPic[2].getImage(),aLand.getPos_X()*100,aLand.getPos_Y()*100, this);
////				}
////				
//			}
//		}
		
	}
	
	public ImageIcon[] getBoatsPic() {
		return boatsPic;
	}

	public void setBoatsPic(ImageIcon[] boatsPic) {
		this.boatsPic = boatsPic;
	}

	public GameController getaGameController() {
		return aGameController;
	}
	public void setaGameController(GameController aGameController) {
		this.aGameController = aGameController;
	}
   
}
