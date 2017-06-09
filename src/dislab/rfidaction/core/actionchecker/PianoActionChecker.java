package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.PianoCommand;
import dislab.rfidaction.core.CommandInfo;

/**
 * 钢琴动作监测器
 *
 */
public class PianoActionChecker extends AbstractActionChecker {

	private int[] tagPressed;
	/**
	 * 每根手指对应的阈值，最好每次都根据pinao.csv数据调一下
	 */
	private double[] threholds = new double[] { 47, 47, 45, 42, 48, 48, 48, 49, 50, 50 };

	public PianoActionChecker(List<String> tagIds) {
		super(tagIds);
		System.out.println("PianoActionChecker created");
		tagPressed = new int[tagIds.size()];// 以tagIds的大小确定数组大小
	}

	@Override
	public String checkAction(double[] tagRssi) {
		for (int i = 1; i < 4; i++) {
			matchPianoAction(tagRssi, i, i - 1);
		} // 2、3、4
		for (int i = 6; i < 10; i++) {
			matchPianoAction(tagRssi, i, i - 3);
		} // 7、8、9、10
			// TODO Auto-generated method stub
		return null;

	}

	/**
	 * 匹配钢琴动作
	 * 
	 * @param tagRssi
	 *            标签的RSSI
	 * @param index
	 *            标签对应的索引
	 * @param key
	 *            钢琴键与index对应
	 */
	private void matchPianoAction(double[] tagRssi, int index, int key) {
		double rssi = tagRssi[index];
		double threholdPress = threholds[index];
		// if(index!=3&&index!=2) {
		// threholdPress = threholdRress = threholdPressPinao;
		// }
		if (rssi > threholdPress && tagPressed[index] != 1) {

			new PianoCommand(new CommandInfo(CommandInfo.PRESS, key)).excute();
			tagPressed[index] = 1;

			// new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			//// try {
			//// Thread.sleep(500);
			//// tagPressed[index] = 0;
			//// } catch (InterruptedException e1) {
			//// // TODO Auto-generated catch block
			//// e1.printStackTrace();
			//// }
			// try {
			// Thread.sleep(800);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//// if(tagPressed[index] ==0)
			// new PianoCommand(new CommandInfo(CommandInfo.RELEASE, key))
			// .excute();
			// }
			// }).start();
		} else if (rssi < threholdPress && tagPressed[index] == 1) {
			new PianoCommand(new CommandInfo(CommandInfo.RELEASE, key)).excute();
			tagPressed[index] = 0;
		}
		// else if ( tagPressed[index]!=0) {
		// tagPressed[index]--;
		// if(tagPressed[index]==0){
		//
		// }
		// }
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		PianoCommand.quitGui();

	}

}
