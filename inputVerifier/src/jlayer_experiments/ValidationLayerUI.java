package jlayer_experiments;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import javax.swing.plaf.LayerUI;

/**
 * UI Layer which paints an additional notifier of an invalid edit to
 * a JFormattedTextField.
 *
 * Note: This is inspired by the FieldValidator demo in the Oracle JLayer tutorial
 * ({@see http://docs.oracle.com/javase/tutorial/uiswing/misc/jlayer.html}). This expands
 * on that demo by also adding a tooltip to the notification that gives a reason for
 * the invalid edit.
 */
public class ValidationLayerUI extends LayerUI<JFormattedTextField>
{
   private String tooltipText;
   
   public ValidationLayerUI(String text)
   {
      if(text == null)
         text = "";

      this.tooltipText = text;
   }

   public void setTooltip(String text)
   {
      if(text == null)
         text = "";

      this.tooltipText = text;
   }

   public String getTooltip()
   {
      return this.tooltipText;
   }

   @Override
   public void paint(Graphics g, JComponent c)
   {
      super.paint(g, c);
      
      JFormattedTextField field = (JFormattedTextField)c;
      
      // Paint a red 'X' on the right edge of the text field
      // if the edit was invalid
      if(!field.isEditValid())
      {
         Graphics2D g2d = (Graphics2D)g.create();
         g2d.dispose();
      }
   }
}
