package client;

import common.Card;
import common.ObjectCard;
import common.SectorCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a pop-up view to shown a drawn Sector Card and possibly a drawn Object Card
 */
public class CardSplashScreen extends JWindow {

	public CardSplashScreen(Frame owner)
    {
        super(owner);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setLocation(screenSize.width/2 - 200, screenSize.height/2 - 170);
        addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    setVisible(false);
                    dispose();
                }
            });
    }

    public void showCards(Card sectorCard, Card objectCard){
        if (sectorCard != null){
            ResourceMapper mapper = new ResourceMapper();
            JLabel cardLbl1 = new JLabel();
            JLabel cardLbl2 = new JLabel();
            cardLbl1.setIcon(new ImageIcon(mapper.getCardImage(sectorCard)));
            if (objectCard != null){
                cardLbl2.setIcon(new ImageIcon(mapper.getCardImage(objectCard)));
            }

            JPanel pane = new JPanel(new FlowLayout());
            pane.setPreferredSize(new Dimension(400,192));
            pane.setBackground(Color.BLACK);

            cardLbl1.setAlignmentX(CENTER_ALIGNMENT);
            cardLbl2.setAlignmentX(CENTER_ALIGNMENT);
            cardLbl1.setAlignmentY(CENTER_ALIGNMENT);
            cardLbl2.setAlignmentY(CENTER_ALIGNMENT);

            pane.add(cardLbl1);
            pane.add(cardLbl2);

            getContentPane().add(pane, BorderLayout.CENTER);
            pack();
            setVisible(true);
        }

    }
}
