// 2001/12/16
// ゲームのプレイヤーを表す抽象クラス


package SimpleOthello;

abstract class Player
{
	public String getName()
	{
		return	"Player";
	}

	// 次の手があるかどうか判断して無限ループを避ける。
	public void next(Board b, int friendly)
	{
		if ( !b.isEnd() ) {
			for ( int i=0; i<8*8; i++ ) {
				if ( b.isPut( friendly, i ) ) {
					inNext( b, friendly );
					break;
				}
			}
		}
	}
	// 実際に一手を探す抽象メソッド
	protected abstract void inNext(Board b,int friendly);
}

