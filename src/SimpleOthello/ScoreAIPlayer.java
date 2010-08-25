// 2001/12/30

package SimpleOthello;

class ScoreAIPlayer extends AIPlayer
{
/*	private int _scoreList[] =
				{ 300, -60,	 60,  25,  25,	60, -60, 300,
				  -60, -70, -20, -20, -20, -20, -70, -60,
				   60, -20,	 50,  15,  15,	50, -20,  60,
				   25, -20,	 15,   0,	0,	15, -20,  25,
				   25, -20,	 15,   0,	0,	15, -20,  25,
				   60, -20,	 50,  15,  15,	50, -20,  60,
				  -60, -70, -20, -20, -20, -20, -70, -60,
				  300, -60,	 60,  25,  25,	60, -60, 300 } ;//*/

	public String getName()
	{
		return "AI-レベル２";
	}

	protected void inNext(Board b, int friendly)
	{
		Board  temp;
		int p = 0;
		int score = -999999;

		for ( int i=0; i<8*8; i++ ) {
			temp = new Board(b);
			if ( temp.put(friendly,i) && getScore(temp,friendly) > score ) {
				score = getScore( temp, friendly );
				p = i;
			}
		}
		b.put( friendly, p );
	}

	protected int getScore( Board b, int friendly )
	{
		int enemy = Board.getEnemy( friendly );
		int score = 0;
		int ScoreList[] = {	 99, -10,  6,  5,  5,  6, -10,	99
						  , -10, -30, -5, -3, -3, -5, -30, -10
						  ,	  6,  -5,  6,  2,  2,  6,  -5,	 6
						  ,	  5,  -3,  2,  0,  0,  2,  -3,	 5
						  ,	  5,  -3,  2,  0,  0,  2,  -3,	 5
						  ,	  6,  -5,  6,  2,  2,  6,  -5,	 6
						  , -10, -30, -5, -3, -3, -5, -30, -10
						  ,	 99, -10,  6,  5,  5,  6, -10,	99 } ;//*/
						  /*{ 99, -8,  8,  6,  6,  8, -8, 99
						  , -8,-24, -4, -3, -3, -4,-24, -8
						  ,	 8, -4,	 7,	 4,	 4,	 7, -4,	 8
						  ,	 6, -3,	 4,	 0,	 0,	 4, -3,	 6
						  ,	 6, -3,	 4,	 0,	 0,	 4, -3,	 6
						  ,	 8, -4,	 7,	 4,	 4,	 7, -4,	 8
						  , -8,-24, -4, -3, -3, -4,-24, -8
						  , 99, -8,	 8,	 6,	 6,	 8, -8, 99 };//*/

/*		for ( int i=0; i<8*8; i++ )
			if ( b.at( i ) == friendly )
				score += _scoreList[ i ];
			else if ( b.at( i ) == enemy )
				score -= _scoreList[ i ];//*/
		for ( int i=0; i<8*8; i++ )
			if ( b.at( i ) == friendly )
				score += ScoreList[ i ];
			else if ( b.at( i ) == enemy )
				score -= ScoreList[ i ];

		return	score;
	}
}

