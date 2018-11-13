package biophics.mdot.FireStore;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import biophics.mdot.Utility.GetDateTime;

import static android.support.constraint.Constraints.TAG;

public class FireStore_Insert {

    public static void Firestore_Insert_Dot(String Dot_By, String Dot_ID, String Dot_Warring, String Dot_Status) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> TBL_TB_DOT = new HashMap<>();
        TBL_TB_DOT.put("TB_INFO_TELL", firebaseUser.getPhoneNumber());
        TBL_TB_DOT.put("TB_DOT_BY", Dot_By);
        TBL_TB_DOT.put("TB_DOT_ID", Dot_ID);
        TBL_TB_DOT.put("TB_DOT_DATE", GetDateTime.getDate());
        TBL_TB_DOT.put("TB_DOT_TIME", GetDateTime.getTime());
        TBL_TB_DOT.put("TB_DOT_TIMESTAMP", GetDateTime.getTimeStamp());
        TBL_TB_DOT.put("TB_DOT_WARRING", Dot_Warring);
        TBL_TB_DOT.put("TB_DOT_STATUS", Dot_Status);


        db.collection("TBL_TB_DOT")
                .add(TBL_TB_DOT)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID:" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public static void Firestore_Insert_Config() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> TBL_TB_CONFIG = new HashMap<>();
        TBL_TB_CONFIG.put("TB_INFO_TELL", firebaseUser.getPhoneNumber());
        TBL_TB_CONFIG.put("TB_CONFIG_TIME_FRIST", GetDateTime.getTimeStamp());
        TBL_TB_CONFIG.put("TB_CONFIG_TIME_ALEART", "");
        TBL_TB_CONFIG.put("TB_CONFIG_TIME_FREQUENCY", "");
        TBL_TB_CONFIG.put("TB_CONFIG_IMEI", "");
        TBL_TB_CONFIG.put("TB_CONFIG_STATUS", "0");

        db.collection("TBL_TB_CONFIG")
                .add(TBL_TB_CONFIG)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID:" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public static void Firestore_Update_Config() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TBL_TB_CONFIG").document()
                .update(
                        "TB_CONFIG_STATUS", "88"
                );
    }
}
