package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
import dislab.rfidaction.core.view.ClarinetGui;

/**
 * 竖笛命令执行
 * 
 */
public class ClarinetCommand extends Command {
	static ClarinetGui recoderGui = new ClarinetGui();

	public ClarinetCommand(CommandInfo commandInfo) {
		// TODO Auto-generated constructor stub
		if (!recoderGui.isVisible()) {
			recoderGui.setVisible(true);
		}
		setCommandType(CommandType.ClARINET);
		setName("recoder");
		this.commandinfo = commandInfo;
	}

	public static final int command1 = 0;
	public static final int command2 = 1;
	public static final int command3 = 3;

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		System.out.println("Action:" + getName() + "," + commandinfo);

		if (commandinfo.getEventType() == CommandInfo.PRESS) {
			recoderGui.pressed(commandinfo.getValue());

		} else if (commandinfo.getEventType() == CommandInfo.RELEASE) {
			recoderGui.released(commandinfo.getValue());
		}

	}

	public static void quitGui() {
		// TODO Auto-generated method stub
		if (recoderGui.isVisible()) {
			recoderGui.setVisible(false);
			recoderGui.exit();
		}

	}

}
