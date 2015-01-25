package th.in.whs.k2ausbkbd.hid;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class Keyboard {
    public static final String DEVICE = "/dev/hidg0";

    private Process process;

    public Keyboard() throws UnsupportedOperationException {
        if(!new File(DEVICE).exists()){
            throw new UnsupportedOperationException("Unsupported kernel");
        }

        try {
            process = Runtime.getRuntime().exec(new String[]{
                    "/system/bin/su",
                    "-c",
                    "sh"
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        process.destroy();
    }

    public void type(String text) throws IOException {
        for(int i = 0, j = text.length(); i < j; i++){
            char ch = text.charAt(i);
            Keycode.NeedShiftInfo shift = Keycode.needShift(ch);

            byte[] buffer = new byte[]{
                    shift.shift ? Keycode.SHIFT : 0,
                    0,
                    Keycode.getScancode(shift.keyCode),
                    0, 0, 0, 0, 0
            };

            writeToDevice(buffer);
            keyUp();
        }
    }

    private void writeToDevice(byte[] bytes) throws IOException {
        OutputStream stdin = process.getOutputStream();
        stdin.write(("echo -ne " + bytesToEcho(bytes) + " > " + DEVICE + "\n").getBytes());
        stdin.flush();
    }

    private void keyUp() throws IOException {
        OutputStream stdin = process.getOutputStream();
        stdin.write(("echo -ne \\\\x0\\\\x0\\\\x0\\\\x0\\\\x0\\\\x0\\\\x0\\\\x0 > " + DEVICE + "\n").getBytes());
        stdin.flush();
    }

    private String bytesToEcho(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            builder.append("\\\\x");
            builder.append(Integer.toHexString(new Byte(bytes[i]).intValue()));
        }
        return builder.toString();
    }
}
