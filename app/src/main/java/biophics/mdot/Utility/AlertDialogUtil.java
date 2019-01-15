package biophics.mdot.Utility;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import biophics.mdot.Manager.Contextor;
import biophics.mdot.R;

/**
 * Created by chaiphet.saeoun on 22/11/2017.
 */

public class AlertDialogUtil {

    private static AlertDialog alertDialog;

    public static AlertDialog.Builder createAlertDialogBuilder(Activity activity) {
//        return new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AlertDialogStyle));
        return new AlertDialog.Builder(activity);
    }

    public static void setupTextSize(AlertDialog dialog) {
//        float textSize = Contextor.getInstance().getContext().getResources().getDimension(R.dimen.alert_dialog_text_size);
//        TextView textView = dialog.findViewById(android.R.id.message);
//        textView.setTextSize(textSize);
    }

    public static void showLoading(Activity activity) {
        if (alertDialog == null || !alertDialog.isShowing()) {
            LayoutInflater inflater = activity.getLayoutInflater();
            View inflate = inflater.inflate(R.layout.custom_loading_view, null);
            AlertDialog.Builder loadingDialogBuilder = createAlertDialogBuilder(activity)
                    .setMessage(R.string.show_loading)
                    .setCancelable(false)
                    .setView(inflate);
            alertDialog = loadingDialogBuilder.show();
            setupTextSize(alertDialog);
        }
    }

    public static void dismissLoading() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public static void showNoConnection(Activity activity) {
        setupTextSize(
                createAlertDialogBuilder(activity)
                        .setCancelable(false)
                        .setTitle(R.string.error_network_not_connected)
                        .setNegativeButton(R.string.btn_close, null)
                        .show()
        );
    }

//    public static void showErrorConnection(Activity activity) {
//        activity.runOnUiThread(() -> {
//            dismissLoading();
//        });
//
//        AlertDialog.Builder alertDialogBuilder = createAlertDialogBuilder(activity)
//                .setCancelable(false)
//                .setTitle(activity.getString(R.string.error_server_or_network_header))
//                .setMessage(R.string.error_server_or_network)
//                .setNegativeButton(R.string.btn_close, null);
//
//        activity.runOnUiThread(() -> {
//            alertDialog = alertDialogBuilder.show();
//            setupTextSize(alertDialog);
//        });
//    }

    public static void showAlertMessage(Activity activity, int message) {
        showAlertMessage(activity, message, null);
    }

    public static void showAlertMessage(Activity activity, int message, DialogInterface.OnClickListener listener) {
        showAlertMessage(activity, Contextor.getInstance().getContext().getString(message), listener);
    }

    public static void showAlertMessage(Activity activity, String message) {
        showAlertMessage(activity, message, null);
    }

    public static void showAlertMessage(Activity activity, String message, DialogInterface.OnClickListener listener) {
        setupTextSize(createAlertDialogBuilder(activity)
                .setCancelable(false)
                .setMessage(message)
                .setNegativeButton(R.string.btn_close, listener)
                .show());
    }

//    public static void showSomethingWrong(final Activity activity) {
//        setupTextSize(createAlertDialogBuilder(activity)
//                .setCancelable(false)
//                .setTitle(R.string.error_something_went_wrong)
//                .setNegativeButton(R.string.btn_close, null).show());
//    }

}
