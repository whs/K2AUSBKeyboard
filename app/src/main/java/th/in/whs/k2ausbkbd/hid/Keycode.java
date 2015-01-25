package th.in.whs.k2ausbkbd.hid;

/**
 * Created by whs on 1/25/2015 AD.
 */
public class Keycode {
    public static final int[] SCANCODE_TABLE = new int[]{
            0, 0, 0, 0,
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            '0',
            '\n',
            27,
            8,
            '\t',
            ' ',
            '-',
            '=',
            '[',
            ']',
            '\\',
            '#',
            ';',
            '\'',
            '`',
            ',',
            '.',
            '/',
    };

    public static final byte SHIFT = 2;

    public static byte getScancode(char ch){
        for(int i = 0; i < SCANCODE_TABLE.length; i++){
            if(SCANCODE_TABLE[i] == ch){
                return (byte) i;
            }
        }
        throw new KeycodeNotFoundException();
    }

    public static NeedShiftInfo needShift(char ch){
        boolean needShift = false;

        switch(ch){
            case '!': ch = '1'; needShift = true; break;
            case '@': ch = '2'; needShift = true; break;
            case '#': ch = '3'; needShift = true; break;
            case '$': ch = '4'; needShift = true; break;
            case '%': ch = '5'; needShift = true; break;
            case '^': ch = '6'; needShift = true; break;
            case '&': ch = '7'; needShift = true; break;
            case '*': ch = '8'; needShift = true; break;
            case '(': ch = '9'; needShift = true; break;
            case ')': ch = '0'; needShift = true; break;
            case '_': ch = '-'; needShift = true; break;
            case '+': ch = '='; needShift = true; break;
            case '|': ch = '\\';needShift = true; break;
            case '<': ch = ','; needShift = true; break;
            case '>': ch = '.'; needShift = true; break;
            case '?': ch = '/'; needShift = true; break;
            case ':': ch = ';'; needShift = true; break;
            case '"': ch = '\'';needShift = true; break;
            case '{': ch = '['; needShift = true; break;
            case '}': ch = ']'; needShift = true; break;
            case '~': ch = '`'; needShift = true; break;
            default:
                if(ch >= 'A' && ch <= 'Z'){
                    ch += 'a' - 'A';
                    needShift = true;
                }
        }

        return new NeedShiftInfo(ch, needShift);
    }

    public static final class NeedShiftInfo {
        public char keyCode;
        public boolean shift;

        public NeedShiftInfo(char keyCode, boolean shift) {
            this.keyCode = keyCode;
            this.shift = shift;
        }
    }

    public static final class KeycodeNotFoundException extends RuntimeException {
    }
}
