package liuliu.custom.method.cube.image;

/**
 * Created by liuliu on 2015/11/27   15:33
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class ImagePerformanceStatistics {

    private static int sSAMPLE_NUM = 0;
    private static ImageLoadProfiler sImageLoadProfiler;

    public static void setSample(int num) {
        sSAMPLE_NUM = num;
    }

    public static boolean sample(int id) {
        return sSAMPLE_NUM != 0 && id % sSAMPLE_NUM == 0;
    }

    /**
     * @param profiler
     */
    @SuppressWarnings({"unused"})
    public static void setImageLoadProfile(ImageLoadProfiler profiler) {
        sImageLoadProfiler = profiler;
    }

    public static void onImageLoaded(ImageTask task, ImageTaskStatistics stat) {
        if (sImageLoadProfiler != null) {
            sImageLoadProfiler.onImageLoaded(task, stat);
        }
    }
}
