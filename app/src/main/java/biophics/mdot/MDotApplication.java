package biophics.mdot;

import android.app.Application;

import biophics.mdot.Manager.Contextor;

public class MDotApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());

        //Restore Singleton datas here (Context)
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // Save Singleton datas here (Context)
    }
}
