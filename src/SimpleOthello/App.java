// 2001/12/16

package SimpleOthello;

import javax.swing.*;

public class App
{
	public static void main(String args[])
	{
		GameDialog	 gd;
		ViewFrame	 f;
		Game		 g;
		String		message;
		int  blackCount;
		int  whiteCount;
		int  inputButton;
		
		gd = new GameDialog();
		inputButton = JOptionPane.YES_OPTION;
		while ( inputButton == JOptionPane.YES_OPTION ) {
			gd.setLocation(300,100);
			gd.show();
			while ( (g=gd.getGame()) == null )
				;
			gd.hide();
			
			f = new ViewFrame(g);
			f.setVisible(true);
			
			g.start();
			
			blackCount = g.getBoard().getCount(Board.BLACK);
			whiteCount = g.getBoard().getCount(Board.WHITE);
			message = "黒 : " + blackCount + "個\n" +
					  "白 : " + whiteCount + "個\n";
			if ( g.isDraw() )
				message += "勝負は引き分けです\n";
			else {
				message += g.getWinner().getName();
				message += "プレイヤーの勝ちです\n";
			}
			message += "ゲームを続けますか？";
			
			inputButton = JOptionPane.showConfirmDialog( f, message,
							   "ゲーム結果", JOptionPane.YES_NO_OPTION );
			
			f.setVisible(false);
			
			g = null;
			f = null;
			System.gc();
		}
		
		System.exit(0);
	}
}

