package dislab.rfidaction.core.actionchecker;

/**
 * 动作监测器
 * @author leocai
 *
 */
public interface ActionChecker {

	/**
	 * 监测动作
	 * @param targetTagRssi
	 * @return
	 */
	String checkAction(double[] targetTagRssi);

	/**
	 * 退出监测器
	 */
	void exit();

}
