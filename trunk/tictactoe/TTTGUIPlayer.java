package tictactoe;

import javax.swing.JFrame;

import tourney.Move;

/**
 * 
 * A GUI-based TicTacToe player.
 * 
 * @author Ryan Beckett
 */
public class TTTGUIPlayer extends TTTPlayer
{
    private JFrame frm;
    
    public TTTGUIPlayer()
    {
        initGUI();
    }
    
    private void initGUI()
    {
        frm = new JFrame("Tic Tac Toe");
        frm.setSize(100, 100);
        frm.setVisible(true);
    }
    

    @Override
    public Move getMove()
    {
        return null;
    }

    @Override
    public void signalWinner(boolean isWinner)
    {
    }

    @Override
    public void signalDraw()
    {
    }

    public static void main(String[] args)
    {

    }

}
