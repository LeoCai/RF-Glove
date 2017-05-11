package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
import dislab.rfidaction.event.MoveEvent;

/**
 * 移动命令
 *
 */
public class MoveCommand extends Command {
	private MoveEvent moveEvent;

	public MoveCommand(CommandInfo commandInfo, MoveEvent moveEvent) {
		// TODO Auto-generated constructor stub
		setCommandType(CommandType.MOVE);
		setName("move");
		this.commandinfo = commandInfo;
		this.moveEvent = moveEvent;
	}

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		moveEvent.move(commandinfo.getValue() * 200);
	}

}
