package dislab.rfidaction.reference;
import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;
import com.alien.enterpriseRFID.tags.Tag;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RFIDtest {
	private static final int TAG_NUM = 10;
	boolean[] last = new boolean[7];
	String[] tagids = new String[] { "E20090276205016224501969",
			"E20090276205016225101643", "E20090276205016224401CB4",
			"E2009027620501622460196A", "E2009027620501622480196C",
			"E20090276205016224101CB1", "E20090276205016224301CB3",
			"E2009027620501622470196B", "E20090276205016224901641",
			"E20090276205016224201CB2" };
	List<String> tags = Arrays.asList(tagids);

	public RFIDtest() throws AlienReaderException, IOException,
			InterruptedException {

		AlienClass1Reader reader = new AlienClass1Reader();
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
		System.out.println("-----------------");

		// System.out.print("\nR:\t" + r + "\t");

		// Output file
		BufferedWriter write = new BufferedWriter(new FileWriter(
				"./12_11_7_Alien_3finger.csv"));
		String tag_info = "";
		double[] _info = new double[TAG_NUM];

		for (int i = 0; i < 7; i++) {
			last[i] = false;
		}

		for (int j = 0; j < 120; j++) {
			try {
				tag_info = "";
				for (int k = 0; k < 7; k++) {
					_info[k] = 0;
				}

				long timeBefore = System.currentTimeMillis();
				Tag tagList[] = reader.getCustomTagList();
				long timeAfter = System.currentTimeMillis();

				if (tagList == null) {
					// System.out.println("No Tags Found");
					System.out
							.print("\t" + 0 + "\t" + (timeAfter - timeBefore));
				} else {
					// System.out.print("\t" + tagList.length + "\t"
					// + (timeAfter - timeBefore));
					// piano p=new piano();

					for (int i = 0; i < tagList.length; i++) {

						Tag tag = tagList[i];
						/*
						 * if
						 * (tag.getTagID().contentEquals("E20090025907017726600CA7"
						 * )) { _info[0] = tag.getRSSI(); // break; } if
						 * (tag.getTagID
						 * ().contentEquals("E20090025907017726500CA6")) {
						 * _info[1] = tag.getRSSI(); // break; } if
						 * (tag.getTagID
						 * ().contentEquals("E20090025907017726400CA5")) {
						 * _info[2] = tag.getRSSI(); // break; } if
						 * (tag.getTagID
						 * ().contentEquals("E20090025907017726300E88")) {
						 * _info[3] = tag.getRSSI(); // break; }
						 */
						// System.out.println(tag.getTagID());
						int index = tags.indexOf(tag.getTagID());
						System.out.println(index);
						if (index > 0)
							_info[index] = tag.getRSSI();
					}
					/*
					 * if(_info[0]>0 && !last[0]){ p.Play_1();
					 * System.out.print("----1----\n"); last[0]=true; }
					 * if(_info[0] == 0){ last[0]=false; } if(_info[1]>0 &&
					 * !last[1]){ p.Play_2(); System.out.print("----2----\n");
					 * last[1]=true; } if(_info[1] == 0){ last[1]=false; }
					 * if(_info[2]>0 && !last[2]){ p.Play_3();
					 * System.out.print("----3----\n"); last[2]=true; }
					 * if(_info[2] == 0){ last[2]=false; } if(_info[3]>0 &&
					 * !last[3]){ p.Play_4(); System.out.print("----4----\n");
					 * last[3]=true; } if(_info[3] == 0){ last[3]=false; }
					 * if(_info[4]>1000 && !last[4]){ p.Play_5();
					 * System.out.print("----5----\n"); last[4]=true; }
					 * if(_info[4] <= 1000){ last[4]=false; } if(_info[5]>1000
					 * && !last[5]){ p.Play_6();
					 * System.out.print("----6----\n"); last[5]=true; }
					 * if(_info[5] <= 1000){ last[5]=false; } if(_info[6]>1000
					 * && !last[6]){ p.Play_7();
					 * System.out.print("----7----\n"); last[6]=true; }
					 * if(_info[6] <= 1000){ last[6]=false; }
					 */
					// tag_info = _info[0] + "," + _info[1] + "," + _info[2] +
					// "," + _info[3] ;
					// + ","+ _info[4] + "," + _info[5]+ "," + _info[6];
					tag_info = "";
					for (int i = 0; i < TAG_NUM; i++) {
						tag_info += _info[i] + ",";
					}
					tag_info.substring(0, tag_info.length() - 2);
					// tag_info = "" + _info[0] + "," + _info[1] + "," +
					// _info[2];
					write.write(tag_info);
					write.newLine();
				}
			} catch (AlienReaderTimeoutException e) {
			}
			// Thread.sleep(100);
			// p.zero();
		}
		write.flush();
		write.close();
		// Close the connection
		reader.close();
	}

	public static final void main(String args[]) {
		try {
			new RFIDtest();
		} catch (AlienReaderException e) {
			System.out.println("Error: " + e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
