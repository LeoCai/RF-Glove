package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.core.view.WheelGui;

public class WheelActionChecker extends AbstractActionChecker {
	WheelGui wheelGui = new WheelGui();
	private int threhold = 1500;
	private int threhold2 = 4000;

	public WheelActionChecker(List<String> tags) {
		super(tags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String checkAction(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		if (!wheelGui.isVisible())
			wheelGui.setVisible(true);
		int[] tagUpDown = tagRssiEncoder.encode(targetTagRssi, initRssi,
				threhold);
		int[] tagUpDown2 = tagRssiEncoder.encode(targetTagRssi, initRssi,
				threhold2);
		printTagUpDown(tagUpDown, new int[] { 0, 2, 4 });
		if (checkUpDownEqual(tagUpDown2, new int[] { 5 }, new int[] { -1 })) {
			wheelGui.rotate(-1);
		} else if (checkUpDownEqual(tagUpDown, new int[] { 2, 4 }, new int[] {
				1, -1 })) {
			wheelGui.rotate(1);
		} else {
			wheelGui.rotate(0);
		}

		return null;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		wheelGui.setVisible(false);

	}

}
