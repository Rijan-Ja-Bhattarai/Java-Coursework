import javax.swing.*;
import java.awt.*;

public class UIComponents
{
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Styling.PLAIN_FONT);
        label.setForeground(Styling.PRIMARY_TEXT);
        return label;
    }

    public static JLabel createTitle(String text) {
        JLabel title = new JLabel(text);
        title.setForeground(Styling.PRIMARY_TEXT);
        title.setFont(Styling.TITLE_FONT);
        return title;
    }

    public static JTextField inputField() {
        JTextField textField = new JTextField();
        textField.setFont(Styling.PLAIN_FONT);
        textField.setForeground(Styling.PRIMARY_TEXT);
        textField.setBackground(Styling.SURFACE);
        textField.setPreferredSize(new Dimension(250, 25));
        return textField;
    }

    public static JTextField inputField(int cols) {
        JTextField textField = new JTextField(cols);
        textField.setFont(Styling.PLAIN_FONT);
        textField.setBackground(Styling.SURFACE);
        return textField;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Styling.PRIMARY_TEXT);
        button.setBackground(Styling.SURFACE);
        button.setFont(Styling.PLAIN_FONT);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(150, 25));
        button.setBorder(null);
        return button;
    }
}
