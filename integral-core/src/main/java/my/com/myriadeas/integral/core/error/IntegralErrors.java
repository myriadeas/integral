package my.com.myriadeas.integral.core.error;

import java.util.ArrayList;
import java.util.List;

public class IntegralErrors {

	public List<IntegralError> errors;

	public IntegralErrors() {
		errors = new ArrayList<IntegralError>();
	}

	public void addError(IntegralError error) {
		errors.add(error);
	}

	public List<IntegralError> getErrors() {
		return errors;
	}

	public String toString() {
		return (new StringBuilder("IntegralErrors [errors=")).append(errors)
				.append("]").toString();
	}

}
