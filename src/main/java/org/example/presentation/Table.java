package org.example.presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Table extends JFrame {
    private JPanel panel0;
    private JScrollPane pane;
    private JTable table;

    /**
     * Constructs an instance of the Table class with the specified column names and attribute values.
     *
     * @param columns    an array of column names for the table
     * @param attributes a list of string arrays representing the attribute values for each row
     */
    public Table(String[] columns, List<String[]> attributes) {
        super("GREETINGS ALL");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(730, 440);
        this.panel0 = new JPanel(new GridLayout(1, 1));

        this.table = new JTable(attributes.toArray(new String[0][0]), columns);
        this.pane = new JScrollPane(table);

        panel0.add(pane);
        this.getContentPane().add(panel0);
        this.setVisible(true);
    }
}
