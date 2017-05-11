package dislab.rfidaction.utils;

import java.util.Arrays;

/**
 * 初始化的rssi
 * 
 * 
 */
public class TagsInit {

	public static final double[] initRssiMenu = new double[] {
			1794.3700000000001, 577.8, 2366.7699999999995, 4222.460000000001,
			2814.0299999999997, 2457.04, 2411.63, 2660.21, 6717.35,
			7380.540000000001 };
	public static final double[] initRssiClarinet = new double[] { 5468.36,
			2323.9900000000002, 2046.0500000000004, 95.39, 1591.75,
			3317.9300000000003, 2441.1600000000008, 2475.0, 6288.6900000000005,
			2171.65 };
	public static final double[] initRssiSCALE = new double[] {
			1704.9100000000003, 3071.4100000000008, 3006.0600000000004,
			6526.999999999999, 4476.69, 5136.860000000001, 7112.610000000001,
			194.54000000000002, 4125.320000000001, 1491.8899999999996 };
	public static final double[] initRssiRotate = new double[] {
			1477.7099999999998, 2963.58, 3271.44, 5101.930000000001, 3822.16,
			1952.92, 6494.849999999999, 7885.630000000002, 2052.8499999999995,
			11027.73 };
	public static final double[] initRssiMove = new double[] { 3242.53,
			2850.49, 2767.25, 4852.54, 3265.49, 6761.25, 8883.539999999999,
			6370.28, 673.3799999999999, 6966.660000000001 };

	private static TagsInit tagsInit = new TagsInit();

	private double[] tagSumRssis = new double[10];

	private int initNum;

	private int maxInitNum = 10;

	private double[] tagAverageRssi = initRssiMenu;

	private TagsInit() {

	}

	public static TagsInit getInstance() {
		// TODO Auto-generated method stub
		return tagsInit;
	}

	/**
	 * 初始化基准rssi
	 * @param targetTagRssi
	 * @return
	 */
	public boolean initing(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		for (int i = 0; i < targetTagRssi.length; i++) {
			tagSumRssis[i] += targetTagRssi[i];
		}
		initNum += 1;
		if (initNum < maxInitNum)
			return true;
		else {
			for (int i = 0; i < tagSumRssis.length; i++) {
				tagAverageRssi[i] = tagSumRssis[i] / initNum;
			}
			System.out.println(Arrays.toString(tagAverageRssi));
			tagSumRssis = new double[10];
			initNum = 0;
			return false;
		}
	}

	public double[] getTagAverageRssi() {
		return tagAverageRssi;
	}

	/**
	 * 设置被初始化的rssi数组
	 * @param tagAverageRssi
	 */
	public void setTagAverageRssi(double[] tagAverageRssi) {
		this.tagAverageRssi = tagAverageRssi;
	}

}
