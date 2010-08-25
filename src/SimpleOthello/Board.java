// 2001/12/16
// 2001/08/14

package SimpleOthello;

class Board
{
	public static final int EMPTY =	 0;
	public static final int WHITE =	 1;
	public static final int BLACK = -1;
	private int _board[] = new int[ 8*8 ];

	public Board()
	{
		_board[ 3 + 3*8 ] = WHITE;
		_board[ 4 + 4*8 ] = WHITE;
		_board[ 3 + 4*8 ] = BLACK;
		_board[ 4 + 3*8 ] = BLACK;
	}
	public Board( Board b )
	{
		for ( int i=0; i<8*8; i++ )
			_board[ i ] = b.at( i );
	}

	public static int getEnemy( int friendly )
	{
		if ( friendly == BLACK || friendly == WHITE )
			return	( friendly == BLACK ) ? WHITE : BLACK;
		else
			return	EMPTY;
	}

	public int at( int p )
	{
		if ( p < 0 || 8*8 <= p )  return  EMPTY;

		return	_board[ p ];
	}
	public int at( int px, int py )
	{
		if ( px < 0 || 8 <= px || py < 0 || 8 <= py ) return EMPTY;

		return	at( px + py*8 );
	}

	public int getCount( int friendly )
	{
		int count = 0;

		if ( friendly != BLACK && friendly != WHITE ) return 0;

		for ( int i=0; i<8*8; i++ )
			if ( _board[ i ] == friendly )
				count++;

		return	count;
	}

	public boolean put( int friendly, int p )
	{
		if ( !isPut( friendly, p ) )  return  false;

		_board[ p ] = friendly;
		turn( p );
		return	true;
	}
	public boolean put( int friendly, int x, int y )
	{
		if ( x < 0 || 8 <= x || y < 0 || 8 <= y ) return false;

		return	put( friendly, x + y*8 );
	}

	// オセロのルールに従って，置たら true, 置けなかったら false を返す
	public boolean isPut( int friendly, int px, int py )
	{
		int enemy = getEnemy( friendly );
		int x, y;

		if ( friendly != BLACK && friendly != WHITE ) return false;
		if ( px < 0 || 8 <= px || py < 0 || 8 <= py ) return false;
		if ( at( px, py ) != EMPTY ) return false;

		for ( int dx = -1; dx<=1; dx++ )
			for ( int dy = -1; dy<=1; dy++ ) {
				x = px+dx;
				y = py+dy;
				if ( 0<=x && x<8 && 0<=y && y<8 )
					while ( at(x,y) == enemy ) {
						x += dx;
						y += dy;
						if ( x<0 || 7<x || y<0 || 7<y )
							break;
						if ( at(x,y) == friendly )
							return	true;
					}
			}

		return false;
	}
	public boolean isPut( int friendly, int p )
	{
		if ( p < 0 || 8*8 <= p ) return false;

		return	isPut( friendly, p%8, p/8 );
	}

	public boolean isPass( int friendly )
	{
		if ( friendly != BLACK && friendly != WHITE ) return false;

		for ( int i=0; i<8*8; i++ )
			if ( isPut( friendly, i ) )
					return	false;

		return	true;
	}

	public boolean isEnd()
	{
		if ( isPass(WHITE) && isPass(BLACK) )
			return	true;
		else
			return	false;
	}

	private void turn( int px, int py )
	{
		int friendly;
		int enemy ;
		int x, y;
		boolean flag;

		if ( px < 0 || 8 <= px || py < 0 || 8 <= py ) return;
		if ( at( px, py ) == EMPTY ) return;

		friendly = at( px, py );
		enemy = getEnemy( friendly );
		for ( int dx = -1; dx<=1; dx++ ) {
			for ( int dy = -1; dy<=1; dy++ ) {
				flag = true;
				x = px;
				y = py;
				do {
					if ( flag ) {
						x += dx;
						y += dy;
						if ( x<0 || 7<x || y<0 || 7<y )
							break;
						if ( at(x,y) == friendly ) {
							flag = false;
							x -= dx;
							y -= dy;
						}
					}
					else {
						if ( x != px || y != py )
							_board[ x + y*8 ] = friendly;
						else
							break;
						x -= dx;
						y -= dy;
					}
				} while ( at(x,y) == enemy ) ;
			}
		}
	}
	private void turn( int p )
	{
		if ( p < 0 || 8*8 <= p ) return;
		turn( p%8, p/8 );
	}
}

