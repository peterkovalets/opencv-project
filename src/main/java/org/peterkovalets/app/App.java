package org.peterkovalets.app;

import org.opencv.core.Mat;
import org.peterkovalets.app.components.ImageLabel;
import org.peterkovalets.app.warning.WarningDialog;
import org.peterkovalets.app.warning.WarningMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Основной класс приложения.
 */
public class App extends JFrame {

  private static final int CAMERA_ID = 0;

  private final Mat imageMatrix;
  private final Camera camera;
  private final ImageLoader imageLoader;
  private final Filters filters;

  /**
   * Конструктор класса.
   */
  public App() {
    super("OpenCV Project");

    imageMatrix = new Mat();
    ImageLabel imageLabel = new ImageLabel();
    camera = new Camera(imageMatrix, imageLabel, CAMERA_ID);
    imageLoader = new ImageLoader(imageMatrix, imageLabel);
    filters = new Filters(imageMatrix, imageLabel);
    getContentPane().add(BorderLayout.CENTER, imageLabel);

    setUpRightPanel();
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
   * Создает панель в правой части экрана.
   */
  private void setUpRightPanel() {
    Box rightBox = new Box(BoxLayout.Y_AXIS);
    JButton colorChannelBtn = new JButton("Показать канал изображения");
    JButton grayscaleBtn = new JButton("Получить в оттенках серого");
    JButton rotateBtn = new JButton("Выполнить вращение");
    JButton drawRectBtn = new JButton("Нарисовать прямоугольник");

    colorChannelBtn.addActionListener(new ColorChannelListener());
    grayscaleBtn.addActionListener(new GrayscaleListener());

    colorChannelBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
    grayscaleBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
    rotateBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
    drawRectBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);

    rightBox.add(Box.createVerticalGlue());
    rightBox.add(colorChannelBtn);
    rightBox.add(grayscaleBtn);
    rightBox.add(rotateBtn);
    rightBox.add(drawRectBtn);
    rightBox.add(Box.createVerticalGlue());
    getContentPane().add(BorderLayout.EAST, rightBox);
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
        WarningDialog.showDialog(WarningMessage.CAPTURING);
        return;
      }

      imageLoader.loadImage();
    });
    startCameraBtn.addActionListener(event -> camera.start());
    cameraScreenshotBtn.addActionListener(event -> camera.stop());

    bottomPanel.add(loadImageBtn);
    bottomPanel.add(startCameraBtn);
    bottomPanel.add(cameraScreenshotBtn);
    getContentPane().add(BorderLayout.SOUTH, bottomPanel);
  }

  /**
   * Класс слушателя для кнопки показа канала изображения.
   */
  private class ColorChannelListener implements ActionListener {

    /**
     * Метод, который вызывается при нажатии кнопки.
     *
     * @param actionEvent объект события
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      if (camera.getIsCapturing()) {
        WarningDialog.showDialog(WarningMessage.CAPTURING);
        return;
      }
      if (imageMatrix.empty()) {
        WarningDialog.showDialog(WarningMessage.EMPTY_IMAGE);
        return;
      }
      if (imageMatrix.channels() == 1) {
        WarningDialog.showDialog(WarningMessage.IMAGE_GRAYSCALE);
        return;
      }

      String[] buttons = { "Синий", "Зеленый", "Красный" };
      int initialBtnIndex = 0;
      int returnValue = JOptionPane.showOptionDialog(null, "Выберите цветовой канал",
          "Канал изображения", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
          buttons, buttons[initialBtnIndex]);

      if (returnValue != -1) {
        filters.extractColorChannel(returnValue);
      }
    }
  }

  /**
   * Класс слушателя для кнопки показа изображения в оттенках серого.
   */
  private class GrayscaleListener implements ActionListener {

    /**
     * Метод, который вызывается при нажатии кнопки.
     *
     * @param actionEvent объект события
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      if (camera.getIsCapturing()) {
        WarningDialog.showDialog(WarningMessage.CAPTURING);
        return;
      }
      if (imageMatrix.empty()) {
        WarningDialog.showDialog(WarningMessage.EMPTY_IMAGE);
        return;
      }
      if (imageMatrix.channels() == 1) {
        WarningDialog.showDialog(WarningMessage.IMAGE_GRAYSCALE);
        return;
      }

      filters.grayscaleImage();
    }
  }
}
