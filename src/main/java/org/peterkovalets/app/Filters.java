package org.peterkovalets.app;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
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

  /**
   * Поворачивает изображение и выводит его на экран.
   *
   * @param angle значение угла поворота
   */
  public void rotateImage(int angle) {
    int width = imageMatrix.cols();
    int height = imageMatrix.rows();
    Point center = new Point(width / 2.0, height / 2.0);
    int scale = 1;
    Size size = new Size(width, height);
    Mat rotationMatrix = Imgproc.getRotationMatrix2D(center, angle, scale);
    Imgproc.warpAffine(imageMatrix, imageMatrix, rotationMatrix, size);
    imageLabel.drawImage(imageMatrix);
  }
}
