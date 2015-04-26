import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by site on 4/25/15.
 */
public class Hangman implements KeyListener{
    private Stack<String> wordlist = new Stack<>();
    private ArrayList<JLabel> puzzle;
    private JFrame myFrame;
    private JPanel panel;
    private String currentPuzzle;
    private int numLives;
    private JLabel lives;

    public Hangman() {
        addPuzzles();
        createUI();
    }
    private void addPuzzles() {
        wordlist.push("hellofriend");
        wordlist.push("goodday");
        wordlist.push("keepgoing");
    }
    private void createUI() {
        myFrame = new JFrame();
        panel = new JPanel();
        getNextPuzzle();
        myFrame.add(panel);
        myFrame.setSize(400, 80);
        myFrame.setVisible(true);
        myFrame.addKeyListener(this);
    }
    private void removeBoxes() {
        if (wordlist.isEmpty())
            return;
        myFrame.remove(panel);
        panel = new JPanel();
        myFrame.add(panel);
    }
    private void getNextPuzzle() {
        if (wordlist.isEmpty()) {
            System.exit(0);
        }
        removeBoxes();
        currentPuzzle = wordlist.pop();
        System.out.println("Puzzle is now " + currentPuzzle);
        createBoxes();
    }
    private void createBoxes() {
        puzzle = new ArrayList<>();
        for(int i=0; i<currentPuzzle.length(); i++) {
            JLabel label = new JLabel("_");
            puzzle.add(label);
            panel.add(label);
        }
        numLives = 9;
        lives = new JLabel("" + numLives);
        panel.add(lives);
        lives.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }
    private void updateSpacesWithUserInput(char keyChar) {
        for (int i=0;i<currentPuzzle.length();i++) {
            if(currentPuzzle.charAt(i) == keyChar)
                puzzle.get(i).setText("" + keyChar);
        }
    }
    private String getCurrentAnswer() {
        StringBuffer answer = new StringBuffer();
        for (JLabel textbox : puzzle) {
            answer.append(textbox.getText());
        }
        return answer.toString();
    }
    public static void playDeathKnell() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new
                    URL("http://school.wintrisstech.org/sounds/death.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        Hangman hm = new Hangman();
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {
        if (!currentPuzzle.contains("" + e.getKeyChar())) {
            numLives--;
            lives.setText(numLives + "");
            if (numLives == 0) {
                //playDeathKnell();
                System.exit(0);
            }
            return;
        }
        updateSpacesWithUserInput(e.getKeyChar());
        if (getCurrentAnswer().equals(currentPuzzle)) {
            JOptionPane.showMessageDialog(null, "You're nice!", "yay <3", JOptionPane.INFORMATION_MESSAGE);
            getNextPuzzle();
        }
    }
}
