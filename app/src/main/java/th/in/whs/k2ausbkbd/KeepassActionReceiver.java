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
import th.in.whs.k2ausbkbd.hid.Keycode;

public class KeepassActionReceiver extends PluginActionBroadcastReceiver {
    @Override
    protected void openEntry(OpenEntryAction oe) {
        Context ctx = oe.getContext();
        String type = ctx.getString(R.string.type);
        try {
            for (String field: oe.getEntryFields().keySet()) {
                oe.addEntryFieldAction("th.in.whs.k2ausb.type", Strings.PREFIX_STRING + field, type, R.drawable.ic_launcher, Bundle.EMPTY);
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
            String fieldKey = actionSelected.getFieldId().substring(Strings.PREFIX_STRING.length());
            type.append(actionSelected.getEntryFields().get(fieldKey));
        }

        if(type.length() > 0){
            try {
                Keyboard kbd = new Keyboard();
                kbd.type(type.toString());
            } catch (Keycode.KeycodeNotFoundException e){
                Toast.makeText(actionSelected.getContext(), R.string.no_keycode, Toast.LENGTH_LONG).show();
            } catch (UnsupportedOperationException | IOException e) {
                Toast.makeText(actionSelected.getContext(), R.string.error_send, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}
