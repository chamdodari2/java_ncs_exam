 package java_ncs_exam.content;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java_ncs_exam.dto.Title;
import java_ncs_exam.exam.exception.NotSelectedException;
import java_ncs_exam.service.TitleService;

@SuppressWarnings("serial")
public  class TitleTablePanel extends JPanel {
	private JTable table;
	protected List<Title> list;
	
	private TitleService service;
	//= new TitleService();   
	
	public TitleTablePanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(getModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	
	public void loadData() {
		initList();
		setList();
	}
	
	private void initList() {
		list = service.showTitles();
		
	}
	
	public void setPopupMenu(JPopupMenu popMenu) {
		table.setComponentPopupMenu(popMenu);
	}
	public DefaultTableModel getModel() {
		CustomTableModel model = new CustomTableModel(getData(), getColumnNames());
		return model;
	}

	public Object[][] getData() {
		return new Object[][] { { null, null, null }, };
	}

	public void setList() {
		Object[][] data = new Object[list.size()][];
		for (int i = 0; i < data.length; i++) {
			data[i] = toArray(list.get(i));
		}
		
	 	CustomTableModel model = new CustomTableModel(data, getColumnNames());
		table.setModel(model);
		
		setAlignAndWidth();
		
	}

	protected  void setAlignAndWidth() {
	setTableCellAlign(SwingConstants.CENTER, 0, 1);
		
		setTableCellWidth(100,250);	
		
	}
	
	protected void setTableCellWidth(int...width) {
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	protected void setTableCellAlign(int align, int...idx) {
		TableColumnModel tcm = table.getColumnModel();
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	public  Object[] toArray(Title title) {
		return new Object[] {title,title.gettName()};
		
	}
	
	public String[] getColumnNames() {
		return new String[] {"직책번호","직책명"};
	}
	
	private class CustomTableModel extends DefaultTableModel{

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	public void setService(TitleService service) {
		this.service = service;
	}
	public Title getItem() {
		int row = table.getSelectedRow();
		 //테이블의 제일 앞에있는거 가져오기	
		if(row == -1 ) {
			throw new NotSelectedException();
		}
		return  (Title) table.getValueAt(row, 0);  //list.get(list.indexOf(new Title(tNo))); //이거에 일치하는 인덱스번호를 넘겨준다  이제필요없당!!! 객체 통째로 가져왔기때문에!!	
	}
}
