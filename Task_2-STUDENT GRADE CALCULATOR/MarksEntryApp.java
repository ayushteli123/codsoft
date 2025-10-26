import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MarksEntryApp {
    private JFrame frame;
    private JPanel subjectsPanel;
    private ArrayList<JTextField> subjectFields;
    private JLabel lblTotal, lblAverage, lblGrade;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MarksEntryApp().createGUI());
    }

    private void createGUI() {
        frame = new JFrame("Marks Entry & Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Marks Entry & Results", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setOpaque(true);
        title.setBackground(new Color(0, 120, 215));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(title, BorderLayout.NORTH);

        // Panel for subjects
        subjectsPanel = new JPanel();
        subjectsPanel.setLayout(new BoxLayout(subjectsPanel, BoxLayout.Y_AXIS));
        subjectsPanel.setBorder(BorderFactory.createTitledBorder("Enter Marks for Subjects"));
        subjectsPanel.setBackground(new Color(220, 240, 255));

        subjectFields = new ArrayList<>();

        // Add initial subject input
        addSubjectField();

        // Button to add more subjects
        JButton btnAddSubject = new JButton("Add Subject");
        btnAddSubject.setFont(new Font("Arial", Font.BOLD, 16));
        btnAddSubject.setBackground(new Color(0, 200, 100));
        btnAddSubject.setForeground(Color.WHITE);
        btnAddSubject.setFocusPainted(false);
        btnAddSubject.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnAddSubject.addActionListener(e -> addSubjectField());

        // Button to calculate results
        JButton btnCalculate = new JButton("Calculate Results");
        btnCalculate.setFont(new Font("Arial", Font.BOLD, 18));
        btnCalculate.setBackground(new Color(0, 120, 215));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setFocusPainted(false);
        btnCalculate.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCalculate.addActionListener(e -> calculateResults());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(220, 240, 255));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnAddSubject);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(btnCalculate);

        // Panel to display results
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(3, 2, 10, 10));
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        resultsPanel.setBackground(new Color(200, 230, 255));

        lblTotal = new JLabel("");
        lblAverage = new JLabel("");
        lblGrade = new JLabel("");

        resultsPanel.add(new JLabel("Total Marks:"));
        resultsPanel.add(lblTotal);
        resultsPanel.add(new JLabel("Average Percentage:"));
        resultsPanel.add(lblAverage);
        resultsPanel.add(new JLabel("Grade:"));
        resultsPanel.add(lblGrade);

        // Scroll pane for subjects
        JScrollPane scrollPane = new JScrollPane(subjectsPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        // Combine everything in center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(resultsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Method to add a new subject input field
    private void addSubjectField() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(220, 240, 255));
        JTextField txtSubject = new JTextField(15);
        JTextField txtMarks = new JTextField(5);
        txtSubject.setFont(new Font("Arial", Font.PLAIN, 16));
        txtMarks.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(new JLabel("Subject:"));
        panel.add(txtSubject);
        panel.add(new JLabel("Marks:"));
        panel.add(txtMarks);
        subjectsPanel.add(panel);
        subjectsPanel.revalidate();
        subjectsPanel.repaint();

        subjectFields.add(txtMarks);
    }

    // Method to calculate total, average, and grade
    private void calculateResults() {
        try {
            double total = 0;
            int count = 0;

            for (JTextField field : subjectFields) {
                String text = field.getText();
                if (!text.isEmpty()) {
                    double marks = Double.parseDouble(text);
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(frame, "Marks should be between 0 and 100.");
                        return;
                    }
                    total += marks;
                    count++;
                }
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(frame, "Please enter marks for at least one subject.");
                return;
            }

            double average = total / count;
            String grade;
            if (average >= 90) grade = "A+";
            else if (average >= 80) grade = "A";
            else if (average >= 70) grade = "B+";
            else if (average >= 60) grade = "B";
            else if (average >= 50) grade = "C";
            else grade = "F";

            lblTotal.setText(String.format("%.2f", total));
            lblAverage.setText(String.format("%.2f%%", average));
            lblGrade.setText(grade);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric marks.");
        }
    }
}
