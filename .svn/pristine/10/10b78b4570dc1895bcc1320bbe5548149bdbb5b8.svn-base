package dislab.rfidaction.core.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 旋转界面
 * 
 */
public class RotateGui extends ActionGui {

	/**
	 * 
	 */
	private static final long serialVersionUID = 18550087636031472L;

	private int center[] = new int[] { getWidth() / 2, getHeight() / 2 };

	private int lineWidth = 400;

	private double angle = 0;

	private float bold = 5;

	private int p1[] = new int[] { center[0] - 200, getHeight() / 2 };
	private int p2[] = new int[] { center[0] + 200, getHeight() / 2 };

	private static RotateGui rotateGui = new RotateGui();

	public static RotateGui getInstance() {
		return rotateGui;
	}

	private RotateGui() {

		JButton btn = new JButton("rotate");
		JButton btn_scale = new JButton("scale");
		setLayout(new FlowLayout());
		// add(btn);
		// add(btn_scale);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rotate(0.2);
			}
		});
		btn_scale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				scale(5);
			}
		});

	}

	public void move(double offX) {
		this.center[0] += offX;
		refresh();
	}

	public void rotate(double offAngle) {
		this.angle += offAngle;
		refresh();
	}

	public void scale(int off) {
		if ((bold + off) > 0)
			bold += off;
		// lineWidth += off;
		refresh();
	}

	public void refresh() {
		p1 = new int[] { center[0] - lineWidth / 2, getHeight() / 2 };
		p2 = new int[] { center[0] + lineWidth / 2, getHeight() / 2 };
		rotate(angle, center);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Stroke stroke = new BasicStroke(bold);
		g2d.setColor(Color.GREEN);
		g2d.setStroke(stroke);
		g2d.drawLine(p1[0], p1[1], p2[0], p2[1]);
	}

	private void rotate(double angle, int[] center) {
		// TODO Auto-generated method stub
		p1 = rotate(p1, angle, center);
		p2 = rotate(p2, angle, center);
		repaint();
	}

	private int[] rotate(int[] p, double angle, int[] center) {
		// TODO Auto-generated method stub
		int x = p[0] - center[0];
		int y = p[1] - center[1];

		return new int[] {
				(int) (x * Math.cos(angle) - y * Math.sin(angle) + center[0]),
				(int) (x * Math.sin(angle) + y * Math.cos(angle)) + center[1] };
	}

	public static void main(String args[]) {
		RotateGui r = new RotateGui();
		r.setVisible(true);
	}

}
