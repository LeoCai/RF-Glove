package dislab.rfidaction.core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.impinj.octanesdk.AntennaConfigGroup;
import com.impinj.octanesdk.BitPointers;
import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.MemoryBank;
import com.impinj.octanesdk.OctaneSdkException;
import com.impinj.octanesdk.ReaderMode;
import com.impinj.octanesdk.ReportConfig;
import com.impinj.octanesdk.ReportMode;
import com.impinj.octanesdk.SearchMode;
import com.impinj.octanesdk.Settings;
import com.impinj.octanesdk.Tag;
import com.impinj.octanesdk.TagFilter;
import com.impinj.octanesdk.TagFilterMode;
import com.impinj.octanesdk.TagFilterOp;
import com.impinj.octanesdk.TagReport;
import com.impinj.octanesdk.TagReportListener;

import dislab.rfidaction.core.view.PianoGui;
import dislab.rfidaction.core.view.RockGameGui;
import dislab.rfidaction.utils.RfidUtils;
import dislab.rfidaction.utils.TagsInit;

/**
 * 主程序
 * 
 */
public class ImpinjStater implements TagReportListener {

	/**
	 * 阅读器IP地址
	 * 注意每次确认阅读器IP地址是否正确
	 */
	private static final String READER_IP = "192.168.1.100";
	
	/**
	 * 六根手指对应的标签EPC，若更换标签后这边一定要换
	 * 注意MASK设置，目前设置只允许E200开头
	 */
	private String tagEPCs[] = new String[] { "E200 9027 6205 0162 2320 2724", "E280 1160 6000 0204 A12A 5ADA",
			"E280 1160 6000 0204 A12A 5A8A", "E280 1160 6000 0204 A12A 5A9A", "E200 9027 6205 0162 2480 196C",
			"E200 9027 6205 0148 0520 DDD0", "E280 1160 6000 0204 A12A 5ABA", "E280 1160 6000 0204 A12A 5AAA",
			"E280 1160 6000 0204 A12A 5ACA", "E200 9027 6205 0148 0510 DDCF" };
	/**
	 * 读取数据的缓存区大小
	 */
	private static final int BUFFER_SIZE = 10;

	/**
	 * 缓冲队列
	 */
	private ArrayList<ArrayList<Tag>> tagBufferLists = new ArrayList<ArrayList<Tag>>();
	{
		for (int i = 0; i < 4; i++) {
			tagBufferLists.add(new ArrayList<Tag>());
		}
	}

	

	private List<String> tagsStrList = Arrays.asList(tagEPCs);

	private ChartDrawer chartDrawer;

	private int timeCount;

	private ActionManager actionManager = new ActionManager(tagsStrList);
	
	/**
	 * 每次都会将数据写入piano.csv中，供debug用
	 */
	FileWriter logger;
	{
		try {
			logger = new FileWriter("piano.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImpinjStater() throws IOException, InterruptedException {
		this.chartDrawer = ChartDrawer.getInstance();
		// RockGameGui rockGameGui = new RockGameGui();
		// rockGameGui.setVisible(true);
		// PianoGui pianoGui = new PianoGui();
		// pianoGui.setVisible(true);
		initReader();

	}

	/**
	 * 初始化阅读器
	 * 注意MASK设置
	 */
	private void initReader() {

		try {
			ImpinjReader reader = new ImpinjReader();

			this.chartDrawer.setReader(reader);

			System.out.println("Connecting");
			reader.connect(READER_IP);

			Settings settings = reader.queryDefaultSettings();

			ReportConfig report = settings.getReport();
			report.setIncludeAntennaPortNumber(true);
			report.setIncludePeakRssi(true);
			report.setIncludePhaseAngle(true);
			report.setMode(ReportMode.Individual);

			TagFilter t1 = settings.getFilters().getTagFilter1();
			t1.setBitCount(16);
			t1.setBitPointer(BitPointers.Epc);
			t1.setMemoryBank(MemoryBank.Epc);
			t1.setFilterOp(TagFilterOp.Match);
			t1.setTagMask("E280");
			settings.getFilters().setMode(TagFilterMode.OnlyFilter1);

			settings.setReaderMode(ReaderMode.MaxThroughput);
			settings.setSearchMode(SearchMode.DualTarget);

			// set some special settings for antenna 1
			AntennaConfigGroup antennas = settings.getAntennas();
			antennas.disableAll();
			antennas.enableById(new short[] { 1});
			antennas.getAntenna((short) 1).setIsMaxRxSensitivity(false);
			antennas.getAntenna((short) 1).setIsMaxTxPower(false);
			antennas.getAntenna((short) 1).setTxPowerinDbm(20.0);
			antennas.getAntenna((short) 1).setRxSensitivityinDbm(-70);

			reader.setTagReportListener(this);

			System.out.println("Applying Settings");
			reader.applySettings(settings);

			System.out.println("Starting");
			reader.start();

			System.out.println("Press Enter to exit.");
			Scanner s = new Scanner(System.in);
			s.nextLine();

			reader.stop();
			reader.disconnect();
		} catch (OctaneSdkException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace(System.out);
		}
	}

	@Override
	public void onTagReported(ImpinjReader arg0, TagReport arg1) {
		// TODO Auto-generated method stub
		for (Tag tag : arg1.getTags()) {
			int myAntenna = tag.getAntennaPortNumber() - 1;
			addToBuffer(myAntenna, tag);
		}
	}

	/**
	 * 将读到的tag放到对应缓冲区位置
	 * @param myAntenna
	 * @param tag
	 */
	private void addToBuffer(int myAntenna, Tag tag) {
		// TODO Auto-generated method stub
		List<Tag> tagList = tagBufferLists.get(myAntenna);
		tagList.add(tag);
		String text = "";
		double[] rssi = new double[tagsStrList.size()];
		double[] phase = new double[tagsStrList.size()];
		double[] attener = new double[tagsStrList.size()];
		if (tagList.size() >= BUFFER_SIZE) {
			for (Tag t : tagList) {
				String epc;
				int tagIndex = tagsStrList.indexOf(t.getEpc().toString());
				if (tagIndex >= 0) {
					rssi[tagIndex] = t.getPeakRssiInDbm();
					rssi[tagIndex] = rssi[tagIndex] != 0 ? 100 + rssi[tagIndex] : 0;
					phase[tagIndex] = t.getPhaseAngleInRadians();
					attener[tagIndex] = t.getAntennaPortNumber();
				}
			}

			text = "";
			for (int j = 0; j < tagsStrList.size(); j++) {
				text += rssi[j] + ",";
			}
			addToChart(rssi);
			// if (chartDrawer.isIniting()) {
			// if (!TagsInit.getInstance().initing(rssi)) {
			// chartDrawer.setIniting(false);
			// }
			// } else {
			actionManager.checkAction(rssi);
			// }

			System.out.println(text);
			try {
				logger.write(text + "\n");
				logger.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// listTags.Items.Add(text);
			tagList.clear();
		}
	}

	/**
	 * 显示rssi动态图表
	 * 
	 * @param targetTagRssi
	 */
	private void addToChart(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		chartDrawer.addData(targetTagRssi);

	}

	public static final void main(String args[]) {
		try {
			new ImpinjStater();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
