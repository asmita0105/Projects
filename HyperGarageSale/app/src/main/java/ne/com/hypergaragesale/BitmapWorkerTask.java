package ne.com.hypergaragesale;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import static ne.com.hypergaragesale.newPost.decodeSampledBitmapFromResource;

/**
 * Created by alok on 5/7/16.
 */
public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;
    private int data = 0;


    public BitmapWorkerTask(ImageView imageView) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
    }


    @Override
    protected Bitmap doInBackground(Integer... params) {
        data= params[0];
        String imgSource="";
        return decodeSampledBitmapFromResource(imgSource, data, 100, 100);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }


}
