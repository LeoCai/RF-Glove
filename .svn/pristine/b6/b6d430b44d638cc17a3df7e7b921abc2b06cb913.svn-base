package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.ScaleCommand;
import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.view.RotateGui;
import dislab.rfidaction.event.ScaleEvent;

/**
 * 缩放动作监测器
 * 
 */
public class ScaleActionChecker extends AbstractActionChecker {

	private static final int THREHOLD_MOVE = 300;
	private static final int SCALE_EVENT_SMALLER = -1;
	private static final int SCALE_EVENT_LARGER = 1;

	private RotateGui scaleFrame;

	public ScaleActionChecker(List<String> tags) {
		super(tags);
		scaleFrame = RotateGui.getInstance();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String checkAction(double[] tagRssi) {
		// TODO Auto-generated method stub
		// int[] tagUpDown = tagRssiEncoder.encode(tagList, initRssi);
		if (!scaleFrame.isVisible())
			scaleFrame.setVisible(true);
		int[] tagUpDownOff = tagRssiEncoder.preBasedEncode(tagRssi,
				THREHOLD_MOVE);

		printTagUpDown(tagUpDownOff, new int[] { 5, 6, 7, 8, 9 });

		// if (tagRssi[9] < THREHOLD_SCALE_MODE) {
		matchScaleAction(tagUpDownOff, new int[] { 6, 9 }, new int[] { 1, 1 },
				SCALE_EVENT_LARGER);
		matchScaleAction(tagUpDownOff, new int[] { 6, 9 },
				new int[] { -1, -1 }, SCALE_EVENT_SMALLER);
		// }

		return null;

	}

	private void matchScaleAction(int[] tagUpDown, int[] matchIndexs,
			int[] targetMatch, int event) {
		// TODO Auto-generated method stub
		matchAction(tagUpDown, matchIndexs, targetMatch, new ScaleCommand(
				new CommandInfo(CommandInfo.Scale, event), new ScaleEvent() {

					@Override
					public void scale(int offSize) {
						// TODO Auto-generated method stub
						String info = offSize > 0 ? "larger" : "smaller";
						System.out.println(info);
						scaleFrame.scale(offSize * 10);
					}
				}));
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		if (scaleFrame.isVisible()) {
			scaleFrame.setVisible(false);
		}

	}

}
