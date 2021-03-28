package java_ncs_exam.exam.exception; //////취소버튼 누르면 클린 기능 추가하기!!

@SuppressWarnings("serial")
public class InValidationException extends RuntimeException { //전파. 계속 위롱 ㅗㄹ라간다

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
