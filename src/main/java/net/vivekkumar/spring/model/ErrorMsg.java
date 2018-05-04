package net.vivekkumar.spring.model;

public class ErrorMsg {

	private String error;
	
	private boolean hasError = false;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean getHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
	
}
