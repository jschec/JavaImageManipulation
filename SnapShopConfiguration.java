// Write your short report here (-2 if there is no report)
/**
 * Short report
 * 
 * Additional filters we wrote:
 *	1) UnsharpMaskingFilter.java -
 * 		Uses a 5x5 kernel to sharpen an image and reduce its noise
 * 		which reduces the exaggeration of the edges. Our code is
 * 		robust enough to handle a 5x5 grid.
 *	2) GrayScaleFilter.java -
 *		Modify each rgb value in each Pixel by making them each equal
 *		to the weighted sum after applying rgb weights. This produces
 *		a 2d Pixel matrix with a gray scale.
 *	3) PurpleFilter.java -
 *		Set each green value in each Pixel to zero which allows the
 *		different combinations of different blue and red intensities,
 *		thus producing an image with purple tones.
 *
 * What works and what doesn't?
 * 		All of the required and extra filters work as expected when
 * 		comparing them with reference images. No noticeable side 
 * 		effects.
 * 
 * Surprises or problems we encountered while implementing these 
 * transformations?
 * 		At first the 3x3 transformations were challenging because
 * 		we directly modified the initial 2d Pixel matrix. This,
 * 		however, is wrong because modifying the reference 2d matrix
 * 		will ruin change neighboring Pixels that will alter subsequent
 * 		calculations. We quickly addressed this issue by reading the
 * 		assignment document more carefully and creating a 2nd 2d matrix
 * 		to store the new Pixels.
 */


/**
 * A class to configure the SnapShop application
 * 
 * @author Joshua Scheck
 * @version 2020/03/06
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
		theShop.addFilter(new SubtleSharpenFilter(), "Subtle Sharpen Filter");
		theShop.addFilter(new GrayScaleFilter(), "Gray Scale Filter");
		theShop.addFilter(new PurpleFilter(), "Purple Filter");
	}
}
