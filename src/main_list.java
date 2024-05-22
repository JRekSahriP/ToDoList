import javax.swing.JFrame;

public class main_list extends JFrame {
	public static void main(String[] args) {
		JFrame window = new JFrame("To Do List");
		window.add(new ToDoList());
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}
}
