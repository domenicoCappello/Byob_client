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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
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
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DefaultFormatter;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.text.NumberFormatter;

/**
 * Class creates and manages anything who's connected to the Graphic User 
 * Interface: components, their visibility, their aspects and their being 
 * enabled/disabled.
 * @author Cappello, Nazio
 */
public final class GUI extends javax.swing.JFrame {
       
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
    "Never",
    "AM",
    "PM",
    "Never" };
    
    // Mapping of the Sleep conditions
    String[] conditionLetter = {
    "E",
    "O",
    "",
    "A",
    "P",
    "" };
    
    // Help strings to show to user
    String[] helpText = {
    "<html>URL to contact <BR>i.e. http://www.facebook.com</html>",
    "<html>Specify for how long the URL must be contacted</html>",
    "<html>Specify for how long the URL must be contacted</html>",
    "<html>Number of times the URL must be contacted</html>",
    "<html>Define when the bot should operate and when it should sleep</html>",
    "<html>Personalize User-Agent <BR>i.e. Mozilla/3.0 (OS/2; U)</html>",
    "<html>Define the IP of the proxy <BR>i.e 10.0.0.1</html>",
    "<html>Define the port of the proxy in range [1025, 65535]<BR>i.e 80</html>" };
    
    // Default Values
    String[] defaultValue = {
    "*http://www.google.it",
    "6000",
    "10000",
    "100",
    " ",
    "Mozilla/3.0",
    " ",
    " " };
    
    // Variable for sleep conditions
    String sleepCondition = "";
    
    // Help aspect of the labels' border
    Border helpBorder = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red);
    
    /**
     * Constructor. 
     * It creates new form GUI, it initiates components and creates lists to
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
        configurationLabel.add(jLabel8);
        textParam.add(jFormattedTextField2);
        textParam.add(jFormattedTextField3);
        textParam.add(jFormattedTextField4);
        textParam.add(jFormattedTextField5);
        textParam.add(jFormattedTextField6);
        textParam.add(jFormattedTextField7);
        textParam.add(jFormattedTextField8);
        textParam.add(jFormattedTextField9);
        
        NumberFormat a = NumberFormat.getNumberInstance();
        a.setGroupingUsed(false);
        NumberFormatter b = new NumberFormatter(a);
        //b.setAllowsInvalid(false);
        //b.setCommitsOnValidEdit(true);
        DefaultFormatterFactory c = new DefaultFormatterFactory(b);
        jFormattedTextField3.setFormatterFactory(c);
        jFormattedTextField4.setFormatterFactory(c);
        jFormattedTextField5.setFormatterFactory(c);
        jFormattedTextField9.setFormatterFactory(c);
        
//        jFormattedTextField4.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent event) {
//                String min = jFormattedTextField3.getText().equals("") ?
//                        "0" : jFormattedTextField3.getText();
//                jFormattedTextField3.setText(min);
//                String max = jFormattedTextField4.getText();
//                if (Integer.parseInt(min) < Integer.parseInt(max))
//                    jFormattedTextField4.setText("");
//            }
//        });
        
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
        
        //////////////
//        String s = Tools.getBrowsers();
//        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * Method's called from within the constructor to initialize the form.
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
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        Pattern p = Pattern.compile( "^(?:" + _255 + "\\.){3}" + _255 + "$");
        RegexFormatter ipFormatter = new RegexFormatter(p);
        jFormattedTextField8 = new javax.swing.JFormattedTextField(ipFormatter);
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton6 = new javax.swing.JButton();
        jFormattedTextField4 = new javax.swing.JFormattedTextField(NumberFormat.getNumberInstance());
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("URL");
        jLabel1.setEnabled(false);
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("Contact Time (ms)");
        jLabel2.setEnabled(false);
        jLabel2.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("# contacts");
        jLabel3.setEnabled(false);
        jLabel3.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("Sleep Condition (opt.)");
        jLabel4.setEnabled(false);
        jLabel4.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("User-Agent (opt.)");
        jLabel5.setEnabled(false);
        jLabel5.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("Proxy (opt.)");
        jLabel6.setEnabled(false);
        jLabel6.setOpaque(true);

        jToggleButton1.setText("Enable Help");
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

        jFormattedTextField2.setEnabled(false);

        jFormattedTextField3.setEnabled(false);

        jRadioButton3.setText("Interval");
        jRadioButton3.setEnabled(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Fixed");
        jRadioButton4.setEnabled(false);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jFormattedTextField5.setEnabled(false);

        jFormattedTextField6.setEnabled(false);

        jFormattedTextField7.setEnabled(false);

        jFormattedTextField8.setEnabled(false);

        jFormattedTextField9.setEnabled(false);

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

        jButton6.setText("Save file as .txt");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jFormattedTextField4.setEnabled(false);
        jFormattedTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText("-");
        jLabel8.setEnabled(false);
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setOpaque(true);

        jButton7.setText("Launch");
        jButton7.setEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFormattedTextField8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jRadioButton3))
                                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jToggleButton1))
                                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(0, 1, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            jButton7.setEnabled(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        JFrame parentFrame = new JFrame();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        boolean flag = true;
        boolean exit = false;
        File fileToSave = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to save");

        while(!exit){
            
            /**Check selected proxy syntax*/
//            String ip = "";
//            int port = 0;
//            if(!Tools.checkIPv4String(ip) || !Tools.checkPort(port)){
//                JOptionPane.showMessageDialog (null, "Invalid Proxy format (IP : port)",
//                        "Warning", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            if (userSelection == JFileChooser.CANCEL_OPTION)
                exit = true;
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fileChooser.getSelectedFile();
                int dialogResult = JOptionPane.showConfirmDialog (null, "Is the name correct?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    flag = false;
                    exit = true;
                    if (!fileToSave.getName().contains(".txt"))
                        fileToSave = new File((fileToSave.toString() + ".txt"));
                    /**Update fileConfPath*/
                    fileConfPath = fileToSave.getAbsolutePath();
                    jButton7.setEnabled(true);
                }
                else
                {
                    flag = true;
                    exit = false;
                }
            }
             
        }
        if(!flag)
        {
            System.out.println("Extracting parameters.");
            try {
                Parser.writeConfigurationFile(fileToSave, jTextArea1.getText().split("\n", -1), textParam.size());
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearFields(true);
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
                    dxRadio.get(i%3).setSelected(true);
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
                sleepCondition = String.join("", choice);
                jFormattedTextField6.setText(sleepCondition);
                jFormattedTextField6.setEnabled(true);
                parent.setVisible(false);   
            }

        } );
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String[] params = extractData(false);
        List<String> warning = Tools.warningMessage(params);
        if(warning!=null)
        {
            final JFrame parent = new JFrame();
            int choice = JOptionPane.showConfirmDialog(parent, String.join("", warning), "Error", JOptionPane.YES_NO_OPTION);
            parent.pack();
            parent.setVisible(true);
            if(choice == JOptionPane.YES_OPTION){
                writeTextArea(extractData(true));
                clearFields(false);
                parent.dispose();
            }
            else {
                parent.dispose();
                JOptionPane.showMessageDialog(null, "Parameters must be modified manually.");
            }
        } 
        else {
            writeTextArea(params);
            clearFields(false);
        }
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String[] params = jTextArea1.getText().split("\n");
        clearFields(true);
        if(params[0].contains("$")) {
            String[] proxy;
            proxy = params[0].replace("$", "").split(":");
            jFormattedTextField8.setText(proxy[0]);
            jFormattedTextField9.setText(proxy[1]);
            params = Arrays.copyOfRange(params, 1, params.length);
        }
        for(int i=0; i < textParam.size()-2; i++) {
            if(i==0)
                params[i] = params[i].replace("*", ""); 
            if(i==2) {
                if(params[i].equals(params[i-1])) {
                    params[i] = "";
                    jRadioButton4.setSelected(true);
                    jFormattedTextField4.setEnabled(false);
                    
                } else {
                    jRadioButton3.setSelected(true);
                    jFormattedTextField4.setEnabled(true);
                }
            }
            textParam.get(i).setText(params[i]);
        }
        List textArea = Arrays.asList(params).subList(textParam.size()-2, params.length);

        for(int i=0; i < textArea.size(); i++)
            jTextArea1.append(textArea.get(i).toString()+"\n");
        
        if(!jFormattedTextField4.isEnabled())
            jFormattedTextField4.setText("");      
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(!evt.getActionCommand().equals("on")) {
            jFormattedTextField4.setEnabled(true);
            if(jTextArea1.getText().length() != 0)
                if(!Parser.checkNumber(jFormattedTextField3.getText()))
                    defaultValue[2] = "10000";
                else
                    defaultValue[2] = ""+(Integer.parseInt(jFormattedTextField3.getText())+10);     
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if(!evt.getActionCommand().equals("on")) {
            jFormattedTextField4.setEnabled(false);
            jFormattedTextField4.setText("");
            defaultValue[2] = defaultValue[1];
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        Parser parser = new Parser(fileConfPath);
        try {
            ArrayList <URLDetails> taskList = parser.readConfigurationFile();
            Tools.schedule(taskList);
        } catch (IOException ex) {
            ByobSingleton.myLogger.severe("Parser I/O exception");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jFormattedTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4ActionPerformed
    
    public void writeTextArea(String[] params){
        StringBuilder sb = new StringBuilder();
        if(!jTextArea1.getText().contains(("$")) && !params[params.length-2].equals(" ") && !params[params.length-1].equals(" "))
            sb.append("$").
                append(params[params.length-2]).
                append(":").
                append(params[params.length-1]).
                append("\n");
        for(int i=0; i < params.length-2; i++){ 
            sb.append(params[i]).append("\n");
        }
        jTextArea1.append(sb.toString());
    }
    
    /**
     *  Method clears the user's input fields.
     *  @param area if true clears jTextArea, false otherwise.
     */
    public void clearFields(boolean area){
        for(int i=0; i < textParam.size(); i++)
            textParam.get(i).setText("");
        jFormattedTextField2.setText("http://");
        if(area)
            jTextArea1.setText("");
    }
    
//    /**
//     * Auto-generated NetBeans' main for the GUI.
//     * @param args command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | 
//                IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new GUI().setVisible(true);
//            }
//        });
//    }
    
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
     *  The method sets/removes help's border and tooltip from each jFormattedTextField.
     */
    public void helpLegend() {
            for(int i=0; i < textParam.size(); i++) {
                textParam.get(i).setBorder(jToggleButton1.isSelected() ? helpBorder : null);
                textParam.get(i).setToolTipText(jToggleButton1.isSelected() ? helpText[i]: null);
            }
    }
    
    /**
     *  Method enables/disables components of the GUI.
     *  @param flag enable/disable element
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
        
        for(int i=0; i < textParam.size(); i++) {
            textParam.get(i).setEnabled(!flag);
            configurationLabel.get(i).setEnabled(!flag);    
        }
        jFormattedTextField2.setText(flag ? "" : "http://");
        jFormattedTextField6.setEnabled(flag);   
    }
    
    /**
     *  Method enables/disables components of the GUI.
     */
    public void addConditions() {
         for(int i = 0; i < conditionLabel.length; i++)
            conditionMap.put(conditionLabel[i],conditionLetter[i]);    
    }
    
    /**
     *  Function returns the data of the user's input field.
     *  @return Array of the parameters
     */
    private String[] extractData(boolean standard) {
        String[] params = new String[textParam.size()];   
        for(int i = 0; i < textParam.size() ; i++) {
            switch (i) {
                    case 0:
                        if(!textParam.get(i).getText().equals("http://"))
                            params[i] = "*"+textParam.get(i).getText();
                        else
                            params[i] = standard ? defaultValue[i] : textParam.get(i).getText();
                        break;
                        
                    case 2:
                            if(jFormattedTextField4.isEnabled()) {
                                params[i] = standard ? defaultValue[i] : textParam.get(i).getText();
                            } else {
                                params[i] = standard ? defaultValue[i] : textParam.get(i-1).getText();
                            }
                        break;
                        
                    default:
                        params[i] = standard  && textParam.get(i).getText().equals("") ? 
                            defaultValue[i] : textParam.get(i).getText().equals("") ? " " : textParam.get(i).getText();
                        break;
                        
            }
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
    private javax.swing.JButton jButton7;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JFormattedTextField jFormattedTextField9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
    
    /**
     *  Class is used to format proxy's IP address input field
     */
    public final class RegexFormatter extends DefaultFormatter {
        
        //  IP Address Pattern
        private Pattern pattern;
        
        // Matcher on sequence by interpreting the Pattern
        private Matcher matcher;

        /**
         * Constructor.
         */ 
        public RegexFormatter() {
            super();
        }

        /**
         * Constructor.
         * @param pattern String of the pattern to set
         */
        public RegexFormatter(String pattern) throws PatternSyntaxException {
            this();
            setPattern(Pattern.compile(pattern));
        }

        /**
         * Constructor.
         * @param pattern Pattern to set
         */
        public RegexFormatter(Pattern pattern) {
            this();
            setPattern(pattern);
        }

        /**
         *  Method sets Pattern in the RegexFormat object.
         *  @param pattern Pattern to set
         */
        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        /**
         *  Function gets Pattern of the object.
         *  @return Pattern
         */
        public Pattern getPattern() {
            return pattern;
        }

        /**
         *  Method sets the Matcher.
         * @param matcher Matcher on the Pattern
        */
        protected void setMatcher(Matcher matcher) {
            this.matcher = matcher;
        }

        /**
         *  Function gets the Matcher of the object.
         *  @return Matcher on the Pattern
         */
        protected Matcher getMatcher() {
            return matcher;
        }

        /**
         *  Function returns the string value of the object.
         *  @param text Matcher's string text
         *  @return object's string value
         *  @throws ParseException 
         */
        @Override
        public Object stringToValue(String text) throws ParseException {
            Pattern p = getPattern();

            if (p != null) {
                Matcher match = p.matcher(text);

                if (match.matches()) {
                    setMatcher(match);
                    return super.stringToValue(text);
                }
                throw new ParseException("Pattern did not match", 0);
            }
            return text;
        }
    }
}
