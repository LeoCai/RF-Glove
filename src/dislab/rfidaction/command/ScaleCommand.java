package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
import dislab.rfidaction.event.ScaleEvent;

/**
 * 缩放命令
 *
 */
public class ScaleCommand extends Command {

	private ScaleEvent scaleEvent;

	public ScaleCommand(CommandInfo commandInfo, ScaleEvent scaleEvent) {
		// TODO Auto-generated constructor stub
		setCommandType(CommandType.ClARINET);
		setName("recoder");
		this.commandinfo = commandInfo;
		this.scaleEvent = scaleEvent;
	}

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		scaleEvent.scale(commandinfo.getValue() * 2);
	}

}
