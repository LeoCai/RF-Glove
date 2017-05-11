package dislab.rfidaction.menu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JButton;

/**
 * 菜单view
 *
 */
public class MenuView {

	private Panel panle;
	private RfidMenu rfidMenu;
	private Map<String, JButton> btnMap = new HashMap<String, JButton>();

	public MenuView(RfidMenu rfidMenu) {
		this.rfidMenu = rfidMenu;
		this.panle = new Panel(new FlowLayout());
		this.rfidMenu = rfidMenu;
		Map<Integer, RfidMenuItem> items = rfidMenu.getMenuItems();
		Iterator<Integer> iter = items.keySet().iterator();
		while (iter.hasNext()) {
			Integer key = iter.next();
			RfidMenuItem item = items.get(key);
			JButton btn = new JButton(item.getTitle());
			panle.add(btn);
			btnMap.put(item.getTitle(), btn);
		}
	}

	public void refreshView() {
		Iterator<String> iter = btnMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			btnMap.get(key).setBackground(Color.GRAY);
		}
		RfidMenuItem cuItem = rfidMenu.getCuRfidMenuItem();
		if (cuItem != null)
			btnMap.get(cuItem.getTitle()).setBackground(Color.RED);
		RfidMenuItem clickItem = rfidMenu.getClickItem();
		if (clickItem != null)
			btnMap.get(clickItem.getTitle()).setBackground(Color.GREEN);

	}

	public Panel getView() {
		refreshView();
		return panle;
	}

}
