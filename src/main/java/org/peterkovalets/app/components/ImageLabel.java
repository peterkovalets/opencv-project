package org.peterkovalets.app.components;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

/**
 * Компонент для показа изображения.
 */
public class ImageLabel extends JLabel {

  /**
   * Конструктор класса.
   */
  public ImageLabel() {
    setHorizontalAlignment(SwingConstants.CENTER);
  }

  /**
   * Выводит изображение на компонент.
   *
   * @param imageMatrix матрица изображения
   */
  public void drawImage(Mat imageMatrix) {
    MatOfByte buffer = new MatOfByte();
    Imgcodecs.imencode(".jpg", imageMatrix, buffer);
    byte[] bytes = buffer.toArray();
    ImageIcon icon = new ImageIcon(bytes);
    setIcon(icon);
  }
}
