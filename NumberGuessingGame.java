import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGuessingGame extends JFrame implements ActionListener {

    private final JTextField guessField;
    private final JLabel messageLabel;
    private final JLabel attemptsLabel;
    private int numberToGuess;
    private int numberOfAttempts;
    private int score;
    private final Random random;

    public NumberGuessingGame() {
        random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfAttempts = 0;
        score = 0;

        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JLabel promptLabel = new JLabel("I have generated a number between 1 and 100. Can you guess it?");
        add(promptLabel);

        guessField = new JTextField();
        add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        messageLabel = new JLabel("Enter your guess above and click 'Guess'");
        add(messageLabel);

        attemptsLabel = new JLabel("Attempts: 0");
        add(attemptsLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            numberOfAttempts++;
            if (userGuess == numberToGuess) {
                messageLabel.setText("Congratulations! You guessed the correct number.");
                score++;
                resetGame();
            } else if (userGuess > numberToGuess) {
                messageLabel.setText("Your guess is too high.");
            } else {
                messageLabel.setText("Your guess is too low.");
            }
            attemptsLabel.setText("Attempts: " + numberOfAttempts);

            if (numberOfAttempts >= 10) {
                messageLabel.setText("Sorry, you've used all 10 attempts. The correct number was " + numberToGuess + ".");
                resetGame();
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void resetGame() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            numberToGuess = random.nextInt(100) + 1;
            numberOfAttempts = 0;
            guessField.setText("");
            messageLabel.setText("Enter your guess above and click 'Guess'");
            attemptsLabel.setText("Attempts: 0");
        } else {
            messageLabel.setText("Game over! Your final score is: " + score);
        }
    }
}
