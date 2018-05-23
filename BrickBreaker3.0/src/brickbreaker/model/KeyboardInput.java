package brickbreaker.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class KeyboardInput {
	private InputMap inp;
	private ActionMap am;
	
	public KeyboardInput(JComponent comp) {
		inp = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		am = comp.getActionMap();
	}
	
	public void tasteGedrückt(int KeyCode,String id, ActionListener lambda) {
		inp.put(KeyStroke.getKeyStroke(KeyCode, 0, false), id);
		am.put(id, new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lambda.actionPerformed(e);
				
			}
		});
	}
	
	public void tasteLosgelassen(int KeyCode,String id, ActionListener lambda) {
		inp.put(KeyStroke.getKeyStroke(KeyCode, 0, true), id);
		am.put(id, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lambda.actionPerformed(e);
				
			}
		});
	}
}
