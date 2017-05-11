package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.ClarinetCommand;
import dislab.rfidaction.core.CommandInfo;

/**
 * 竖笛动作监测器
 *
 */
public class ClarinetActionChecker extends AbstractActionChecker {

	private int threhold = 2000;
	private boolean[] pressed;

	public ClarinetActionChecker(List<String> tagIds) {
		super(tagIds);
		pressed = new boolean[tagIds.size()];
	}

	@Override
	public String checkAction(double[] tagRssi) {
		// TODO Auto-generated method stub
		int[] tagUpDown = tagRssiEncoder.encode(tagRssi, initRssi, threhold);
		// int[] tagUpDown = tagRssiEncoder.preBasedEncode(tagList);

		matchAction(tagUpDown, new int[] { 3, 2, 1 }, new int[] { 1, 0, 0 }, 0);
		matchAction(tagUpDown, new int[] { 2, 0 }, new int[] { 1, 0 }, 1);
		matchAction(tagUpDown, new int[] { 0, 2 }, new int[] { -1, 0 }, 2);
		matchAction(tagUpDown, new int[] { 6, 8 }, new int[] { -1, 0 }, 3);
		matchAction(tagUpDown, new int[] { 6, 9 }, new int[] { 1, 0 }, 4);
		matchAction(tagUpDown, new int[] { 9, 7 }, new int[] { -1, 0 }, 5);

		return printTagUpDown(tagUpDown, new int[] { 0, 1, 2, 3, 6, 7, 8, 9 });

	}

	private void matchAction(int[] tagUpDown, int[] matchIndexs,
			int[] targetMatch, int key) {
		// TODO Auto-generated method stub
		if (checkUpDownEqual(tagUpDown, matchIndexs, targetMatch)) {
			if (!pressed[key]) {
				new ClarinetCommand(new CommandInfo(CommandInfo.PRESS, key))
						.excute();
				pressed[key] = true;
			}
		} else {
			if (pressed[key]) {
				new ClarinetCommand(new CommandInfo(CommandInfo.RELEASE, key))
						.excute();
				pressed[key] = false;
				// System.out.println("released " + key);
			}
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		ClarinetCommand.quitGui();

	}

}
