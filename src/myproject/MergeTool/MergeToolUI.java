package myproject.MergeTool;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MergeToolUI extends JFrame {
	
	private MergeTool mt;
	
	private JLabel jLable1;
	private JPanel panel1;
	private JPanel panel2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JTextArea textArea1;
	private JTextArea textArea2;

	public MergeToolUI (MergeTool mt) {
		
		super("Merge Tool");
		this.mt = mt;
		
		initComponent();
		
		displayData();
	}



	private void initComponent() {
		
		jLable1 = new JLabel();
		jLable1.setSize(600, 40);
		
		textArea1 = new JTextArea(40, 50);
		textArea2 = new JTextArea(40, 50);
		textArea1.setEditable(false);
		textArea2.setEditable(false);
		
		
		scrollPane1 = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
				);
		scrollPane2 = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
				);
		
		JScrollBar scrollBar1 = scrollPane1.getVerticalScrollBar();		
		scrollPane2.setVerticalScrollBar(scrollBar1);
		
		scrollPane1.setViewportView(textArea1);
		scrollPane2.setViewportView(textArea2);
		
		panel1 = new JPanel();
		panel1.add(jLable1);
		
		panel2 = new JPanel();
		panel2.add(scrollPane1, BorderLayout.WEST);
		panel2.add(scrollPane2, BorderLayout.EAST);
		
		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.SOUTH);
		
		this.setSize(1200, 720);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	private void displayData() {
		
		List<ChangeState> stateList = mt.getStateList();
		
		int update = 0;
		int insert = 0;
		int delete = 0;
		int unmodified = 0;
		for (ChangeState state : stateList) {
			if (state == ChangeState.UPDATE) update++;
			if (state == ChangeState.INSERT) insert++;
			if (state == ChangeState.DELETE) delete++;
			if (state == ChangeState.UNMODIFIED) unmodified++;
		}
		
		jLable1.setText("Unmodified=" + unmodified 
				+ "  Update=" + update 
				+ "  Insert=" + insert 
				+ "  Delete=" + delete);
		
		
		
		List<String> list1 = mt.getList1();
		for (int i = 0; i < list1.size(); i++) {
			String str = "";
			switch (stateList.get(i)) {
			case UPDATE:
				str = "* ";
				break;
			case INSERT:
				str = "+ ";
				break;
			case DELETE:
				str = "- ";
				break;
			default:
				str = "   ";
				break;
			}
			textArea1.append(str + list1.get(i) + "\n");
		}

		List<String> list2 = mt.getList2();
		for (int i = 0; i < list2.size(); i++) {
			String str = "";
			switch (stateList.get(i)) {
			case UPDATE:
				str = "* ";
				break;
			case INSERT:
				str = "+ ";
				break;
			case DELETE:
				str = "- ";
				break;
			default:
				str = "   ";
				break;
			}
			textArea2.append(str + list2.get(i) + "\n");
		}
		
	}
	
	
	
}
