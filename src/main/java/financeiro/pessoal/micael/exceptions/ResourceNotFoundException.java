package financeiro.pessoal.micael.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	// You can also add a constructor to pass in the cause of the exception
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	// Optionally, you can add more constructors, for example, to pass in custom
	// identifiers
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
	}
}
