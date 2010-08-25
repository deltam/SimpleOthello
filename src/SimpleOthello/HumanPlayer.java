// 2001/12/16

package SimpleOthello;

import java.awt.event.*;
import javax.swing.*;

class HumanPlayer extends Player implements MouseListener
{
	private int _point;
	private boolean _isChanged;
	private int _cellWidth;
	private int _cellHeight;

	public HumanPlayer() {}

	public HumanPlayer(JPanel p)
	{
		setPanelSize( p.getWidth(), p.getHeight() );
		p.addMouseListener(this);
	}

	public String getName()
	{
		return "人間";
	}

	protected void inNext(Board b, int friendly)
	{
		do {
			_isChanged = false;
			while ( !_isChanged ) ;
		}
		while ( !b.put( friendly, _point ) ) ;
	}

	public void setPanelSize(int x, int y)
	{
		_cellWidth = x/8;
		_cellHeight = y/8;
	}

	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX()/_cellWidth;
		int y = e.getY()/_cellHeight;
		_point = x + y*8;
		_isChanged = true;
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}

