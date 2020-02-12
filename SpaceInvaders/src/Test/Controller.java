package Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	
	private Model model;
	
	public void Controller(Model model) {
		this.model = model;
	}
	
	public void init() {
		view.addKeyListeners();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
