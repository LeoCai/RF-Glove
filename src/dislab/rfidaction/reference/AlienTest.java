package dislab.rfidaction.reference;
import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;
import com.alien.enterpriseRFID.tags.Tag;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AlienTest {
	
	boolean[] last=new boolean[6];

	public AlienTest() throws AlienReaderException, IOException,
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
		reader.setAntennaSequence("1");

		// Set power
		int r = 307;
		reader.setRFLevel(r);
		reader.setTagMask("FFFF");
		System.out.println("-----------------");

	//	System.out.print("\nR:\t" + r + "\t");

		// Output file
		BufferedWriter write = new BufferedWriter(new FileWriter(
				"E://piano_11_18_3.csv"));
		String tag_info = "";
		double[] _info = new double[6];
		
		for (int i = 0; i < 6; i++) {
			last[i]=false;
		}

		for (int j = 0; j < 6000; j++) {
			try {
				tag_info = "";
				for (int k = 0; k < 6; k++) {
					_info[k] = 0;
				}

				Tag tagList[] = reader.getCustomTagList();

				if (tagList == null) {
				} else {
					piano p=new piano();
					for (int i = 0; i < tagList.length; i++) {
						
						Tag tag = tagList[i];

						if (tag.getTagID().contentEquals("FFFF00000000000000000252")) {
							_info[0] = tag.getRSSI();
							
						}
						if (tag.getTagID().contentEquals("FFFF00000000000000000236")) {
							_info[1] = tag.getRSSI();
							
						}
						if (tag.getTagID().contentEquals("FFFF00000000000000000214")) {
							_info[2] = tag.getRSSI();
							
						}
						if (tag.getTagID().contentEquals("FFFF00000000000000000216")) {
							_info[3] = tag.getRSSI();
							
						}
						if (tag.getTagID().contentEquals("FFFF00000000000000000259")) {
							_info[4] = tag.getRSSI();
							
						}
						if (tag.getTagID().contentEquals("FFFF00000000000000000219")) {
							_info[5] = tag.getRSSI();
							
						}
					}
					
					if(_info[0]>6000 && !last[0]){
						
						p.Play_1();
						System.out.print(_info[0]+"----1----\n");
						last[0]=true;
					}
					if(_info[0]<=6000){
						last[0]=false;
					}
					if(_info[1]>6500 && !last[1]){
						
						p.Play_2();
						System.out.print(_info[1]+"----2----\n");
						last[1]=true;
					}
					if(_info[1]<=6500){
						last[1]=false;
					}
					if(_info[2]>7000 && !last[2]){
						
						p.Play_3();
						System.out.print(_info[2]+"----3----\n");
						last[2]=true;
					}
					if(_info[2]<=7000){
						last[2]=false;
					}
					if(_info[3]>6000 && !last[3]){
						
						p.Play_4();
						System.out.print(_info[3]+"----4----\n");
						last[3]=true;
					}
					if(_info[3]<=6000){
						last[3]=false;
					}
					if(_info[4]>6000 && !last[4]){
						
						p.Play_5();
						System.out.print(_info[4]+"----5----\n");
						last[4]=true;
					}
					if(_info[4]<=6000){
						last[4]=false;
					}
					if(_info[5]>6000 && !last[5]){
						
						p.Play_6();
						System.out.print(_info[5]+"----6----\n");
						last[5]=true;
					}
					if(_info[5]<=6000){
						last[5]=false;
					}
					tag_info = _info[0] + "," + _info[1] + "," + _info[2] + ","
							+ _info[3] + "," + _info[4] + "," + _info[5];
					write.write(tag_info);
					write.newLine();
				}
				if(_info[0]>6000 &&_info[5]>6000){
					
					break;
				}
			} catch (AlienReaderTimeoutException e) {
			}
			//Thread.sleep(100);
			//p.zero();
		}
		write.flush();
		write.close();
		// Close the connection
		reader.close();
	}

	public static final void main(String args[]) {
		try {
			new AlienTest();
		} catch (AlienReaderException e) {
			System.out.println("Error: " + e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
