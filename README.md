# Java Image Manipulation
A java course assignment, in which we were given a java swing gui with a Filter interface and an single implementation of that interface with the FlipHorizontalFilter class.

I created the following filters that implement the Filter interface:
FlipVerticalFilter
GaussianFilter
GrayScaleFilter
LaplacianFilter
NegativeFilter
PurpleFilter
SubtleSharpenFilter
UnsharpMaskingFilter

Created the getTransformedData method in the PixelImage class that transforms source images with image kernels. This method does not leverage any image transformation libraries. This method is called by many of the filter implementation classes I produced that have 3x3 or 5x5 image kernels. Larger kernels have not been tested, but the code should be robust and dynamic enough for those larger kernels to work.

Lastly, I called the Filter classes I created in the SnapShopConfiguration class.
