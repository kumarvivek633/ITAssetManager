/*
 *
 */
package net.vivekkumar.spring.model;

/**
 * The Class ErrorMsg.
 */
public class ErrorMsg {

    /** The error. */
    private String error;

    /** The has error. */
    private boolean hasError = false;

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Gets the checks for error.
     *
     * @return the checks for error
     */
    public boolean getHasError() {
        return hasError;
    }

    /**
     * Sets the error.
     *
     * @param error
     *            the new error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Sets the checks for error.
     *
     * @param hasError
     *            the new checks for error
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

}
