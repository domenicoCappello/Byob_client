package byob_v1;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The class creates and manages anything who's connected to the Graphic User 
 * Interface: components, their visibility, their aspects and their being 
 * enabled/disabled.
 * @author Cappello - Nazio
 */
public final class GUI extends javax.swing.JFrame{
    //Configuration file's Path 
    String fileConfPath;
    // List of user's params JTextField 
    List<JTextField> textParam = new ArrayList<>();
    // List of labels in which help to users can be provided
    List<JLabel> configurationLabel = new ArrayList<>();
    // Map of available sleep condition
    HashMap<String, String> conditionMap = new HashMap<>();
    // Sleep conditions
    String[] conditionLabel = {
    "Even days",
    "Odd days",
    "Seven days",
    "AM",
    "PM",
    "24 hours"
    };
    // Mapping of the Sleep conditions
    String[] conditionLetter = {
    "E",
    "O",
    "D",
    "A",
    "P",
    "H"
    };
    // Help strings to show to user
    String[] helpText = {
    // TODO
    "Write each URL on a different row. First URL is C&C's.",
    "Contact Time",
    "# contacts",
    "Sleep condition",
    "User-Agent",
    "ProxyHostName",
    "ProxyPort"
    };
    // Variable for sleep conditions
    String sleepCondition = "";
    // Help aspect of the labels' border
    Border helpBorder = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red);
    
    /**
     * Constructor. 
     * It creates new form GUI, it initiates components and creates Lists to
     * represent the set of components.
     */
    public GUI() {
        initComponents();
        addConditions();        
        configurationLabel.add(jLabel1);
        configurationLabel.add(jLabel2);
        configurationLabel.add(jLabel3);
        configurationLabel.add(jLabel4);
        configurationLabel.add(jLabel5);
        configurationLabel.add(jLabel6);
        configurationLabel.add(jLabel7);
        textParam.add(jFormattedTextField2);
        textParam.add(jFormattedTextField3);
        textParam.add(jFormattedTextField4);
        textParam.add(jFormattedTextField5);
        textParam.add(jFormattedTextField6);
        textParam.add(jFormattedTextField7);
        textParam.add(jFormattedTextField8);
        
        //jTextArea1.setEditable(false);
        //jTextArea1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        //jTextArea1.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
        
        jTextArea1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (jTextArea1.getText().length() >= 7){
                    jButton5.setEnabled(true);
                    jButton6.setEnabled(true);
                }
                else {
                    jButton5.setEnabled(false);
                    jButton6.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (jTextArea1.getText().length() >= 7){
                    jButton5.setEnabled(true);
                    jButton6.setEnabled(true);
                }
                else {
                    jButton5.setEnabled(false);
                    jButton6.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (jTextArea1.getText().length() >= 7){
                    jButton5.setEnabled(true);
                    jButton6.setEnabled(true);
                }
                else {
                    jButton5.setEnabled(false);
                    jButton6.setEnabled(true);
                }
            }
        });
        
        jFormattedTextField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                String content = jFormattedTextField2.getText();
                if (!content.equals("")) 
                    jButton4.setEnabled(true);
                else 
                    jButton4.setEnabled(false);
            }
        });
        
        ButtonGroup mainGroup = new ButtonGroup();
        mainGroup.add(jRadioButton1);
        mainGroup.add(jRadioButton2);
    
        ButtonGroup contactTimeGroup = new ButtonGroup();
        contactTimeGroup.add(jRadioButton3);
        contactTimeGroup.add(jRadioButton4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Open conf.txt");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel1.setText("URL");
        jLabel1.setEnabled(false);
        jLabel1.setOpaque(true);

        jLabel2.setText("Contact Time");
        jLabel2.setEnabled(false);
        jLabel2.setOpaque(true);

        jLabel3.setText("# contacts");
        jLabel3.setEnabled(false);
        jLabel3.setOpaque(true);

        jLabel4.setText("Sleep Condition");
        jLabel4.setEnabled(false);
        jLabel4.setOpaque(true);

        jLabel5.setText("User-Agent");
        jLabel5.setEnabled(false);
        jLabel5.setOpaque(true);

        jLabel6.setText("Proxy");
        jLabel6.setEnabled(false);
        jLabel6.setOpaque(true);

        jToggleButton1.setText("Enable Help on parameters");
        jToggleButton1.setEnabled(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Insert values");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Push ↓");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Open");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Erase ALL");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("Save file as .txt");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jFormattedTextField2.setEnabled(false);

        jFormattedTextField3.setEnabled(false);

        jRadioButton3.setText("Interval");
        jRadioButton3.setEnabled(false);

        jRadioButton4.setText("Fixed");
        jRadioButton4.setEnabled(false);

        jFormattedTextField4.setEnabled(false);

        jFormattedTextField5.setEnabled(false);
        jFormattedTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField5ActionPerformed(evt);
            }
        });

        jFormattedTextField6.setEnabled(false);

        jFormattedTextField7.setEnabled(false);

        jFormattedTextField8.setEnabled(false);

        jLabel7.setText(":");
        jLabel7.setEnabled(false);
        jLabel7.setOpaque(true);

        jButton5.setText("Pull ↑");
        jButton5.setEnabled(false);
        jButton5.setPreferredSize(new java.awt.Dimension(65, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(81, 81, 81)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jFormattedTextField7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jFormattedTextField3)
                                            .addGap(29, 29, 29)
                                            .addComponent(jRadioButton4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButton3))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jToggleButton1))
                                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 6, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jToggleButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(!evt.getActionCommand().equals("on"))
            disableEnable(true);
        else
            disableEnable(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            File file = new File(selectedFile.getName());
            fileConfPath = file.getAbsolutePath();
            jFormattedTextField1.setText(fileConfPath);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        JFrame parentFrame = new JFrame();
        boolean flag = true;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        File fileToSave = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to save");

        while(flag)
        {
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fileChooser.getSelectedFile();
                int dialogResult = JOptionPane.showConfirmDialog (null, "Is the name correct?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    flag = false;
                    if (!fileToSave.getName().contains(".txt"))
                        fileToSave = new File(fileToSave.toString() + ".txt");
                }
                else
                    flag = true;
            }
        }
        System.out.println("Extracting parameters.");
        try {
            Parser.writeConfigurationFile(fileToSave, extractDataFromTextArea(), textParam.size());
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearFields();
        jButton4sdf.setEnabled(false);
        jButton5.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        final JFrame parent = new JFrame();
        final JPanel top = new JPanel();
        final JPanel top1 = new JPanel();
        final JPanel bottom = new JPanel();
        JButton okButton = new JButton("Ok");
        top.setLayout(new GridLayout(1,0));
        top1.setLayout(new GridLayout(1,0));
        bottom.setLayout(new GridLayout(1,0));
        top.setBorder(new TitledBorder(BorderFactory.createTitledBorder("Days")));
        top1.setBorder(new TitledBorder(BorderFactory.createTitledBorder("Hours")));
        bottom.add(okButton);
        parent.setLayout(new GridLayout(0,1));
        //List<ButtonGroup> group = new ArrayList<>();
        final ButtonGroup group = new ButtonGroup();
        final ButtonGroup group1 = new ButtonGroup();
        final List<JRadioButton> sxRadio = new ArrayList<>();
        final List<JRadioButton> dxRadio = new ArrayList<>();
        int numberOfChoice = 2;
        int maxChoice = (conditionLabel.length)/numberOfChoice;
        for(int i=0; i < conditionLabel.length; i++){
            int flag = (i < maxChoice ? 0 : 1);
            switch(flag)    
            {
                case 0:
                    sxRadio.add(new JRadioButton(conditionLabel[i]));
                    sxRadio.get(i).setSelected(true);
                    group.add(sxRadio.get(i));
                    top.add(sxRadio.get(i));
                    break;
                case 1:
                    dxRadio.add(new JRadioButton(conditionLabel[i]));
                    dxRadio.get(i%3).isSelected();
                    group1.add(dxRadio.get(i%3));
                    top1.add(dxRadio.get(i%3));
                    break;
            }
        }
        parent.add(top);
        parent.add(top1);
        parent.add(bottom, BorderLayout.CENTER);
        parent.pack();
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - parent.getWidth()) / 2;
        final int y = (screenSize.height - parent.getHeight()) / 2;
                 
        final String[] choice = new String[maxChoice];
        for(int i=0; i<maxChoice; i++)
            choice[i] = "";
        parent.setLocation(x, y);
        parent.setVisible(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = conditionMap.get(getSelectedButtonText(group));
                choice[1] = conditionMap.get(getSelectedButtonText(group1));
                sleepCondition = String.join("; ", choice);
                jFormattedTextField5.setText(sleepCondition);
            }

        } );
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String[] params = extractData();
        writeTextArea(params);
        for(int i=0; i < textParam.size(); i++)
            textParam.get(i).setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(!evt.getActionCommand().equals("on"))
            disableEnable(false);
        else
            disableEnable(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(jToggleButton1.isSelected())
            jToggleButton1.setText("Disable Help");
        else
            jToggleButton1.setText("Enable Help");
        
        helpLegend();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jFormattedTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField5ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String[] params = jTextArea1.getText().split(";\n");
        clearFields();
        for(int i=0; i < textParam.size(); i++)
            textParam.get(i).setText(params[i]);
        List textArea = Arrays.asList(params).subList(textParam.size(), params.length);
        for(int i=0; i < textParam.size(); i++)
            textParam.get(i).setText(params[i]);
        for(int i=0; i < textArea.size(); i++)
            jTextArea1.append(textArea.get(i).toString()+";\n");
        
//        jTextArea1.append(textArea.toString());
//        jTextArea1.setText(jTextArea1.getText().replace(",", ";\n"));
        
    }//GEN-LAST:event_jButton5ActionPerformed

    public void writeTextArea(String[] params){
        StringBuilder sb = new StringBuilder();
        for (String param : params) {
            sb.append(param).append("\n");
        }
        jTextArea1.append(sb.toString());
    }
    
    public void clearFields(){
        for(int i=0; i < textParam.size(); i++)
            textParam.get(i).setText("");
        jTextArea1.setText("");
    }
    
    /**
     * Auto-generated NetBeans' main for the GUI.
     * @param args command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    
      public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    

    
    /**
     * The method sets/removes help's border and tooltip from each jFormattedTextField.
     */
    public void helpLegend() {
            for(int i=0; i < textParam.size(); i++) {
                textParam.get(i).setBorder(jToggleButton1.isSelected() ? helpBorder : null);
                textParam.get(i).setToolTipText(jToggleButton1.isSelected() ? helpText[i]: null);
            }
    }
    
    /**
     * The method enables/disables components of the GUI.
     * @param flag enable/disable element
     */
    public void disableEnable(boolean flag) {
        jButton1.setEnabled(flag);
        jFormattedTextField1.setEnabled(flag);
        jButton2.setEnabled(!flag);
        jButton3.setEnabled(!flag);
        jTextArea1.setEnabled(!flag);
        jToggleButton1.setEnabled(!flag);
        jRadioButton3.setEnabled(!flag);
        jRadioButton3.setSelected(true);
        jRadioButton4.setEnabled(!flag);
        
        for(int i=0; i < textParam.size(); i++)
        {
            textParam.get(i).setEnabled(!flag);
            configurationLabel.get(i).setEnabled(!flag);    
        }
    }
    
    /**
     * The method enables/disables components of the GUI.
     */
    public void addConditions() {
         for(int i = 0; i < conditionLabel.length; i++) {
            conditionMap.put(conditionLabel[i],conditionLetter[i]);
        }     
    }
    
    /**
     * The function returns the data of the jFormattedTextFields.
     * @return Array of the parameters
     */
    private String[] extractData() {
//        String[] url = jTextArea2.getText().replace(" ","").split("\n");    //Perche' array di url?
//        String urlParam = "*" +
//                String.join(";", Arrays.asList(url)) +
//                "*";
        String[] params = new String[textParam.size()];
//        params[0] = urlParam;
        for(int i = 0; i < textParam.size() ; i++) {
            params[i] = textParam.get(i).getText().equals("") ?
                    "-;" : textParam.get(i).getText()+";";          //Nuova convenzione?
        } 
        return params;
    }
    
    private String[][] extractDataFromTextArea() {
        String[] textArea = jTextArea1.getText().split(";\n");
        int rows = textArea.length / textParam.size();
        int columns = textParam.size();
        String[][] params = new String[rows][columns];
        for(int i = 0; i < rows ; i++) {
            for(int j = 0; j < columns ; j++) 
                params[i][j] = textArea[(i*columns)+j];
        }
        return params;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
