// 2001/12/30

package SimpleOthello;

class SimpleAIPlayer extends AIPlayer
{
	public String getName()
	{
		return "AI-レベル１";
	}

	protected void inNext(Board b, int friendly)
	{
		Board  temp;
		int	   p = 0;
		int	   count = 0;

		for ( int i=0; i<8*8; i++ ) {
			temp = new Board( b );
			if ( temp.put( friendly, i ) && temp.getCount(friendly) > count ) {
				count = temp.getCount( friendly );
				p = i;
			}
		}
		b.put( friendly, p );
	}
}

