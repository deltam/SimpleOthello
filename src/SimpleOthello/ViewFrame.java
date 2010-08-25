// 2001/12/16

package SimpleOthello;

import java.awt.*;
import javax.swing.*;

class ViewFrame extends JFrame
{
	public ViewFrame()
	{
		super("シンプルオセロ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public ViewFrame(Game g)
	{
		this(g.getPanel());
		setTitle("シンプルオセロ   " + 
					g.getFirst().getName() + " vs " +
					g.getSecond().getName() );
	}
	
	public ViewFrame(JPanel p)
	{
		this();
		getContentPane().add(p);
		setSize( p.getWidth()+7, p.getHeight()+26 );
	}
}

