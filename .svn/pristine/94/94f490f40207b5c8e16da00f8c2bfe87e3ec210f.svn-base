package dislab.rfidaction.core;

import java.util.List;

import dislab.rfidaction.core.actionchecker.AbstractActionChecker;
import dislab.rfidaction.core.actionchecker.ActionChecker;
import dislab.rfidaction.core.actionchecker.MenuActionChecker;
import dislab.rfidaction.core.actionchecker.MoveActionChecker;
import dislab.rfidaction.core.actionchecker.PianoActionChecker;
import dislab.rfidaction.core.actionchecker.ClarinetActionChecker;
import dislab.rfidaction.core.actionchecker.RotateActionChecker;
import dislab.rfidaction.core.actionchecker.ScaleActionChecker;
import dislab.rfidaction.utils.TagsInit;

/**
 * 集中管理所有action
 * 
 */
public class ActionManager {

	private AbstractActionChecker clarinetActionChecker;
	private AbstractActionChecker pinaoActionChecker;
	private AbstractActionChecker scaleActionChecker;
	private AbstractActionChecker rotateActionChecker;
	private AbstractActionChecker moveActionChecker;

	private AbstractActionChecker menuActionChecker;

	private ActionChecker actionChecker;

	private TagsInit tagsInit;
	private ChartDrawer chartDrawer;

	public ActionManager(List<String> tags) {

		clarinetActionChecker = new ClarinetActionChecker(tags);
		pinaoActionChecker = new PianoActionChecker(tags);
		scaleActionChecker = new ScaleActionChecker(tags);
		menuActionChecker = new MenuActionChecker(tags, this);
		rotateActionChecker = new RotateActionChecker(tags);
		moveActionChecker = new MoveActionChecker(tags);
		actionChecker = null;

		this.tagsInit = TagsInit.getInstance();
		this.chartDrawer = ChartDrawer.getInstance();

		clarinetActionChecker.setInitRssi(TagsInit.initRssiClarinet);
		pinaoActionChecker.setInitRssi(TagsInit.initRssiMenu);
		scaleActionChecker.setInitRssi(TagsInit.initRssiSCALE);
		menuActionChecker.setInitRssi(TagsInit.initRssiMenu);
		rotateActionChecker.setInitRssi(TagsInit.initRssiRotate);
		moveActionChecker.setInitRssi(TagsInit.initRssiMove);

		// TODO Auto-generated constructor stub
	}

	/**
	 * 监测rfid的动作
	 * 
	 * @param targetTagRssi
	 */
	public void checkAction(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		if (actionChecker != null) {
			String tagUpDown = actionChecker.checkAction(targetTagRssi);
			chartDrawer.showStateInfo(tagUpDown);
		}
		menuActionChecker.checkAction(targetTagRssi);
	}

	/**
	 * 选择模式
	 * 
	 * @param commandType
	 */
	public void selectMode(CommandType commandType) {
		switch (commandType) {
		case PAINAO:
			actionChecker = pinaoActionChecker;
			tagsInit.setTagAverageRssi(TagsInit.initRssiMenu);
			break;
		case ClARINET:
			tagsInit.setTagAverageRssi(TagsInit.initRssiClarinet);
			actionChecker = clarinetActionChecker;
			break;
		case SCALE:
			tagsInit.setTagAverageRssi(TagsInit.initRssiSCALE);
			actionChecker = scaleActionChecker;
			break;
		case ROTATE:
			tagsInit.setTagAverageRssi(TagsInit.initRssiRotate);
			actionChecker = rotateActionChecker;
			break;
		case MOVE:
			tagsInit.setTagAverageRssi(TagsInit.initRssiMove);
			actionChecker = moveActionChecker;
			break;
		default:
			break;
		}
		// ChartDrawer.getInstance().setIniting(true);
		System.out.println("selectMode:" + commandType);
	}

	/**
	 * 退出当前的动作监测器
	 */
	public void exitActionChecker() {
		// TODO Auto-generated method stub
		if (actionChecker != null) {
			actionChecker.exit();
			actionChecker = null;
			tagsInit.setTagAverageRssi(TagsInit.initRssiMenu);
			ChartDrawer.getInstance().setIniting(true);
		}

	}

}
