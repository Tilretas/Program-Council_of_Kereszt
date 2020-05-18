package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import game.Game;
import game.Player;



/**
 * Maga az ablak(frame), bal oldal�n az action gombok, k�z�pen a mez�k, jobb oldalon a t�rgyak
 * 
 *
 */
public class View extends JFrame
{
	private ActionPanel actionPanel;
	private BoardPanel boardPanel;
	private ItemPanel itemPanel;
	private StatPanel statPanel;
	
	private JPanel panel;

	public View()
	{
		super("I miss my Coffin Niggas :'(");
		Game.getInstance().setView(this);
		init();
	}
	
	public void init()
	{
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		actionPanel = new ActionPanel();
		boardPanel = new BoardPanel();
		itemPanel = new ItemPanel();
		
		actionPanel.setBackground(new Color(91, 92, 110));
		itemPanel.setBackground(new Color(91, 92, 110));

		
		panel.add(actionPanel, BorderLayout.WEST);
		panel.add(boardPanel, BorderLayout.CENTER);
		panel.add(itemPanel, BorderLayout.EAST);
		
		actionPanel.setMinimumSize(new Dimension(100, 700));
		itemPanel.setMinimumSize(new Dimension(100, 700));

		Draw();
		add(panel);
		setLocation(500, 200);
        //setMinimumSize(new Dimension(700, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

	}
	
	public void Draw() 
	{
		actionPanel.Draw(this);
		boardPanel.Draw(this, Game.getInstance().getBoard().getTiles());
		itemPanel.Draw(this, Game.getInstance().getActivePlayer().getPiece().getInventory());
	}
	
	public void Refresh() 
	{
		boardPanel.Refresh();
		itemPanel.Draw(this, Game.getInstance().getActivePlayer().getPiece().getInventory());
	}

	/*
	public void paintComponent(Graphics g)
	{
		
	}
	
	public void clear()
	{
		
	}
	
	public void validate()
	{
		
	}*/

}
