package jlayer_experiments;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLayer;

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
   private static final int ERR_SQ_OFFSET = 2;

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

      JLayer layer = (JLayer)c;
      JFormattedTextField field = (JFormattedTextField)layer.getView();

      // Paint a red 'X' on the right edge of the text field
      // if the edit was invalid and the field is non-empty
      if(!field.isEditValid() && !field.getText().isEmpty())
      {
         Graphics2D g2d = (Graphics2D)g.create();
         final int width = field.getWidth();
         final int height = field.getHeight();
         final int x = width - ERR_SQ_LEN - ERR_SQ_LEN;
         final int y = (height - ERR_SQ_LEN) / 2;

         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);

         // Red box
         g2d.setPaint(Color.RED);
         g2d.fillRect(x, y, ERR_SQ_LEN, ERR_SQ_LEN);

         // Black border
         g2d.setPaint(Color.BLACK);
         g2d.drawRect(x, y, ERR_SQ_LEN, ERR_SQ_LEN);

         // White 'X'
         g2d.setPaint(Color.WHITE);
         g2d.drawLine(x+1, y+1, x+ERR_SQ_LEN-1, y+ERR_SQ_LEN-1);
         g2d.drawLine(x+ERR_SQ_LEN-1, y+1, x+1, y+ERR_SQ_LEN-1);

         g2d.dispose();
      }
   }
}
