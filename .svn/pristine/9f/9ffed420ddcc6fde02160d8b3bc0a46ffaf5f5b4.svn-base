package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
import dislab.rfidaction.event.RotateEvent;

/**
 * 旋转命令
 *
 */
public class RotateCommand extends Command {
	private RotateEvent rotateEvent;

	public RotateCommand(CommandInfo commandInfo, RotateEvent rotateEvent) {
		// TODO Auto-generated constructor stub
		setCommandType(CommandType.MOVE);
		setName("move");
		this.commandinfo = commandInfo;
		this.rotateEvent = rotateEvent;
	}

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		rotateEvent.rotate(commandinfo.getValue());
	}

}
