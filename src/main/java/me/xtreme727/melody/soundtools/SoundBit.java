package me.xtreme727.melody.soundtools;

public class SoundBit {

    private Instrument instrument;
    private Note note;
    private Dynamic dynamic;

    public SoundBit(Instrument instrument, Note note, Dynamic dynamic) {
        this.instrument = instrument;
        this.note = note;
        this.dynamic = dynamic;
    }

    public Dynamic getDynamics() { return dynamic; }

    public Instrument getInstrument() {
        return instrument;
    }

    public Note getNote() {
        return note;
    }

}
