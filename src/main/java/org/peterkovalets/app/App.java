package org.peterkovalets.app;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Основной класс приложения.
 */
public class App {
  /**
   * Точка входа в приложение.
   *
   * @param args аргументы командной строки
   */
  public static void main(String[] args) {
    nu.pattern.OpenCV.loadLocally();
    Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
    System.out.println("mat = " + mat.dump());
  }
}
