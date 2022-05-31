package UI;

import MarblesPuzzle.Model.*;
import API.*;
import MarblesPuzzle.Model.Utils.IOHandler;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.*;

public class SimulationWindow extends JFrame {

    private int width, height;
    private String title = "Marbles Puzzle Solver Simulation";

    private State s;
    private Node n;

    public SimulationWindow() {
        initializeEntities();
        setTitle(title);
//        setIconImage();
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(width/2, height/2);

        setLayout(new BorderLayout());
        // Simulation window setup
        initializePanels();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeEntities() {
        IOHandler io;
        IProblem p;
        io = new IOHandler("MarblesPuzzle/Inputs/input1.txt");

        // Extract execution info:
        p = io.getProblem();
        String algoName = io.getAlgorithmName();
        boolean showOpenList = io.getVerbose();
        s = (State) p.getInitialState();
        n = new Node(s);
    }

    private void initializePanels() {
        // Top Panel:
        JPanel titlePanel = new JPanel();

        titlePanel.setBackground(Color.red);
        titlePanel.add(new JLabel(title));
//        titlePanel.
        add(titlePanel, NORTH);


        // Central Frame:
        JPanel frame = new JPanel();
        frame.setBackground(Color.green);
//        centerPanel.add(new JLabel("Game Board:"));
        int dim = s.size();
        frame.setLayout(new GridLayout(dim, dim));

        JButton b;
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                b = new JButton(i + ", " + j);
                if (j % 2 == 0) {
                    b.setBackground(Color.LIGHT_GRAY);
                } else {
                    b.setBackground(Color.GRAY);
                }
                b.setActionCommand("onClick");
                b.setBackground(Color.blue);
                frame.add(b);
            }
        }

        add(frame, CENTER);
        add(new JPanel(), EAST);
        add(new JPanel(), WEST);
        add(new JPanel(), SOUTH);
    }
    public void onClick(View v) {
        System.out.println("Hellow");
    }
    public int getWindowHeight() {
        return height;
    }

    public int getWindowWidth() {
        return width;
    }
}