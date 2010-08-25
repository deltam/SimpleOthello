// 2002/01/08

package SimpleOthello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameDialog extends JDialog implements ActionListener
{
	private JButton  _bStart;
	private JButton  _bExit;
	private JRadioButton  _rbHumanFirst;
	private JRadioButton  _rbHumanSecond;
	private JRadioButton  _rbSimpleFirst;
	private JRadioButton  _rbSimpleSecond;
	private JRadioButton  _rbScoreFirst;
	private JRadioButton  _rbScoreSecond;
	private JRadioButton  _rbSmartFirst;
	private JRadioButton  _rbSmartSecond;
	private Game  _game;
	
	private final int HUMAN  = 0;
	private final int SIMPLE = 1;
	private final int SCORE  = 2;
	private final int SMART  = 3;
	
	
	public GameDialog()
	{
		ButtonGroup  groupFirst;
		ButtonGroup  groupSecond;
		JPanel	jp1, jp2, jp3, jp4, jps, jpb;
		Container  cp = getContentPane();
		
		
		setTitle("対戦者の設定");
		
		_bStart = new JButton("開始");
		_bStart.addActionListener(this);
		
		_bExit = new JButton("終了");
		_bExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		_rbHumanFirst = new JRadioButton();
		_rbSimpleFirst = new JRadioButton();
		_rbScoreFirst = new JRadioButton();
		_rbSmartFirst = new JRadioButton();
		groupFirst = new ButtonGroup();
		groupFirst.add(_rbHumanFirst);
		groupFirst.add(_rbSimpleFirst);
		groupFirst.add(_rbScoreFirst);
		groupFirst.add(_rbSmartFirst);
		
		_rbHumanSecond = new JRadioButton();
		_rbSimpleSecond = new JRadioButton();
		_rbScoreSecond = new JRadioButton();
		_rbSmartSecond = new JRadioButton();
		groupSecond = new ButtonGroup();
		groupSecond.add(_rbHumanSecond);
		groupSecond.add(_rbSimpleSecond);
		groupSecond.add(_rbScoreSecond);
		groupSecond.add(_rbSmartSecond);
		
		jp1 = new JPanel();
		jp1.setLayout(new BoxLayout(jp1,BoxLayout.X_AXIS) );
		jp1.add(_rbHumanFirst);
		jp1.add(_rbHumanSecond);
		jp1.add(new JLabel("人間"));
		
		jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2,BoxLayout.X_AXIS) );
		jp2.add(_rbSimpleFirst);
		jp2.add(_rbSimpleSecond);
		jp2.add(new JLabel("AI-レベル１"));
		
		jp3 = new JPanel();
		jp3.setLayout(new BoxLayout(jp3,BoxLayout.X_AXIS) );
		jp3.add(_rbScoreFirst);
		jp3.add(_rbScoreSecond);
		jp3.add(new JLabel("AI-レベル２"));
		
		jp4 = new JPanel();
		jp4.setLayout(new BoxLayout(jp4,BoxLayout.X_AXIS) );
		jp4.add(_rbSmartFirst);
		jp4.add(_rbSmartSecond);
		jp4.add(new JLabel("AI-レベル３"));
		
		jps = new JPanel();
		jps.setLayout(new GridLayout(4,3));
		jps.add(jp1);
		jps.add(jp2);
		jps.add(jp3);
		jps.add(jp4);
		
		jpb = new JPanel();
		jpb.setLayout(new FlowLayout());
		jpb.add(_bStart);
		jpb.add(_bExit);
		
		cp.setLayout(new FlowLayout());
		cp.add(jps);
		cp.add(jpb);
		
		_rbHumanFirst.setSelected(true);
		_rbSimpleSecond.setSelected(true);
		
		setSize(200,200);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		_game = null;
	}
	
	public Game getGame()
	{
		Game  temp = _game;
		_game = null;
		return	temp;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int  f = -1;
		int  s = -1;
		
		if ( _rbHumanFirst.isSelected() )
			f = HUMAN;
		else if ( _rbSimpleFirst.isSelected() )
			f = SIMPLE;
		else if ( _rbScoreFirst.isSelected() )
			f = SCORE;
		else if ( _rbSmartFirst.isSelected() )
			f = SMART;
		
		if ( _rbHumanSecond.isSelected() )
			s = HUMAN;
		else if ( _rbSimpleSecond.isSelected() )
			s = SIMPLE;
		else if ( _rbScoreSecond.isSelected() )
			s = SCORE;
		else if ( _rbSmartSecond.isSelected() )
			s = SMART;
		
		_game = createGame(f,s);
	}
	
	private Game createGame(int f, int s)
	{
		Game  g = new Game();
		Player	fp = null;
		Player	sp = null;
		
		switch(f) {
			case HUMAN:
				fp = new HumanPlayer(g.getPanel());
				break;
			case SIMPLE:
				fp = new SimpleAIPlayer();
				break;
			case SCORE:
				fp = new ScoreAIPlayer();
				break;
			case SMART:
				fp = new SmartAIPlayer();
				break;
			default:;
		}
		g.setFirst(fp);
		switch(s) {
			case HUMAN:
				sp = new HumanPlayer(g.getPanel());
				break;
			case SIMPLE:
				sp = new SimpleAIPlayer();
				break;
			case SCORE:
				sp = new ScoreAIPlayer();
				break;
			case SMART:
				sp = new SmartAIPlayer();
				break;
			default:;
		}
		g.setSecond(sp);
		
		return	g;
	}
}

