package th.in.whs.k2ausbkbd;

import android.util.Log;

import java.util.ArrayList;

import keepass2android.pluginsdk.PluginAccessBroadcastReceiver;
import keepass2android.pluginsdk.Strings;

public class KeepassAccessReceiver extends PluginAccessBroadcastReceiver {
    @Override
    public ArrayList<String> getScopes() {
        Log.d("AccessReceiver", "got getScopes call");
        ArrayList<String> scopes = new ArrayList<String>();
        scopes.add(Strings.SCOPE_CURRENT_ENTRY);
        return scopes;
    }
}
