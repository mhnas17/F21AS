package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import gui.Gui;
import queue.WaitingQueue;

public class CheckinControler {

	private Gui gui;
	
	public CheckinControler(Gui gui) {
		this.gui = gui;
		gui.addAddButtonListener(new addButtonControler());
		gui.addRemoveButtonListener(new removeButtonControler());
		
	}
	
	class addButtonControler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {					
			gui.addButton();
			if (gui.getNumberofDesks()==3) {
				gui.disableAddButton();
				gui.enableRemoveButton();
			}else if(gui.getNumberofDesks()==1) {
				gui.enableRemoveButton();
			}
			
			}
		}
	
	class removeButtonControler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			
			gui.removeButton();
			if (gui.getNumberofDesks()==0) {
				gui.enableAddButton();
				gui.disableRemoveButton();
			}else if(gui.getNumberofDesks()==2) {
				gui.enableAddButton();
			}	
		
		
			
			}
		}
		
	}
	
	
	

