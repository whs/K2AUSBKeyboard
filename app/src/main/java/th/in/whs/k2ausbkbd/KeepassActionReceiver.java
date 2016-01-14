package th.in.whs.k2ausbkbd;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

import keepass2android.pluginsdk.KeepassDefs;
import keepass2android.pluginsdk.PluginAccessException;
import keepass2android.pluginsdk.PluginActionBroadcastReceiver;
import keepass2android.pluginsdk.Strings;
import th.in.whs.k2ausbkbd.hid.Keyboard;

public class KeepassActionReceiver extends PluginActionBroadcastReceiver {
    @Override
    protected void openEntry(OpenEntryAction oe) {
        Context ctx = oe.getContext();

        try {
            for (String field: oe.getEntryFields().keySet()) {
                Bundle qwertz_layout = new Bundle();
                Bundle qwerty_layout = new Bundle();
                qwertz_layout.putString("layout", "qwertz");
                qwerty_layout.putString("layout", "qwerty");


                oe.addEntryFieldAction("th.in.whs.k2ausb.type.qwertz",
                        Strings.PREFIX_STRING + field,
                        ctx.getString(R.string.type_qwertz),
                        R.drawable.ic_launcher,
                        qwertz_layout);

                oe.addEntryFieldAction("th.in.whs.k2ausb.type.qwerty",
                        Strings.PREFIX_STRING + field,
                        ctx.getString(R.string.type_qwerty),
                        R.drawable.ic_launcher,
                        qwerty_layout);
            }


            Bundle type_user_pass = new Bundle();
            type_user_pass.putBoolean("username", true);
            type_user_pass.putBoolean("password", true);

            Bundle type_user_pass_enter = new Bundle();
            type_user_pass_enter.putBoolean("username", true);
            type_user_pass_enter.putBoolean("password", true);
            type_user_pass_enter.putBoolean("enter", true);

            oe.addEntryAction(ctx.getString(R.string.type_user_pass), R.drawable.ic_launcher, type_user_pass);
            oe.addEntryAction(ctx.getString(R.string.type_user_pass_enter), R.drawable.ic_launcher, type_user_pass_enter);
        } catch (PluginAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionSelected(ActionSelectedAction actionSelected) {
        StringBuilder type = new StringBuilder();
        String layout = "qwerty";

        if(actionSelected.isEntryAction()) {
            Bundle data = actionSelected.getActionData();
            if(data.containsKey("username")){
                type.append(actionSelected.getEntryFields().get(KeepassDefs.UserNameField));
            }
            if(data.containsKey("password")){
                if(data.containsKey("username")) {
                    type.append("\t");
                }
                type.append(actionSelected.getEntryFields().get(KeepassDefs.PasswordField));
            }
            if(data.containsKey("enter")){
                type.append("\n");
            }
        }else{
            Bundle data = actionSelected.getActionData();
            layout = data.getString("layout");

            String fieldKey = actionSelected.getFieldId().substring(Strings.PREFIX_STRING.length());
            type.append(actionSelected.getEntryFields().get(fieldKey));
        }

        if(type.length() > 0){
            try {
                Keyboard.getInstance().type( type.toString(), layout );
            } catch (IOException e) {
                Toast.makeText(actionSelected.getContext(), R.string.error_send, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}
