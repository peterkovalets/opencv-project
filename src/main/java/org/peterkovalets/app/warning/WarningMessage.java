package org.peterkovalets.app.warning;

/**
 * Перечисление для сообщения предупреждения.
 */
public enum WarningMessage {
  CAPTURING("Камера должна быть остановлена!"),
  EMPTY_IMAGE("Изображение не существует!"),
  IMAGE_GRAYSCALE("Изображение уже в оттенках серого!"),
  CAMERA_ALREADY_CAPTURING("Камера уже работает!"),
  CAMERA_NOT_ENABLED("Камера не включена!"),
  EMPTY_VALUE("Значение не может быть пустым!");

  /**
   * Конструктор класса.
   *
   * @param message сообщение предупреждения
   */
  WarningMessage(String message) {
    this.message = message;
  }

  private final String message;

  /**
   * Возвращает сообщение предупреждения.
   *
   * @return сообщение предупреждения
   */
  public String getMessage() {
    return message;
  }
}
