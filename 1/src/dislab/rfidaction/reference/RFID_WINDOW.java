package dislab.rfidaction.reference;
import com.alien.enterpriseRFID.reader.AlienClass1Reader;

import com.alien.enterpriseRFID.reader.AlienReaderConnectionException;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionRefusedException;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.reader.AlienReaderNotValidException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;
import com.alien.enterpriseRFID.tags.Tag;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RFID_WINDOW {

	private static final int MAX_READ_COUNT = 10000;
	
	private static final int WINDOW_SIZE = 3;
	private static final int WINDOW_MOVE_SIZE = WINDOW_SIZE;
	private static final int INIT_SIZE = 5;
	private static final double THRED_HOLED_RSSI = 500;
	private static final int COLLECT_THREHOLD_MAX_NUM = 10;

	// String[] tagids = new String[] { "E20090276205016224501969",
	// "E20090276205016225101643", "E20090276205016224401CB4",
	// "E2009027620501622460196A", "E2009027620501622480196C",
	// "E20090276205016224101CB1", "E20090276205016224301CB3",
	// "E2009027620501622470196B", "E20090276205016224901641",
	// "E20090276205016224201CB2" };
	// String[] tagids = new String[] { "E20090276205016227400662" };
	String[] tagids = new String[] {"E20090276205016224301CB3", "E2009027620501622470196B",
			"E20090276205016224901641","E20090276205016224201CB2" };
	// »¬¶¯´°¿Ú
	private int tagNum = tagids.length;
	List<String> tags = Arrays.asList(tagids);

	private double[][] tagRssiWindows = new double[tagNum][WINDOW_SIZE];
	//private int[] windowFullSize = new int[tagNum];
	private int windowFullSize ;

	private double[][] tagInitWindow = new double[tagNum][INIT_SIZE];
	private double[] tagInitRssi;

	private BufferedWriter fileWriter;

	private AlienClass1Reader reader;
	private int timeCount;
	private int threholdNum;
	private double[] deltaThrehold = new double[tagNum];
	private double[] preDeltas = new double[tagNum];

	public RFID_WINDOW() throws AlienReaderException, IOException,
			InterruptedException {

		fileWriter = new BufferedWriter(new FileWriter(
				"./12_11_7_Alien_3finger.csv"));

		initReader();

		System.out.println("-----------------");

		startReading();

		fileWriter.close();
		// Close the connection
		reader.close();
	}

	private void initReader() throws AlienReaderException {

		reader = new AlienClass1Reader();
		reader.setConnection("192.168.1.102", 23);
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
		tagInitRssi = new double[tagNum];
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

					if (timeCount < INIT_SIZE) {

						collectInitInfo(tagList);
					}

					else {

						addToWindow(tagList);

					}

				}
			} catch (AlienReaderTimeoutException e) {
			}

		}
	}

	private void collectInitInfo(Tag[] tagList) {
		for (int i = 0; i < tagList.length; i++) {
			Tag tag = tagList[i];
			int index = tags.indexOf(tag.getTagID());
			// System.out.println(tag.getTagID() + "," + tag.getRSSI());
			if (index > -1) {
				tagInitWindow[index][timeCount] = tag.getRSSI();
			}
		}

		if (timeCount == INIT_SIZE - 1) {

			for (int i = 0; i < tagNum; i++) {
				for (int j = 0; j < INIT_SIZE; j++) {
					tagInitRssi[i] += tagInitWindow[i][j];
				}
			}
			for (int i = 0; i < tagNum; i++) {
				tagInitRssi[i] /= INIT_SIZE;
			}
			System.out.println("initStateWindow:"
					+ Arrays.toString(tagInitWindow[0]));
			System.out.println("initState:" + Arrays.toString(tagInitRssi));

		}
	}

	private void addToWindow(Tag[] tagList) throws IOException {
		double[] _info = new double[tagNum];

		String tag_info;

		for (int i = 0; i < tagList.length; i++) {
			Tag tag = tagList[i];
			// System.out.println(tag.getTagID() + "," + tag.getRSSI());
			int index = tags.indexOf(tag.getTagID());

			if (index > -1) {
				// System.out.println(tag.getTagID());
				tagRssiWindows[index][(timeCount + WINDOW_MOVE_SIZE - 1)
						% WINDOW_SIZE] = tag.getRSSI();
				//windowFullSize[index] += 1;
				// System.out.println(tag.getRSSI());
				_info[index] = tag.getRSSI();

//				if (windowFullSize[index] == 3) {
////					System.out.println("start check action");
//					checkAction(index);
//					clearWindow(index);
//				}

			}
			
		}
		windowFullSize += 1;
		if (windowFullSize == 3) {
//			System.out.println("start check action");
			for(int k=0;k<tagNum;k++){
				checkAction(k);
				clearWindow(k);
			}
			
		}
		tag_info = "";
		for (int i = 0; i < tagNum; i++) {
			// System.out.println(_info[i]);
			tag_info += _info[i] + ",";
		}
		tag_info.substring(0, tag_info.length() - 2);

		fileWriter.write(tag_info);
		fileWriter.newLine();
	}

	private void checkAction(int i) {
		// TODO Auto-generated method stub
		double[] deltas = new double[tagNum];
		// double squareRoot = 0;
		// System.out.println("\n" + Arrays.toString(tagRssiWindows[i]));
		// System.out.println(tagInitRssi[i] + "\n");
		for (int j = 0; j < WINDOW_SIZE; j++) {
			deltas[i] += tagRssiWindows[i][j] - tagInitRssi[i];
		}
//		System.out.println("deltas:" + deltas[i]);
		if (threholdNum < COLLECT_THREHOLD_MAX_NUM) {
			deltaThrehold[i] += deltas[i];
			threholdNum++;
		} else if (threholdNum == COLLECT_THREHOLD_MAX_NUM) {
			deltaThrehold[i] /= threholdNum;
			System.out.println("start check action");
//			System.out.println("deltaThrehold:"
//					+ Arrays.toString(deltaThrehold));
			threholdNum++;
		} else {
			preDeltas[i] = preDeltas[i] == 0 ? deltas[i] : preDeltas[i];
			if ((deltas[i] - preDeltas[i]) < -THRED_HOLED_RSSI) {
				System.out.println("\nTAG:" + i + "Action UP\n");
			} else if ((deltas[i] - preDeltas[i]) > THRED_HOLED_RSSI) {
				System.out.println("\nTAG:" + i + "Action DOWN\n");
			}
			preDeltas[i] = deltas[i];
		}

	}

	private void clearWindow(int i) {
		windowFullSize = 0;
		tagRssiWindows[i] = new double[WINDOW_SIZE];
	}

	public class WindowState {
		public int meanRssi;
		// public int maxRssi;
	}

	public static final void main(String args[]) {
		try {
			new RFID_WINDOW();
		} catch (AlienReaderException e) {
			System.out.println("Error: " + e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
