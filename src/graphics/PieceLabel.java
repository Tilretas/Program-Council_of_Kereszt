package graphics;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.Colour;
import game.Piece;

/**
 * 1 b�bu k�p�t jelen�ti meg
 * 
 *
 */
public class PieceLabel extends JLabel{
	private Piece piece;
	private Colour colour;
	private ImageIcon icon;
	
	public PieceLabel(game.Piece p) 
	{
		piece = p;
		colour = p.getColour();
		System.out.println(colour);
	}
	
	public void Draw() 
	{
		setIcon(new ImageIcon(new ImageIcon("Images/" + piece.getType() + colour + ".png").getImage().getScaledInstance(20, 40, Image.SCALE_SMOOTH)));
	}
}
