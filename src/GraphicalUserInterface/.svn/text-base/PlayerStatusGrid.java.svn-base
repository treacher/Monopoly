package GraphicalUserInterface;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PlayerStatusGrid extends JPanel {
	
	public List<PlayerStatusPanel> getPanels(){
		List<PlayerStatusPanel> list = new ArrayList<PlayerStatusPanel>();
		for(Component c : this.getComponents()){
			PlayerStatusPanel p = (PlayerStatusPanel) c;
			list.add(p);
		}
		return list;
	}
	
}
