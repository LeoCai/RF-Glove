package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.Command;
import dislab.rfidaction.utils.TagRssiEncoder;
import dislab.rfidaction.utils.TagsInit;

/**
 * 抽象的rfid动作监测器
 * 
 */
public abstract class AbstractActionChecker implements ActionChecker {

	protected List<String> tags;
	protected TagRssiEncoder tagRssiEncoder;
	protected double[] initRssi;

	public AbstractActionChecker(List<String> tags) {
		// TODO Auto-generated constructor stub
		this.tags = tags;
		tagRssiEncoder = new TagRssiEncoder(tags);
		initRssi = TagsInit.getInstance().getTagAverageRssi();
	}

	protected String printTagUpDown(int[] tagUpDown, int[] tagIndexs) {
		// TODO Auto-generated method stub
		return printTagUpDown(tagUpDown, tagIndexs, true);
	}

	protected String printTagUpDown(int[] tagUpDown, int[] tagIndexs,
			boolean printFlag) {
		// TODO Auto-generated method stub
		String info = "";
		for (int i = 0; i < tagIndexs.length; i++) {
			info += tagUpDown[tagIndexs[i]] + " ";
		}
		if (printFlag) {
			System.out.println(info);
		}
		return info;
	}

	/**
	 * 判断编码数组在对应位置是否和目标数组相同
	 * 
	 * @param tagUpDown
	 *            编码数组
	 * @param tagIndexs
	 *            对应位置
	 * @param targetUpDown
	 *            目标数组
	 * @return
	 */
	protected boolean checkUpDownEqual(int[] tagUpDown, int[] tagIndexs,
			int[] targetUpDown) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tagIndexs.length; i++) {
			if (tagUpDown[tagIndexs[i]] != targetUpDown[i])
				return false;
		}
		return true;
	}

	/**
	 * 匹配编码并执行动作
	 * 
	 * @param tagUpDown
	 *            rssi编码
	 * @param matchIndexs
	 *            匹配的索引号
	 * @param targetMatch
	 *            目标编码
	 * @param command
	 *            执行命令
	 */
	public void matchAction(int[] tagUpDown, int[] matchIndexs,
			int[] targetMatch, Command command) {
		// TODO Auto-generated method stub
		if (checkUpDownEqual(tagUpDown, matchIndexs, targetMatch)) {
			command.excute();
		}
	}

	public void setInitRssi(double[] initRssi) {
		this.initRssi = initRssi;
	}

}
