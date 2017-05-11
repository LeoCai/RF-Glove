package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
import dislab.rfidaction.core.view.PianoGui;

/**
 * 钢琴命令
 *
 */
public class PianoCommand extends Command {
	static PianoGui pianoGui = new PianoGui();

	public PianoCommand(CommandInfo commandInfo) {
		// TODO Auto-generated constructor stub
		if (!pianoGui.isVisible()) {
			pianoGui.setVisible(true);
		}
		setCommandType(CommandType.PAINAO);
		setName("Panio");
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
			pianoGui.pressed(commandinfo.getValue());

		} else if (commandinfo.getEventType() == CommandInfo.RELEASE) {
			pianoGui.released(commandinfo.getValue());
		}

	}

	public static void quitGui() {
		// TODO Auto-generated method stub
		if (pianoGui.isVisible()) {
			pianoGui.setVisible(false);
		}
	}

}
