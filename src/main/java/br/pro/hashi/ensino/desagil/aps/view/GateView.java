package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {

    private final Gate gate;

    private final JCheckBox[] entradas;
    private final Light saida;

    private final Image gateImage;
    private Color color;

    public GateView(Gate gate) {

        super(360, 200);

        this.gate = gate;

        entradas = new JCheckBox[gate.getInputSize()];
        saida = new Light(255,0,0);

        for (int i = 0; i < entradas.length; i++) {
            entradas[i] = new JCheckBox();
            add(entradas[i]);
            entradas[i].addActionListener(this);
        }

        if(entradas.length > 1){
            entradas[0].setLocation(20, 55);
            entradas[0].setSize(25,25);
            entradas[1].setLocation(20, 120);
            entradas[1].setSize(25,25);
        } else {
            entradas[0].setLocation(20, 88);
            entradas[0].setSize(25,25);
        }

        saida.connect(0, gate);

        color = Color.BLACK;

        String imageName = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(imageName);
        gateImage = getToolkit().getImage(url);

        addMouseListener(this);

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

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(gateImage, 20, 20, 320, 160, this);

        g.setColor(saida.getColor());
        g.fillOval(330, 90, 20, 20);

        getToolkit().sync();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        int x = event.getX();
        int y = event.getY();

        if (Math.sqrt(Math.pow((x-330),2) + Math.pow((y-90),2)) > 10) {

            color = JColorChooser.showDialog(this, null, color);
            saida.setColor(color);

            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }
}
