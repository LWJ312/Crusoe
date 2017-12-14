package view;
import model.*;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OtherInfoView extends JPanel{
	//右侧信息面板
	//玩家信息面板
	private JPanel playersInfos;
	
	//玩家信息背景图片
	//private BufferedImage playerInfo_bg = null;
	//玩家信息面板数组
    private PlayerInfoView[] playersInfoView;
    private LandInfoView landInfoView;
    
    public OtherInfoView(Player[] players,Object allPoints) {
    	playersInfoView = new PlayerInfoView[3];
    	landInfoView = new LandInfoView(allPoints);
    	
    	playersInfoView[0] = new PlayerInfoView(players[0]);
    	playersInfoView[1] = new PlayerInfoView(players[1]);
    	playersInfoView[2] = new PlayerInfoView(players[2]);

    	playersInfos = new JPanel();
    	playersInfos.setOpaque(false);
    	this.playersInfos.setLayout(new GridLayout(3, 1));
    	for(PlayerInfoView playerInfoView : playersInfoView) {
    		this.playersInfos.add(playerInfoView);
    	}
    	
        this.setOpaque(false);

    	this.setLayout(new GridLayout(2, 1));
    	this.add(playersInfos);
    	this.add(landInfoView);
    }
    
    
    @Override
	public void paint(Graphics g) {
        
    	super.paint(g);
    	for(PlayerInfoView aPlayerInfoView:this.getPlayersInfoView()) {
    		aPlayerInfoView.repaint();
    	}
    	this.getLandInfoView().repaint();
    }

    
	public JPanel getPlayersInfos() {
		return playersInfos;
	}

	public void setPlayersInfos(JPanel playersInfos) {
		this.playersInfos = playersInfos;
	}

	public PlayerInfoView[] getPlayersInfoView() {
		return playersInfoView;
	}

	public void setPlayersInfoView(PlayerInfoView[] playersInfoView) {
		this.playersInfoView = playersInfoView;
	}

	public LandInfoView getLandInfoView() {
		return landInfoView;
	}

	public void setLandInfoView(LandInfoView landInfoView) {
		this.landInfoView = landInfoView;
	}
}
