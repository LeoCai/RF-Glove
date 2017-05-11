package dislab.rfidaction.core.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import dislab.rfidaction.core.ChartDrawer;
import dislab.rfidaction.menu.MenuView;
import dislab.rfidaction.menu.RfidMenu;
import dislab.rfidaction.menu.RfidMenuItem;

public class ViewTest {

	private JFrame jFrame;
	private RfidMenu rfidMenu;
	private MenuView menuView;

	private static final String MENU_SCALE = "缩放";
	private static final String MENU_MOVE = "移动";
	private static final String MENU_ROTATE = "旋转";
	private static final String MENU_PIANO = "乐器";
	private static final String MENU_CLARINET = "竖笛";
	private static final String MENU_WHEEL = "方向盘";
	private static final String MENU_ROCKGAME = "石头剪刀布";

	public ViewTest() {
		rfidMenu = RfidMenu.getInstance();
		rfidMenu.addItem(0, new RfidMenuItem(MENU_SCALE));
		rfidMenu.addItem(1, new RfidMenuItem(MENU_MOVE));
		rfidMenu.addItem(2, new RfidMenuItem(MENU_ROTATE));

		rfidMenu.addItem(3, new RfidMenuItem(MENU_WHEEL));
		rfidMenu.addItem(4, new RfidMenuItem(MENU_ROCKGAME));

		rfidMenu.addItem(5, new RfidMenuItem(MENU_PIANO));
		// rfidMenu.addItem(6, new RfidMenuItem(MENU_CLARINET));

		menuView = new MenuView(rfidMenu);
		jFrame = new JFrame();
		jFrame.add(menuView.getView());

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int swidth = (int) screen.getWidth();
		int sheight = (int) (screen.getHeight() * ActionGui.rateMenu);
		jFrame.setSize(swidth, sheight);
		jFrame.setVisible(true);

		ActionGui actionGui = RotateGui.getInstance();
		actionGui.setVisible(true);
		ClarinetGui clarinetGui = new ClarinetGui();
		clarinetGui.setVisible(true);
		PianoGui pianoGui = new PianoGui();
		pianoGui.setVisible(true);
		ChartDrawer chartDrawer = ChartDrawer.getInstance();
		chartDrawer.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ViewTest viewTest = new ViewTest();

	}

}
