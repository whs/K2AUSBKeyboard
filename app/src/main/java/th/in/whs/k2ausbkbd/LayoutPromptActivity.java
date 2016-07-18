package th.in.whs.k2ausbkbd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import th.in.whs.k2ausbkbd.dialog.LayoutPromptCancelHandler;
import th.in.whs.k2ausbkbd.dialog.LayoutPromptDialog;
import th.in.whs.k2ausbkbd.dialog.LayoutPromptHandler;
import th.in.whs.k2ausbkbd.hid.Keyboard;
import th.in.whs.k2ausbkbd.layout.KeyboardLayoutFactory;
import th.in.whs.k2ausbkbd.layout.Layout;

import java.io.IOException;

public class LayoutPromptActivity extends Activity implements LayoutPromptHandler, LayoutPromptCancelHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutPromptDialog dialog = new LayoutPromptDialog(this);
        dialog.setHandler(this);
        dialog.setCancelHandler(this);
        dialog.prompt();
    }

    @Override
    public void onLayoutChosen(String layout) {
        String type = getIntent().getStringExtra("type");
        if(type.length() > 0){
            try {
                Layout layoutInstance = KeyboardLayoutFactory.getLayout(layout);
                Keyboard.getInstance().type(type, layoutInstance);
            } catch (IOException | InstantiationException | IllegalAccessException e) {
                Toast.makeText(this, R.string.error_send, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (UnsupportedOperationException e) {
                Toast.makeText(this, R.string.error_kernel, Toast.LENGTH_LONG).show();
            }
        }
        finish();
    }

    @Override
    public void onCancel() {
        finish();
    }
}
