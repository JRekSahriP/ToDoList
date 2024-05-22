import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class Task {
	private JCheckBox checkTask;
	private JTextArea taskArea;
	private JButton removeTask;
	
	public Task(JCheckBox checkTask,JTextArea taskArea, JButton removeTask) {
		this.checkTask = checkTask;
		this.taskArea = taskArea;
		this.removeTask = removeTask;
	}
	
	public JCheckBox getCheckTask() {return checkTask;}
	public JTextArea getTaskField() {return taskArea;}
	public JButton getRemoveTask() {return removeTask;}
}