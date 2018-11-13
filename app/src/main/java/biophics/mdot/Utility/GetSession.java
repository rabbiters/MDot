package biophics.mdot.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetSession {
    @SuppressWarnings("rawtypes")
    public static HashMap profile = new HashMap();

    /*
     * list key user_org select_month select_year
     *
     *
     * inv_id list v_picture
     */

    public static String getValue(String key) {
        return (profile.get(key) == null) ? "" : "" + profile.get(key);
    }

    @SuppressWarnings("unchecked")
    public static void setValue(String key, String value) {
        profile.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static void clearValue(String key) {
        profile.put(key, "");
    }

    @SuppressWarnings("rawtypes")
    public static void clearSession() {
        profile = new HashMap();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void setList(String key, List v) {
        profile.put(key, v);
    }

    @SuppressWarnings("rawtypes")
    public static List getList(String key) {
        List v = new ArrayList();
        try {
            v = (List) profile.get(key);
        } catch (Exception ex) {
            v = new ArrayList();
        }
        return v;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void clearList(String key) {
        profile.put(key, new ArrayList());
    }

}
