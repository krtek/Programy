package cz.hackathon.programy.provider;

import android.app.Activity;
import cz.hackathon.programy.FestivalyApplication;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 12:27
 */
public class ProviderFactory {

    public static ActionProvider getProvider(Activity context) {
        return ((FestivalyApplication)(context.getApplicationContext())).provider;
    }
}
