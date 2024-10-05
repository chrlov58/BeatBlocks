package me.xtreme727.melody.soundtools;

public class SoundBit {

    private int tempo;
    private Instrument instrument;
    private Note note;
    private Dynamic dynamic;

    public SoundBit(int count, Instrument instrument, Note note, Dynamic dynamic) {
        this.tempo = count;
        this.instrument = instrument;
        this.note = note;
        this.dynamic = dynamic;
    }

    public int getTempo() {
        return tempo;
    }

    public long getTempoLong() {
        return (long) (60*20)/tempo;
        //  1 min x 60 sec x 20L     ~ (long) T
        // T beats   1 min  1 sec          1 beat
    }

    public Dynamic getDynamics() {
        return dynamic;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Note getNote() {
        return note;
    }

}
