package java_ncs_exam.exam.exception;

@SuppressWarnings("serial")
public class EmptyTfException extends RuntimeException {

	public EmptyTfException() {
		super("형식이 맞지 않습니다.");
		// TODO Auto-generated constructor stub
	}

	public EmptyTfException(Throwable cause) {
		super("형식이 맞지 않습니다.", cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyTfException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
