package dislab.rfidaction.core.actionchecker;

import java.util.List;

import com.sun.xml.internal.ws.api.ComponentFeature.Target;

import dislab.rfidaction.core.view.RockGameGui;

public class RockGameChecker extends AbstractActionChecker {

	RockGameGui rockGameGui = new RockGameGui();
	private int threhold = 55;

	public RockGameChecker(List<String> tags) {
		super(tags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String checkAction(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		if (!rockGameGui.isVisible())
			rockGameGui.setVisible(true);
//		int goodRssi = 0;
//		for (int i = 5; i < targetTagRssi.length; i++) {
//			if(targetTagRssi[i]>threhold){
//				goodRssi++;
//			}
//		}
		if (targetTagRssi[9]>52) {
			rockGameGui.setImage(rockGameGui.BU);

		} else if (targetTagRssi[7]>51||targetTagRssi[6]>51) {
			rockGameGui.setImage(rockGameGui.JIANDAO);
		} else  {
			rockGameGui.setImage(rockGameGui.STONE);
		}
		return null;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		rockGameGui.setVisible(false);
	}

}
