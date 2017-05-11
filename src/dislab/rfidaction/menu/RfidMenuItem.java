package dislab.rfidaction.menu;

public class RfidMenuItem {

	private String title;

	private int type;

	private RfidMenu subMenu;

	public RfidMenuItem(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public RfidMenu getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(RfidMenu subMenu) {
		this.subMenu = subMenu;
	}

}
