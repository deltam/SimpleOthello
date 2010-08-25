// 2001/12/16

package SimpleOthello;

import java.awt.*;
import javax.swing.*;

class ViewPanel extends JPanel
{
	private Board  _board;

	public ViewPanel()
	{
		setBackground(Color.white);
	}

	public ViewPanel( Board b )
	{
		this();
		_board = b;
	}

	public void setBoard(Board b) { _board = b; }

	public void paint(Graphics g)
	{
//		int w = getWidth();
//		int h = getHeight();
		int w = 360;
		int h = 360;

		g.clearRect( 0, 0, w, h );

		super.paint(g);

		g.setColor(Color.black);
		for (int i=0; i<=w; i += w/8)
			g.drawLine( i, 0, i, h );
		for (int j=0; j<=h; j += h/8)
			g.drawLine( 0, j, w, j);

		for ( int i=0; i<8; i++ )
			for ( int j=0; j<8; j++ )
				switch( _board.at(i,j) ) {
				case Board.BLACK:
					g.fillOval( i*w/8+2, j*h/8+2, w/8-4, h/8-4 );
					break;
				case Board.WHITE:
					g.drawOval( i*w/8+2, j*h/8+2, w/8-4, h/8-4 );
					break;
				case Board.EMPTY:
					break;
				default:
					break;
				}
	}
}

