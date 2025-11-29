import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator extends JFrame implements ActionListener {
private JTextField display;
private StringBuilder currentInput;
private double firstOperand = 0;
private String operator = "";
public Calculator() {
setTitle("Calculator");
setSize(340, 480);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setResizable(false);
currentInput = new StringBuilder();
// Layout
setLayout(new BorderLayout(10, 10));
getContentPane().setBackground(Color.BLACK);
// Display Field
display = new JTextField();
display.setEditable(false);
display.setFont(new Font("Segoe UI", Font.BOLD, 28));
display.setHorizontalAlignment(JTextField.RIGHT);
display.setBackground(Color.DARK_GRAY);
display.setForeground(Color.WHITE);
display.setCaretColor(Color.WHITE);
display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
JPanel displayPanel = new JPanel(new BorderLayout());
displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
displayPanel.setBackground(Color.BLACK);
displayPanel.add(display, BorderLayout.CENTER);
// Button Panel
JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
buttonPanel.setBackground(Color.BLACK);
String[] buttons = {
"C", "x", "%", "/",
"7", "8", "9", "*",
"4", "5", "6", "-",
"1", "2", "3", "+",
"+/-", "0", ".", "="
};
for (String text : buttons) {
JButton btn = new JButton(text);
if (!text.isEmpty()) {
btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
btn.setFocusPainted(false);
btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
btn.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
btn.setForeground(Color.WHITE);
// Style based on type
if ("/*-+".contains(text)) {
btn.setBackground(new Color(255, 165, 0)); // Orange for operators
} else if (text.equals("=")) {
btn.setBackground(new Color(0, 168, 107)); // Green
} else if (text.equals("C")) {
btn.setBackground(new Color(220, 20, 60)); // Crimson
} else {
btn.setBackground(new Color(50, 50, 50)); // Dark gray
}
// Hover effect
btn.addMouseListener(new MouseAdapter() {
public void mouseEntered(MouseEvent e) {
btn.setBackground(btn.getBackground().brighter());
}
public void mouseExited(MouseEvent e) {
if ("/*-+".contains(btn.getText()))
btn.setBackground(new Color(255, 165, 0));
else if (btn.getText().equals("="))
btn.setBackground(new Color(0, 168, 107));
else if (btn.getText().equals("C"))
btn.setBackground(new Color(220, 20, 60));
else
btn.setBackground(new Color(50, 50, 50));
}
});
btn.addActionListener(this);
} else {
btn.setEnabled(false);
btn.setBackground(Color.BLACK);
btn.setBorder(null);
}
buttonPanel.add(btn);
}
add(displayPanel, BorderLayout.NORTH);
add(buttonPanel, BorderLayout.CENTER);
}
public void actionPerformed(ActionEvent e) {
String input = e.getActionCommand();
if (input.matches("\\d") || input.equals(".")) {
currentInput.append(input);
display.setText(currentInput.toString());
} else if (input.matches("[+\\-*/]")) {
if (currentInput.length() > 0) {
firstOperand = Double.parseDouble(currentInput.toString());
currentInput.setLength(0);
operator = input;
display.setText("");
}
} else if (input.equals("=")) {
    if (currentInput.length() > 0 && !operator.isEmpty()) {
double secondOperand = Double.parseDouble(currentInput.toString());
double result = calculate(firstOperand, secondOperand, operator);
display.setText(String.valueOf(result));
currentInput.setLength(0);
currentInput.append(result);
operator = "";
}
} else if (input.equals("C")) {
currentInput.setLength(0);
operator = "";
firstOperand = 0;
display.setText("");
}
}
private double calculate(double a, double b, String op) {
switch (op) {
case "+": return a + b;
case "-": return a - b;
case "*": return a * b;
case "/": return b != 0 ? a / b : 0;
default: return 0;
}
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
}
}