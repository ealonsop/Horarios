package capacliente;

//
// A simple Java Console for your application (Swing version)
// Requires Java 1.1.5 or higher
//
// Disclaimer the use of this source is at your own risk. 
//
// Permision to use and distribute into your own applications
//
// RJHM van den Bergh , rvdb@comweb.nl

import capalogica.CAsignacion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class consola extends WindowAdapter implements WindowListener, ActionListener
{
	private static JFrame frame;
	private static JTextArea textArea;
	private static JScrollPane jsp;
	private static JButton bt[];
//        private static boolean vbotones = true;
        private static boolean vbotones = false;
        
	public consola()
	{
		// create all components and add them
		frame=new JFrame("Generador de Horarios");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/2));
		int x=(int)(frameSize.width/2);
		int y=(int)(frameSize.height/2);
		frame.setBounds(x,y,frameSize.width,frameSize.height);
		
		textArea=new JTextArea();
		textArea.setEditable(false);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 12 ));
                bt = new JButton[5];
                int i;
                for ( i = 0; i < bt.length; i++ ) {
		   bt[i]=new JButton("B");
                   bt[i].addActionListener(this);
                }
                bt[0].setText("Prob0");
                bt[1].setText("Prob1-SIS");
                bt[2].setText("Prob1-CIV");
                bt[3].setText("trace");
                bt[4].setText("restart");
                
	
                jsp = new JScrollPane(textArea);
                jsp.getVerticalScrollBar().addAdjustmentListener(
                        new AdjustmentListener() {
                                public void adjustmentValueChanged(AdjustmentEvent e) {
                                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                                }
                            }
                        );
                frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(jsp,BorderLayout.CENTER);

                JPanel botones = new JPanel();
                botones.setLayout(new FlowLayout());
                for ( i = (vbotones?0:3) ; i < bt.length; i++ )
		    botones.add(bt[i]);
                frame.getContentPane().add(botones,BorderLayout.SOUTH);
                
		frame.setVisible(true);		
		
		frame.addWindowListener(this);	
		
	}
        
        
	public synchronized void actionPerformed(ActionEvent evt)
	{
                Object b = evt.getSource();
                int i;
                for ( i = 0; i < bt.length; i++ )
                    if ( bt[i] == b )
                        break;
                switch ( i ) {
                    case 0: CAsignacion.prob0();
                            break;
                    case 1: CAsignacion.prob1();
                            break;
                    case 2: CAsignacion.prob2();
                            break;
                    case 3: CAsignacion.debug = !CAsignacion.debug;
                           break;
                    case 4: CAsignacion.restart = true;
                           break;
                    default:
                           break;
                }
                
		//textArea.setText("");
	}

	public synchronized void windowClosed(WindowEvent evt)
	{
		this.notifyAll(); // stop all threads
		System.exit(0);
	}		
		
	public synchronized void windowClosing(WindowEvent evt)
	{
		frame.setVisible(false); // default behaviour of JFrame	
		frame.dispose();
	}      
        
        public static void out(String m) {
            textArea.append(m);
        }
        
        public static void outln(String m) {
            textArea.append(m);
            textArea.append("\n");
        }
        public static void outln() {
            textArea.append("\n");
        }
        
        public static void clear() {
            textArea.setText("");
        }

        
        
        public static void stopscroll() {
            AdjustmentListener al[] = jsp.getVerticalScrollBar().getAdjustmentListeners();
            jsp.getVerticalScrollBar().removeAdjustmentListener(
                    al[al.length-1]
                     );
        }
        
        public static void settitle(String t) {
            frame.setTitle(t);
        }
        
        

}