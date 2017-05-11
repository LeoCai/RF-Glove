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
	private int threholdclar = 1000;
	private boolean[] pressed;
	private double[] refRssi = new double[]{0,0,0,0,0,0};

	public ClarinetActionChecker(List<String> tagIds) {
		super(tagIds);
		pressed = new boolean[tagIds.size()];
	}

	@Override
	public String checkAction(double[] tagRssi) {
		// TODO Auto-generated method stub
		int[] tagUpDown = tagRssiEncoder.encode(tagRssi, initRssi, threhold);
		// int[] tagUpDown = tagRssiEncoder.preBasedEncode(tagList);

		//原方法
		/*
		matchAction(tagUpDown, new int[] { 3, 2, 1 }, new int[] { 1, 0, 0 }, 0);
		matchAction(tagUpDown, new int[] { 2, 0 }, new int[] { 1, 0 }, 1);
		matchAction(tagUpDown, new int[] { 0, 2 }, new int[] { -1, 0 }, 2);
		matchAction(tagUpDown, new int[] { 6, 8 }, new int[] { -1, 0 }, 3);
		matchAction(tagUpDown, new int[] { 6, 9 }, new int[] { 1, 0 }, 4);
		matchAction(tagUpDown, new int[] { 9, 7 }, new int[] { -1, 0 }, 5);
		*/
		//新方法
		matchClarAction(tagRssi[3], 0, 1);
		matchClarAction(tagRssi[2], 1, 1);
		matchClarAction(tagRssi[1], 2, 1);
		matchClarAction(tagRssi[6], 3, 1);
		matchClarAction(tagRssi[7], 4, -1);
		matchClarAction(tagRssi[8], 5, -1);
		
		return printTagUpDown(tagUpDown, new int[] { 0, 1, 2, 3, 6, 7, 8, 9 });

	}
	//new
	private void matchClarAction(double tagRssi, int key, int v){
		if(refRssi[key] == 0){
			refRssi[key] = tagRssi;
			return;
		}
		double dis = tagRssi - refRssi[key];
		int w = 0;
		if(dis > threholdclar)
			w = 1;
		if(dis < -threholdclar)
			w = -1;
		if(w == v)
			new ClarinetCommand(new CommandInfo(CommandInfo.RELEASE, key)).excute();
		if(w == -v)
			new ClarinetCommand(new CommandInfo(CommandInfo.PRESS, key)).excute();
		refRssi[key] = tagRssi;
	}
	//old
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
