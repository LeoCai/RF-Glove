package dislab.rfidaction.menu;

import java.util.HashMap;
import java.util.Map;

public class RfidMenu {

	private Map<Integer, RfidMenuItem> menuItems = new HashMap<Integer, RfidMenuItem>();
	private RfidMenuItem cuRfidMenuItem = null;
	private RfidMenuItem clickItem;
	private ItemClickListener itemClickListener;

	private static RfidMenu rfidMenu = new RfidMenu();

	private RfidMenu() {

	}

	public static RfidMenu getInstance() {
		return rfidMenu;
	}

	public void addItem(int id, RfidMenuItem item) {
		menuItems.put(id, item);
	}

	public void selectItem(int menuId,
			MenuItemSelectListener menuItemSelectListener) {
		cuRfidMenuItem = menuItems.get(menuId);
		menuItemSelectListener.onClick(cuRfidMenuItem);
	}

	public RfidMenuItem getCuRfidMenuItem() {
		return cuRfidMenuItem;
	}

	public Map<Integer, RfidMenuItem> getMenuItems() {
		return menuItems;
	}

	public void clickCuItem() {
		// TODO Auto-generated method stub
		if (cuRfidMenuItem == null)
			return;
		this.clickItem = cuRfidMenuItem;
		itemClickListener.onItemClick(clickItem);

	}

	public RfidMenuItem getClickItem() {
		return clickItem;
	}

	public void releaseClickItem() {
		// TODO Auto-generated method stub
		this.clickItem = null;

	}

	public void addMenuClickListener(ItemClickListener menuClickListener) {
		this.itemClickListener = menuClickListener;
		// TODO Auto-generated method stub

	}

}
