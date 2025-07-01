package org.peterkovalets.app;

import org.opencv.core.Mat;
import org.peterkovalets.app.components.ImageLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Основной класс приложения.
 */
public class App extends JFrame {

  private static final int CAMERA_ID = 0;

  private final Mat imageMatrix;
  private final Camera camera;

  /**
   * Конструктор класса.
   */
  public App() {
    super("OpenCV Project");

    imageMatrix = new Mat();
    ImageLabel imageLabel = new ImageLabel();
    camera = new Camera(imageMatrix, imageLabel, CAMERA_ID);
    getContentPane().add(BorderLayout.CENTER, imageLabel);

    setUpBottomPanel();

    setSize(800, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  /**
   * Точка входа в приложение.
   *
   * @param args аргументы командной строки
   */
  public static void main(String[] args) {
    nu.pattern.OpenCV.loadLocally();
    new App();
  }

  /**
   * Создает нижнюю панель пользовательского интерфейса.
   */
  private void setUpBottomPanel() {
    JPanel bottomPanel = new JPanel();
    JButton loadImageBtn = new JButton("Загрузить изображение");
    JButton startCameraBtn = new JButton("Включить камеру");
    JButton cameraScreenshotBtn = new JButton("Получить изображение с камеры");

    loadImageBtn.addActionListener(event -> {
      if (camera.getIsCapturing()) {
        showCapturingWarningDialog();
        return;
      }

      // Загрузить изображение
    });
    startCameraBtn.addActionListener(event -> camera.start());
    cameraScreenshotBtn.addActionListener(event -> camera.stop());

    bottomPanel.add(loadImageBtn);
    bottomPanel.add(startCameraBtn);
    bottomPanel.add(cameraScreenshotBtn);
    getContentPane().add(BorderLayout.SOUTH, bottomPanel);
  }

  /**
   * Показывает предупреждение о том, что камера уже работает.
   */
  private void showCapturingWarningDialog() {
    JOptionPane.showMessageDialog(null, "Камера должна быть остановлена!",
        "Камера", JOptionPane.WARNING_MESSAGE);
  }
}
