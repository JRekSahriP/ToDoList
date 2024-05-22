import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ToDoList extends JPanel {
	final int SCREEN_WIDTH = 500,SCREEN_HEIGHT = 600;
	
	JPanel taskPanel;

	ArrayList<Task>TaskList = new ArrayList<>();
	
	ToDoList(){
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setFocusable(true);
		this.setLayout(null);
		initScroll();
		TaskButton();
	}
	
	public void initScroll() {
		taskPanel = new JPanel();
		taskPanel.setLayout(null);
		taskPanel.setPreferredSize(new Dimension(SCREEN_WIDTH-30,SCREEN_HEIGHT-100));
		
		JScrollPane scrollPane = new JScrollPane(taskPanel);
		scrollPane.setBounds(15,10,SCREEN_WIDTH-30,SCREEN_HEIGHT-70);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		this.add(scrollPane);
	}
	
	public void updateTaskPanel() {
		int newHeight = 10 + (TaskList.size()*70);
		taskPanel.setPreferredSize(new Dimension(SCREEN_WIDTH-30, newHeight));
		taskPanel.revalidate();
		taskPanel.repaint();
	}

	public void TaskButton() {
		JButton addTask = new JButton("Add new Task");
		addTask.setBounds(15, SCREEN_HEIGHT-50, SCREEN_WIDTH-30, 35);
	
		addTask.addActionListener(e -> {
			Task newTask = createTask();
			taskPanel.add(newTask.getCheckTask());
			taskPanel.add(newTask.getTaskField());
			taskPanel.add(newTask.getRemoveTask());
			
			updateTaskPanel();
		});
		
		this.add(addTask);
	}
	

	public Task createTask() {
		int Y = 10 + (TaskList.size() * 70);
		
		JCheckBox checkTask = new JCheckBox();
		JTextArea taskArea = new JTextArea();
		JButton removeTask = new JButton("X");
		
		checkTask.setBounds(5,Y+20,20,20);
		taskArea.setBounds(35,Y,SCREEN_WIDTH-150,60);
		removeTask.setBounds(SCREEN_WIDTH-105,Y+5,50,50);
		
		Task currentTask = new Task(checkTask,taskArea,removeTask);
		
		checkTask.addActionListener(e -> {
			if(checkTask.isSelected()) {taskArea.setFont(new Font(Font.DIALOG, Font.BOLD, 12));}
			else {taskArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));}
		});
		
		taskArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		taskArea.setLineWrap(true);
		taskArea.setWrapStyleWord(true);
		
		removeTask.addActionListener(e ->{deleteTask(currentTask);});
		
		TaskList.add(currentTask);
		
		return currentTask;
	}
	
	public void deleteTask(Task task) {
		taskPanel.remove(task.getCheckTask());
		taskPanel.remove(task.getTaskField());
		taskPanel.remove(task.getRemoveTask());
		
		updateTaskPositionY(task);
		
		updateTaskPanel();
		
		TaskList.remove(task);
	}
	
	public void updateTaskPositionY(Task task) {
		int removedY = task.getCheckTask().getY();
		for(Task item: TaskList) {
			if(item.getCheckTask().getY() > removedY) {
				item.getCheckTask ().setLocation(item.getCheckTask().getX (), item.getCheckTask ().getY() - 70);
				item.getTaskField ().setLocation(item.getTaskField().getX (), item.getTaskField ().getY() - 70);
				item.getRemoveTask().setLocation(item.getRemoveTask().getX(), item.getRemoveTask().getY() - 70);
			}
		}
	}
}