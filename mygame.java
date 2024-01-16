import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class mygame extends JFrame implements ActionListener {
    JLabel heading,clockLabel;
    Font font=new Font("",Font.BOLD,40 );
    JPanel mainPanel;

    JButton []btns = new JButton[9];

    /// game intance variable.....
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;

    int wps[][]= {
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    
    };

    int winner=2;

    boolean gameOver=false;


    mygame(){
        System.out.println("creating instance of game");
        setTitle("My TicTac Toe game...");
        setSize(850,850);
        ImageIcon icon= new ImageIcon("image/img2.jpeg");
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
        
    }
     private void createGUI()
    {
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout() );

        //north heading/....
        heading=new JLabel("Tic Tac Toe");
        //heading.setIcon(new ImageIcon("image/img2.jpeg") );
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(heading,BorderLayout.NORTH);


        //creating object of jleble for clock
        clockLabel =new JLabel("Clock");
        clockLabel.setForeground(Color.white);
        
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel, BorderLayout.SOUTH); 


            Thread t=new Thread()
        { 
            public void run()
            {
                try
                {
                     while(true)
                {
                    String datetime =new Date().toLocaleString();

                    clockLabel.setText(datetime);

                    Thread.sleep(1000);
            

                }

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }


            }

        };

        t.start();

        ///////////panel section....
        mainPanel=new JPanel();

        mainPanel.setLayout(new GridLayout(3, 3));

        for( int i=1;i<=9;i++)
        {
            JButton btn=new JButton();
            //btn.setIcon(new ImageIcon("image/img1.jpeg"));
            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[ i-1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(mainPanel,BorderLayout.CENTER);






    } // it's make a dynamic button
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton) e.getSource();
        String nameStr = currentButton.getName();
       int name=Integer.parseInt(nameStr.trim());


       ///// game over part
       if(gameOver )
       {
        JOptionPane.showMessageDialog(this, "GAME ALREADY OVER....");
        return;
       }
       


       ////// GAME START PART
          if(gameChances[name]==2)
          {
            if(activePlayer==1)
            {
                currentButton.setIcon(new ImageIcon("image/img3.jpeg"));

                 gameChances[name]=activePlayer;
                activePlayer=0;
            }
            else
            {
                currentButton.setIcon(new ImageIcon("image/img1.jpeg"));

                 gameChances[name]=activePlayer;
                activePlayer=1;
            }
             //find the winner................ 
             for(int [] temp:wps)
             {
                if((gameChances[temp[0]]== gameChances[temp[1]]) && (gameChances[temp[1]]== gameChances[temp[2]])&&gameChances[temp[2]] !=2 )
                {
                    winner=gameChances[temp[0]];
                    gameOver=true;
                    JOptionPane.showMessageDialog(null, "PLAYER "+ winner + " HAS WON THE GAME..");
                    int i = JOptionPane.showConfirmDialog(this, "DO YOU WANT TO PLAY MORE??");
                    if( i==0)
                    {
                        this.setVisible(false);
                        new mygame();
                    }
                    else if( i==1)
                    { 
                       System.exit(2345);
                    }
                    else
                    {

                    }
                    System.out.println(i);
                    break;
                }
             }
             

             ///GAME IS DRAW THEN 
             int c=0;
             for(int x : gameChances)
             {
                if(x==2)
                {
                    c++;
                    break;
                }
             }
               if(c==0 && gameOver== false)
               {
                JOptionPane.showMessageDialog(null, "it's draw...");

                int i=JOptionPane.showConfirmDialog(this,"play more??...");
                if(i==0)
                {
                    this.setVisible(false);
                    new mygame();
                }
                else if(i==1)
                {
                    System.exit(1234);

                }
                else
                {

                }
                gameOver=true;
                
               }
         
         
            }
          
       else
       {
        JOptionPane.showMessageDialog(this, "Position already  occupied");
       }
        
        
      

        
    }
}
