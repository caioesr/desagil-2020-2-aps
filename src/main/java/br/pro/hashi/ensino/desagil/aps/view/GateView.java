package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
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
    private final JRadioButton saida;

    private final Image gateImage;
    private Color color;

    public GateView(Gate gate) {

        super(360, 200);

        this.gate = gate;

        JLabel entradaLabel = new JLabel("Entrada(s):");
        JLabel saidaLabel = new JLabel("Saida:");

        entradas = new JCheckBox[gate.getInputSize()];
        saida = new JRadioButton();
        saida.setLocation(320, 85);
        saida.setSize(30,30);

        // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(entradaLabel);
        for (int i = 0; i < entradas.length; i++) {
            entradas[i] = new JCheckBox();
            add(entradas[i]);
            entradas[i].addActionListener(this);
        }
        add(saidaLabel);
        add(saida);

        if(entradas.length > 1){
            entradas[0].setLocation(20, 55);
            entradas[0].setSize(25,25);
            entradas[1].setLocation(20, 120);
            entradas[1].setSize(25,25);
        } else {
            entradas[0].setLocation(20, 88);
            entradas[0].setSize(25,25);
        }

        saida.setEnabled(false);

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

        boolean resultado = gate.read();

        saida.setSelected(resultado);

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(gateImage, 20, 20, 320, 160, this);

        // Desenha um quadrado cheio.
        // g.setColor(color);
        // g.fillRect(20, 20, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        // Se o clique foi dentro do quadrado colorido...
        if (x >= 210 && x < 235 && y >= 311 && y < 336) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);

            // ...e chamamos repaint para atualizar a tela.
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
