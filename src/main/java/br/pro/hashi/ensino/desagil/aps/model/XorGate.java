package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate[] nands;

    public XorGate() {
        super("XOR", 2);

        nands = new NandGate[4];
        for(int i=0;i<nands.length;i++){
            nands[i] = new NandGate();
        }
    }

    @Override
    public boolean read() {
        return nands[3].read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        if(inputIndex == 0){
            nands[0].connect(inputIndex, emitter);
            nands[1].connect(inputIndex, emitter);
            nands[1].connect(1-inputIndex, nands[0]);
            nands[3].connect(inputIndex, nands[1]);
        }else{
            nands[0].connect(inputIndex, emitter);
            nands[2].connect(1-inputIndex, nands[0]);
            nands[2].connect(inputIndex, emitter);
            nands[3].connect(inputIndex, nands[2]);
        }
    }

}