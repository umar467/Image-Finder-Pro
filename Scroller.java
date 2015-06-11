import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class Scroller extends MouseInputAdapter {
	page a;
	ImageGrabber ig;
	public void mouseEntered(MouseEvent event) {
		 //JOptionPane.showMessageDialog(null, "Mouse entered!");
		 
	 }
	 	public void mouseClicked(MouseEvent event){
	 		System.out.println("mouse clicked !!!!!!!!!!!!!!!!!!!!!!!!!");
	 	}
	 	public void mouseExited(MouseEvent event){
	 		a.endfocus();
	 	}
	 
	 public void mouseMoved(MouseEvent event) {
		System.out.println("("+event.getX()+","+event.getY()+")");
		 if (event.getX()<1080 && event.getY()<1024) {
				// text.setText("("+event.getX()+","+event.getY()+")");
				// JOptionPane.showMessageDialog(null,
				// "Mouse moved to ("+event.getX()+","+event.getY()+")");
				int r = event.getY() / 110;
				int c = event.getX() / 110;
				System.out
						.println("   ==r=" + r + "==c=" + c + "==   " + r + c);
				// text.setIcon(new ImageIcon(ig.get(r+c)));
				System.out.println(" calling focus");
				a.focus(r * 10 + c, event.getX(), event.getY());
			}else{a.endfocus();}
			
		}
	public Scroller() throws HeadlessException {
		
		JFrame f = new JFrame();
		
		
		
		a = new page();
		f.add(a);
		a.init();
		a.addMouseListener(this);
		a.addMouseMotionListener(this);
		
		// final JPanel panel = new JPanel();
		a.setBorder(BorderFactory.createLineBorder(Color.red));
		a.setPreferredSize(new Dimension(1280, 1024));

		final JScrollPane scroll = new JScrollPane(a);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		f.add(scroll, BorderLayout.CENTER);
		f.setSize(600,400);
		f.setVisible(true);
		
	 ig = new ImageGrabber(JOptionPane.showInputDialog("Please input Your search Query "));
		
		for (int i = 0; i < 150; i++) {
			a.add(ig.get(i));}
	}

	public static void main(final String[] args) throws Exception {
		Scroller p = new Scroller();

	}
}
