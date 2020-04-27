package game;

public class Unstable extends Ice
{
	public Unstable(int c, int s) {
		super(c,s);
	}
	
	public void movedOn(Piece p)
	{
		pieces.add(p);
		p.setOnTile(this);
		if(pieces.size() > getCapacity())
			flip();
	}
	
	public void flip()
	{
		for(int i = 0; i < getCapacity(); i++)
			pieces.get(i).setInWater(true);
	}
}
