package biophics.mdot.FireStore;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import biophics.mdot.Utility.GetSession;

import static android.support.constraint.Constraints.TAG;

public class FireStore_Read {
    public static void Read_Config_Frist() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TBL_TB_CONFIG")
                .whereEqualTo("TB_INFO_TELL", firebaseUser.getPhoneNumber())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Log.d(TAG, document.getId() + " => " + document.get("TB_DOT_DATE"));
                                i++;
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                        if (i == 0) {
                            FireStore_Insert.Firestore_Insert_Config();
                        }
                    }
                });
    }

    public static void Read_Config_Id() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TBL_TB_CONFIG")
                .whereEqualTo("TB_INFO_TELL", firebaseUser.getPhoneNumber())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getId() != null) {
                                    GetSession.setValue("g_id", document.getId());
                                    GetSession.setValue("Time", (String) document.get("TB_CONFIG_TIME_ALEART"));
                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public static void Read_Carlendar() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TBL_TB_DOT")
                .whereEqualTo("TB_INFO_TELL", firebaseUser.getPhoneNumber())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Log.d(TAG, document.getId() + " => " + document.get("TB_DOT_DATE"));
                                i++;
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                        Log.d(TAG, "I => " + i);
                    }
                });
    }
}
