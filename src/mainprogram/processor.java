package mainprogram;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;

import javax.swing.JDialog;

import javax.swing.JTextField;
import javax.swing.JButton;




public class processor extends JDialog
{

	/**
	 * @param args
	 */
    JTextField taget_url=new JTextField();;
    JButton button1=new JButton("go");;
	public processor()
	{
		super();
	    this.setSize(400, 400);
	    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    this.setTitle("view writer only");
	    taget_url=new JTextField();
	    taget_url.setLocation(50, 150); taget_url.setSize(150, 50);
	    //taget_url.setBorder(new Border(1));
	    taget_url.addKeyListener(new KeyListener()
	    {
	    	public void keyTyped(KeyEvent e)
	    	{
	    		if(e.getKeyCode()==KeyEvent.VK_ENTER)
	    		{
	    			button1.doClick();
	    		}
	    		
	    	}
	    	public void keyReleased(KeyEvent e){}
	    	public void keyPressed(KeyEvent e)
	    	{
	    		if(e.getKeyCode()==KeyEvent.VK_ENTER)
	    		{
	    			button1.doClick();
	    		}
	    	}
	    	
	    });
	    
	    taget_url.addMouseListener(new MouseListener(){
	    	
	    	public void mouseExited(MouseEvent ee){}
	    	public void mouseReleased(MouseEvent ee){}
	    	public void mousePressed(MouseEvent ee){}
	    	public void mouseEntered(MouseEvent ee){}
	    	public void mouseClicked(MouseEvent ee){
	    		
	    		taget_url.setText("");
	    	}
	    });
	    
	    button1=new JButton("go");
	    button1.setLocation(300, 150); button1.setSize(70, 70);
	    button1.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		try
	    		{
	    			button1.setEnabled(false);
	    			core_processer cp=new core_processer();
	    			cp.execute(taget_url.getText());
	    			button1.setEnabled(true);
	    			
	    			
	    		}catch(IOException ee){ee.printStackTrace();}
	    		
	    	}
	    } );
	    
	    this.getContentPane().add(button1);this.getContentPane().add(taget_url);
	}
		
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
        new processor().setVisible(true);
	}

}
