package game;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Piece.java
//  @ Date : 2020. 04. 22.
//  @ Author : 
//
//




public abstract class Piece
{
	private int bodyTemp;
	private int actionPoints;
	private boolean inWater;
	private Board pieces;
	private Tile onTile;
	private Ice pieces;
	private Colour colour;
	public void moved(Tile t)
	{
	}
	
	public abstract void ability(Tile t);
	
	public void addItem(Item i)
	{
	}
	
	public void removeItem(Item i)
	{
	}
	
	public void useItem(Item i)
	{
	}
	
	public void incBodyTemp()
	{
	}
	
	public void decBodyTemp()
	{
	}
	
	public void setInWater(boolean value)
	{
	}
	
	public Tile getTile()
	{
	}
}
