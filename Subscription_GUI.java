import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Subscription_GUI extends JFrame {
    boolean isPersonal = true;
    JPanel currentAppPanel;
    JButton addProbtn, addPersonalbtn;
    ArrayList<AIModel> aiData = new ArrayList<>();

    JTextField modelName, params, price, contextWin, prompt;
    JTextField teamMemberSlot, memberName, remMember;
    JTextField promptQuota, addTokens;

    public Subscription_GUI() {
        this.setTitle("Java Coursework");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        currentAppPanel = createMainPanel();
        this.add(createHeader(), BorderLayout.NORTH);
        this.add(currentAppPanel, BorderLayout.CENTER);
        this.add(buttonPanel(), BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public JPanel createMainPanel() {
        JPanel appContainer = new JPanel();
        appContainer.setBackground(Styling.BACKGROUND);
        appContainer.add(createInputFields());
        appContainer.add(planFields());
        appContainer.add(createDisplayArea());
        return appContainer;
    }

    public JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        header.setBackground(Styling.BACKGROUND);
        header.add(UIComponents.createTitle("Plan: "));
        header.add(planSelector(ProPlan.plan, PersonalPlan.plan));
        return header;
    }

    public JPanel planSelector(String textPro, String textPersonal) {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setBackground(Styling.SURFACE);
        JButton proButton = UIComponents.createButton(textPro);
        UtilityFunctions.hoverAction(proButton);
        proButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isPersonal = false;
                remove(currentAppPanel);
                currentAppPanel = createMainPanel();
                add(currentAppPanel, BorderLayout.CENTER);
                addProbtn.setEnabled(true);
                addPersonalbtn.setEnabled(false);
                revalidate();
                repaint();
            }
        });
        JButton personalButton = UIComponents.createButton(textPersonal);
        UtilityFunctions.hoverAction(personalButton);
        personalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isPersonal = true;
                remove(currentAppPanel);
                currentAppPanel = createMainPanel();
                add(currentAppPanel, BorderLayout.CENTER);
                addProbtn.setEnabled(false);
                addPersonalbtn.setEnabled(true);
                revalidate();
                repaint();
            }
        });
        buttons.add(proButton);
        buttons.add(personalButton);
        return buttons;
    }

    public JPanel createInputFields() {
        JPanel inputFields = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 40, 10, 40);
        c.fill = GridBagConstraints.HORIZONTAL;

        inputFields.setBackground(Styling.SURFACE);

        c.gridx = 0; c.gridy = 0;
        inputFields.add(UIComponents.createLabel("Model Name"), c);
        modelName = UIComponents.inputField();
        UtilityFunctions.addPlaceholder(modelName, "Enter model name");
        c.gridx = 1;
        inputFields.add(modelName, c);

        c.gridx = 0; c.gridy = 1;
        inputFields.add(UIComponents.createLabel("Parameters"), c);
        c.gridx = 1;
        params = UIComponents.inputField();
        UtilityFunctions.addPlaceholder(params, "Enter Number of Parameter");
        inputFields.add(params, c);

        c.gridx = 0; c.gridy = 2;
        inputFields.add(UIComponents.createLabel("Price"), c);
        c.gridx = 1;
        price = UIComponents.inputField();
        UtilityFunctions.addPlaceholder(price, "Enter Price");
        inputFields.add(price, c);

        c.gridx = 0; c.gridy = 3;
        inputFields.add(UIComponents.createLabel("Context Window Size"), c);
        c.gridx = 1;
        contextWin = UIComponents.inputField();
        UtilityFunctions.addPlaceholder(contextWin, "Enter Context Window Size");
        inputFields.add(contextWin, c);

        c.gridx = 0; c.gridy = 4;
        inputFields.add(UIComponents.createLabel("Prompt"), c);
        c.gridx = 1;
        prompt = UIComponents.inputField();
        UtilityFunctions.addPlaceholder(prompt, "Enter Prompt");
        inputFields.add(prompt, c);

        return inputFields;
    }

    public JPanel planFields() {
        JPanel planField = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        planField.setBackground(Styling.SURFACE);

        if (!isPersonal) {
            c.insets = new Insets(10, 36, 10, 36);
            c.gridx = 0; c.gridy = 0;
            planField.add(UIComponents.createLabel("Team Members"), c);
            c.gridx = 1;
            teamMemberSlot = UIComponents.inputField();
            UtilityFunctions.addPlaceholder(teamMemberSlot, "Add Team Member Slots");
            planField.add(teamMemberSlot, c);

            c.gridx = 0; c.gridy = 1;
            planField.add(UIComponents.createLabel("Add Member Name"), c);
            c.gridx = 1;
            memberName = UIComponents.inputField();
            UtilityFunctions.addPlaceholder(memberName, "Add Team Member Name");
            planField.add(memberName, c);

            c.gridx = 0; c.gridy = 2;
            planField.add(UIComponents.createLabel("Remove Team Member"), c);
            c.gridx = 1;
            remMember = UIComponents.inputField();
            UtilityFunctions.addPlaceholder(remMember, "Remove a team member");
            planField.add(remMember, c);
            return planField;
        }
        c.insets = new Insets(10, 33, 10, 33);
        c.gridx = 0; c.gridy = 0;
        planField.add(UIComponents.createLabel("Prompt Quota                "), c);
        c.gridx = 1;
        promptQuota = UIComponents.inputField();
        UtilityFunctions.addPlaceholder(promptQuota, "Add Monthly Quota");
        planField.add(promptQuota, c);

        c.gridx = 0; c.gridy = 1;
        planField.add(UIComponents.createLabel("Add Tokens                 "), c);
        c.gridx = 1;
        addTokens = UIComponents.inputField();
        UtilityFunctions.addPlaceholder(addTokens, "Purchase Tokens");
        planField.add(addTokens, c);
        return planField;
    }

    public JPanel buttonPanel() {
        JPanel buttonLayout = new JPanel(new GridBagLayout());
        buttonLayout.setBackground(Styling.SURFACE);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 20, 25, 20);

        c.gridx = 0; c.gridy = 0;
        addPersonalbtn = UIComponents.createButton("Add Personal Plan");
        UtilityFunctions.hoverAction(addPersonalbtn);
        addPersonalbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        addPersonalbtn.setEnabled(isPersonal);
        buttonLayout.add(addPersonalbtn, c);

        c.gridx = 1;
        addProbtn = UIComponents.createButton("Add Pro Plan");
        addProbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt_model = modelName.getText();
                String txt_parameter = params.getText();
                int txt_price = Integer.parseInt(price.getText());
                int txt_contextWindow = Integer.parseInt(contextWin.getText());
                String txt_prompt = prompt.getText();
                AIModel personal = new ProPlan(2,txt_model, txt_price, txt_parameter, txt_contextWindow);
                aiData.add(AIModel());
            }
        });
        UtilityFunctions.hoverAction(addProbtn);
        addProbtn.setEnabled(!isPersonal);
        buttonLayout.add(addProbtn, c);

        c.gridx = 2;
        JButton displayAll = UIComponents.createButton("Display All");
        UtilityFunctions.hoverAction(displayAll);
        buttonLayout.add(displayAll, c);

        c.gridx = 3;
        JButton clear = UIComponents.createButton("Clear");
        clear.addActionListener(UtilityFunctions.clearFunction(this));
        UtilityFunctions.hoverAction(clear);
        buttonLayout.add(clear, c);

        c.gridx = 4;
        JButton promptEnter = UIComponents.createButton("Send Prompt");
        UtilityFunctions.hoverAction(promptEnter);
        buttonLayout.add(promptEnter, c);

        c.gridx = 0; c.gridy = 1;
        JButton addTeamMember = UIComponents.createButton("Add Team Member");
        UtilityFunctions.hoverAction(addTeamMember);
        buttonLayout.add(addTeamMember, c);

        c.gridx = 1;
        JButton remTeamMember = UIComponents.createButton("Remove Team Member");
        UtilityFunctions.hoverAction(remTeamMember);
        buttonLayout.add(remTeamMember, c);

        c.gridx = 2;
        JButton checkPlan = UIComponents.createButton("Check Plan");
        UtilityFunctions.hoverAction(checkPlan);
        buttonLayout.add(checkPlan, c);

        c.gridx = 3;
        JButton export = UIComponents.createButton("Export");
        UtilityFunctions.hoverAction(export);
        buttonLayout.add(export, c);

        c.gridx = 4;
        JButton load = UIComponents.createButton("Load");
        UtilityFunctions.hoverAction(load);
        buttonLayout.add(load, c);
        return buttonLayout;
    }

    public JScrollPane createDisplayArea() {
        JTextArea output = new JTextArea(12, 55);
        output.setBackground(Styling.SURFACE);
        output.setForeground(Styling.PRIMARY_TEXT);
        output.setBorder(null);
        output.setEditable(false);
        return new JScrollPane(output);
    }


}