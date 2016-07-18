package th.in.whs.k2ausbkbd.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import th.in.whs.k2ausbkbd.R;

public class LayoutPromptDialog implements DialogInterface.OnCancelListener {
    private Context context;
    private LayoutPromptHandler handler;
    private LayoutPromptCancelHandler cancelHandler;

    public LayoutPromptDialog(Context ctx){
        context = ctx;
    }

    public LayoutPromptHandler getHandler() {
        return handler;
    }

    public void setHandler(LayoutPromptHandler handler) {
        this.handler = handler;
    }

    public LayoutPromptCancelHandler getCancelHandler() {
        return cancelHandler;
    }

    public void setCancelHandler(LayoutPromptCancelHandler cancelHandler) {
        this.cancelHandler = cancelHandler;
    }

    public void prompt(){
        if(this.handler == null){
            throw new IllegalArgumentException("dialog handler has not been set, but dialog is showing");
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.layout)
                .setItems(R.array.layout_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        String[] data = context.getResources().getStringArray(R.array.layout_list);
                        handler.onLayoutChosen(data[index]);
                    }
                })
                .setOnCancelListener(this)
                .create();
        dialog.show();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if(cancelHandler != null){
            cancelHandler.onCancel();
        }
    }
}
