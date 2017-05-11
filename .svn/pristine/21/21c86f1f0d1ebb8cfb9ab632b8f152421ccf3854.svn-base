package dislab.rfidaction.core.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * 竖笛界面
 * 
 */
public class ClarinetGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4885593715262813002L;
	private JLabel[] lb = new JLabel[9];
	private ImageIcon [] imgp = new ImageIcon[7]; 
	private ImageIcon [] imgr = new ImageIcon[7];
	
	private File wav0;
	private File wav1;
	private File wav2;
	private File wav3;
	private File wav4;
	private File wav5;
	private File wav6;
	private Clip[] c = new Clip[7];
	private AudioInputStream ais0;
	private AudioInputStream ais1;
	private AudioInputStream ais2;
	private AudioInputStream ais3;
	private AudioInputStream ais4;
	private AudioInputStream ais5;
	private AudioInputStream ais6;
	private boolean[] key = new boolean[6];

	public ClarinetGui() {
		super();
		// set layout
		GridLayout gridlayout = new GridLayout(7, 1);
		//GridLayout gridlayout1 = new GridLayout(1, 1);
		this.setLayout(null);

		// set panel
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p.setLayout(gridlayout);
		//p1.setLayout(gridlayout1);
		//p2.setLayout(gridlayout1);
		p.setSize(352, 724);
		//p1.setSize(250, 286);
		//p2.setSize(250, 96);

		// load image
		for(int i=1;i<8;i++){
			imgr[i-1] = new ImageIcon("./img/re/0_0"+i+".jpg"); 
			imgp[i-1] = new ImageIcon("./img/re/1_0"+i+".jpg"); 
		}

		// load wav
		wav0 = new File("./wav/recorder/0.wav");
		wav1 = new File("./wav/recorder/1.wav");
		wav2 = new File("./wav/recorder/2.wav");
		wav3 = new File("./wav/recorder/3.wav");
		wav4 = new File("./wav/recorder/4.wav");
		wav5 = new File("./wav/recorder/5.wav");
		wav6 = new File("./wav/recorder/6.wav");

		try {
			initaudio();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// set label
		lb[0] = new JLabel(imgr[0]);
		lb[1] = new JLabel(imgr[1]);
		lb[2] = new JLabel(imgr[2]);
		lb[3] = new JLabel(imgr[3]);
		lb[4] = new JLabel(imgr[4]);
		lb[5] = new JLabel(imgr[5]);
		lb[6] = new JLabel(imgr[6]);
		/*
		 * lb[0].setBounds(0, 20, 286, 227); lb[1].setBounds(0, 306, 37, 227);
		 * lb[2].setBounds(0, 343, 37, 227); lb[3].setBounds(0, 380, 37, 227);
		 * lb[4].setBounds(0, 417, 46, 227); lb[5].setBounds(0, 463, 37, 227);
		 * lb[6].setBounds(0, 500, 37, 227); lb[7].setBounds(0, 537, 37, 227);
		 * lb[8].setBounds(0, 574, 96, 227);
		 */

		for (int i = 0; i < 7; i++) {
			lb[i].setOpaque(true);
		}

		// set frame location
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) (screen.getWidth() / 2 - 176),
				(int)screen.getHeight()/2 - 412);

		// add to frame
		this.setLayout(null);
		this.setSize(352, 724);
		
		this.add(p,BorderLayout.CENTER);
		
		this.setResizable(true);
		this.setFocusable(false);
		this.setUndecorated(true);
		this.setTitle("CLARINET");
		
		for (int i = 0; i < 7; i++) {
			p.add(lb[i], null);
		}

	}

	private void initaudio() throws Exception {
		ais0 = AudioSystem.getAudioInputStream(wav0);
		ais1 = AudioSystem.getAudioInputStream(wav1);
		ais2 = AudioSystem.getAudioInputStream(wav2);
		ais3 = AudioSystem.getAudioInputStream(wav3);
		ais4 = AudioSystem.getAudioInputStream(wav4);
		ais5 = AudioSystem.getAudioInputStream(wav5);
		ais6 = AudioSystem.getAudioInputStream(wav6);

		c[0] = AudioSystem.getClip();
		c[1] = AudioSystem.getClip();
		c[2] = AudioSystem.getClip();
		c[3] = AudioSystem.getClip();
		c[4] = AudioSystem.getClip();
		c[5] = AudioSystem.getClip();
		c[6] = AudioSystem.getClip();

		c[0].open(ais0);
		c[1].open(ais1);
		c[2].open(ais2);
		c[3].open(ais3);
		c[4].open(ais4);
		c[5].open(ais5);
		c[6].open(ais6);
	}

	private void pressed(int i, boolean[] key) {
		System.out.println("pressed" + i);

		if(i == 0){
			lb[1].setIcon(imgp[1]);
		}
		if(i == 1){
			lb[3].setIcon(imgp[3]);
		}
		if(i == 2){
			lb[5].setIcon(imgp[5]);
		}
		if(i == 3){
			lb[2].setIcon(imgp[2]);
		}
		if(i == 4){
			lb[4].setIcon(imgp[4]);
		}
		if(i == 5){
			lb[6].setIcon(imgp[6]);
		}
		if(key[0] && key[1] && key[2] && key[3] && key[4] && key[5])
			c[4].loop(Clip.LOOP_CONTINUOUSLY);
		else c[4].stop();
		if(key[0] && key[1] && key[2] && key[3] && key[4] && key[5]==false)
			c[5].loop(Clip.LOOP_CONTINUOUSLY);
		else c[5].stop();
		if(key[1] && key[2] && key[4] && key[5] && key[0]==false && key[3]==false)
			c[3].loop(Clip.LOOP_CONTINUOUSLY);
		else c[3].stop();
		if(key[0] && key[1] && key[2] && key[3] && key[4]==false && key[5]==false)
			c[6].loop(Clip.LOOP_CONTINUOUSLY);
		else c[6].stop();
		if(key[0] && key[1] && key[2] && key[3]==false && key[4]==false && key[5]==false)
			c[0].loop(Clip.LOOP_CONTINUOUSLY);
		else c[0].stop();
		if(key[0] && key[1] && key[2]==false && key[3]==false && key[4]==false && key[5]==false)
			c[1].loop(Clip.LOOP_CONTINUOUSLY);
		else c[1].stop();
		if(key[0] && key[1]==false && key[2]==false && key[3]==false && key[4]==false && key[5]==false)
			c[2].loop(Clip.LOOP_CONTINUOUSLY);	
		else c[2].stop();
	}

	private void released(int i, boolean[] key) {
		System.out.println("released" + i);

		if(i == 0){
			lb[1].setIcon(imgr[1]);
		}
		if(i == 1){
			lb[3].setIcon(imgr[3]);
		}
		if(i == 2){
			lb[5].setIcon(imgr[5]);
		}
		if(i == 3){
			lb[2].setIcon(imgr[2]);
		}
		if(i == 4){
			lb[4].setIcon(imgr[4]);
		}
		if(i == 5){
			lb[6].setIcon(imgr[6]);
		}	
		if(key[0]==false && key[1]==false && key[2]==false && key[3]==false && key[4]==false && key[5]==false)
			c[4].stop();
		if(key[0]==false && key[1]==false && key[2]==false && key[3]==false && key[4]==false)
			c[5].stop();
		if(key[1]==false && key[2]==false && key[4]==false && key[5]==false)
			c[3].stop();
		if(key[0]==false && key[1]==false && key[2]==false && key[3]==false)
			c[6].stop();
		if(key[0]==false && key[1]==false && key[2]==false)
			c[0].stop();
		if(key[0]==false && key[1]==false)
			c[1].stop();
		if(key[0]==false)
			c[2].stop();
	}

	public void pressed(int key) {
		// TODO Auto-generated method stub
		this.key[key] = true;
		pressed(key, this.key);
	}

	public void released(int key) {
		// TODO Auto-generated method stub
		this.key[key] = false;
		released(key, this.key);
	}

	public void exit() {
		for (int i = 0; i < 7; i++) {
			c[i].stop();

		}
	}
}
