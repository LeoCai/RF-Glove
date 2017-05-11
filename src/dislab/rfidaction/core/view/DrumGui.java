package dislab.rfidaction.core.view;

import java.awt.BorderLayout;
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
 * 鼓界面
 * 
 */
public class DrumGui extends JFrame {
	/**
	 * 
	 */
	// private static final long serialVersionUID =
	// 5204718033784360062L;//what's this?
	private boolean[] key;
	private JLabel[] lb = new JLabel[4];
	private ImageIcon[] img0 = new ImageIcon[4];// 初始状态
	private ImageIcon[] img1 = new ImageIcon[4];// 锣被敲下
	private ImageIcon[] img2 = new ImageIcon[4];// 鼓被敲下
	private File wav0;
	private File wav1;
	private Clip[] c = new Clip[2];
	private AudioInputStream ais0;
	private AudioInputStream ais1;

	public DrumGui() {
		super();
		// set layout
		GridLayout gridlayout = new GridLayout(1, 4);

		// set menu
		/*
		 * JMenuBar mbar = new JMenuBar(); JMenu menu1 = new JMenu("菜单",true);
		 * JMenuItem exit = new JMenuItem("exit"); exit.addActionListener(new
		 * ActionListener(){ public void actionPerformed(ActionEvent e){
		 * System.exit(0); } }); menu1.add(exit); mbar.add(menu1);
		 * setJMenuBar(mbar);
		 */
		// set panel
		JPanel p = new JPanel();
		p.setLayout(gridlayout);

		// load image
		for (int i = 1; i < 5; i++) {
			img0[i - 1] = new ImageIcon("./img/drum/0_0" + i + ".jpg");
			img1[i - 1] = new ImageIcon("./img/drum/1_0" + i + ".jpg");
			img2[i - 1] = new ImageIcon("./img/drum/2_0" + i + ".jpg");
		}
		// load wav
		wav0 = new File("./wav/drum/drum.wav");
		wav1 = new File("./wav/drum/cha.wav");

		try {
			initaudio();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		key = new boolean[] { false, false, false, false };
		// releadsedKey = new boolean[] {false,false,false,false};
		// set label
		lb[0] = new JLabel(img0[0]);
		lb[1] = new JLabel(img0[1]);
		lb[2] = new JLabel(img0[2]);
		lb[3] = new JLabel(img0[3]);

		for (int i = 0; i < 4; i++) {
			lb[i].setOpaque(true);
		}

		// set frame location
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) screen.getWidth() / 2 - 477,
				(int) screen.getHeight() / 2 - 268);

		// add to frame
		this.add(p, BorderLayout.CENTER);
		this.setTitle("DRUM");
		this.setResizable(false);
		this.setUndecorated(true);
		this.setSize(952, 412);
		for (int i = 0; i < 4; i++) {
			p.add(lb[i], null);
		}
	}

	private void initaudio() throws Exception {
		ais0 = AudioSystem.getAudioInputStream(wav0);
		ais1 = AudioSystem.getAudioInputStream(wav1);
		c[0] = AudioSystem.getClip();
		c[1] = AudioSystem.getClip();
		c[0].open(ais0);
		c[1].open(ais1);

	}

	public void pressed(int i) {
		if (key[i] != false)
			return;
		key[i] = true;
		// System.out.println("pressed");
		if (key[0] == true && key[1] == false) {
			lb[0].setIcon(img1[0]);
			lb[1].setIcon(img1[1]);
			c[1].start();
			System.out.println("left cha");
		}
		if (key[3] == true && key[2] == false) {
			lb[2].setIcon(img1[2]);
			lb[3].setIcon(img1[3]);
			c[1].start();
		}
		if (key[0] == false && key[1] == true) {
			lb[0].setIcon(img2[0]);
			lb[1].setIcon(img2[1]);
			c[0].start();
		}
		if (key[2] == true && key[3] == false) {
			lb[2].setIcon(img2[2]);
			lb[3].setIcon(img2[3]);
			c[0].start();
		}
	}

	public void released(int i) {
		
		if (key[i] != true)
			return;
		System.out.println("released:" + i);
		key[i] = false;
		if (i == 0 || i == 1) {
			lb[0].setIcon(img0[0]);
			lb[1].setIcon(img0[1]);
		}
		if (i == 2 || i == 3) {
			lb[2].setIcon(img0[2]);
			lb[3].setIcon(img0[3]);
		}
		if (i == 0 || i == 3) {
			c[1].setFramePosition(0);
			// c[1].stop();
		}
		if (i == 1 || i == 2) {
			c[0].setFramePosition(0);
			// c[0].stop();
		}
	}

}
