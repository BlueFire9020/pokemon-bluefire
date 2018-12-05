package bluefire.editor;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class ThickBevelBorder extends AbstractBorder
{
	private Color lightBevel;
	private Color darkBevel;
	private Color backgroundColor;
	private int thickness;
	
	public ThickBevelBorder(Color _lightBevel, Color _darkBevel, int _thickness)
	{
		this(_lightBevel,_darkBevel,null,_thickness);
	}
	
	public ThickBevelBorder(Color _lightBevel, Color _darkBevel, Color _backgroundColor, int _thickness)
	{
		lightBevel = _lightBevel;
		darkBevel = _darkBevel;
		backgroundColor = _backgroundColor;
		thickness = Math.max(0,_thickness);
	}
	
	@Override
	public void paintBorder(Component c, Graphics page, int x, int y, int width, int height)
	{
		super.paintBorder(c, page, x, y, width, height);
		
		page.setColor(darkBevel);
		page.fillRect(x,y,width, thickness);
		page.fillRect(x,y,thickness, height);
		
		page.setColor(lightBevel);
		page.fillRect(x + thickness, height - thickness, width - thickness, thickness);
		page.fillRect(width - thickness,y + thickness,thickness, height - thickness);
		page.fillPolygon(new int[] {x, thickness, thickness}, new int[] {height, height - thickness, height}, 3);
		page.fillPolygon(new int[] {width - thickness, width, width}, new int[] {y + thickness, y + thickness, y}, 3);
		
		if (backgroundColor != null)
		{
			page.setColor(backgroundColor);
			page.fillRect(x + thickness, y + thickness, width - (thickness * 2), height - (thickness * 2));
		}
		
		
	}
	
	@Override
	public Insets getBorderInsets(Component c)
	{
		 return new Insets(thickness, thickness, thickness + 1, thickness + 1);
	}
}
