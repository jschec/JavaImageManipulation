// Write your short report here (-2 if there is no report)

/**
 * Report:
 * 
 * What additional filters we used:
 * 1) Subtle Sharpen filter - which is supposed to provide
 * a means to sharpen an image while filtering out some noise
 * and not make the edges stand out too much. You will find that
 * this required a 5x5 weighted filtering matrix
 * 2) Emboss
 * 
 * 
 * 
 * What works and what doesn't:
 * All the requested filters match the references images
 * and no abnormalities were observed.
 * 
 * 
 * Surprises or problems encountered:
 * We had issues with matching the reference images initially
 * but realized that the mismatch arose because we directly
 * modified the initial 2d Pixel matrix. This was solved by
 * declaring a new pixel matrix to be modified, however, we
 * subsequently had to match missing pixels that occured on the
 * edges of the image where the filter did not reach.
 * 
 */



/**
 * A class to configure the SnapShop application
 * 
 * @author Joshua Scheck
 * @version 2020/03/07
 */
public class SnapShopConfiguration {
	/**
	 * Method to configure the SnapShop. Call methods like addFilter and
	 * setDefaultFilename here.
	 * 
	 * @param theShop
	 *            A pointer to the application
	 */
	public static void configure(SnapShop theShop) {

		theShop.setDefaultFilename("C:\\Users\\joshu\\Desktop\\csc142\\ImageProject\\billg.jpg");
		theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
		
		// Required filters
		theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
		theShop.addFilter(new NegativeFilter(), "Negative Filter");
		theShop.addFilter(new GaussianFilter(), "Gaussian Filter");
		theShop.addFilter(new LaplacianFilter(), "Laplacian Filter");
		theShop.addFilter(new UnsharpMaskingFilter(), "Unsharp Masking Filter");
		theShop.addFilter(new EdgyFilter(), "Edgy Filter");
		
		// Extra filters
		theShop.addFilter(new EmbossFilter(), "Emboss Filter");
		theShop.addFilter(new SubtleSharpenFilter(), "Subtle Sharpen Filter");
		theShop.addFilter(new MedianFilter(), "Median Filter");
		theShop.addFilter(new GrayScaleFilter(), "Gray Scale Filter");
	}
}
