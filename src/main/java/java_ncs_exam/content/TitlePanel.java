package java_ncs_exam.content;

import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java_ncs_exam.dto.Title;
import java_ncs_exam.exam.exception.EmptyTfException;
import java_ncs_exam.exam.exception.InvalidChechException;

public class TitlePanel  extends JPanel {

	private JTextField tfTNo;
	private JTextField tfTName;

	public TitlePanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 20, 20));
		
		JLabel lblTNo = new JLabel("번호");
		lblTNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTNo);
		
		tfTNo = new JTextField();
		add(tfTNo);
		tfTNo.setColumns(10);
		
		JLabel lblTName = new JLabel("직책");
		lblTName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTName);
		
		tfTName = new JTextField();
		tfTName.setColumns(10);
		add(tfTName);
		
	}
	
	
	
	
	public Title getTitle() { // title에 있는거 가져오기
		int tNo = Integer.parseInt(tfTNo.getText().trim());
		String tName = tfTName.getText().trim();
		return new Title();
	}
	public void setTitle(Title title) {
	tfTNo.setText(title.gettNo() + "");
	tfTName.setText(title.gettName() + "");
	
	}
	
	
	public void clearTf() {
		tfTNo.setText("");
		tfTName.setText("");
		
		if(!tfTNo.isEditable()) {
			tfTNo.setEditable(true);
		}
		
	}
	
	public void setItem(Title item) {
		tfTNo.setText(item.gettNo() + "");
		tfTName.setText(item.gettName() + "");
		tfTNo.setEditable(false);
		
	}
	
	public Title getItem() {
		validCheck();
		typeCheck();		
	

		int tNo = Integer.parseInt(tfTNo.getText().trim());
		String tName = tfTName.getText().trim();
		return new Title(tNo, tName);
	}
	
	public void validCheck() {
		// 두개의 값이 모두 입력되어야만 가능하게 !!! 만약  공백과 같다면 익셉션 발생시키기!!
		if (tfTNo.getText().contentEquals("") || tfTName.getText().equals("")) {
			throw new InvalidChechException();
		}
		
	}
	
	public void typeCheck() {  //
		if(!(Pattern.matches("^[0-9]*$", tfTNo.getText()) &&
			Pattern.matches("^[가-힣]*$",tfTName.getText()))) {
			throw new EmptyTfException();
		}
		
	}
}