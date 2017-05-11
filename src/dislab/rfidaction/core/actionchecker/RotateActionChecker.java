package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.RotateCommand;
import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.view.RotateGui;
import dislab.rfidaction.event.RotateEvent;

/**
 * 旋转动作监测器
 *
 */
public class RotateActionChecker extends AbstractActionChecker {

	private int threhold = 2000;

	private RotateGui rotateGui = RotateGui.getInstance();

	public RotateActionChecker(List<String> tags) {
		super(tags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String checkAction(double[] tagRssi) {
		if (!rotateGui.isVisible()) {
			rotateGui.setVisible(true);
		}
		int[] tagUpDown = tagRssiEncoder.encode(tagRssi, initRssi, threhold);

		
		//5,6,7三指旋转
		matchRotateAction(tagUpDown, new int[] { 5, 6, 7 },
				new int[] { 0, 0, 0 }, -1);//初始状态
		matchRotateAction(tagUpDown, new int[] { 6, 7 }, new int[] { 0, -1 }, 0);//第二个状态
		matchRotateAction(tagUpDown, new int[] { 5, 6, 7 }, new int[] { 0, 1,
				-1 }, 0);
		matchRotateAction(tagUpDown, new int[] { 5, 6, 7 }, new int[] { 1, 1,
				-1 }, 1);
		return printTagUpDown(tagUpDown, new int[] { 5, 6, 7 });
	}

	private void matchRotateAction(int[] tagUpDown, int[] matchIndexs,
			int[] targetMatch, int event) {
		// TODO Auto-generated method stub
		matchAction(tagUpDown, matchIndexs, targetMatch, new RotateCommand(
				new CommandInfo(CommandInfo.ROTATE, event), new RotateEvent() {

					@Override
					public void rotate(int offSize) {
						// TODO Auto-generated method stub
						String info = "angle:" + offSize;
						System.out.println(info);
						rotateGui.rotate(1.0 * offSize / 2);

					}
				}));
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		rotateGui.setVisible(false);

	}

}
