package java_ncs_exam.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import java_ncs_exam.content.TitlePanel;
import java_ncs_exam.content.TitleTablePanel;
import java_ncs_exam.dto.Title;
import java_ncs_exam.exam.exception.EmptyTfException;
import java_ncs_exam.exam.exception.InvalidChechException;
import java_ncs_exam.exam.exception.NotSelectedException;
import java_ncs_exam.exam.exception.SqlConstraintException;
import java_ncs_exam.service.TitleService;

public class Management extends JFrame implements ActionListener {
	private TitleService service;
	private TitleTablePanel pList;
	private TitlePanel pContent;
	private JButton btnAdd;
	private JButton btnClear;
	private JPanel contentPane;

	public Management() {
		service = new TitleService(); 
		initialize(); 
		

	}

	public void initialize() {
		setTitle("직책관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = new TitlePanel();
		contentPane.add(pContent);

		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);

		pList = new TitleTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListner);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListner);
		popMenu.add(deleteItem);

		return popMenu;
	}

	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("삭제")) {
					Title delTitle = pList.getItem();
					System.out.println(delTitle);
					service.removeTitle(delTitle);
					pList.loadData();
					JOptionPane.showMessageDialog(null, delTitle.toString2() + "이(가) 삭제 되었습니다.");
				}
				if (e.getActionCommand().equals("수정")) {
					Title updateTitle = pList.getItem();
					pContent.setItem(updateTitle);
					btnAdd.setText("수정");
					System.out.println(updateTitle);
					
				}

			} catch (NotSelectedException | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "선택오류", JOptionPane.CANCEL_OPTION);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	};
	

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnClear) {
				actionPerformedBtnClear(e);
			}

			if (e.getSource() == btnAdd) {
				if (e.getActionCommand().contentEquals("추가")) {
					actionPerformedBtnAdd(e);
				}
				if (e.getActionCommand().contentEquals("수정")) {
					actionPerformedBtnupdate(e);
				}
			}

		} catch (InvalidChechException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);

		} catch (EmptyTfException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnupdate(ActionEvent e) {
		Title beforeTitle = pList.getItem();
		Title updateTitle = pContent.getItem();
		
		service.modifyTitle(updateTitle);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, beforeTitle.toString2() + "이(가) "+ updateTitle.toString2() + "로 변경되었습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getItem();
		service.addTitle(title);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, title.toString2() + "이(가) 추가되었습니다."); 
																			

	}

	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();

	}

}
