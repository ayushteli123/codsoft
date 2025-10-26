import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }
}

public class ATMGUI extends JFrame implements ActionListener {

    private BankAccount account;
    private JLabel balanceLabel, messageLabel;
    private JTextField amountField;
    private JButton withdrawButton, depositButton, checkBalanceButton, clearButton, exitButton;

    
    public ATMGUI(BankAccount account) {
        this.account = account;
        setTitle("🏧 ATM Machine");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 2️⃣ Design the user interface
        JLabel title = new JLabel("Welcome to ATM", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 42, 86));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        balanceLabel = new JLabel("Current Balance: ₹" + account.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(39, 174, 96));

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setToolTipText("Enter amount");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        checkBalanceButton = new JButton("Check Balance");
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(checkBalanceButton);

        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.add(clearButton);
        bottomPanel.add(exitButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        messageLabel.setForeground(new Color(192, 57, 43));

        centerPanel.add(balanceLabel);
        centerPanel.add(new JLabel("Enter Amount:", SwingConstants.CENTER));
        centerPanel.add(amountField);
        centerPanel.add(buttonPanel);
        centerPanel.add(messageLabel);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        JButton[] buttons = {withdrawButton, depositButton, checkBalanceButton, clearButton, exitButton};
        for (JButton btn : buttons) {
            btn.setBackground(new Color(52, 152, 219));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185)));
        }
        exitButton.setBackground(new Color(231, 76, 60));
        clearButton.setBackground(new Color(241, 196, 15));

 
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(e -> System.exit(0));
    }

 
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = amountField.getText().trim();
        double amount = 0;

        if (!input.isEmpty()) {
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                messageLabel.setText("❌ Please enter a valid numeric amount!");
                return;
            }
        }

        if (e.getSource() == withdrawButton) {
            if (account.withdraw(amount)) {
                messageLabel.setForeground(new Color(39, 174, 96));
                messageLabel.setText("✅ Withdrawal successful: ₹" + amount);
            } else {
                messageLabel.setForeground(new Color(192, 57, 43));
                messageLabel.setText("❌ Invalid amount or insufficient balance!");
            }
        } else if (e.getSource() == depositButton) {
            if (account.deposit(amount)) {
                messageLabel.setForeground(new Color(39, 174, 96));
                messageLabel.setText("✅ Deposited successfully: ₹" + amount);
            } else {
                messageLabel.setForeground(new Color(192, 57, 43));
                messageLabel.setText("❌ Deposit amount must be positive!");
            }
        } else if (e.getSource() == checkBalanceButton) {
            messageLabel.setForeground(new Color(52, 73, 94));
            messageLabel.setText("💰 Current Balance: ₹" + account.getBalance());
        } else if (e.getSource() == clearButton) {
            amountField.setText("");
            messageLabel.setText("");
        }
        balanceLabel.setText("Current Balance: ₹" + account.getBalance());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAccount account = new BankAccount(5000.00);
            new ATMGUI(account).setVisible(true);
        });
    }
}
