package data;

public class OperationResult<T, U> {
	
	T t;
	U u;
	
	boolean OK;
	boolean hadError;
	
	/**
	 * 
	 * @param order
	 * @param wasOK
	 * @param hadError
	 * @param serviceInstance
	 */
	public OperationResult(T order, boolean wasOK, boolean hadError, U serviceInstance) {
		setT(order);
		setOK(wasOK);
		setHadError(hadError);
		setU(serviceInstance);
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public boolean isOK() {
		return OK;
	}

	public void setOK(boolean oK) {
		OK = oK;
	}

	public boolean isHadError() {
		return hadError;
	}

	public void setHadError(boolean hadError) {
		this.hadError = hadError;
	}

	public U getU() {
		return u;
	}

	public void setU(U u) {
		this.u = u;
	}
}
