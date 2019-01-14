package biophics.mdot.Manager;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nuuneoi on 12/6/14 AD.
 */

public class Contextor {

    private static Contextor instance;

    public static Contextor getInstance() {
        if (instance == null)
            instance = new Contextor();
        return instance;
    }

    public void clearContext() {
        configs = null;
    }

    private Context context;
    private Map<String, String> configs;

    public void init(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public String getConfig(String key) {
        return configs != null ? configs.get(key) : null;
    }

    public void setConfig(String key, String value) {
        if (configs == null)
            configs = new HashMap<>();
        configs.put(key, value);
    }

}
