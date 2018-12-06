package bluefire.game;

public class Vector2 
{
	private int x, y;
	
	public Vector2(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int newX)
	{
		x = newX;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int newY)
	{
		y = newY;
	}
}
