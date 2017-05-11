package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.event.MenuClickEvent;

/**
 * 菜单点击命令
 *
 */
public class MenuClickCommand extends Command {

	private MenuClickEvent menuClickEvent;
	private int type;

	public MenuClickCommand(CommandInfo commandInfo,
			MenuClickEvent menuClickEvent) {
		this.type = commandInfo.getValue();

		this.menuClickEvent = menuClickEvent;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		menuClickEvent.onMenuClick(type);

	}

}
