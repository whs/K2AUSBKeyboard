package com.convert;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by freehunt on 13.01.2016.
 */

public class QwertyCodes {
    private static QwertyCodes instance = null;

    private HashMap<Character, KeyCode> keyTable;

    protected QwertyCodes() {
        // First we initialize our keyMap, which contains the keys normal, shift, altgr buttons
        TreeMap<Character, char[]> keyMap = new TreeMap<Character, char[]>();
        keyMap.put((char)0x00, new char[]{'\0', '\0', '\0'}); 	// Reserved (no event indicated)
        keyMap.put((char)0x01, new char[]{'\0', '\0', '\0'}); 	// Keyboard ErrorRollOver
        keyMap.put((char)0x02, new char[]{'\0', '\0', '\0'}); 	// Keyboard POSTFail
        keyMap.put((char)0x03, new char[]{'\0', '\0', '\0'}); 	// Keyboard ErrorUndefined
        keyMap.put((char)0x04, new char[]{'a', 'A', '\0'}); 	// Keyboard a and A
        keyMap.put((char)0x05, new char[]{'b', 'B', '\0'}); 	// Keyboard b and B
        keyMap.put((char)0x06, new char[]{'c', 'C', '\0'}); 	// Keyboard c and C
        keyMap.put((char)0x07, new char[]{'d', 'D', '\0'}); 	// Keyboard d and D
        keyMap.put((char)0x08, new char[]{'e', 'E', '\0'}); 	// Keyboard e and E
        keyMap.put((char)0x09, new char[]{'f', 'F', '\0'}); 	// Keyboard f and F
        keyMap.put((char)0x0A, new char[]{'g', 'G', '\0'}); 	// Keyboard g and G
        keyMap.put((char)0x0B, new char[]{'h', 'H', '\0'}); 	// Keyboard h and H
        keyMap.put((char)0x0C, new char[]{'i', 'I', '\0'}); 	// Keyboard i and I
        keyMap.put((char)0x0D, new char[]{'j', 'J', '\0'}); 	// Keyboard j and J
        keyMap.put((char)0x0E, new char[]{'k', 'K', '\0'}); 	// Keyboard k and K
        keyMap.put((char)0x0F, new char[]{'l', 'L', '\0'}); 	// Keyboard l and L
        keyMap.put((char)0x10, new char[]{'m', 'M', '\0'}); 	// Keyboard m and M
        keyMap.put((char)0x11, new char[]{'n', 'N', '\0'}); 	// Keyboard n and N
        keyMap.put((char)0x12, new char[]{'o', 'O', '\0'}); 	// Keyboard o and O
        keyMap.put((char)0x13, new char[]{'p', 'P', '\0'}); 	// Keyboard p and P
        keyMap.put((char)0x14, new char[]{'q', 'Q', '\0'}); 	// Keyboard q and Q
        keyMap.put((char)0x15, new char[]{'r', 'R', '\0'}); 	// Keyboard r and R
        keyMap.put((char)0x16, new char[]{'s', 'S', '\0'}); 	// Keyboard s and S
        keyMap.put((char)0x17, new char[]{'t', 'T', '\0'}); 	// Keyboard t and T
        keyMap.put((char)0x18, new char[]{'u', 'U', '\0'}); 	// Keyboard u and U
        keyMap.put((char)0x19, new char[]{'v', 'V', '\0'}); 	// Keyboard v and V
        keyMap.put((char)0x1A, new char[]{'w', 'W', '\0'}); 	// Keyboard w and W
        keyMap.put((char)0x1B, new char[]{'x', 'X', '\0'}); 	// Keyboard x and X
        keyMap.put((char)0x1C, new char[]{'y', 'Y', '\0'}); 	// Keyboard y and Y
        keyMap.put((char)0x1D, new char[]{'z', 'Z', '\0'}); 	// Keyboard z and Z
        keyMap.put((char)0x1E, new char[]{'1', '!', '\0'}); 	// Keyboard 1 and !
        keyMap.put((char)0x1F, new char[]{'2', '@', '\0'}); 	// Keyboard 2 and @
        keyMap.put((char)0x20, new char[]{'3', '#', '\0'}); 	// Keyboard 3 and #
        keyMap.put((char)0x21, new char[]{'4', '$', '\0'}); 	// Keyboard 4 and $
        keyMap.put((char)0x22, new char[]{'5', '%', '\0'}); 	// Keyboard 5 and %
        keyMap.put((char)0x23, new char[]{'6', '^', '\0'}); 	// Keyboard 6 and ^
        keyMap.put((char)0x24, new char[]{'7', '&', '\0'}); 	// Keyboard 7 and &
        keyMap.put((char)0x25, new char[]{'8', '*', '\0'}); 	// Keyboard 8 and *
        keyMap.put((char)0x26, new char[]{'9', '(', '\0'}); 	// Keyboard 9 and (
        keyMap.put((char)0x27, new char[]{'0', ')', '\0'}); 	// Keyboard 0 and )
        keyMap.put((char)0x28, new char[]{'\n', '\n', '\0'}); 	// Keyboard Return (ENTER)
        keyMap.put((char)0x29, new char[]{'\0', '\0', '\0'}); 	// Keyboard ESCAPE
        keyMap.put((char)0x2A, new char[]{'\b', '\b', '\b'}); 	// Keyboard DELETE (Backspace)
        keyMap.put((char)0x2B, new char[]{'\t', '\t', '\t'}); 	// Keyboard Tab
        keyMap.put((char)0x2C, new char[]{' ', ' ', ' '}); 	// Keyboard Spacebar
        keyMap.put((char)0x2D, new char[]{'-', '_', '\0'}); 	// Keyboard - and (underscore)
        keyMap.put((char)0x2E, new char[]{'=', '+', '\0'}); 	// Keyboard = and +
        keyMap.put((char)0x2F, new char[]{'[', '{', '\0'}); 	// Keyboard [ and {
        keyMap.put((char)0x30, new char[]{']', '}', '\0'}); 	// Keyboard ] and }
        keyMap.put((char)0x31, new char[]{'\\', '|', '\0'}); 	// Keyboard \ and |
        keyMap.put((char)0x32, new char[]{'\0', '\0', '\0'}); 	// Keyboard Non-US # and ~
        keyMap.put((char)0x33, new char[]{';', ':', '\0'}); 	// Keyboard ; and :
        keyMap.put((char)0x34, new char[]{'\'', '"', '\0'}); 	// Keyboard ‘ and “
        keyMap.put((char)0x35, new char[]{'`', '~', '\0'}); 	// Keyboard Grave Accent and Tilde
        keyMap.put((char)0x36, new char[]{',', '<', '\0'}); 	// Keyboard , and <
        keyMap.put((char)0x37, new char[]{'.', '>', '\0'}); 	// Keyboard . and >
        keyMap.put((char)0x38, new char[]{'/', '?', '\0'}); 	// Keyboard / and ?
        keyMap.put((char)0x39, new char[]{'\0', '\0', '\0'}); 	// Keyboard Caps Lock1
        keyMap.put((char)0x3A, new char[]{'\0', '\0', '\0'}); 	// Keyboard F1
        keyMap.put((char)0x3B, new char[]{'\0', '\0', '\0'}); 	// Keyboard F2
        keyMap.put((char)0x3C, new char[]{'\0', '\0', '\0'}); 	// Keyboard F3
        keyMap.put((char)0x3D, new char[]{'\0', '\0', '\0'}); 	// Keyboard F4
        keyMap.put((char)0x3E, new char[]{'\0', '\0', '\0'}); 	// Keyboard F5
        keyMap.put((char)0x3F, new char[]{'\0', '\0', '\0'}); 	// Keyboard F6
        keyMap.put((char)0x40, new char[]{'\0', '\0', '\0'}); 	// Keyboard F7
        keyMap.put((char)0x41, new char[]{'\0', '\0', '\0'}); 	// Keyboard F8
        keyMap.put((char)0x42, new char[]{'\0', '\0', '\0'}); 	// Keyboard F9
        keyMap.put((char)0x43, new char[]{'\0', '\0', '\0'}); 	// Keyboard F10
        keyMap.put((char)0x44, new char[]{'\0', '\0', '\0'}); 	// Keyboard F11
        keyMap.put((char)0x45, new char[]{'\0', '\0', '\0'}); 	// Keyboard F12
        keyMap.put((char)0x46, new char[]{'\0', '\0', '\0'}); 	// Keyboard PrintScreen
        keyMap.put((char)0x47, new char[]{'\0', '\0', '\0'}); 	// Keyboard Scroll Lock1
        keyMap.put((char)0x48, new char[]{'\0', '\0', '\0'}); 	// Keyboard Pause
        keyMap.put((char)0x49, new char[]{'\0', '\0', '\0'}); 	// Keyboard Insert
        keyMap.put((char)0x4A, new char[]{'\0', '\0', '\0'}); 	// Keyboard Home
        keyMap.put((char)0x4B, new char[]{'\0', '\0', '\0'}); 	// Keyboard PageUp
        keyMap.put((char)0x4C, new char[]{'\0', '\0', '\0'}); 	// Keyboard Delete Forward
        keyMap.put((char)0x4D, new char[]{'\0', '\0', '\0'}); 	// Keyboard End
        keyMap.put((char)0x4E, new char[]{'\0', '\0', '\0'}); 	// Keyboard PageDown
        keyMap.put((char)0x4F, new char[]{'\0', '\0', '\0'}); 	// Keyboard RightArrow
        keyMap.put((char)0x50, new char[]{'\0', '\0', '\0'}); 	// Keyboard LeftArrow
        keyMap.put((char)0x51, new char[]{'\0', '\0', '\0'}); 	// Keyboard DownArrow
        keyMap.put((char)0x52, new char[]{'\0', '\0', '\0'}); 	// Keyboard UpArrow
        keyMap.put((char)0x53, new char[]{'\0', '\0', '\0'}); 	// Keypad Num Lock and Clear1
        keyMap.put((char)0x54, new char[]{'/', '\0', '\0'}); 	// Keypad /
        keyMap.put((char)0x55, new char[]{'*', '\0', '\0'}); 	// Keypad *
        keyMap.put((char)0x56, new char[]{'-', '\0', '\0'}); 	// Keypad -
        keyMap.put((char)0x57, new char[]{'+', '\0', '\0'}); 	// Keypad +
        keyMap.put((char)0x58, new char[]{'\n', '\n', '\0'}); 	// Keypad ENTER
        keyMap.put((char)0x59, new char[]{'1', '\0', '\0'}); 	// Keypad 1 and End
        keyMap.put((char)0x5A, new char[]{'2', '\0', '\0'}); 	// Keypad 2 and Down Arrow
        keyMap.put((char)0x5B, new char[]{'3', '\0', '\0'}); 	// Keypad 3 and PageDn
        keyMap.put((char)0x5C, new char[]{'4', '\0', '\0'}); 	// Keypad 4 and Left Arrow
        keyMap.put((char)0x5D, new char[]{'5', '\0', '\0'}); 	// Keypad 5
        keyMap.put((char)0x5E, new char[]{'6', '\0', '\0'}); 	// Keypad 6 and Right Arrow
        keyMap.put((char)0x5F, new char[]{'7', '\0', '\0'}); 	// Keypad 7 and Home
        keyMap.put((char)0x60, new char[]{'8', '\0', '\0'}); 	// Keypad 8 and Up Arrow
        keyMap.put((char)0x61, new char[]{'9', '\0', '\0'}); 	// Keypad 9 and PageUp
        keyMap.put((char)0x62, new char[]{'0', '\0', '\0'}); 	// Keypad 0 and Insert
        keyMap.put((char)0x63, new char[]{'.', '\0', '\0'}); 	// Keypad . and Delete
        keyMap.put((char)0x64, new char[]{'\0', '\0', '\0'}); 	// Keyboard Non-US \ and |
        keyMap.put((char)0x65, new char[]{'\0', '\0', '\0'}); 	// Keyboard Application
        keyMap.put((char)0xE0, new char[]{'\0', '\0', '\0'}); 	// Keyboard LeftControl
        keyMap.put((char)0xE1, new char[]{'\0', '\0', '\0'}); 	// Keyboard LeftShift
        keyMap.put((char)0xE2, new char[]{'\0', '\0', '\0'}); 	// Keyboard LeftAlt
        keyMap.put((char)0xE3, new char[]{'\0', '\0', '\0'}); 	// Keyboard Left GUI
        keyMap.put((char)0xE4, new char[]{'\0', '\0', '\0'}); 	// Keyboard RightControl
        keyMap.put((char)0xE5, new char[]{'\0', '\0', '\0'}); 	// Keyboard RightShift
        keyMap.put((char)0xE6, new char[]{'\0', '\0', '\0'}); 	// Keyboard RightAlt
        keyMap.put((char)0xE7, new char[]{'\0', '\0', '\0'}); 	// Keyboard Right GUI

        // Now we create a ascii table for the buttons to easy get the keycode
        keyTable = new HashMap<Character, KeyCode>();

        for( Map.Entry<Character, char[]> entry : keyMap.entrySet() ) {
            char[] keys = entry.getValue();

            for( int i = 0; i < 3; i++ ) {
                if( keys[i] != '\0' ) { // Its a character
                    if( keyTable.get(keys[i]) == null ) { // There character is not registered yet
                        char modifier = 0x00;

                        if( i == 1 ) // If its in index 1, its shifted
                            modifier = KeyModifiers.RSHIFT;
                        else if( i == 2 ) // If its in index 2, its alted
                            modifier = KeyModifiers.RALT;

                        keyTable.put( keys[i], new KeyCode(entry.getKey(), modifier) );
                    }
                }
            }
        }
    }

    public KeyCode getKeycode(char key) {
        return keyTable.get(key);
    }

    // Singleton
    public  static QwertyCodes getInstance() {
        if(instance == null) {
            instance = new QwertyCodes();
        }
        return instance;
    }
}
