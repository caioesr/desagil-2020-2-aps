package br.pro.hashi.ensino.desagil.aps;

import br.pro.hashi.ensino.desagil.aps.model.AndGate;
import br.pro.hashi.ensino.desagil.aps.model.NandGate;
import br.pro.hashi.ensino.desagil.aps.model.NotGate;
import br.pro.hashi.ensino.desagil.aps.model.OrGate;
import br.pro.hashi.ensino.desagil.aps.model.XorGate;
import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.view.View;

import javax.swing.*;
import java.util.LinkedList;

public class APS {
    public static void main(String[] args) {
        LinkedList<Gate> model = new LinkedList<>();
        model.add(new AndGate());
        model.add(new NandGate());
        model.add(new NotGate());
        model.add(new OrGate());
        model.add(new XorGate());

        View view = new View(model);

        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(view);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        });
    }

}