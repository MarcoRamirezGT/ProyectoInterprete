import java.applet.Applet;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
//clase que ejecuta el programa y hace visible los paneles
/**
 * 
 * @author Marco Ramirez
 * @author Oscar De Leon
 * @author Edman 
 *
 */
public class main extends Applet{
	  public static void main (String[] args) {
	       lisp lisp = new lisp();
	       lisp.init();
	       lisp.start();
	       JFrame window = new JFrame();
	       window.setContentPane(lisp);
	       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       window.pack();
	       window.setVisible(true);
	       
	    }

}
