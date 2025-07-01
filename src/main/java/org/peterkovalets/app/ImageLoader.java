package org.peterkovalets.app;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.peterkovalets.app.components.ImageLabel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Класс для загрузки изображений.
 */
public class ImageLoader {

  private final Mat imageMatrix;
  private final ImageLabel imageLabel;
  private final static JFileChooser fileChooser;;

  static {
    fileChooser = new JFileChooser();
  }

  /**
   * Конструктор класса.
   *
   * @param imageMatrix матрица изображения
   * @param imageLabel компонент изображения
   */
  public ImageLoader(Mat imageMatrix, ImageLabel imageLabel) {
    this.imageMatrix = imageMatrix;
    this.imageLabel = imageLabel;
  }

  /**
   * Получает файл от пользователя и показывает его на экран.
   */
  public void loadImage() {
    File file = chooseImage();
    if (file != null) {
      Imgcodecs.imread(file.getAbsolutePath()).assignTo(imageMatrix);
      imageLabel.drawImage(imageMatrix);
    }
  }

  /**
   * Показывает окно для выбора файла.
   *
   * @return выбранный файл
   */
  private File chooseImage() {
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images",
        "jpg", "png");
    fileChooser.setFileFilter(filter);
    fileChooser.setAcceptAllFileFilterUsed(false);
    int returnVal = fileChooser.showOpenDialog(null);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    }

    return null;
  }
}
