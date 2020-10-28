package wordcount;

class WordCountException extends RuntimeException {

    public WordCountException() {
    }

    public WordCountException(String message) {
        super(message);
    }

    public WordCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public WordCountException(Throwable cause) {
        super(cause);
    }

    public WordCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
