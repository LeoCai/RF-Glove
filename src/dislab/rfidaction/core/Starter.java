package dislab.rfidaction.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;
import com.alien.enterpriseRFID.tags.Tag;

import dislab.rfidaction.utils.RfidUtils;
import dislab.rfidaction.utils.TagsInit;

/**
 * 涓荤▼搴�
 * 
 */
public class Starter {

	private static final int MAX_READ_COUNT = 10000;

	static String[] tagids = new String[] { "E2009027620501480520DDD0",
			"E2009027620501480530DA99", "E2009027620501480490DDCD",
			"E2009027620501480500DDCE", "E2009027620501480510DDCF",
			"E20090276205016224101CB1", "E20090276205016224301CB3",
			"E2009027620501622470196B", "E20090276205016224901641",
			"E20090276205016225801056" };

	public static final List<String> tags = Arrays.asList(tagids);

	private ChartDrawer chartDrawer;

	private AlienClass1Reader reader;

	private int timeCount;

	private ActionManager actionManager = new ActionManager(tags);

	public Starter() throws AlienReaderException, IOException,
			InterruptedException {
		this.chartDrawer = ChartDrawer.getInstance();

		initReader();

		startReading();

		reader.close();
	}

	private void initReader() throws AlienReaderException {

		reader = new AlienClass1Reader();
		reader.setConnection("192.168.1.103", 23);
		reader.setUsername("alien");
		reader.setPassword("password");
		reader.open();

		// We only need the EPC and RSSI values
		String customFomatStr = "Tag:${TAGID},RSSI:${RSSI}";
		reader.setTagListFormat(AlienClass1Reader.CUSTOM_FORMAT);
		reader.setTagListCustomFormat(customFomatStr);
		reader.setAntennaSequence("0");

		// Set power
		int r = 307;
		reader.setRFLevel(r);
		
		reader.setTagMask("E200");
	}

	private void startReading() throws AlienReaderException, IOException {
		for (timeCount = 0; timeCount < MAX_READ_COUNT; timeCount++) {
			try {
				long timeBefore = System.currentTimeMillis();
				Tag tagList[] = reader.getCustomTagList();
				long timeAfter = System.currentTimeMillis();

				if (tagList == null) {
					// System.out.println("No Tags Found");
					System.out
							.print("\t" + 0 + "\t" + (timeAfter - timeBefore));
				} else {

					double targetTagRssi[] = RfidUtils.getTargetTagRssi(
							tagList, tags);
					 double targetTagRssi2[] = RfidUtils.getTargetTagRssi(
					 tagList, tags, new int[] {5,6,7,8,9});
					addToChart(targetTagRssi);
					if (chartDrawer.isIniting()) {
						if (!TagsInit.getInstance().initing(targetTagRssi)) {
							chartDrawer.setIniting(false);
						}
					} else {
						actionManager.checkAction(targetTagRssi);
					}

				}
			} catch (AlienReaderTimeoutException e) {
			}

		}
	}

	/**
	 * 鏄剧ずrssi鍔ㄦ�佸浘琛�
	 * 
	 * @param targetTagRssi
	 */
	private void addToChart(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		chartDrawer.addData(targetTagRssi);

	}

	public static final void main(String args[]) {
		try {
			new Starter();
		} catch (AlienReaderException e) {
			System.out.println("Error: " + e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
