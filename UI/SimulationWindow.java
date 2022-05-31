package UI;

import MarblesPuzzle.Model.*;
import API.*;
import MarblesPuzzle.Model.Utils.IOHandler;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

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
        int dim = s.size();
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(dim, dim));
        gridPanel.setBackground(Color.green);
//        centerPanel.add(new JLabel("Game Board:"));
        String[][] board = s.getBoard();
        JButton b;
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                b = new MarbleButton(board[i][j]);
                gridPanel.add(b);
            }
        }

        add(gridPanel, CENTER);

        // Left panel:
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));
        leftPanel.add(new MarbleButton("Start",  Color.green));
        leftPanel.add(new MarbleButton("Pause", Color.orange));
        leftPanel.add(new MarbleButton("Stop", Color.lightGray));
        // Add the panel
        add(leftPanel, WEST);


//        add(new JPanel(), EAST);
//        add(new JPanel(), SOUTH);
    }
}