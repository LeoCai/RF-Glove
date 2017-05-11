package dislab.rfidaction.core.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class WheelGui extends ActionGui {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7840048455161916695L;
	private BufferedImage img;
	private double angle = 0;

	public WheelGui() {
		setTitle("WHEEL");
		try {
			img = ImageIO.read(new File("./img/fangxiangpan.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton btnRotate = new JButton("rotate");
		btnRotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rotate(1);
			}
		});
		add(btnRotate);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2d = (Graphics2D) g;
		double locationX = img.getWidth() / 2;
		double locationY = img.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(angle,
				locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		g2d.drawImage(op.filter(img, null),
				getWidth() / 2 - img.getWidth() / 2,
				getHeight() / 2 - img.getHeight() / 2, null);

	}

	public void rotate(int angle) {
		this.angle = angle;
		repaint();
	}

	public static void main(String args[]) {
		WheelGui fangxiangpanGui = new WheelGui();
		fangxiangpanGui.setVisible(true);
	}

}
