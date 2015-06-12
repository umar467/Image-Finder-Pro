import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComponent;

public class page extends JComponent {

	private ArrayList<Image> stack;
	int x, y = 0;
	public ArrayList<Image> col;
	int screenWidth = 1024;
	int screenHeight = 800;
	int focused = -100;
	Image focusedImg;
	boolean isfocused = false;
	int fx,fy =0;
	boolean load = false;
	public void endfocus(){
		System.out.println("focus ended");
		isfocused = false;
		repaint();
	}
	public void focus(int n,int xa,int ya){
		if (col.size()> n) {
			
			focused = n;
			//focusedImg = a;
			isfocused = true;
			fx=xa;
			fy = ya;
			System.out.println("focus called!");
			repaint();
		}
	}
	
	public void init() {
		// TODO Auto-generated constructor stub
		stack = new ArrayList<Image>();
		col = new ArrayList<Image>();
	load = isfocused = false;
	}

	public void add(Image img) {
		if (img != null) {
			Image tim = img.getScaledInstance(300, -1, Image.SCALE_SMOOTH);
			int width = img.getWidth(null);
			int height = img.getHeight(null);
			BufferedImage uyu = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = uyu.getGraphics();
			g2.drawImage(tim, 0, 0, null);
			g2.dispose();
			col.add(uyu);
			if (img.getWidth(null) > 200 || img.getHeight(null)>200) {
				img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//				int width = img.getWidth(null);
//				int height = img.getHeight(null);

				// width and height are of the toolkit image
				BufferedImage newImage = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_ARGB);
				Graphics g = newImage.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				stack.add(newImage);
			} else {
				stack.add(img);
			}
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//			for (int w = 110; w < screenWidth; w += 110) {
//				g.drawLine(w, 0, w, screenHeight);
//			}
//			for (int w = 110; w < screenHeight; w += 110) {
//				g.drawLine(0, w, screenWidth, w);
//			}
			System.out.println("repainted");
			if (stack != null) {
				x = y = 0;
				int count = 0;
				for (Image img : stack) {

					//				if(count != focused){
					g.drawImage(img, x, y, this);
					//				}else{
					//g.drawImage(focusedImg,x,y,this); System.out.println("count == "+count+"    focus == "+focused);//}
					g.drawString(count + "", x, y);
					count++;
					x += 110;
					if (x > screenWidth) {
						x = 0;
						y += 110;
					}
				}
			}
			if(isfocused){
				Image tim = col.get(focused);//.getScaledInstance(300, -1, Image.SCALE_SMOOTH);
				int width =  col.get(focused).getWidth(null);
				int height =  col.get(focused).getHeight(null);
//				BufferedImage newImage = new BufferedImage(width, height,
//						BufferedImage.TYPE_INT_ARGB);
//				Graphics g2 = newImage.getGraphics();
//				g2.drawImage(tim, 0, 0, null);
//				g2.dispose();
				//fx = fx-300;//tim.getWidth(null)/2;
				//appfy = fy-300;//tim.getHeight(null)/2;
				System.out.println("fx="+fx+"  fy="+fy+" imageWIdth="+tim.getWidth(null)+" imge Height = "+tim.getHeight(null));
			
				
				g.drawImage(tim, fx, fy, this);System.out.println("focusss called ddone!!!!!!!!!!!");isfocused = false;}
	
	
	}
	}

