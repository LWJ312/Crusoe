package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Game;


public class GameView extends JPanel{
	
	private Game game;
	private Image image;
	
	private PlayGroundView playGroundView;
	private OtherInfoView otherInfoView;
	
	public GameView(Game game) {
		this.setLayout(new FlowLayout());
//		this.playGroundView=new PlayGroundView();
//		this.playerInfoView=new PlayerInfoView();
//		this.add(this.playGroundView);
//		this.add(this.playerInfoView);
		this.setGame(game);
		this.playGroundView=new PlayGroundView();
		this.otherInfoView=new OtherInfoView(getGame().getPlayers(),getGame().getAllPoints()[0][0]);
		this.add(this.playGroundView);
		this.add(this.otherInfoView);
//		AccidentDialogView.wrongMoneyLacked();
//		AccidentDialogView.chooseServe();
		image=Toolkit.getDefaultToolkit().getImage("./src/pic/infoPic.png");
		
	}

    @Override
    public void paint(Graphics g) {  
    	super.paint(g);
    	this.getPlayGroundView().repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	int imWidth=image.getWidth(this); 
        int imHeight=image.getHeight(this);
        int FWidth = this.getWidth();
        int FHeight = this.getHeight();
        int x=(FWidth-imWidth)/2;
        int y=(FHeight-imHeight)/2;
        g.drawImage(image,900,0,null);
    }
    
    
	public PlayGroundView getPlayGroundView() {
		return playGroundView;
	}

	public void setPlayGroundView(PlayGroundView playGroundView) {
		this.playGroundView = playGroundView;
	}

	public OtherInfoView getOtherInfoView() {
		return otherInfoView;
	}

	public void setotherInfoView(OtherInfoView otherInfoView) {
		this.otherInfoView = otherInfoView;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
	
	

}
