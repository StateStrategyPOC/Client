package client;

import client_store.ClientStore;
import client_store_actions.ClientRequestDiscardObjectCardAction;
import client_store_actions.ClientPublishChatMessageAction;
import client_store_actions.ClientRequestEndTurnAction;
import client_store_actions.ClientRequestUseObjectCardAction;
import common.GameMap;
import common.ObjectCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Represents the main window in which are the map, log, cards, etc... are
 * displayed
 *
 */
public class GUIGamePane extends JPanel {

    private final ClientStore CLIENT_STORE = ClientStore.getInstance();
    private final JLabel connectionAlert = new JLabel("The connection with the server is not active");
    private final JLabel infoMsg = new JLabel();


    private final DefaultListModel<String> logModel;
    private final JScrollPane logScrollPane;

    private final JLabel stateLabel;

    private final JPanel inputPane;
    private final JPanel rightPanel;
    private final JPanel cardsPane;

    private final JPanel holdingPanel;
    private final GUIMap mapPanel;

    private final JButton endTurnButton;
    private final JButton msgButton;

    private JTextField chatTextField;

    private final JPopupMenu humanUseCardMenu = new JPopupMenu();
    private final JPopupMenu humanUseDiscCardMenu = new JPopupMenu();
    private final JPopupMenu alienCardMenu = new JPopupMenu();
    private final JPopupMenu emptyMenu = new JPopupMenu();
    private JPopupMenu currentCardMenu = new JPopupMenu();

    private ObjectCard selectedObjectCard;

    public GUIGamePane() {

        mapPanel = new GUIMap();
        logModel = new DefaultListModel<>();
        JList<String> logPane = new JList<>(logModel);
        logScrollPane = new JScrollPane();
        logScrollPane.setViewportView(logPane);
        holdingPanel = new JPanel();

        stateLabel = new JLabel();
        stateLabel.setFont(new Font("Arial", Font.BOLD, 25));
        stateLabel.setForeground(Color.LIGHT_GRAY);
        inputPane = new JPanel();
        rightPanel = new JPanel(new GridBagLayout());
        cardsPane = new JPanel(new FlowLayout());

        JMenuItem humanDiscardItem = new JMenuItem("Discard");
        JMenuItem useCardItem = new JMenuItem("Use Card");
        this.humanUseDiscCardMenu.add(humanDiscardItem);
        this.humanUseDiscCardMenu.add(useCardItem);

        JMenuItem humanUseOnlyItem = new JMenuItem("Use card");
        this.humanUseCardMenu.add(humanUseOnlyItem);

        JMenuItem alienDiscardItem = new JMenuItem("Discard");
        alienCardMenu.add(alienDiscardItem);

        endTurnButton = new JButton("End Turn!");
        msgButton = new JButton("Send");

        // Button events definition
        endTurnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CLIENT_STORE.propagateAction(new ClientRequestEndTurnAction());
            }
        });

        msgButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CLIENT_STORE.propagateAction(new ClientPublishChatMessageAction(chatTextField.getText()));
                chatTextField.setText("");
            }
        });

        // Event definitions
        useCardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CLIENT_STORE.propagateAction(new ClientRequestUseObjectCardAction(selectedObjectCard));
            }
        });
        humanUseOnlyItem.addActionListener(useCardItem.getActionListeners()[0]);

        alienDiscardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CLIENT_STORE.propagateAction(new ClientRequestDiscardObjectCardAction(selectedObjectCard));
            }
        });
        humanDiscardItem.addActionListener(alienDiscardItem
                .getActionListeners()[0]);

        currentCardMenu = emptyMenu;
    }

    /**
     * Gets the panel containing the game map
     *
     * @return the panel containing the game map
     */
    public GUIMap getMapPane() {
        return this.mapPanel;
    }

    /**
     * Inits the gui for the user displaying the mapName game map
     */
    public void load(GameMap map) {

        mapPanel.displayGameMap(map);

        GridBagConstraints c = new GridBagConstraints();

        setMaximumSize(getPreferredSize());
        setBackground(Color.BLACK);

        mapPanel.setPreferredSize(new Dimension(800, 588));

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        mapPanel.setVisible(true);
        add(mapPanel, c);

        c.gridy = 1;
        stateLabel.setMaximumSize(stateLabel.getPreferredSize());
        add(stateLabel, c);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        logScrollPane.setPreferredSize(new Dimension(325, 190));

        holdingPanel.setMaximumSize(new Dimension(325, 200));
        holdingPanel.add(logScrollPane);
        holdingPanel.setBackground(Color.BLACK);
        logScrollPane
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        logScrollPane
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        logModel.addElement("Welcome to Escape from aliens in outer space");
        this.connectionAlert.setFont(new Font("Arial", Font.BOLD, 14));
        this.connectionAlert.setForeground(Color.LIGHT_GRAY);
        //rightPanel.add(connectionAlert);
        //this.connectionAlert.setVisible(false);
        //rightPanel.add(infoMsg);
        this.infoMsg.setFont(new Font("Arial", Font.BOLD, 14));
        this.infoMsg.setForeground(Color.LIGHT_GRAY);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(holdingPanel);
        //this.infoMsg.setVisible(true);

        chatTextField = new JTextField(20);
        chatTextField.setMaximumSize(chatTextField.getPreferredSize());
        msgButton.setMaximumSize(new Dimension(200, 50));
        inputPane.setLayout(new FlowLayout());
        inputPane.add(chatTextField);
        inputPane.add(msgButton);
        inputPane.setMaximumSize(inputPane.getPreferredSize());
        rightPanel.add(inputPane);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        endTurnButton.setPreferredSize(new Dimension(150, 20));
        endTurnButton.setMaximumSize(new Dimension(300, 20));
        endTurnButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        endTurnButton.setVisible(false);
        //endTurnButton.setEnabled(false);
        endTurnButton.setIcon(new ImageIcon("endBtn.png"));
        rightPanel.add(endTurnButton);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        cardsPane.setBackground(Color.BLACK);
        cardsPane.setMaximumSize(new Dimension(350, 600));
        rightPanel.add(cardsPane);

        rightPanel.setBackground(Color.BLACK);
        rightPanel.setMaximumSize(new Dimension(400, 650));
        rightPanel.setPreferredSize(new Dimension(400, 675));

        c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        add(rightPanel, c);
    }

    /**
     * Appends a message to the editor pane
     */
    public void appendMsg(String msg) {
        String[] targetString = msg.split("\n");
        for (String line : targetString) {
            this.logModel.addElement(line);
        }
    }

    public void resetMsgs(){
        this.logModel.clear();
    }

    /**
     * Changes the state message to be shown to the player
     *
     * @param msg the new state message to be shown to the player
     */
    public void setStateMessage(String msg) {
        this.stateLabel.setText(msg);
    }

    /**
     * Displays an object card in the proper panel
     *
     * @param objectCard the object card to display in the proper panel
     */
    public void addCardToPanel(ObjectCard objectCard) {
        ResourceMapper mapper = new ResourceMapper();
        ObjectCardLabel cardLbl = new ObjectCardLabel(objectCard);
        cardLbl.setIcon(new ImageIcon(mapper.getCardImage(objectCard)));
        cardLbl.setMaximumSize(cardLbl.getPreferredSize());
        cardsPane.add(cardLbl);

        cardLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ObjectCardLabel l = (ObjectCardLabel) e.getSource();
                selectedObjectCard = l.getCard();
                showMenu(l, e);
            }
        });
        this.cardsPane.repaint();
    }

    /**
     * Removes all the cards from the card panel
     */
    public void refreshCardPanel(List<ObjectCard> objectCards) {
        cardsPane.removeAll();
        for (ObjectCard objectCard : objectCards){
            this.addCardToPanel(objectCard);
        }
    }

    /**
     * Changes the menu displayed when the user clicks on a object card label
     *
     * @param type the type of the menu to be displayed
     */
    public void changeCardMenu(MenuType type) {
        switch (type) {
            case HUMAN_USE_MENU:
                this.currentCardMenu = this.humanUseCardMenu;
                break;
            case HUMAN_USE_DISC_MENU:
                this.currentCardMenu = this.humanUseDiscCardMenu;
                break;
            case ALIEN_CARD_MENU:
                this.currentCardMenu = alienCardMenu;
                break;
            default:
                this.currentCardMenu = emptyMenu;
                break;
        }
    }
    public void setInfoMsg(String infoMsg){
        this.infoMsg.setText(infoMsg);
    }

    public void setSectorMenu(MenuType mode) {
        this.mapPanel.changeMapMenu(mode);
    }

    /**
     * Displays on the screen the given object card label
     */
    private void showMenu(ObjectCardLabel lbl, MouseEvent e) {
        this.currentCardMenu.show(lbl, e.getX(), e.getY());
    }

    /**
     * Enables or not the end turn button
     *
     * @param show the flag that indicates if the end button should be enabled or
     *             not
     */
    public void showEndTurnButton(boolean show) {
        //this.endTurnButton.setEnabled(show);
        this.endTurnButton.setVisible(show);
        this.repaint();
    }

    public void alertConnectionProblem(boolean isConnectionActive) {
        this.connectionAlert.setVisible(isConnectionActive);
    }
}