package cz.hackathon.programy.utils;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import cz.hackathon.programy.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 5.6.11
 * Time: 13:49
 */
public class ImageLoader {
    static HttpClient httpClient = new DefaultHttpClient();
    static Map<String, Drawable> cache = new WeakHashMap<String, Drawable>();
    static ImageLoader instance;
    
    private ImageLoader() {        
    }
    
    public static synchronized ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public void loadImage(String imageUrl, final ImageView view, Handler handler) {
        Drawable d;
        if ((d = cache.get(imageUrl)) == null) {
            d = loadImageFromRemote(imageUrl);
            cache.put(imageUrl, d);
        }
        final Drawable imageToDraw = d;
        handler.post(new Runnable() {
            public void run() {
                view.setImageDrawable(imageToDraw);
            }
        });
    }

    private Drawable loadImageFromRemote(String imageUrl) {
        Log.d(ImageLoader.class.getName(), "Loading image from: " + imageUrl);
        HttpGet httpGet = new HttpGet(imageUrl);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            InputStream content = response.getEntity().getContent();
            final Drawable d = Drawable.createFromStream(content, "src");
            content.close();
            httpGet.abort();
            return d;
        } catch (IOException e) {
            Log.e(ImageLoader.class.getName(), "Failed to load image: " + imageUrl, e);
            return null;
        }
    }
}
