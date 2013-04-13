package com.gamejam.core;

import java.io.IOException;
import java.net.URL;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 *
 * @author mrobel
 */
public class MidiSoundTest {

    private static Sequencer midiPlayer;

    // testing main method
    public static void main(String[] args) {
        startMidi("http://www.angelfire.com/fl/herky/images/teddybear.mid");     // start the midi player
        try {
            Thread.sleep(10000);   // delay
        } catch(InterruptedException e) {
        }
        System.out.println("faster");
        midiPlayer.setTempoFactor(2.0F);   // >1 to speed up the tempo
        try {
            Thread.sleep(10000);   // delay
        } catch(InterruptedException e) {
        }

        // Do this on every move step, you could change to another song
        if(!midiPlayer.isRunning()) {  // previous song finished
            // reset midi player and start a new song
            midiPlayer.stop();
            midiPlayer.close();
            startMidi("http://www.angelfire.com/fl/herky/images/teddybear.mid");
        }
    }

    public static void startMidi(String midFilename) {
        try {
            URL midiFile = new URL(midFilename);
            Sequence song = MidiSystem.getSequence(midiFile);
            midiPlayer = MidiSystem.getSequencer();
            midiPlayer.open();
            midiPlayer.setSequence(song);
            midiPlayer.setLoopCount(0); // repeat 0 times (play once)
            midiPlayer.start();
        } catch(MidiUnavailableException e) {
            e.printStackTrace();
        } catch(InvalidMidiDataException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
