import javax.swing.*;
import java.awt.event.*;

public class UtilityFunctions {

    public static void addPlaceholder(JTextField textField, String placeholderText) {
        textField.setText(placeholderText);
        textField.setForeground(Styling.DISABLED);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(Styling.DISABLED);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setForeground(Styling.PRIMARY_TEXT);
                    textField.setText("");
                }
            }
        });
    }

    public static void hoverAction(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Styling.HOVER);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(Styling.SURFACE);
            }
        });
    }

    public static ActionListener clearFunction(Subscription_GUI gui) {
        return e -> {
            JTextField[] textFields;

            if (gui.isPersonal) {
                textFields = new JTextField[]{
                        gui.modelName, gui.params, gui.price,
                        gui.contextWin, gui.prompt,
                        gui.promptQuota, gui.addTokens
                };
            } else {
                textFields = new JTextField[]{
                        gui.modelName, gui.params, gui.price,
                        gui.contextWin, gui.prompt,
                        gui.teamMemberSlot, gui.memberName, gui.remMember
                };
            }

            for (JTextField field : textFields) {
                if (field != null) {
                    field.setText("");
                    field.transferFocus();      
                }
            }
        };
    }

    public static void validateInput(String input) throws Exception {
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error" ,JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public static void validateInput(int idx) {
        if (idx < 0) {
            JOptionPane.showMessageDialog(null, "Index cant be less than 0", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}