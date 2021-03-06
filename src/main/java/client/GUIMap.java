package client;

import client_store.ClientStore;
import client_store_actions.ClientRequestAttackAction;
import client_store_actions.ClientRequestGlobalNoiseAction;
import client_store_actions.ClientRequestLightsAction;
import client_store_actions.ClientRequestMoveToSectorAction;
import common.Coordinate;
import common.GameMap;
import common.Sector;
import common.SectorType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the panel in which is displayed the game map
 *
 */
public class GUIMap extends JLayeredPane {
	static final long serialVersionUID = 1L;
	private final ClientStore CLIENT_STORE = ClientStore.getInstance();
	private final transient List<SectorLabel> sectorsList;
	private final transient List<SectorLabel> lightedSectors;

	private static final int LAYER_BACKGROUND = 1;
	private static final int SELECT_LAYER = 5;
	private static final int UP_SELECT_LAYER = 10;

	private SectorLabel selectedSector;

	private JPopupMenu currentMapMenu;

	private final JPopupMenu noiseMenu = new JPopupMenu();
	private final JPopupMenu lightMenu = new JPopupMenu();
	private final JPopupMenu humanNormalMenu = new JPopupMenu();
	private final JPopupMenu alienNormalMenu = new JPopupMenu();
	private final JPopupMenu emptyMenu = new JPopupMenu();
	private final JPopupMenu humanAttackMenu = new JPopupMenu();

	public GUIMap() {

		sectorsList = new ArrayList<>();
		lightedSectors = new ArrayList<>();

		JMenuItem alienMoveItem = new JMenuItem("Move");
		JMenuItem attackItem = new JMenuItem("Attack");
		JMenuItem humanMoveItem = new JMenuItem("Move");
		humanNormalMenu.add(humanMoveItem);

		alienNormalMenu.add(alienMoveItem);
		alienNormalMenu.add(attackItem);

		JMenuItem noiseItem = new JMenuItem("Noise");
		noiseMenu.add(noiseItem);

		JMenuItem lightItem = new JMenuItem("Light");
		lightMenu.add(lightItem);

		JMenuItem humanAttack = new JMenuItem("Attack");
		humanAttackMenu.add(humanAttack);

		// Define the event when the user clicks on item in a popup menu
		alienMoveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CLIENT_STORE.propagateAction(new ClientRequestMoveToSectorAction(selectedSector.getCoordinate()));
			}
		});

		humanMoveItem.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CLIENT_STORE.propagateAction(new ClientRequestMoveToSectorAction(selectedSector.getCoordinate()));
					}
				}
		);

		attackItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CLIENT_STORE.propagateAction(new ClientRequestAttackAction(selectedSector.getCoordinate()));
			}
		});
		humanAttack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                CLIENT_STORE.propagateAction(new ClientRequestAttackAction(selectedSector.getCoordinate()));
			}
		});

		noiseItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    CLIENT_STORE.propagateAction(new ClientRequestGlobalNoiseAction(selectedSector.getCoordinate(),true));
			}
		});

		lightItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                CLIENT_STORE.propagateAction(new ClientRequestLightsAction(selectedSector.getCoordinate()));
			}
		});

		currentMapMenu = emptyMenu;
	}

	/**
	 * @return The list of sectors displayed in the panel
	 */
	public List<SectorLabel> getSectors() {
		return sectorsList;
	}

	/**
	 * @return The list of sectors that has been highlighted
	 */
	public List<SectorLabel> getLightedSectors() {
		return this.lightedSectors;
	}

	/**
	 * This method inits the map panel loading the map from the correct file,
	 * then adds the proper SectorLabel to the panel in order to display it on
	 * the GUI
	 */
	public void displayGameMap(GameMap gameMap) {
		JLabel backgroundLabel = new JLabel(new ImageIcon("maps/back.png"));
		backgroundLabel.setBounds(0, 0, 800, 600);
		add(backgroundLabel);
		this.setLayer(backgroundLabel, LAYER_BACKGROUND);

		// Some offset to optimize the appearance of the sectors on the map
		// panel
		int factorX = 34;
		int factorY = 38;
		int offset = 19;
		String image = "";

		/*
		 * Analyze the graph produced by the map loading and creates the
		 * SectorLabel that will be displayed in the panel
		 */
		for (int i = 97; i < 120; i++) {
			for (int j = 1; j < 15; j++) {
				boolean label = false;
				Sector sect = gameMap
						.getSectorByCoords(new Coordinate((char) i, j));
				if (sect != null) {
					if (sect.getSectorType() == SectorType.SAFE) {
						image = "safe.png";
						label = true;
					} else if (sect.getSectorType() == SectorType.DANGEROUS) {
						image = "dang.png";
						label = true;
					} else if (sect.getSectorType() == SectorType.CLOSED_RESCUE
							|| sect.getSectorType() == SectorType.OPEN_RESCUE) {
						image = "rescue.png";
					} else if (sect.getSectorType() == SectorType.HUMAN) {
						image = "human.png";
					} else if (sect.getSectorType() == SectorType.ALIEN) {
						image = "alien.png";
					}
				} else {
					image = "";
				}

				if (i % 2 == 0) {
					this.addSectorToGridLabel((i - 97) * factorX + 3, offset
							+ ((j - 1) * factorY) + 26, image, new Coordinate(
							(char) i, j), label);
				} else {
					this.addSectorToGridLabel((i - 97) * factorX + 3, (j - 1)
							* factorY + 26, image, new Coordinate((char) i, j),
							label);
				}
			}
		}
		backgroundLabel.setVisible(true);
	}

	/**
	 * Util method(used internally by the class) Adds a sector of coordinate x,y
	 * to the map panel, adding the proper mouse click event
	 */
	private void addSectorToGridLabel(int x, int y, final String image,
			Coordinate coords, boolean label) {
		SectorLabel lbl = new SectorLabel(coords, image);
		sectorsList.add(lbl);

		if (label)
			lbl.setText(coords.getX() + "" + coords.getY());
		lbl.setIcon(new ImageIcon("sectors" + File.separator + image));
		lbl.setHorizontalTextPosition(JLabel.CENTER);
		lbl.setBounds(x, y, 45, 39);

		if (!("").equals(image)) {
			lbl.setToolTipText(coords.getX() + "" + coords.getY());
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					SectorLabel l = (SectorLabel) e.getSource();
					showMenu(l, e);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					SectorLabel l = (SectorLabel) e.getSource();
					l.setIcon(new ImageIcon("sectors" + File.separator + "S"
							+ l.getImageFilename()));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					SectorLabel l = (SectorLabel) e.getSource();
					l.setIcon(new ImageIcon("sectors" + File.separator
							+ l.getImageFilename()));
				}
			});
		}

		add(lbl);
		setLayer(lbl, SELECT_LAYER);
	}

	/**
	 * Util method(used internally by the class) Display the popup menu when the
	 * user clicks on a sector
	 */
	private void showMenu(SectorLabel lbl, MouseEvent e) {
		this.selectedSector = lbl;
		this.currentMapMenu.show(lbl, e.getX(), e.getY());
	}

	/**
	 * Highlight a sector on the GUI map by changing its appearance(the sector
	 * is colored in red) in the highlighted sector is displayed the specified
	 * text, and when the mouse hover on it the toolTipText will be displayed If
	 * the specified sector doesn't exist the map appearance is unchanged
	 * 
	 * @param coords
	 *            The coordinates of the sector to highlight
	 * @param text
	 *            The text displayed in the highlighted sector
	 * @param toolTipText
	 *            The text displayed when the mouse hover on the sector
	 */
	public void lightSector(Coordinate coords, String text, String toolTipText) {

		for (SectorLabel s : sectorsList) {
			if (s.getCoordinate().equals(coords)) {
				SectorLabel light = new SectorLabel(s.getCoordinate(), "");
				light.setBounds(s.getBounds());
				light.setIcon(new ImageIcon("sectors" + File.separator
						+ "lighted.png"));
				light.setHorizontalTextPosition(JLabel.CENTER);
				light.setText(text);
				light.setToolTipText(toolTipText);
				lightedSectors.add(light);
				add(light);
				setLayer(light, UP_SELECT_LAYER);
				break;
			}
		}
		this.repaint();
	}

	/**
	 * Delight all sectors lighted in the map
	 */
	public void delightAllSectors() {
		List<SectorLabel> tmp = new ArrayList<>(lightedSectors);
		for (SectorLabel s : tmp) {
			lightedSectors.remove(lightedSectors.indexOf(s));
			remove(s);
		}
	}

	/**
	 * Changes the menu displayed when the user click on a sector
	 */
	public void changeMapMenu(MenuType mode) {
		switch (mode) {
		case HUMAN_INITIAL:
			this.currentMapMenu = humanNormalMenu;
			break;
		case ALIEN_INITIAL:
			this.currentMapMenu = alienNormalMenu;
			break;
		case LIGHT_MENU:
			this.currentMapMenu = lightMenu;
			break;
		case NOISE_MENU:
			this.currentMapMenu = noiseMenu;
			break;
		case ATTACK_MENU:
			this.currentMapMenu = humanAttackMenu;
			break;
		default:
			this.currentMapMenu = emptyMenu;
		}
	}

}
