package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.PianoCommand;
import dislab.rfidaction.core.CommandInfo;

/**
 * 钢琴动作监测器
 *
 */
public class PianoActionChecker extends AbstractActionChecker {

	private boolean[] tagPressed;

	private double threholdPressPinao = 6000;
	private double threholdReleasePinao = 6000;

	public PianoActionChecker(List<String> tagIds) {
		super(tagIds);
		tagPressed = new boolean[tagIds.size()];
	}

	@Override
	public String checkAction(double[] tagRssi) {
		for (int i = 1; i < 4; i++) {
			matchPianoAction(tagRssi, i, i - 1);
		}
		for (int i = 6; i < 9; i++) {
			matchPianoAction(tagRssi, i, i - 2);
		}
		// TODO Auto-generated method stub
		return null;

	}

	private void matchPianoAction(double[] tagRssi, int index, int key) {
		double rssi = tagRssi[index];
		if (rssi > threholdPressPinao && !tagPressed[index]) {
			tagPressed[index] = true;
			new PianoCommand(new CommandInfo(CommandInfo.PRESS, key)).excute();
		} else if (rssi < threholdReleasePinao && tagPressed[index]) {
			tagPressed[index] = false;
			new PianoCommand(new CommandInfo(CommandInfo.RELEASE, key))
					.excute();
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		PianoCommand.quitGui();

	}

}
