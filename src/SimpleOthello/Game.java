// 2001/12/16

package SimpleOthello;

import javax.swing.*;

class Game
{
	private Board	 _board;
	private JPanel	 _panel;
	private Player	 _first;
	private Player	 _second;

	public Game()
	{
		Board	   b = new Board();
		ViewPanel  p = new ViewPanel();

		p.setSize( 360, 360 );
		p.setBoard( b );

		_board = b;
		_panel = p;
	}

	public Game(Board b, JPanel p, Player fp, Player sp)
	{
		_board	= b;
		_panel = p;
		_first = fp;
		_second = sp;
	}

	public void setBoard(Board b) { _board = b; }
	public void setPanel(JPanel p) { _panel = p; }
	public void setFirst(Player p) { _first = p; }
	public void setSecond(Player p) { _second = p; }

	public Board getBoard() { return  _board; }
	public JPanel getPanel() { return  _panel; }
	public Player getFirst() { return  _first; }
	public Player getSecond() { return	_second; }

	public void start()
	{
		while ( true ) {
			try {
				getFirst().next( getBoard(), Board.BLACK );
				getPanel().repaint();
				Thread.sleep( 500 );
				if ( getBoard().isEnd() )
					break;
				getSecond().next( getBoard(), Board.WHITE );
				getPanel().repaint();
				Thread.sleep( 500 );
				if ( getBoard().isEnd() )
					break;
			} catch (InterruptedException e) {}
		}
	}

	public boolean isDraw()
	{
		int	 bc = getBoard().getCount( Board.BLACK );
		int	 wc = getBoard().getCount( Board.WHITE );
		if ( bc == wc )
			return	true;
		else
			return	false;
	}

	public Player getWinner()
	{
		int	 bc = getBoard().getCount( Board.BLACK );
		int	 wc = getBoard().getCount( Board.WHITE );
		if ( !isDraw() )
			return	( bc > wc ) ? getFirst(): getSecond();
		else
			return	null;
	}

	public Player getLoser()
	{
		int	 bc = getBoard().getCount( Board.BLACK );
		int	 wc = getBoard().getCount( Board.WHITE );
		if ( !isDraw() )
			return	( bc < wc ) ? getFirst(): getSecond();
		else
			return	null;
	}
}

