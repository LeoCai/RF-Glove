package dislab.rfidaction.core.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 钢琴界面
 *
 */
public class PianoGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5204718033784360062L;
	private JLabel[] lb = new JLabel[7];
	private ImageIcon img1;
	private ImageIcon img2;
	private ImageIcon img3;
	private ImageIcon img11;
	private ImageIcon img22;
	private ImageIcon img33;
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

	// private AudioStream as;
	public PianoGui() {
		super();
		// set layout
		GridLayout gridlayout = new GridLayout(1, 7);

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
		p.setLayout(gridlayout);

		// load image
		img1 = new ImageIcon("./img/1.jpg");
		img2 = new ImageIcon("./img/2.jpg");
		img3 = new ImageIcon("./img/3.jpg");
		img11 = new ImageIcon("./img/11.jpg");
		img22 = new ImageIcon("./img/22.jpg");
		img33 = new ImageIcon("./img/33.jpg");

		// load wav
		wav0 = new File("./wav/piano/0.wav");
		wav1 = new File("./wav/piano/1.wav");
		wav2 = new File("./wav/piano/2.wav");
		wav3 = new File("./wav/piano/3.wav");
		wav4 = new File("./wav/piano/4.wav");
		wav5 = new File("./wav/piano/5.wav");
		wav6 = new File("./wav/piano/6.wav");

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
		lb[3] = new JLabel(img1);
		lb[4] = new JLabel(img2);
		lb[5] = new JLabel(img2);
		lb[6] = new JLabel(img3);

		for (int i = 0; i < 7; i++) {
			lb[i].setOpaque(true);
		}

		// set frame location
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) screen.getWidth() / 2, 0);

		// add to frame
		this.add(p, BorderLayout.CENTER);
		this.setTitle("RFID����");
		this.setResizable(false);
		this.setSize(640, 460);
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

	public void pressed(int i) {
		System.out.println("pressed");
		if (i == 0 || i == 3) {
			lb[i].setIcon(img11);
			// c[i].setFramePosition(0);
			c[i].start();
		}
		if (i == 1 || i == 4 || i == 5) {
			lb[i].setIcon(img22);
			// c[i].setFramePosition(0);
			c[i].start();
		}
		if (i == 2 || i == 6) {
			lb[i].setIcon(img33);
			// c[i].setFramePosition(0);
			c[i].start();
		}

	}

	public void released(int i) {
		System.out.println("released");
		if (i == 0 || i == 3) {
			lb[i].setIcon(img1);
			// c[i].stop();
			c[i].setFramePosition(0);
			c[i].stop();
		}
		if (i == 1 || i == 4 || i == 5) {
			lb[i].setIcon(img2);
			c[i].setFramePosition(0);
			c[i].stop();
		}
		if (i == 2 || i == 6) {
			lb[i].setIcon(img3);
			c[i].setFramePosition(0);
			c[i].stop();
		}
		lb[i].setBackground(Color.white);
		lb[i].setForeground(Color.black);
	}
}
