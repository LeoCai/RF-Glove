package dislab.rfidaction.core.actionchecker;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import dislab.rfidaction.command.MenuClickCommand;
import dislab.rfidaction.command.MoveCommand;
import dislab.rfidaction.core.ActionManager;
import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
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

	private static final String MENU_SCALE = "SCALE";
	private static final String MENU_MOVE = "MOVE";
	private static final String MENU_ROTATE = "ROTATE";
	private static final String MENU_PIANO = "PIANO";
	private static final String MENU_RECODER = "CLARINET";

	private int threhold = 1500;

	private ActionManager actionManager;

	protected int menuId;
	private RfidMenu rfidMenu;

	private MenuView menuView;
	protected boolean menuSelect = true;
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
		rfidMenu.addItem(3, new RfidMenuItem(MENU_PIANO));
		rfidMenu.addItem(4, new RfidMenuItem(MENU_RECODER));
		menuView = new MenuView(rfidMenu);
		jFrame = new JFrame();
		jFrame.add(menuView.getView());

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int swidth = (int) screen.getWidth() / 2;
		int sheight = (int) screen.getHeight() / 2;
		jFrame.setSize(swidth, sheight);
	}

	@Override
	public String checkAction(double[] tagRssi) {
		// TODO Auto-generated method stub
		if (!jFrame.isVisible())
			jFrame.setVisible(true);
		int[] tagUpDownOff = tagRssiEncoder.preBasedEncode(tagRssi,
				THREHOLD_MOVE);
		int[] tagUpDown = tagRssiEncoder.encode(tagRssi, initRssi, threhold);

		if (menuSelect && tagRssi[6] > 6000 && tagRssi[4] > 1000
				&& tagRssi[9] < 8000) {
			matchMoveAction(tagUpDownOff, new int[] { 6 }, new int[] { 1 },
					MENU_RIGHT);
			matchMoveAction(tagUpDownOff, new int[] { 6 }, new int[] { -1 },
					MENU_LEFT);
		}
		if (menuSelect)
			matchMenuClickAction(tagUpDown, new int[] { 5, 6, 7, 8, 9 },
					new int[] { 1, 1, 1, 1, 1 });
		if (!menuSelect)
			matchMenuClickAction(tagUpDown, new int[] { 0, 1, 2, 3, 4 },
					new int[] { 1, 1, 1, 1, 1 });
		return printTagUpDown(tagUpDownOff, new int[] { 5, 6, 7, 8, 9 });
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
			actionManager.selectMode(CommandType.PAINAO);
			break;
		case MENU_RECODER:
			actionManager.selectMode(CommandType.ClARINET);
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
