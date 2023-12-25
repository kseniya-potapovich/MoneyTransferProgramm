package util;

public enum StatusCode {
    OK("успешно обработан"),
    INCORRECT_AMOUNT("ошибка во время обработки, неверная сумма перевода"),
    INVALID_TO_NUMBER("ошибка во время обработки, невалидный номер счета отправителя"),
    INVALID_FROM_NUMBER("ошибка во время обработки, невалидный номер счета получателя"),
    NON_EXISTENT_TO_NUMBER("ошибка во время обработки, несуществующий номер отправителя"),
    NON_EXISTENT_FROM_NUMBER("ошибка во время обработки, несуществующий номер получателя"),
    NEGATIVE_AMOUNT("ошибка во время обработки, неверная сумма на счете отправителя"),
    ;
    String message;

    StatusCode( String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
