package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.MoveCommand;
import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.view.RotateGui;
import dislab.rfidaction.event.MoveEvent;

/**
 * 移动动作监测器
 *
 */
public class MoveActionChecker extends AbstractActionChecker {

	private static final int THREHOLD_MOVE = 1000;
	private static final int MOVE_LEFT = 1;
	private static final int MOVE_RIGHT = -1;

	private RotateGui moveGui = RotateGui.getInstance();

	public MoveActionChecker(List<String> tags) {
		super(tags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String checkAction(double[] tagRssi) {
		// TODO Auto-generated method stub
		if (!moveGui.isVisible()) {
			moveGui.setVisible(true);
		}
		int[] tagUpDownOff = tagRssiEncoder.preBasedEncode(tagRssi,
				THREHOLD_MOVE);
		matchMoveAction(tagUpDownOff, new int[] { 6 }, new int[] { 1 },
				MOVE_LEFT);
		matchMoveAction(tagUpDownOff, new int[] { 6 }, new int[] { -1 },
				MOVE_RIGHT);

		return null;
	}

	private void matchMoveAction(int[] tagUpDown, int[] matchIndexs,
			int[] targetMatch, int event) {
		// TODO Auto-generated method stub
		matchAction(tagUpDown, matchIndexs, targetMatch, new MoveCommand(
				new CommandInfo(CommandInfo.MOVE, event), new MoveEvent() {

					@Override
					public void move(int offSize) {
						// TODO Auto-generated method stub
						String info = offSize > 0 ? "left" : "right";
						System.out.println(info);
						int offX = offSize > 0 ? 100 : -100;
						moveGui.move(offX);
					}
				}));
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		if (moveGui.isVisible()) {
			moveGui.setVisible(false);
		}
	}

}
