//NOAH CHIANG
//student id : 216612285
//https://www.youtube.com/watch?v=6rVLxI-zDPI&feature=youtu.be
package com.example.kryptonote;

public class cipher {
  private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public cipher(String k){
        this.key = k;
    }

    public String Encrypt(String note){
        note = note.toUpperCase();
        String pad = makePad(note);
        String result = "";
        for(int i = 0;i<note.length();i++){
            int pos = ALPHABET.indexOf(note.charAt(i));
            int shift = Integer.parseInt(pad.substring(i,i+1));
            int newpos = (pos+shift)%ALPHABET.length();
            result+= ALPHABET.substring(newpos,newpos+1);
        }
        return result;
    }

    public String Decrypt(String note){
        String pad = makePad(note);
        String result = "";
        for(int i = 0;i<note.length();i++){
            int pos = ALPHABET.indexOf(note.charAt(i));
            int shift = Integer.parseInt(pad.substring(i,i+1));
            int newpos = ((pos-shift)%ALPHABET.length());
            if(newpos<0){
                newpos+= 27;
            }
            result+= ALPHABET.substring(newpos,newpos+1);
        }
        return result;
    }

    private String makePad(String note){
        String pad = this.key;
        for(; pad.length()<note.length();){
            pad += this.key;
        }
        return pad;
    }
}
