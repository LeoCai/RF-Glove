package dislab.rfidaction.core.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class ActionGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2388964096485997221L;
	public static final double rateMenu = 0.15;
	public static final double rateCenter = 0.5;
	public static final double rateBottom = 1 - rateMenu - rateCenter;

	public ActionGui() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screen.getWidth();
		int height = (int) screen.getHeight();
		setSize(width, (int) (height * rateCenter));
		setLocation(0, (int) (height * rateMenu));
		setLayout(new FlowLayout());
	}

}
