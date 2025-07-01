package org.peterkovalets.app;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.peterkovalets.app.components.ImageLabel;
import org.peterkovalets.app.warning.WarningDialog;
import org.peterkovalets.app.warning.WarningMessage;

import javax.swing.*;

/**
 * Класс камеры.
 */
public class Camera {

  private VideoCapture capture;
  private final Mat imageMatrix;
  private final ImageLabel imageLabel;
  private final int cameraId;
  private boolean isCapturing = false;

  /**
   * Конструктор класса.
   *
   * @param imageMatrix матрица изображения
   * @param imageLabel компонент изображения
   * @param cameraId идентификатор камеры
   */
  public Camera(Mat imageMatrix, ImageLabel imageLabel, int cameraId) {
    this.imageMatrix = imageMatrix;
    this.imageLabel = imageLabel;
    this.cameraId = cameraId;
  }

  /**
   * Запускает камеру.
   */
  public void start() {
    if (isCapturing) {
      WarningDialog.showDialog(WarningMessage.CAMERA_ALREADY_CAPTURING);
      return;
    }

    Runnable task = () -> {
      if (capture == null) {
        capture = new VideoCapture();
      }
      isCapturing = capture.open(cameraId);

      if (!isCapturing) {
        JOptionPane.showMessageDialog(null, "Не удалось получить доступ к камере!",
            "Ошибка", JOptionPane.ERROR_MESSAGE);
      }

      while (isCapturing) {
        capture.read(imageMatrix);
        imageLabel.drawImage(imageMatrix);
      }

      capture.release();
    };
    new Thread(task).start();
  }

  /**
   * Останавливает камеру.
   */
  public void stop() {
    if (!isCapturing) {
      WarningDialog.showDialog(WarningMessage.CAMERA_NOT_ENABLED);
      return;
    }

    isCapturing = false;
  }

  /**
   * Возвращает булево значение захвата камеры.
   *
   * @return булево значение, обозначающее работает ли камера
   */
  public boolean getIsCapturing() {
    return isCapturing;
  }
}
