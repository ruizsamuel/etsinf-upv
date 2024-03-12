package practica2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PPhilo extends JPanel implements ActionListener {
    private final DefaultListModel<State> states;
    private final JList<State> text;
    private final Box box;
    private static int delay;
    private static final int I = 0; // Delay in ms
    JRadioButton[] typeRadioButtons;

    void simulate(String s) {
        final int cycles=4;
        int i, type=Integer.parseInt(s.substring(5)); System.out.println(type+"");
        StateManager sman= new StateManager(text);
        Thread[] philo=new Philo[5];
        Table t;
        switch (type) {
            case 0 -> {
                t = new RegularTable(sman);
                for (i = 0; i < 5; i++) philo[i] = new Philo(i, cycles, delay, t);
            }
            case 1 -> {
                System.out.println("Asymmetry (last/but last).");
                t = new RegularTable(sman);
                for (i = 0; i < 4; i++) philo[i] = new Philo(i, cycles, delay, t);
                philo[4] = new LefthandedPhilo(4, cycles, delay, t);
            }
            case 2 -> {
                System.out.println("Asymmetry (even/odd).");
                t = new RegularTable(sman);
                for (i = 0; i < 5; i++)
                    philo[i] = (i % 2 == 0) ? new Philo(i, cycles, delay, t) : new LefthandedPhilo(i, cycles, delay, t);
            }
            case 3 -> {
                System.out.println("Both or None.");
                t = new BothOrNoneTable(sman);
                for (i = 0; i < 5; i++) philo[i] = new BothOrNonePhilo(i, cycles, delay, t);
            }
            case 4 -> {
                System.out.println("Limited capacity Dining Room.- TO BE IMPLEMENTED");
                t = new LimitedTable(sman);
                for (i = 0; i < 5; i++) philo[i] = new LimitedPhilo(i, cycles, delay, t);
            }
        }
        for (i=0; i<5; i++) philo[i].start();
        
        // Wait until the end of the simulation
        sman.await();
        System.out.println(sman.deadlock()?" DEADLOCK":"   OK");
        
    	// If deadlocked, interrupt all the philosophers
        if (sman.deadlock())
        	for (i=0; i<5; i++) philo[i].interrupt();
        
        // Wait for the philosophers to finish
        try {
            for (i=0; i<5; i++) philo[i].join();
        }
        catch (Exception e) {e.printStackTrace();}

        SwingUtilities.invokeLater(() -> enableTypeRadioButtons(true));
    }
    
    public PPhilo() {
        typeRadioButtons = new JRadioButton[5];
        typeRadioButtons[0] = new JRadioButton("Deadlock not prevented");
        typeRadioButtons[1] = new JRadioButton("Asymmetry (last/but last)");
        typeRadioButtons[2] = new JRadioButton("Asymmetry (even/odd)");
        typeRadioButtons[3] = new JRadioButton("Both or None");
        typeRadioButtons[4] = new JRadioButton("Limited capacity Dining Room");

        ButtonGroup typeTable= new ButtonGroup();
        for (int j=0; j<typeRadioButtons.length; j++) {
            typeTable.add(typeRadioButtons[j]);
            typeRadioButtons[j].addActionListener(this);
            typeRadioButtons[j].setActionCommand("Table"+j);
        }
        JPanel ptype= new JPanel(new GridLayout(0,1));
        for (JRadioButton typeRadioButton : typeRadioButtons) {
            ptype.add(typeRadioButton);
        }

        states = new DefaultListModel<>();
        text = new JList<>(states);
        box = new Box();
        text.setCellRenderer(new StateRenderer());
        text.setVisibleRowCount(30);
        text.addListSelectionListener(e -> {
            int i=text.getSelectedIndex();
            if (i>=0) states.get(i).draw(box);
        });
        JPanel q=new JPanel(); 
        q.setLayout(new BorderLayout()); q.add(ptype, BorderLayout.PAGE_START); q.add(box, BorderLayout.PAGE_END);
        setLayout(new BorderLayout());
        add(q, BorderLayout.LINE_START);
        add(new JScrollPane(text), BorderLayout.LINE_END);
    }
    
    public void actionPerformed(ActionEvent e) {
        enableTypeRadioButtons(false);
        states.clear();
        final String action = e.getActionCommand();
        new Thread(() -> simulate(action)).start();
    }
    
    private void enableTypeRadioButtons(boolean f) {
        for(int i=0;i<5;i++) { typeRadioButtons[i].setEnabled(f);}
    }
    
    private static int integer(String[] v) {
        int n= (PPhilo.I == v.length)? 10 : Integer.parseInt(v[PPhilo.I]);
        return (n< 1 || n> 10)? 10 :n;
    }

    public static void main(String[] args) {
    	delay = integer(args);
    	SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CSD: 5 Philosophers");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new PPhilo());
            frame.pack(); frame.setVisible(true);
        });
    }
}