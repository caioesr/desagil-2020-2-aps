package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GateView extends JPanel implements ActionListener {

    private final Gate gate;

    private final JCheckBox[] entradas;
    private final JCheckBox saida;

    public GateView(Gate gate) {
        this.gate = gate;

        JLabel entradaLabel = new JLabel("Entrada(s):");
        JLabel saidaLabel = new JLabel("Saida:");

        entradas = new JCheckBox[gate.getInputSize()];
        saida = new JCheckBox();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(entradaLabel);
        for (int i = 0; i < entradas.length; i++) {
            entradas[i] = new JCheckBox();
            add(entradas[i]);
            entradas[i].addActionListener(this);
        }
        add(saidaLabel);
        add(saida);

        saida.setEnabled(false);

        update();
    }

    private void update() {
        for (int i = 0; i < entradas.length; i++) {
            Switch aSwitch = new Switch();
            if (entradas[i].isSelected()) {
                aSwitch.turnOn();
            } else {
                aSwitch.turnOff();
            }
            gate.connect(i, aSwitch);

        }

        boolean resultado = gate.read();

        saida.setSelected(resultado);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
