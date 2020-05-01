package br.com.tqi.test.development.domain.exception;

/**
 * @author JGaray
 */
public class NegocioException extends RuntimeException {

    /**
     *
     * @param message
     */
    public NegocioException(String message) {
        super(message);
    }
}
