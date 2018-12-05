package bluefire.editor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TileEditor extends JPanel
{
	private int resolution = 16;
	private int pixelScale = 15;

	private Color currentColor;
	//trying to implement dragging with mouse down
	private boolean mouseDown;
	private int selectedTool = 0;

	private Tile openTile;
	private JPanel preview;
	private JPanel curColor;
	private JFrame window;

	public static final Color GRAY = new Color(189,189,189);
	public static final Color LIGHT_GRAY = new Color(255,255,255);
	public static final Color DARK_GRAY = new Color(123,123,123);

	public TileEditor()
	{
		CreateNewTile();
		//remove me later
		currentColor = Color.blue;
		setBorder(new ThickBevelBorder(DARK_GRAY,LIGHT_GRAY,GRAY,5));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		preview = new JPanel();
		GridBagConstraints cx = new GridBagConstraints();
		preview.setBorder(new ThickBevelBorder(DARK_GRAY,LIGHT_GRAY,GRAY,5));
		preview.setLayout(new GridBagLayout());
		preview.setMaximumSize(new Dimension(resolution * pixelScale, resolution * pixelScale));
		for (int y = 0; y < resolution; y++)
		{
			for (int x = 0; x < resolution; x++)
			{
				JPanel pixel = new JPanel();
				JButton dataHolder = new JButton();
				//hacky way of storing data without creating a custom method
				dataHolder.setName(x + "");
				dataHolder.setText(y + "");
				dataHolder.setVisible(false);
				pixel.add(dataHolder);
				//pixel.setPreferredSize(new Dimension(pixelScale, pixelScale));
				pixel.setBackground(openTile.getPixelAt(x,y));
				pixel.setPreferredSize(new Dimension(pixelScale, pixelScale));
				pixel.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						if (mouseDown && selectedTool == 1)
						{
							JButton butt = (JButton)pixel.getComponent(0);
							openTile.setPixelAt(Integer.parseInt(butt.getName()),Integer.parseInt(butt.getText()), currentColor);
							pixel.setBackground(currentColor);
						}
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						if (selectedTool == 0)
						{
							FillTile();
						}
						else if (selectedTool == 1)
						{
							JButton butt = (JButton)pixel.getComponent(0);
							openTile.setPixelAt(Integer.parseInt(butt.getName()),Integer.parseInt(butt.getText()), currentColor);
							pixel.setBackground(currentColor);
						}
						else if (selectedTool == 2)
						{
							JButton butt = (JButton)pixel.getComponent(0);
							currentColor = openTile.getPixelAt(Integer.parseInt(butt.getName()),Integer.parseInt(butt.getText()));
							curColor.setBackground(currentColor);
						}
						mouseDown = true;
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						mouseDown = false;
					}

				});
				cx.insets = new Insets(0,0,0,0);

				cx.gridx = x;
				cx.gridy = y;
				//cx.weighty = 1.0;
				//cx.weightx = 1.0;

				preview.add(pixel, cx);
			}	
		}
		add(preview, this);

		JPanel toolBar = new JPanel();
		toolBar.setBorder(new ThickBevelBorder(DARK_GRAY,LIGHT_GRAY,GRAY,5));
		
		JButton paintBucket = new JButton();
		JButton brush = new JButton();
		JButton palette = new JButton();
		JButton eyedropper = new JButton();

		paintBucket.setPreferredSize(new Dimension(25,25));
		try {
			paintBucket.setIcon(new javax.swing.ImageIcon(
					ImageIO.read(getClass().getClassLoader().getResourceAsStream("paintbucket.png")
							).getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH )));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paintBucket.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectedTool = 0;
				brush.setBackground(DARK_GRAY);
				paintBucket.setBackground(LIGHT_GRAY);
				eyedropper.setBackground(DARK_GRAY);
			}

		});
		brush.setPreferredSize(new Dimension(25,25));
		try {
			brush.setIcon(new javax.swing.ImageIcon(
					ImageIO.read(getClass().getClassLoader().getResourceAsStream("brush.png")
							).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH )));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		brush.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectedTool = 1;
				brush.setBackground(LIGHT_GRAY);
				paintBucket.setBackground(DARK_GRAY);
				eyedropper.setBackground(DARK_GRAY);
			}

		});
		eyedropper.setPreferredSize(new Dimension(25,25));
		try {
			eyedropper.setIcon(new javax.swing.ImageIcon(
					ImageIO.read(getClass().getClassLoader().getResourceAsStream("eyedropper.png")
							).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH )));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eyedropper.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectedTool = 2;
				brush.setBackground(DARK_GRAY);
				paintBucket.setBackground(DARK_GRAY);
				eyedropper.setBackground(LIGHT_GRAY);

			}

		});

		palette.setPreferredSize(new Dimension(25,25));
		try {
			palette.setIcon(new javax.swing.ImageIcon(
					ImageIO.read(getClass().getClassLoader().getResourceAsStream("palette.png")
							).getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH )));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		palette.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				currentColor = JColorChooser.showDialog(TileEditor.this,"Choose Brush Color",currentColor);
				curColor.setBackground(currentColor);
			}

		});

		curColor = new JPanel();
		curColor.setPreferredSize(new Dimension(25,25));
		curColor.setBackground(currentColor);

		JButton openFile = new JButton();
		openFile.setText("Open");
		openFile.setPreferredSize(new Dimension(75,25));
		openFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FileDialog fd = new FileDialog(window, "Choose a tile", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setFile("*.png");
				fd.setVisible(true);
				String filename = fd.getDirectory() + fd.getFile();
				OpenTile(filename);
			}

		});

		JButton saveFile = new JButton();
		saveFile.setText("Save");
		saveFile.setPreferredSize(new Dimension(75,25));
		saveFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FileDialog fd = new FileDialog(window, "Choose a tile", FileDialog.SAVE);
				fd.setDirectory("C:\\");
				fd.setFile("*.png");
				fd.setVisible(true);
				String filename = fd.getDirectory() + fd.getFile() + ".png";
				SaveTile(filename);
			}

		});

		JButton newFile = new JButton();
		newFile.setText("New");
		newFile.setPreferredSize(new Dimension(75,25));
		newFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CreateNewTile();
				RefreshPreview();
			}

		});




		toolBar.add(paintBucket);
		toolBar.add(brush);
		toolBar.add(eyedropper);
		toolBar.add(palette);
		toolBar.add(curColor);
		toolBar.add(openFile);
		toolBar.add(saveFile);
		toolBar.add(newFile);
		add(toolBar);

	}

	public static void main(String[] args)
	{
		JFrame window = new JFrame("Tile Editor");	
		window.setLayout(new FlowLayout());
		window.add(new TileEditor());
		window.pack();
		window.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
	}

	public void SaveTile(String location)
	{
		try 
		{
			Color[][] image = openTile.getPixels();
			System.out.println(openTile.getPixelAt(10, 10).getRGB());
			BufferedImage bufferedImage = new BufferedImage(image.length, image[0].length,
					BufferedImage.TYPE_INT_RGB);

			// Set each pixel of the BufferedImage to the color from the Color[][].
			for (int x = 0; x < image.length; x++) 
			{
				for (int y = 0; y < image[x].length; y++) 
				{
					bufferedImage.setRGB(x, y, image[x][y].getRGB());
				}
			}
			System.out.println(location);
			File outputfile = new File(location);
			ImageIO.write(bufferedImage, "png", outputfile);
		}
		catch (Exception e) {
		}

	}

	public void OpenTile(String location)
	{
		Color[][] pixelMap;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(location));
			pixelMap = new Color[img.getWidth()][img.getWidth()];

			for (int y = 0; y < img.getWidth(); y++)
			{
				for (int x = 0; x < img.getWidth(); x++)
				{
					System.out.println(img.getRGB(x,y));
					pixelMap[x][y] = new Color(img.getRGB(x,y));
				}
			}
			openTile = new Tile(img.getWidth(), pixelMap);
			RefreshPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CreateNewTile()
	{
		openTile = new Tile(resolution, null);
	}

	public void FillTile()
	{
		for (int i = 0; i < preview.getComponentCount(); i++)
		{
			JPanel pixel = (JPanel)preview.getComponent(i);
			JButton butt = (JButton)pixel.getComponent(0);
			openTile.setPixelAt(Integer.parseInt(butt.getName()),Integer.parseInt(butt.getText()), currentColor);
			pixel.setBackground(currentColor);
		}
	}

	public void RefreshPreview()
	{
		for (int i = 0; i < preview.getComponentCount(); i++)
		{
			JPanel pixel = (JPanel)preview.getComponent(i);
			JButton butt = (JButton)pixel.getComponent(0);
			pixel.setBackground(openTile.getPixelAt(Integer.parseInt(butt.getName()),Integer.parseInt(butt.getText())));
			curColor.setBackground(currentColor);
		}
	}

	public Color getCurrentColor()
	{
		return currentColor;
	}
	public void setCurrentColor(Color newColor)
	{
		currentColor = newColor;
	}

	public boolean isMouseDown()
	{
		return mouseDown;
	}



}
