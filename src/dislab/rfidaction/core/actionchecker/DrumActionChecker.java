package dislab.rfidaction.core.actionchecker;

import java.util.List;

import dislab.rfidaction.command.DrumCommand;
import dislab.rfidaction.core.CommandInfo;

/**
 * 鼓动作监测器
 *
 */
public class DrumActionChecker extends AbstractActionChecker {

	private boolean[] tagPressed;
	//press和release的阈值都为6000
	private double threholdPressDrum = 5000;
	private double threholdReleaseDrum = 5000;
	private boolean []cha = new boolean[]{false,false};

	public DrumActionChecker(List<String> tagIds) {
		super(tagIds);
		System.out.println("DrumActionChecker created");
		tagPressed = new boolean[tagIds.size()];//以tagIds的大小确定数组大小
	}

	@Override
	public String checkAction(double[] tagRssi) {
		//System.out.println("DrumChecking");
		//for (int i = 1; i < 4; i++) {
		matchLR(tagRssi);
		matchChaActionl(tagRssi);
		matchChaActionr(tagRssi);
		//匹配吊镲
		matchDrumAction(tagRssi, 4, 1);
		//左手,匹配tag4
		//for (int i = 6; i < 9; i++) {
		matchDrumAction(tagRssi, 5, 2);
		//右手，匹配tag9
		// TODO Auto-generated method stub
		return null;

	}
	private void matchLR(double[] tagRssi){
		int countl = 0;
		int countr = 0;
		for(int i = 0; i<5; i++){
			if(tagRssi[i]<3500)
				countl ++;
		}
		for(int i = 5; i<9; i++){
			if(tagRssi[i]<3500)
				countr ++;
		}
		if(countl == 4)
			cha[0] = false;
		else
			cha[0] = true;
		if(countr == 4)
			cha[1] = false;
		else
			cha[1] = true;
	}
	private void matchChaActionl(double[] tagRssi){
		int count = 0;
		for(int i = 0; i<4; i++){
			if(tagRssi[i]<3500)
				count ++;
		}
		if(count == 4)
			new DrumCommand(new CommandInfo(CommandInfo.PRESS, 0)).excute();		
		else
			new DrumCommand(new CommandInfo(CommandInfo.RELEASE, 0)).excute();
	}
	private void matchChaActionr(double[] tagRssi){
		int count = 0;
		
		for(int i = 5; i<10; i++){
			if(tagRssi[i]<3500)
				count ++;
		}
		if(count == 5 )
			new DrumCommand(new CommandInfo(CommandInfo.PRESS, 3)).excute();
		else
			new DrumCommand(new CommandInfo(CommandInfo.RELEASE, 3)).excute();
	}
	private void matchDrumAction(double[] tagRssi, int index, int key) {
		double rssi = tagRssi[index];
		if (rssi > threholdPressDrum && !tagPressed[index]) {
			tagPressed[index] = true;
			new DrumCommand(new CommandInfo(CommandInfo.PRESS, key)).excute();
		} else if (rssi < threholdReleaseDrum && tagPressed[index]) {
			tagPressed[index] = false;
			new DrumCommand(new CommandInfo(CommandInfo.RELEASE, key))
					.excute();
		}
		
			
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		DrumCommand.quitGui();

	}

}
