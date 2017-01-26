import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Write a description of class Game here.
 * 
 * @colinbowen 
 * @1
 */
public class Game implements ActionListener
{
    // instance variables - replace the example below with your own
    private JFrame frame = new JFrame("Tic-Tac-Toe");
    private static JButton buttons[] = new JButton[9];
    private int count;
    private String letter = "";
    private int[][] wins = new int[][] {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //virticle wins
            {0, 4, 8}, {2, 4, 6}             //diagonal wins
        };
    private boolean win = false;

    /**
     * Constructor for objects of class GUI
     */
    public Game()
    {
        // initialise instance variables
        makeFrame();
        count = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    private void makeFrame()
    {
        // put your code here
        frame.setSize(400,400);

        JPanel panel = new JPanel(new GridLayout(3, 3));
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel();
        
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        
        contentPane.setLayout(new BorderLayout());

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(panel1, BorderLayout.SOUTH);
        contentPane.add(panel2, BorderLayout.EAST);

        panel2.setBorder(BorderFactory.createTitledBorder("Player Info:"));


        for(int i=0; i<=8; i++){
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            buttons[i].addActionListener(this);           
        }
        
        JButton grid1 = new JButton("3 X 3 Grid");
        JButton grid2 = new JButton("4 X 4 Grid"); 
        
        panel1.add(grid1);
        panel1.add(grid2);
        
        panel2.add(new JLabel("Player 1: X"));
        panel2.add(new JLabel("Player 2: O"));

        makeMenuBar(frame);
        frame.pack();
        frame.setSize(400,400);
        frame.setVisible(true);
    }

    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the Game menu
        JMenu gameMenu = new JMenu("Game");
        menubar.add(gameMenu);
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);

        JMenuItem newGame = new JMenuItem("New");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
        newGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { newGame(); }
            });
        gameMenu.add(newGame);
        

        JMenuItem quitGame = new JMenuItem("Quit");
        quitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        quitGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { quitGame(); }
            });
        gameMenu.add(quitGame);

        JMenuItem help = new JMenuItem("Help");
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, SHORTCUT_MASK));
        help.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { help(); }
            });
        helpMenu.add(help);

        JMenuItem about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, SHORTCUT_MASK));
        about.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { about(); }
            });
        helpMenu.add(about);
    }

    public void newGame()
    {
        frame.setVisible(false);
        Game game = new Game();
    }

    private void quitGame()
    {
        System.exit(0);
    }

    private void help()
    {
        System.out.println("Instructions on how to play:\n\n\nPlayers alternate placing Xs and Os on the board until either\n\n(a) one player has three in a row,\nhorizontally, vertically or diagonally;\n\nor\n\n(b) all nine squares are filled.\n\nIf a player is able to draw three Xs or three Os in a row, that player wins.\n\nIf all nine squares are filled and neither player has three in a row,\nthe game is a draw."); 
    }

    private void about()
    {
        JOptionPane.showMessageDialog(frame, "Tic-Tac-Toe\nVersion: 1.0");
    }
    
    /**
         * When an object is clicked, perform an action.
         * @param a action event object
         */
    public void actionPerformed(ActionEvent e) {
        count++;
        
        if(count % 2 == 0){
            letter = "O";
        } else {
            letter = "X";
        }

        JButton pButton = (JButton)e.getSource(); 
        pButton.setText(letter);
        pButton.setEnabled(false);
        
        for(int i=0; i<=7; i++){
            if( buttons[wins[i][0]].getText().equals(buttons[wins[i][1]].getText()) && 
            buttons[wins[i][1]].getText().equals(buttons[wins[i][2]].getText()) && 
            buttons[wins[i][0]].getText() != ""){
                win = true;
            }
        }

        /*Show a dialog when game is over*/
        if(win == true){
            JOptionPane.showMessageDialog(null, letter + " wins the game!");
        }
        else if(count == 9 && win == false){
            JOptionPane.showMessageDialog(null, "The game was a tie!");
        }        
    }
}
