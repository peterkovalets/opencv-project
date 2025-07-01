package org.peterkovalets.app;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.peterkovalets.app.components.ImageLabel;

import java.util.ArrayList;

/**
 * Класс с фильтрами.
 */
public class Filters {

  private final Mat imageMatrix;
  private final ImageLabel imageLabel;

  /**
   * Конструктор класса.
   *
   * @param imageMatrix матрица изображения
   * @param imageLabel компонент изображения
   */
  public Filters(Mat imageMatrix, ImageLabel imageLabel) {
    this.imageMatrix = imageMatrix;
    this.imageLabel = imageLabel;
  }

  /**
   * Извлекает цветовой канал из изображения и выводит его на экран.
   *
   * @param colorNum номер цвета
   */
  public void extractColorChannel(int colorNum) {
    ArrayList<Mat> colorChannels = new ArrayList<>(3);
    Core.split(imageMatrix, colorChannels);
    imageLabel.drawImage(colorChannels.get(colorNum));
  }

  /**
   * Получает изображение в оттенках серого и выводит его на экран.
   */
  public void grayscaleImage() {
    Imgproc.cvtColor(imageMatrix, imageMatrix, Imgproc.COLOR_BGR2GRAY);
    imageLabel.drawImage(imageMatrix);
  }
}
