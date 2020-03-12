/**
 * Applies Gray Scale filter to designated 2d Pixel matrix.
 * 
 * @author 	Joshua Scheck
 * class	CSC 142
 * section	HY1
 * @since	2020/03/06
 */
public class GrayScaleFilter implements Filter {
	
	/**
	 * Each initial rgb value in Pixel class is replaced the same
	 * calculated value. This value is the weighted sum of each 
	 * rgb value.
	 * 
	 * @param	pi	2d Pixel matrix
	 */
	public void filter(PixelImage pi) {
		final double RED_SCALE = 0.299;
		final double GREEN_SCALE = 0.587;
		final double BLUE_SCALE = 0.114;
		
		Pixel[][] data = pi.getData();
		
		for (int row = 0; row < pi.getHeight(); row++) {
			for (int col = 0; col < pi.getWidth(); col++) {
				Pixel pixel = data[row][col];
				
				int r = (int)(pixel.red * RED_SCALE);
			    int g = (int)(pixel.green * GREEN_SCALE);
			    int b = (int)(pixel.blue * BLUE_SCALE);
			    int rgbSum = r + g + b;
			    
				data[row][col] = new Pixel(rgbSum, rgbSum, rgbSum);
			}
		}
		
		pi.setData(data);
	}
}
