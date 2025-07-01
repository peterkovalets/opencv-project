package org.peterkovalets.app.components;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

/**
 * Компонент для ввода целых неотрицательных чисел.
 */
public class IntegerField extends JFormattedTextField {

  private static final NumberFormatter NUMBER_FORMATTER;

  static {
    NumberFormat intFormat = NumberFormat.getIntegerInstance();
    NUMBER_FORMATTER = new NumberFormatter(intFormat);
    NUMBER_FORMATTER.setValueClass(Integer.class);
    NUMBER_FORMATTER.setMinimum(0);
    NUMBER_FORMATTER.setMaximum(Integer.MAX_VALUE);
    NUMBER_FORMATTER.setAllowsInvalid(false);
  }

  /**
   * Конструктор класса.
   */
  public IntegerField() {
    super(NUMBER_FORMATTER);
  }
}
