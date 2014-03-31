package jlayer_experiments;

import java.text.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

public class ValidationTest
{
   private static void createAndShowGui()
   {
      Box content = Box.createVerticalBox();
      JPanel row;

      content.add(Box.createGlue());
      row = createRow("Enter an integer:", 
                        NumberFormat.getIntegerInstance(), 
                        "Enter a whole number only (negatives allowed)");
      content.add(row);

      content.add(Box.createGlue());
      row = createRow("Enter a dollar amount:", 
                        NumberFormat.getCurrencyInstance(),
                        "Format: blah blah blah");
      content.add(row);

      content.add(Box.createGlue());
      row = createRow("Enter a date:",
                        DateFormat.getDateInstance(),
                        "Format: Date blah blah blah");
      content.add(row);

      content.add(Box.createGlue());
      row = createShortRow("Enter a number:", 
                              NumberFormat.getInstance(),
                              "You shouldn't be seeing this");
      content.add(row);

      JFrame frame = new JFrame("Validation UI Test");
      frame.setContentPane(content);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   private static JPanel createRow(String labelText, Format format, String tooltip)
   {
      LayerUI<JFormattedTextField> layerUI =
         new ValidationLayerUI(tooltip);
      Dimension labelSize = new Dimension(200, 20);
      JPanel row = new JPanel();
      JLabel label = new JLabel(labelText, JLabel.LEADING);
      label.setPreferredSize(labelSize);

      JFormattedTextField field = new JFormattedTextField(format);
      field.setColumns(16);
      field.setFocusLostBehavior(JFormattedTextField.PERSIST);
      
      row.add(label);
      row.add(new JLayer<JFormattedTextField>(field, layerUI));

      return row;
   }
   
   private static JPanel createShortRow(String labelText, Format format, String tooltip)
   {
      LayerUI<JFormattedTextField> layerUI =
         new ValidationLayerUI(tooltip);

      Dimension labelSize = new Dimension(200, 20);
      JPanel row = new JPanel();
      JLabel label = new JLabel(labelText, JLabel.LEADING);
      label.setPreferredSize(labelSize);

      JFormattedTextField field = new JFormattedTextField(format);
      field.setPreferredSize(new Dimension(7, 20));
      field.setFocusLostBehavior(JFormattedTextField.PERSIST);
      
      row.add(label);
      row.add(new JLayer<JFormattedTextField>(field, layerUI));

      return row;
   }

   public static void main(String[] args) throws Exception
   {
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            createAndShowGui();
         }
      });
   }
}
