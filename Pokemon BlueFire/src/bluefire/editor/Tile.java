package bluefire.editor;

import java.awt.Color;

public class Tile 
{
	private int resolution;
	private Color[][] image;
	private final Color DEFAULT_FILL = Color.white;
	
	public Tile(int _resolution, Color[][] img)
	{
		resolution = _resolution;
		if (img == null)
		{
			image = new Color[resolution][resolution];
			for (int y = 0; y < resolution; y++)
			{
				for (int x = 0; x < resolution; x++)
				{
					image[x][y] = DEFAULT_FILL;
				}	
			}	
		}
		else
		{
			image = img;
		}
	}
	
	public int getResolution()
	{
		return resolution;
	}
	
	public Color[][] getPixels()
	{
		return image;
	}
	
	public Color getPixelAt(int x, int y)
	{
		return image[x][y];
	}
	
	public void setPixelAt(int x, int y, Color newColor)
	{
		image[x][y] = newColor;
	}
}
