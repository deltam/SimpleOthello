// 2002/01/01
// SmartAIPlayer : 盤面を数手先読みして判断する。


package SimpleOthello;

class SmartAIPlayer extends ScoreAIPlayer
{
	private int	 _count = 0;

	public SmartAIPlayer() { }

	public String getName()
	{
		return "AI-レベル３";
	}

	protected void inNext( Board b, int friendly )
	{
		int depth = 4;
//		if ( _count > 20 )	depth = 4;
//		if ( _count > 25 )	depth = 5;

		recMove( b, friendly, depth );
		_count++;
	}

	private int recMove( Board b, int friendly, int depth )
	{
		int move;
		int score;
		int s;
		Board  brd;
		int enemy = Board.getEnemy( friendly );

		if ( depth <= 0 || b.isEnd() )
			return	( - getScore( b, friendly ) );

		score = -999999;
		move = -1;
		for ( int i=0; i<8*8; i++ ) {
			if ( b.isPut( friendly, i ) ) {
				brd = new Board( b );
				brd.put( friendly, i );
				s = recMove( brd, enemy, depth - 1 );
				if ( s > score ) {
					score = s;
					move = i;
				}
			}
		}
		if ( move == -1 ) {
			s = recMove( b, enemy, depth - 1 );
			if ( s > score ) score = s;
		}

		b.put( friendly, move );
		return	( -score );
	}
}

