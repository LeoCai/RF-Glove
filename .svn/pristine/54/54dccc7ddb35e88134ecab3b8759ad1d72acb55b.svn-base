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
	private ImageIcon img1;
	private ImageIcon img2;
	private ImageIcon img3;
	private ImageIcon img4;
	private ImageIcon img5;
	private ImageIcon img6;
	private ImageIcon img7;
	private ImageIcon img8;
	private ImageIcon img9;
	private ImageIcon img22;
	private ImageIcon img33;
	private ImageIcon img44;
	private ImageIcon img66;
	private ImageIcon img77;
	private ImageIcon img88;
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
		GridLayout gridlayout1 = new GridLayout(1, 1);
		this.setLayout(null);
		// set menu
		JMenuBar mbar = new JMenuBar();
		JMenu menu1 = new JMenu("�˵�", true);
		JMenuItem exit = new JMenuItem("exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu1.add(exit);
		mbar.add(menu1);
		setJMenuBar(mbar);

		// set panel
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p.setLayout(gridlayout);
		p1.setLayout(gridlayout1);
		p2.setLayout(gridlayout1);
		p.setSize(250, 259);
		p1.setSize(250, 286);
		p2.setSize(250, 96);

		// load image
		img1 = new ImageIcon("./img/re/1.jpg");
		img2 = new ImageIcon("./img/re/2.jpg");
		img3 = new ImageIcon("./img/re/3.jpg");
		img4 = new ImageIcon("./img/re/4.jpg");
		img5 = new ImageIcon("./img/re/5.jpg");
		img6 = new ImageIcon("./img/re/6.jpg");
		img7 = new ImageIcon("./img/re/7.jpg");
		img8 = new ImageIcon("./img/re/8.jpg");
		img9 = new ImageIcon("./img/re/9.jpg");
		img22 = new ImageIcon("./img/re/22.jpg");
		img33 = new ImageIcon("./img/re/33.jpg");
		img44 = new ImageIcon("./img/re/44.jpg");
		img66 = new ImageIcon("./img/re/66.jpg");
		img77 = new ImageIcon("./img/re/77.jpg");
		img88 = new ImageIcon("./img/re/88.jpg");

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
		lb[0] = new JLabel(img1);
		lb[1] = new JLabel(img2);
		lb[2] = new JLabel(img3);
		lb[3] = new JLabel(img4);
		lb[4] = new JLabel(img5);
		lb[5] = new JLabel(img6);
		lb[6] = new JLabel(img7);
		lb[7] = new JLabel(img8);
		lb[8] = new JLabel(img9);
		/*
		 * lb[0].setBounds(0, 20, 286, 227); lb[1].setBounds(0, 306, 37, 227);
		 * lb[2].setBounds(0, 343, 37, 227); lb[3].setBounds(0, 380, 37, 227);
		 * lb[4].setBounds(0, 417, 46, 227); lb[5].setBounds(0, 463, 37, 227);
		 * lb[6].setBounds(0, 500, 37, 227); lb[7].setBounds(0, 537, 37, 227);
		 * lb[8].setBounds(0, 574, 96, 227);
		 */

		for (int i = 0; i < 9; i++) {
			lb[i].setOpaque(true);
		}

		// set frame location
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) screen.getWidth() / 2, 0);

		// add to frame
		this.setLayout(null);
		this.setSize(265, 700);
		this.add(p1);
		this.add(p);
		this.add(p2);
		this.setResizable(false);
		this.setFocusable(false);
		p1.setBounds(0, 0, 250, 286);
		p.setBounds(0, 283, 250, 260);
		p2.setBounds(0, 543, 250, 96);
		this.setTitle("RFID��������");
		p1.add(lb[0], null);
		p2.add(lb[8], null);
		for (int i = 1; i < 8; i++) {
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

		if (i == 0)
			lb[1].setIcon(img22);
		if (i == 1)
			lb[2].setIcon(img33);
		if (i == 2)
			lb[3].setIcon(img44);
		if (i == 3)
			lb[5].setIcon(img66);
		if (i == 4)
			lb[6].setIcon(img77);
		if (i == 5)
			lb[7].setIcon(img88);
		if (key[0] && key[1] && key[2] && key[3] && key[4] && key[5])
			c[4].loop(Clip.LOOP_CONTINUOUSLY);
		else if (key[0] && key[1] && key[2] && key[3] && key[4]
				&& key[5] == false)
			c[5].loop(Clip.LOOP_CONTINUOUSLY);
		else if (key[1] && key[2] && key[4] && key[5] && key[0] == false
				&& key[3] == false)
			c[3].loop(Clip.LOOP_CONTINUOUSLY);
		else if (key[0] && key[1] && key[2] && key[3] && key[4] == false
				&& key[5] == false)
			c[6].loop(Clip.LOOP_CONTINUOUSLY);
		else if (key[0] && key[1] && key[2] && key[3] == false
				&& key[4] == false && key[5] == false)
			c[0].loop(Clip.LOOP_CONTINUOUSLY);
		else if (key[0] && key[1] && key[2] == false && key[3] == false
				&& key[4] == false && key[5] == false)
			c[1].loop(Clip.LOOP_CONTINUOUSLY);
		else if (key[0] && key[1] == false && key[2] == false
				&& key[3] == false && key[4] == false && key[5] == false)
			c[2].loop(Clip.LOOP_CONTINUOUSLY);
	}

	private void released(int i, boolean[] key) {
		System.out.println("released" + i);

		if (i == 0)
			lb[1].setIcon(img2);
		if (i == 1)
			lb[2].setIcon(img3);
		if (i == 2)
			lb[3].setIcon(img4);
		if (i == 3)
			lb[5].setIcon(img6);
		if (i == 4)
			lb[6].setIcon(img7);
		if (i == 5)
			lb[7].setIcon(img8);
		if (key[0] == false && key[1] == false && key[2] == false
				&& key[3] == false && key[4] == false && key[5] == false)
			c[4].stop();
		if (key[0] == false && key[1] == false && key[2] == false
				&& key[3] == false && key[4] == false)
			c[5].stop();
		if (key[1] == false && key[2] == false && key[4] == false
				&& key[5] == false)
			c[3].stop();
		if (key[0] == false && key[1] == false && key[2] == false
				&& key[3] == false)
			c[6].stop();
		if (key[0] == false && key[1] == false && key[2] == false)
			c[0].stop();
		if (key[0] == false && key[1] == false)
			c[1].stop();
		if (key[0] == false)
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
}
