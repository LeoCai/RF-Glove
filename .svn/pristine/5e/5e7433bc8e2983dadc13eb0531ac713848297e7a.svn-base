package dislab.rfidaction.core.actionchecker;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;

import dislab.rfidaction.command.MenuClickCommand;
import dislab.rfidaction.command.MoveCommand;
import dislab.rfidaction.core.ActionManager;
import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
import dislab.rfidaction.core.view.ActionGui;
import dislab.rfidaction.event.MenuClickEvent;
import dislab.rfidaction.event.MoveEvent;
import dislab.rfidaction.menu.ItemClickListener;
import dislab.rfidaction.menu.MenuItemSelectListener;
import dislab.rfidaction.menu.MenuView;
import dislab.rfidaction.menu.RfidMenu;
import dislab.rfidaction.menu.RfidMenuItem;

/**
 * 菜单动作监测器
 * 
 */
public class MenuActionChecker extends AbstractActionChecker implements
		ItemClickListener {

	private static final int MENU_LEFT = -1;
	private static final int MENU_RIGHT = 1;

	private static final int THREHOLD_MOVE = 1000;

	private static final String MENU_SCALE = "缩放";
	private static final String MENU_MOVE = "移动";
	private static final String MENU_ROTATE = "旋转";
	private static final String MENU_PIANO = "乐器";
	private static final String MENU_CLARINET = "竖笛";
	private static final String MENU_WHEEL = "方向盘";
	private static final String MENU_ROCKGAME = "石头剪刀布";

	private int threhold = 2500;

	private ActionManager actionManager;

	protected int menuId;
	private RfidMenu rfidMenu;

	private MenuView menuView;
	protected boolean menuSelect = false;
	private JFrame jFrame;

	public MenuActionChecker(List<String> tags, ActionManager actionManager) {
		// TODO Auto-generated constructor stub
		super(tags);
		this.actionManager = actionManager;
		rfidMenu = RfidMenu.getInstance();
		rfidMenu.addMenuClickListener(this);
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
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	@Override
	public String checkAction(double[] tagRssi) {
		// TODO Auto-generated method stub
		if (!jFrame.isVisible())
			jFrame.setVisible(true);
		int[] tagUpDownOff = tagRssiEncoder.preBasedEncode(tagRssi,
				THREHOLD_MOVE);//以上次为基准获取RSSI校验信息
		int[] tagUpDown = tagRssiEncoder.encode(tagRssi, initRssi, threhold);

		// 第六个手指信号要大，第九个手指得收起来，左手要放在旁边
		if (!menuSelect && tagRssi[6] > 6000 && tagRssi[4] > 1000
				&& tagRssi[9] < 8000) {
			matchMoveAction(tagUpDownOff, new int[] { 6 }, new int[] { 1 },
					MENU_RIGHT);
			matchMoveAction(tagUpDownOff, new int[] { 6 }, new int[] { -1 },
					MENU_LEFT);
		}

		// 确定或取消动作
		if (menuSelect && tagRssi[0] > 15000) {
			actionManager.exitActionChecker();
			rfidMenu.releaseClickItem();
			menuView.refreshView();
			menuSelect = false;
		}//取消选定
		// matchMenuClickAction(tagUpDown, new int[] { 5, 6, 7, 8, 9 },
		// new int[] { 1, 1, 1, 1, 1 });
		if (!menuSelect && tagRssi[9] > 20000) {
			rfidMenu.clickCuItem();
			menuView.refreshView();
			menuSelect = true;
		}//选定
		// matchMenuClickAction(tagUpDown, new int[] { 0, 1, 2, 3, 4 },
		// new int[] { 1, 1, 1, 1, 1 });
		return printTagUpDown(tagUpDownOff, new int[] { 5, 6, 7, 8, 9 },false);
	}

	private void matchMenuClickAction(int[] tagUpDown, int[] matchIndexs,
			int[] targetMatch) {
		// TODO Auto-generated method stub
		matchAction(tagUpDown, matchIndexs, targetMatch, new MenuClickCommand(
				new CommandInfo(CommandInfo.MENUCLICKMODE, 0),
				new MenuClickEvent() {
					@Override
					public void onMenuClick(int type) {
						// TODO Auto-generated method stub
						if (menuSelect) {
							rfidMenu.clickCuItem();
							menuView.refreshView();
							menuSelect = false;
						} else {
							actionManager.exitActionChecker();
							rfidMenu.releaseClickItem();
							menuView.refreshView();
							menuSelect = true;
						}

					}
				}));
	}

	private void matchMoveAction(int[] tagUpDown, int[] matchIndexs,
			int[] targetMatch, int event) {
		// TODO Auto-generated method stub
		matchAction(tagUpDown, matchIndexs, targetMatch, new MoveCommand(
				new CommandInfo(CommandInfo.MOVE, event), new MoveEvent() {

					@Override
					public void move(int offSize) {
						// TODO Auto-generated method stub
						String info = offSize > 0 ? "left" : "right";
						menuId += offSize > 0 ? 1 : -1;
						if (menuId == rfidMenu.getMenuItems().size()) {
							menuId = 0;
						}
						if (menuId == -1) {
							menuId = rfidMenu.getMenuItems().size() - 1;
						}
						rfidMenu.selectItem(menuId,
								new MenuItemSelectListener() {

									@Override
									public void onClick(
											RfidMenuItem cuRfidMenuItem) {
										// TODO Auto-generated method
										// stub
										System.out.println("item:"
												+ cuRfidMenuItem.getTitle());
										menuView.refreshView();
									}
								});
						System.out.println(info);
						// posX += offSize;
						// scaleFrame.setLocation(posX, posY);
					}
				}));
	}

	@Override
	public void onItemClick(RfidMenuItem clickItem) {
		// TODO Auto-generated method stub
		switch (clickItem.getTitle()) {
		case MENU_SCALE:
			actionManager.selectMode(CommandType.SCALE);
			break;
		case MENU_MOVE:
			actionManager.selectMode(CommandType.MOVE);
			break;
		case MENU_ROTATE:
			actionManager.selectMode(CommandType.ROTATE);
			break;
		case MENU_PIANO:
			actionManager.selectMode(CommandType.PIANO);
			break;
		case MENU_CLARINET:
			actionManager.selectMode(CommandType.ClARINET);
			break;
		case MENU_WHEEL:
			actionManager.selectMode(CommandType.WHEEL);
			break;
		case MENU_ROCKGAME:
			actionManager.selectMode(CommandType.ROCKGAME);
			break;

		default:
			break;
		}

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
