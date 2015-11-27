package liuliu.custom.method.cube.image;

/**
 * Created by liuliu on 2015/11/27   15:08
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class ImageReuseInfo {

    private String mIdentitySize;
    private String[] mReuseSize;

    public ImageReuseInfo(String identitySize, String[] reuseSize) {
        mIdentitySize = identitySize;
        mReuseSize = reuseSize;
    }

    public String getIdentitySize() {
        return mIdentitySize;
    }

    public String[] getReuseSizeList() {
        return mReuseSize;
    }
}
