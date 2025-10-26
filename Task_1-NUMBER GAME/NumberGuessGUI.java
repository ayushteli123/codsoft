import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class NumberGuessGUI extends JFrame implements ActionListener {
    private int numberToGuess, attemptsLeft, totalScore;
    private final int MAX_ATTEMPTS = 7;
    private String playerName;

    private JLabel messageLabel, scoreLabel, attemptsLabel;
    private JTextField guessField;
    private JButton guessButton, playAgainButton, leaderboardButton, newPlayerButton, exitButton;
    private Random random = new Random();

    private static final String SCORE_FILE = "leaderboard.txt";

    public NumberGuessGUI() {
        initializePlayer();
        createGUI();
        startNewRound();
    }

    private void initializePlayer() {
        playerName = JOptionPane.showInputDialog(this, "Enter your name:", "Player Name", JOptionPane.QUESTION_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Guest";
        }
        totalScore = 0;
    }

    private void createGUI() {
        setTitle("🎯 Number Guessing Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 1, 5, 5));

        messageLabel = new JLabel("I'm thinking of a number between 1 and 100!", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: " + totalScore, SwingConstants.CENTER);
        attemptsLabel = new JLabel("Attempts left: " + MAX_ATTEMPTS, SwingConstants.CENTER);

        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);

        guessButton = new JButton("Guess");
        playAgainButton = new JButton("Play Again");
        leaderboardButton = new JButton("View Leaderboard");
        newPlayerButton = new JButton("New Player");
        exitButton = new JButton("Exit");

        playAgainButton.setEnabled(false);

        guessButton.addActionListener(this);
        playAgainButton.addActionListener(this);
        leaderboardButton.addActionListener(this);
        newPlayerButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(new JLabel("Player: " + playerName, SwingConstants.CENTER));
        add(messageLabel);
        add(new JLabel("Enter your guess:", SwingConstants.CENTER));
        add(guessField);
        add(guessButton);
        add(attemptsLabel);
        add(scoreLabel);
        add(playAgainButton);
        add(leaderboardButton);
        add(newPlayerButton);
        add(exitButton);

        setVisible(true);
    }

    private void startNewRound() {
        numberToGuess = random.nextInt(100) + 1;
        attemptsLeft = MAX_ATTEMPTS;
        messageLabel.setText("I'm thinking of a number between 1 and 100!");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessField.setText("");
        guessField.setEditable(true);
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            handleGuess();
        } else if (e.getSource() == playAgainButton) {
            startNewRound();
        } else if (e.getSource() == leaderboardButton) {
            showLeaderboard();
        } else if (e.getSource() == newPlayerButton) {
            newPlayer();
        } else if (e.getSource() == exitButton) {
            exitGame();
        }
    }

    private void handleGuess() {
        String guessText = guessField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            attemptsLeft--;

            if (guess == numberToGuess) {
                messageLabel.setText("🎉 Correct! The number was " + numberToGuess);
                totalScore += (attemptsLeft + 1) * 10;
                endRound();
            } else if (guess < numberToGuess) {
                messageLabel.setText("📉 Too low! Try again.");
            } else {
                messageLabel.setText("📈 Too high! Try again.");
            }

            if (attemptsLeft == 0 && guess != numberToGuess) {
                messageLabel.setText("❌ Out of attempts! Number was " + numberToGuess);
                endRound();
            }

            attemptsLabel.setText("Attempts left: " + attemptsLeft);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void endRound() {
        scoreLabel.setText("Score: " + totalScore);
        guessField.setEditable(false);
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
        saveScore(playerName, totalScore);
    }

    private void saveScore(String name, int score) {
        try (FileWriter fw = new FileWriter(SCORE_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(name + "," + score);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving score: " + e.getMessage());
        }
    }

    private void showLeaderboard() {
        Map<String, Integer> scores = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    try {
                        int score = Integer.parseInt(parts[1]);
                        scores.put(name, Math.max(scores.getOrDefault(name, 0), score));
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No leaderboard data found yet.");
            return;
        }

        java.util.List<Map.Entry<String, Integer>> sorted = new ArrayList<>(scores.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        StringBuilder leaderboardText = new StringBuilder("🏆 Leaderboard 🏆\n\n");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted) {
            leaderboardText.append(String.format("%2d. %-20s %4d pts\n", rank++, entry.getKey(), entry.getValue()));
            if (rank > 10) break;
        }

        JTextArea textArea = new JTextArea(leaderboardText.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }

    private void newPlayer() {
        initializePlayer();
        // Reset score hoga aur start bhi new hoga 
        scoreLabel.setText("Score: " + totalScore);
        startNewRound();
        // Update player 
        ((JLabel)getContentPane().getComponent(0)).setText("Player: " + playerName);
    }

    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessGUI::new);
    }
}
