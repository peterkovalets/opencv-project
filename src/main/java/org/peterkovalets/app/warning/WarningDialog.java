package org.peterkovalets.app.warning;

import javax.swing.*;

/**
 * Класс с диалогом предупреждения.
 */
public class WarningDialog {

  /**
   * Показывает окно с предупреждением.
   *
   * @param warningMessage сообщение предупреждения
   */
  public static void showDialog(WarningMessage warningMessage) {
    JOptionPane.showMessageDialog(null, warningMessage.getMessage(),
        "Предупреждение", JOptionPane.WARNING_MESSAGE);
  }
}
