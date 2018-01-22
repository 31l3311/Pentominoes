import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * User Interface of the packing problem program
 *
 * @Version 4
 */

public class GUI extends JFrame {

    private JRadioButton parcel = new JRadioButton("Parcels");
    private JRadioButton pentomino = new JRadioButton("Pentomino");
    private JComboBox<String> combo = new JComboBox<>();
    private JButton run = new JButton("Run");
    private JFormattedTextField a, b, c;
    private JPanel inputFields = new JPanel();
    private JPanel algorithmType = new JPanel();
    private JPanel boxType = new JPanel();
    private JPanel main = new JPanel();

    public GUI() {
        initUI();
    }

    /**
     * Handles the initialization of the User Interface
     */
    private void initUI() {
        boxTypeHandler();
        inputFieldHandler();
        algorithmHandler();
        panelMerger();
        buttonPressAction();

        //Setting up the JFrame
        setTitle("3D Knapsack Problem");
        add(main);
        getContentPane().setSize(500,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * First panel of settings panel:
     * Type of parcel that will be used will be decided here by radio buttons (Either box or Pentomino)
     */
    private void boxTypeHandler() {
        boxType.setLayout(new BorderLayout());
        boxType.add(new JLabel("Box Type:"), BorderLayout.PAGE_START);
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(parcel);
        radioButtons.add(pentomino);
        boxType.add(parcel, BorderLayout.CENTER);
        boxType.add(pentomino, BorderLayout.SOUTH);
        boxType.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
    }

    /**
     * Second panel of settings panel
     * Amount of parcels used will be chosen here
     */
    private void inputFieldHandler() {
        inputFields.setLayout(new GridLayout(3, 2));
        //Labels
        JLabel textfield1 = new JLabel("Parcel 1");
        JLabel textfield2 = new JLabel("Parcel 2");
        JLabel textfield3 = new JLabel("Parcel 3");
;
        //TextFields that only accept Integers
        a = new JFormattedTextField(formatter());
        b = new JFormattedTextField(formatter());
        c = new JFormattedTextField(formatter());

        //set bases value to 0
        a.setValue(0);
        b.setValue(0);
        c.setValue(0);

        //defines textfield width
        a.setColumns(6);
        inputFields.add(textfield1);
        inputFields.add(a);
        inputFields.add(textfield2);
        inputFields.add(b);
        inputFields.add(textfield3);
        inputFields.add(c);
        inputFields.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
    }

    /**
     * Final panel of settings panel:
     * Type of algorithm will be chosen here through a combo box
     */

    private void algorithmHandler() {
        algorithmType.setLayout(new BorderLayout());
        algorithmType.add(new JLabel("Algorithm Type:"), BorderLayout.PAGE_START);
        combo.addItem("Greedy");
        combo.addItem("Random");
        combo.addItem("BruteForce");
        algorithmType.add(combo, BorderLayout.CENTER);
    }

    /**
     * Merges all the panels into one panel, so the final pannel can be added to the JFrame
     */

    private void panelMerger() {
        //Settings Panel Merge
        JPanel settings = new JPanel();
        settings.add(boxType);
        settings.add(inputFields);
        settings.add(algorithmType);
        settings.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        settings.setPreferredSize(new Dimension(500, 80));

        //Merges all Panels
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(settings);
        main.add(run);
    }


    /**
     * Creates the "only Integers are accepted" handler used by the formatted Text fields in the inputfields panel
     * @return formatter that only accepts Integers
     */
    private NumberFormatter formatter () {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        return formatter;
    }

    /**
     * Run program when button is pressed
     */
    private void buttonPressAction() {
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //run Algorithm
                Run run = new Run();
                String select = combo.getSelectedItem().toString();
                run.setBoxAmounts((Integer)a.getValue(), (Integer)b.getValue(), (Integer)c.getValue());
                if (parcel.isSelected()) {
                    if (select.equalsIgnoreCase("Greedy")) {
                        //run greedy
                        run.greedyB();
                        //System.out.print(run.amountOfA + "  " + run.amountOfB + "  " + run.amountOfC + "  ");
                    } else if (select.equalsIgnoreCase("Random")) {
                        //run Random
                        run.randombB();
                        //System.out.print(run.amountOfA + "  " + run.amountOfB + "  " + run.amountOfC + "  ");
                    } else if (select.equalsIgnoreCase("BruteForce")) {
                        //run BruteForce
                        //System.out.print(run.amountOfA + "  " + run.amountOfB + "  " + run.amountOfC + "  ");
                    }
                } else if (pentomino.isSelected()) {
                    if (select.equalsIgnoreCase("Greedy")) {
                        //run greedy
                        run.greedyP();
                    } else if (select.equalsIgnoreCase("Random")) {
                        //run Random
                        run.randomP();
                    } else if (select.equalsIgnoreCase("BruteForce")) {
                        //run BruteForce
                        System.out.println("c");
                        System.out.println(select);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.pack();
        gui.setVisible(true);
    }

}
