
import java.io.*;
import java.awt.*;
//import java.applet.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.image.BufferedImage;



//import java.applet.AudioClip;
//import java.net.URL;


public class Application
{
	public static void main(String args[])
	{
		AppListClass tester = new AppListClass();
		tester.addWindowListener(new WindowAdapter()
		{public void windowClosing(WindowEvent e)
		{System.exit(0);}});
		tester.setSize(1200,900);
		tester.setVisible(true);
	}
}
class AppListClass extends Frame implements KeyListener
{
	
	int numRows = 36;  //  35 Rows are displayed.  The 

top row is hidden behind the title bar.
	int numCols = 50;
	String background[];
	String fileName;
	private Graphics gBuffer;
	private Image gBufferedImage;
	private boolean first = true;
	
	BufferedImage img = null;

	int xPos = 35;
	int yPos = 49;

	public AppListClass()
	{
		//setSize(900, 900);
		
		try {
			//Image loaded through ImageIO
		    img = ImageIO.read(new File

("loser.gif"));
		    
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed");
		}

		fileName = JOptionPane.showInputDialog

("Enter file name for graphics background.");
		background = new String[numRows];
		try
		{
			//sound = Applet1.newAudioClip(new 

URL("file:labsound.wav"));
			
			BufferedReader inStream = new 

BufferedReader(new FileReader(fileName));
			String line;
			int row = 0;
			line = inStream.readLine();
			while(line != null)
			{
				background[row] = line;
				row++;
				line = inStream.readLine();
			}
		}
		catch (IOException e)
		{
			System.out.println("There were 

problems with the code as stated below\n");
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		addKeyListener(this);
		
		//sound.loop();
	}


	public void paint(Graphics g)
	{
		if (first)
		{
			gBufferedImage = createImage

(getSize().width, getSize().height);
		    gBuffer = gBufferedImage.getGraphics();
		    gBuffer.setColor(Color.white);
		    gBuffer.fillRect(0, 0, getSize().width, 

getSize().height);
		    gBuffer.setColor(Color.black);
		    gBuffer.drawRect(0, 0, getSize().width - 

1, getSize().height - 1);
			
			gBuffer.setColor(Color.BLACK);
			gBuffer.fillRect(49 * 20 + 10, 35 * 

20 + 10, 20, 20);
			for (int i = 0; i < 

background.length; i++)
				for (int j = 0; j < 

background[i].length(); j++)
					identify(g,  

background[i].charAt(j), (i*20)+10, (j*20)+10);
			gBuffer.setColor(Color.RED);
			gBuffer.drawString("DANGER! DO NOT 

APPROACH", 30, 30);
			
			
			first = false;
		}
		if (xPos*20 > 40 || yPos*20 > 10)
		{
			g.translate(0, 20);
			g.drawImage(gBufferedImage, 0, 0, 

this);
			drawChar(g, xPos, yPos);
		}
		else
		{
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 1500, 1500);
			g.drawImage(img, 500, 500, null, 

null);
		}
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void identify(Graphics g, char q, int x, int 

y)
	{
		switch (q)
		{
		case '.': drawSpace(g, x, y); return;
		case '=': drawCrate(g, x, y); return;
		case '#': drawShrub(g, x, y); return;
		case 'W': drawWater(g, x ,y); return;
		case 'B': drawBrick(g, x, y); return;
		case 'T': drawChar(g, xPos, yPos); return;
		case 'E': drawEnemy(g, x, y); return;
		case '+': drawHealth(g, x, y); return;
		case 'A': Ammo(g, x, y); return;
		}
	}


	public void drawSpace (Graphics g, int y, int x)
	{
		//System.out.println("X: " + x + "Y: " + y);
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(x, y, 20, 20);
	}
	
	public void drawCrate (Graphics g, int y, int x)
	{
		gBuffer.setColor(Color.LIGHT_GRAY);
		gBuffer.fillRect(x, y, 20, 20);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawRoundRect(x+1, y+1, 17, 17, 2, 

2);
	}
	
	public void drawShrub (Graphics g, int y, int x)
	{
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(x, y, 20, 20);
		gBuffer.setColor(Color.GREEN);
		gBuffer.fillArc(x, y+8, 9, 3, 0, 360);
		gBuffer.fillArc(x+5, y+4, 9, 3, 0, 360);
		gBuffer.fillArc(x+9, y+8, 9, 3, 0, 360);
		gBuffer.fillArc(x+9, y+13, 9, 3, 0, 360);
		gBuffer.fillArc(x+3, y+17, 9, 3, 0, 360);
		gBuffer.fillArc(x+14, y+17, 6, 3, 0, 360);
		gBuffer.fillArc(x+4, y+5, 3, 9, 0, 360);
		gBuffer.fillArc(x+9, y+7, 3, 9, 0, 360);
	}
	
	public void drawWater (Graphics g, int y, int x)
	{
		gBuffer.setColor(Color.BLUE);
		gBuffer.fillRect(x, y, 20, 20);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawArc(x-9, y+5, 19, 5, 180, 180);
		gBuffer.drawArc(x+9, y+14, 19, 5, 0, 180);
	}
	
	public void drawBrick (Graphics g, int y, int x)
	{
		gBuffer.setColor(Color.RED);
		gBuffer.fillRect(x, y, 20, 20);
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(x, y+19, 20, 2);
		gBuffer.fillRect(x, y+9, 20, 2);
		gBuffer.fillRect(x+3, y+9, 2, 10);
		gBuffer.fillRect(x+16, y, 2, 10);
	}
	
	public void drawHealth (Graphics g, int y, int x)
	{
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(x, y, 20, 20);
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(x+2, y+7, 15, 10);
		gBuffer.setColor(Color.RED);
		gBuffer.fillRect(x+5, y+10, 9, 3);
		gBuffer.fillRect(x+8, y+8, 3, 8);
	}
	
	public void drawTank (Graphics g, int y, int x)
	{
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(x, y, 20, 20);
		gBuffer.fillRect(x+6, y+9, 4, 5);
		gBuffer.fillRect(x+3, y+12, 3, 1);
		gBuffer.setColor(Color.CYAN);
		gBuffer.fillRect(x+2, y+7, 14, 7);
		gBuffer.setColor(Color.ORANGE);
		gBuffer.fillRect(x+1, y+3, 16, 3);
		gBuffer.fillRect(x+1, y+14, 16, 3);
		
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(x+8, y+8, 4, 5);
		gBuffer.fillRect(x+3, y+9, 5, 2);
	}
	
	public void drawEnemy (Graphics g, int y, int x)
	{
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(x, y, 20, 20);
		gBuffer.setColor(Color.RED);
		gBuffer.fillRect(x+2, y+7, 14, 7);
		gBuffer.setColor(Color.ORANGE);
		gBuffer.fillRect(x+1, y+3, 16, 3);
		gBuffer.fillRect(x+1, y+14, 16, 3);
		
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(x+8, y+8, 4, 5);
		gBuffer.fillRect(x+3, y+9, 5, 2);
	}
	
	public void drawChar (Graphics g, int yPos, int 

xPos)
	{
		int x = xPos * 20 + 10;
		int y = yPos * 20 + 10;
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 20, 20);
		g.fillRect(x+6, y+9, 4, 5);
		g.fillRect(x+3, y+12, 3, 1);
		g.setColor(Color.CYAN);
		g.fillRect(x+2, y+7, 14, 7);
		g.setColor(Color.ORANGE);
		g.fillRect(x+1, y+3, 16, 3);
		g.fillRect(x+1, y+14, 16, 3);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+8, y+8, 4, 5);
		g.fillRect(x+3, y+9, 5, 2);
	}
	
	public void Ammo(Graphics g, int X, int Y)
	{
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(X-20, Y+20, 20, 20);
		double twoPI = 2 * Math.PI;
		int centerX = X + 10;
		int centerY = Y + 10;
		int sides = 6;
		int radius = 8;
		int xCoord[] = new int[sides];
		int yCoord[] = new int[sides];
		gBuffer.setColor(Color.ORANGE); 
		for (int k = 0; k < sides; k++)
		{
			xCoord[k] = (int) Math.round

(Math.cos(twoPI * k/sides) * radius) + centerX;
			yCoord[k] = (int) Math.round

(Math.sin(twoPI * k/sides) * radius) + centerY;
		}
		gBuffer.fillPolygon(xCoord,yCoord,sides);
		gBuffer.setColor(Color.RED);
		gBuffer.drawString("AMMO", centerX-7, 

centerY-9);
	}

	
	public void keyPressed( KeyEvent e )
	{ }
	public void keyReleased( KeyEvent e )
	{ }
	public void keyTyped( KeyEvent e )
	{
		//System.out.println("HELLO");
		char key = e.getKeyChar();
		//System.out.println("KEY: " + key);
		//a
		if (key == 'a')
		{
			yPos--;
		}
		//w
		else if (key == 'w')
		{
			xPos--;
		}
		//s
		else if (key == 's')
		{
			xPos++;
		}
		//d
		else if (key == 'd')
		{
			yPos++;
		}
		//repaint((xPos-1)*20+10, (yPos-1)*20+10, 

60, 60);
		repaint();
	}

}
