package java_ncs_exam.exam.exception; 

@SuppressWarnings("serial")
public class InValidationException extends RuntimeException { 

	public InValidationException() {
		super("취소되었습니다.");
	}

	public InValidationException(Throwable cause) {
		super("취소되었습니다.",cause);
		
	}

	public InValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
