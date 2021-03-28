package java_ncs_exam.exam.exception;

@SuppressWarnings("serial")
public class InvalidChechException extends RuntimeException { //전파. 계속 위롱 ㅗㄹ라간다

	public InvalidChechException() {
		super("공란 존재");
	}

	public InvalidChechException(Throwable cause) {
		super("공란 존재",cause);
		
	}

	public InvalidChechException(String message) {
		super(message);
		
	}
	

}
