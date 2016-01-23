package th.in.whs.k2ausbkbd.hid;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import com.convert.KeyCode;
import com.convert.QwertyCodes;
import com.convert.QwertzCodes;

public class Keyboard {
    private static Keyboard instance = null;

    public static final String DEVICE = "/dev/hidg0";

    private Process process;

    protected Keyboard() throws UnsupportedOperationException {
        if(!new File(DEVICE).exists()){
            throw new UnsupportedOperationException("Unsupported kernel");
        }

        try {
            process = Runtime.getRuntime().exec(new String[]{
                    "su",
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


    public void type(String text, String layout) throws IOException {
        for(int i = 0, j = text.length(); i < j; i++){
            char typeChar = text.charAt(i);

            sendChar(typeChar, layout);

            // Fix for double tap.
            if( typeChar == '^' || typeChar == '´' || typeChar == '`' ) {
                sendChar( ' ', layout );
            }
        }
    }

    private void sendChar(char data, String layout) throws IOException {
        KeyCode key = null;

        switch( layout ) {
            case "qwertz":
                key = QwertzCodes.getInstance().getKeycode( data );
                break;
            default:
                key = QwertyCodes.getInstance().getKeycode( data );
                break;
        }

        byte[] buffer = new byte[]{
                (key != null) ? (byte)key.modifier : 0,
                0,
                (key != null) ? (byte)key.code : 0,
                0 , 0, 0, 0, 0
        };

        writeToDevice(buffer);
        keyUp();
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

    // Singleton
    public static Keyboard getInstance() {
        if(instance == null) {
            instance = new Keyboard();
        }
        return instance;
    }
}
