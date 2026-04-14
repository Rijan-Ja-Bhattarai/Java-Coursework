import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

public class Subscription_GUI extends JFrame {
    boolean isPersonal = true;
    JPanel currentAppPanel;
    JButton addProbtn, addPersonalbtn;
    ArrayList<AIModel> aiData = new ArrayList<>();

    JTextField modelName, params, price, contextWin, prompt;
    JTextField teamMemberSlot, memberName, remMember;
    JTextField promptQuota, addTokens;
    JTextArea output;

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
        this.add(createIndexingPanel(), BorderLayout.EAST);
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
        proButton.addActionListener(e -> {
            isPersonal = false;
            remove(currentAppPanel);
            currentAppPanel = createMainPanel();
            add(currentAppPanel, BorderLayout.CENTER);
            addProbtn.setEnabled(true);
            addPersonalbtn.setEnabled(false);
            revalidate();
            repaint();
        });

        JButton personalButton = UIComponents.createButton(textPersonal);
        UtilityFunctions.hoverAction(personalButton);
        personalButton.addActionListener(e -> {
            isPersonal = true;
            remove(currentAppPanel);
            currentAppPanel = createMainPanel();
            add(currentAppPanel, BorderLayout.CENTER);
            addProbtn.setEnabled(false);
            addPersonalbtn.setEnabled(true);
            revalidate();
            repaint();
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
        planField.add(UIComponents.createLabel("Add Tokens"), c);
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
        addPersonalbtn.addActionListener(e -> {
            try {
                if (promptQuota == null) {
                    JOptionPane.showMessageDialog(this, "Please select Personal plan first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String txt_model = modelName.getText().trim();
                int txt_parameter = Integer.parseInt(params.getText().trim());
                int txt_price = Integer.parseInt(price.getText().trim());
                int txt_contextWindow = Integer.parseInt(contextWin.getText().trim());
                int txt_promptQuota = Integer.parseInt(promptQuota.getText().trim());

                AIModel personal = new PersonalPlan(txt_promptQuota, txt_model, txt_price, txt_parameter, txt_contextWindow);
                aiData.add(personal);
                JOptionPane.showMessageDialog(this, "Personal Plan Added Successfully");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        addPersonalbtn.setEnabled(isPersonal);
        buttonLayout.add(addPersonalbtn, c);

        c.gridx = 1;
        addProbtn = UIComponents.createButton("Add Pro Plan");
        UtilityFunctions.hoverAction(addProbtn);
        addProbtn.addActionListener(e -> {
            try {
                if (teamMemberSlot == null) {
                    JOptionPane.showMessageDialog(this, "Please select Pro plan first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String txt_model = modelName.getText().trim();
                int txt_parameter = Integer.parseInt(params.getText().trim());
                int txt_price = Integer.parseInt(price.getText().trim());
                int txt_contextWindow = Integer.parseInt(contextWin.getText().trim());
                int txt_teamMemberSlot = Integer.parseInt(teamMemberSlot.getText().trim());

                AIModel pro = new ProPlan(txt_teamMemberSlot, txt_model, txt_price, txt_parameter, txt_contextWindow);
                aiData.add(pro);
                JOptionPane.showMessageDialog(this, "Pro Plan Added Successfully");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        addProbtn.setEnabled(!isPersonal);
        buttonLayout.add(addProbtn, c);

        c.gridx = 2;
        JButton displayAll = UIComponents.createButton("Display All");
        UtilityFunctions.hoverAction(displayAll);
        displayAll.addActionListener(e -> {
            if (aiData.isEmpty()) {
                output.setText("No plans added yet.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < aiData.size(); i++) {
                sb.append(i).append(aiData.get(i).display()).append("\n");
            }
            output.setText(sb.toString());
        });
        buttonLayout.add(displayAll, c);

        c.gridx = 3;
        JButton clear = UIComponents.createButton("Clear");
        clear.addActionListener(UtilityFunctions.clearFunction(this));
        UtilityFunctions.hoverAction(clear);
        buttonLayout.add(clear, c);

        c.gridx = 4;
        JButton promptEnter = UIComponents.createButton("Send Prompt");
        UtilityFunctions.hoverAction(promptEnter);
        promptEnter.addActionListener(e -> {
            String promptText = prompt.getText().trim();
            if (promptText.isEmpty() || promptText.equals("Enter Prompt")) {
                JOptionPane.showMessageDialog(this, "Please enter a prompt.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            output.setText("Prompt sent: " + promptText);
        });
        buttonLayout.add(promptEnter, c);

        c.gridx = 0; c.gridy = 1;
        JButton addTeamMember = UIComponents.createButton("Add Team Member");
        UtilityFunctions.hoverAction(addTeamMember);
        addTeamMember.addActionListener(e -> {
            if (!isPersonal && memberName != null) {
                String name = memberName.getText().trim();
                if (name.isEmpty() || name.equals("Add Team Member Name")) {
                    JOptionPane.showMessageDialog(this, "Please enter a member name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (int i = aiData.size() - 1; i >= 0; i--) {
                    if (aiData.get(i) instanceof ProPlan) {
                        ((ProPlan) aiData.get(i)).addTeamMember(name);
                        JOptionPane.showMessageDialog(this, "Member \"" + name + "\" added.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "No Pro Plan found to add a member to.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Switch to Pro plan to add team members.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLayout.add(addTeamMember, c);

        c.gridx = 1;
        JButton remTeamMember = UIComponents.createButton("Remove Team Member");
        UtilityFunctions.hoverAction(remTeamMember);
        remTeamMember.addActionListener(e -> {
            if (!isPersonal && remMember != null) {
                String name = remMember.getText().trim();
                if (name.isEmpty() || name.equals("Remove a team member")) {
                    JOptionPane.showMessageDialog(this, "Please enter a member name to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (int i = aiData.size() - 1; i >= 0; i--) {
                    if (aiData.get(i) instanceof ProPlan) {
                        boolean removed = Boolean.parseBoolean(((ProPlan) aiData.get(i)).removeTeamMember(name));
                        if (removed) {
                            JOptionPane.showMessageDialog(this, "Member \"" + name + "\" removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Member \"" + name + "\" not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "No Pro Plan found.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Switch to Pro plan to remove team members.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLayout.add(remTeamMember, c);

        c.gridx = 2;
        JButton checkPlan = UIComponents.createButton("Check Plan");
        UtilityFunctions.hoverAction(checkPlan);
        checkPlan.addActionListener(e -> {
            if (aiData.isEmpty()) {
                output.setText("No plans added yet.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (AIModel aiDatum : aiData) {
                String type = aiDatum instanceof ProPlan ? "Pro" : "Personal";
                sb.append("Type: ").append(type).append(aiDatum.display()).append("\n");
            }
            output.setText(sb.toString());
        });
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
        output = new JTextArea(12, 55);
        output.setBackground(Styling.SURFACE);
        output.setForeground(Styling.PRIMARY_TEXT);
        output.setBorder(null);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    public JPanel createIndexingPanel() {
        JPanel idxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        idxPanel.setPreferredSize(new Dimension(120, 0));
        idxPanel.setBackground(Styling.SURFACE);
        idxPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        idxPanel.add(UIComponents.createLabel("Index"));

        JTextField idx = UIComponents.inputField(5);
        UtilityFunctions.addPlaceholder(idx, "-1");
        idxPanel.add(idx);

        JButton search = UIComponents.createButton("🔎 Search");
        search.addActionListener(e -> {
            String idxText = idx.getText().trim();

            try {
                UtilityFunctions.validateInput(idxText);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid index: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int i = Integer.parseInt(idxText);

                UtilityFunctions.validateInput(i);

                if (i < 0 || i >= aiData.size()) {
                    JOptionPane.showMessageDialog(this, "Index " + i + " is out of range. Valid range: 0 to " + (aiData.size() - 1), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                output.setText(aiData.get(i).display());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer index.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        idxPanel.add(search);
        return idxPanel;
    }
}