package liuliu.custom.method.cube.image.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import liuliu.custom.method.cube.util.CubeDebug;

/**
 * Created by liuliu on 2015/11/27   15:09
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class RecyclingBitmapDrawable extends BitmapDrawable {

    private final static String LOG_TAG = CubeDebug.DEBUG_IMAGE_LOG_TAG;
    static final boolean DEBUG = false;

    private int mCacheRefCount = 0;
    private int mDisplayRefCount = 0;

    private boolean mHasBeenDisplayed;

    public RecyclingBitmapDrawable(Resources res, Bitmap bitmap) {
        super(res, bitmap);
    }

    /**
     * Notify the drawable that the displayed state has changed. Internally a count is kept so that the drawable knows when it is no longer being displayed.
     */
    public void setIsDisplayed(boolean isDisplayed) {
        synchronized (this) {
            if (isDisplayed) {
                mDisplayRefCount++;
                mHasBeenDisplayed = true;
            } else {
                mDisplayRefCount--;
            }
        }

        // Check to see if recycle() can be called
        checkState();
    }

    /**
     * Notify the drawable that the cache state has changed. Internally a count is kept so that the drawable knows when it is no longer being cached.
     */
    public void setIsCached(boolean isCached) {
        synchronized (this) {
            if (isCached) {
                mCacheRefCount++;
            } else {
                mCacheRefCount--;
            }
        }

        // Check to see if recycle() can be called
        checkState();
    }

    private synchronized void checkState() {
        // If the drawable cache and display ref counts = 0, and this drawable
        // has been displayed, then recycle
        if (mCacheRefCount <= 0 && mDisplayRefCount <= 0 && mHasBeenDisplayed && hasValidBitmap()) {
            if (DEBUG) {
                Log.d(LOG_TAG, "No longer being used or cached so recycling. " + toString());
            }
            getBitmap().recycle();
        }
    }

    private synchronized boolean hasValidBitmap() {
        Bitmap bitmap = getBitmap();
        return bitmap != null && !bitmap.isRecycled();
    }

}

