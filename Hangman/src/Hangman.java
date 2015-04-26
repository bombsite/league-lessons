import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

/**
 * Created by site on 4/25/15.
 */
public class Hangman implements KeyListener{
    private Stack<String> wordlist = new Stack<>();
    private JLabel label;
    private JFrame myFrame;

    public Hangman() {
        addPuzzles();
        createUI();
    }
    private void addPuzzles() {
        wordlist.push("Hellofriend");
        wordlist.push("Goodday");
        wordlist.push("Keepgoing");
    }
    private void createUI() {
        myFrame = new JFrame();
        label = new JLabel(wordlist.pop());
        myFrame.add(label);
        myFrame.setSize(400, 80);
        myFrame.setVisible(true);
        myFrame.addKeyListener(this);
    }
    private void removeAndUpdateWord() {
        if (!wordlist.isEmpty())
            label.setText(wordlist.pop());
    }
    public static void main(String[] args){
        Hangman hm = new Hangman();
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {
        System.out.println("Key pressed");
        removeAndUpdateWord();
    }
}
