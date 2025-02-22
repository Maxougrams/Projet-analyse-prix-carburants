package fr.univrennes.istic.l2gen.Interface;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import fr.univrennes.istic.l2gen.rapport.Fonction;

import java.awt.*;

/**
 * Cette classe représente un ensemble d'onglets dans une interface graphique.
 */
public class Onglet {
    /**
     * Boolean pour indiquer si on a sélectionné Région ou Département.
     */
    private static Boolean granChecked = false; // True = Département / False = Région

    /**
     * Boolean pour indiquer si les stations sont affichées.
     */
    private static Boolean isStationsAffichees = false;

    /**
     * CheckBox pour afficher les stations.
     */
    private static JCheckBox afficheStation;

    /**
     * Panneau d'onglets.
     */
    private JTabbedPane onglets;

    /**
     * Panneau principal contenant les onglets.
     */
    private JPanel panelOnglet = new JPanel();
    private JPanel previ = new JPanel();

    /**
     * Tableau de panneaux pour les diagrammes.
     */
    private static JPanel[] diag = new JPanel[5];

    /**
     * Constructeur de la classe Onglet.
     * Initialise les onglets avec les titres spécifiés et les composants associés.
     * 
     * @param titreOnglet1 Titre du premier onglet.
     * @param titreOnglet2 Titre du deuxième onglet.
     * @param WIDTH        Largeur des composants.
     * @param HEIGTH       Hauteur des composants.
     */
    public Onglet(String titreOnglet1, String titreOnglet2, int WIDTH, int HEIGTH) {
        ///////// Création police de toute l'interface /////////
        Font style1 = new Font("SansSerif", Font.PLAIN, 18);
        Font style2 = new Font("SansSerif", Font.BOLD, 18);
        // Changement de la police pour tous les composants
        UIManager.put("Button.font", style2);
        UIManager.put("Label.font", style2);
        UIManager.put("CheckBox.font", style1);
        UIManager.put("RadioButton.font", style2);
        UIManager.put("ComboBox.font", style1);
        UIManager.put("TextField.font", style2);
        UIManager.put("TextArea.font", style2);
        UIManager.put("List.font", style2);
        UIManager.put("Table.font", style2);
        UIManager.put("TabbedPane.font", style2);
        UIManager.put("MenuBar.font", style2);
        UIManager.put("Menu.font", style2);
        UIManager.put("MenuItem.font", style2);
        UIManager.put("PopupMenu.font", style2);
        UIManager.put("OptionPane.font", style2);
        UIManager.put("TitledBorder.font", style2);

        // Initialisation des onglets
        this.onglets = new JTabbedPane(SwingConstants.TOP);

        // Création onglet 1
        JPanel onglet1 = new JPanel();
        onglet1.setLayout(new BorderLayout());
        onglet1.setPreferredSize(new Dimension(WIDTH, HEIGTH));
        // onglet2.setLayout(new BoxLayout(onglet2,BoxLayout.X_AXIS));
        this.onglets.addTab(titreOnglet1, onglet1);

        // Création onglet 2
        JPanel onglet2 = new JPanel();
        onglet2.setPreferredSize(new Dimension(WIDTH, HEIGTH));
        this.onglets.addTab(titreOnglet2, onglet2);

        // Création d'un sous panel pour l'onglet 1
        JPanel onglet21 = new JPanel();
        onglet21.setSize((int) onglet1.getPreferredSize().getWidth(), (int) onglet1.getPreferredSize().getHeight());
        onglet1.add(onglet21);

        // Création des panneaux pour l'onglet 1
        Region Region = new Region();
        JPanel region = Region.GetRegion();
        Departement Departement = new Departement();
        JPanel dept = Departement.GetDept();
        Statistique statistique = new Statistique();
        JPanel stat = statistique.getPanel();
        Services services = new Services();
        JPanel serv = services.getPanel();
        Carburant carb = new Carburant();
        JPanel carburant = carb.Getcarburant();
        carburant.setLayout(new BoxLayout(carburant, BoxLayout.X_AXIS));

        // Création d'un JScrollPane pour dept
        JScrollPane scrollPaneDept = new JScrollPane(dept);
        scrollPaneDept.getVerticalScrollBar().setUnitIncrement(16); // Défilement plus rapide
        scrollPaneDept.setMaximumSize(new Dimension(230, HEIGTH - 500));
        scrollPaneDept.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // création d'un JscrollPane pour serv
        JScrollPane scrollPaneServ = new JScrollPane(serv);
        scrollPaneServ.getVerticalScrollBar().setUnitIncrement(18);
        scrollPaneServ.setMaximumSize(new Dimension(700, HEIGTH - 500));
        scrollPaneServ.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        ////// Conteneurs pour mieux gérer la position à l'affichage //////
        // Création d'un JPanel pour contenir scrollPaneDept
        JPanel scrollPaneDeptContainer = new JPanel();
        scrollPaneDeptContainer.setLayout(new BoxLayout(scrollPaneDeptContainer, BoxLayout.X_AXIS));
        scrollPaneDeptContainer.add(scrollPaneDept);

        // Création d'un JPanel pour contenir region
        JPanel regionContainer = new JPanel();
        regionContainer.setLayout(new BoxLayout(regionContainer, BoxLayout.Y_AXIS));
        regionContainer.add(region);

        // Création d'un JPanel pour contenir carburant
        JPanel carburantContainer = new JPanel();
        carburantContainer.setLayout(new BoxLayout(carburantContainer, BoxLayout.X_AXIS));
        carburantContainer.add(Box.createHorizontalStrut(180));
        carburantContainer.add(carburant);

        // Création d'un JPanel pour contenir onglet21
        JPanel onglet21Container = new JPanel();
        onglet21Container.setLayout(new BoxLayout(onglet21Container, BoxLayout.Y_AXIS));
        onglet21Container.add(onglet21);
        afficheStation = new JCheckBox("Afficher position des stations les moins chères");
        afficheStation.addItemListener(new ItemListener() { // événement de l'affichage des stations
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    isStationsAffichees = true;
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    isStationsAffichees = false;
                }
            }
        });
        afficheStation.setEnabled(false);
        onglet21Container.add(afficheStation);
        onglet21Container.add(Box.createVerticalStrut(10));
        onglet21Container.add(carburantContainer);

        // Création d'un JPanel pour contenir les stats
        JPanel statContainer = new JPanel();
        statContainer.setLayout(new BoxLayout(statContainer, BoxLayout.Y_AXIS));
        statContainer.add(stat);

        // Création d'un JPanel pour contenir les diag
        JPanel diagContainer = new JPanel();
        diagContainer.setLayout(new BoxLayout(diagContainer, BoxLayout.Y_AXIS));
        Diag diagramme = new Diag();
        diagContainer.add(diagramme.getPanel());
        diagContainer.setVisible(true);
        // Définir une taille maximale pour diagContainer
        diagContainer.setMaximumSize(new Dimension(220, Integer.MAX_VALUE));

        JPanel westContainer = new JPanel();
        westContainer.setLayout(new BoxLayout(westContainer, BoxLayout.X_AXIS));

        // Création d'un panel pour contenir les diag et stats
        JPanel southContainer = new JPanel();
        southContainer.setLayout(new BoxLayout(southContainer, BoxLayout.X_AXIS));
        southContainer.add(statContainer);
        southContainer.add(diagContainer);
        southContainer.add(onglet21Container);

        // Création d'un JPanel pour contenir les services
        JPanel servContainer = new JPanel();
        servContainer.setLayout(new BoxLayout(servContainer, BoxLayout.X_AXIS));
        servContainer.add(scrollPaneServ);

        // Prévisualisation
        JButton previsua = new JButton("Prévisualisation");
        TitledBorder border = BorderFactory.createTitledBorder("Prévisualisation");
        previ.setBorder(border);
        // previ.setVisible(false);
        previsua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TraitementCases test = new TraitementCases();
                if (test.isAnyChecked() == true) {
                    new MessageErreur(
                            "<html><div style='text-align: center;'>Cette fonctionnalité est en cours de développement. <br> Elle sera disponible prochainement, <br> merci de votre soutien et de votre patience.</div></html>");
                    // Nous n'avons pas trouvé la bonne solution, à la fin on partait vers Apache
                    // (puis on a supprimé et changé).
                    /*
                     * try {
                     * // Charger l'image
                     * ImageIcon imageIcon = new ImageIcon("Groupe.png");
                     * 
                     * // Créer un JLabel pour afficher l'image
                     * JLabel label = new JLabel(imageIcon);
                     * 
                     * // Ajouter le JLabel au panel
                     * previ.removeAll();
                     * previ.setLayout(new BorderLayout());
                     * previ.add(label, BorderLayout.CENTER);
                     * previ.revalidate();
                     * previ.repaint();
                     * } catch (Exception ex) {
                     * ex.printStackTrace();
                     * }
                     */
                } else {
                    new MessageErreur(
                            "<html><div style='text-align: center;'>Veuillez sélectionner au moins un carburant, <br> avec au moins un département ou une région afin de générer <br> une prévisualisation.</div></html>");
                }
            }
        });

        // Container prévisualisation et scrollPaneDeptContainer
        JPanel prevDeptContainer = new JPanel();
        prevDeptContainer.setLayout(new BoxLayout(prevDeptContainer, BoxLayout.X_AXIS));
        prevDeptContainer.add(scrollPaneDeptContainer);
        prevDeptContainer.add(previ);

        // Ajout de ces panel à l'onglet 1
        onglet1.add(southContainer, BorderLayout.NORTH);
        onglet1.add(regionContainer, BorderLayout.WEST);
        onglet1.add(prevDeptContainer, BorderLayout.CENTER);
        onglet1.add(servContainer, BorderLayout.EAST);

        // Gestion de la vision au démarrage
        region.setVisible(true);
        scrollPaneDeptContainer.setVisible(false);
        carburantContainer.setVisible(true);
        southContainer.setVisible(true);
        servContainer.setVisible(true);

        // Création des éléments de contrôle pour l'onglet 1
        String[] Granularite = { "Régions", "Départements" };
        JComboBox<String> choixGranuralite = new JComboBox<>(Granularite); // Déroulant Granularité
        JLabel jLabel = new JLabel("Granularité");
        choixGranuralite.setSize(choixGranuralite.getWidth(), choixGranuralite.getMinimumSize().height);
        JButton button = new JButton("Rapport"); // Bouton pour le Rapport
        // Action bouton
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TraitementCases traitement = new TraitementCases();
                if (traitement.isAnyChecked() == true) {
                    ArrayList<String> listeSVG = traitement.traitement();
                    Fonction.createHTMLFile(listeSVG, "Rapport des selections", "rapport");
                    String filePath = "rapport.html";
                    try {
                        // Créer un objet File à partir du chemin du fichier HTML
                        File file = new File(filePath);
                        // Vérifier si le fichier existe avant de l'ouvrir
                        if (file.exists()) {
                            // Lancer le navigateur par défaut avec le fichier HTML
                            Desktop.getDesktop().browse(file.toURI());
                        } else {
                            System.out.println("Le fichier HTML spécifié n'existe pas.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    new MessageErreur(
                            "<html><div style='text-align: center;'>Veuillez sélectionner au moins un carburant, <br> avec au moins un département ou une région afin de générer un rapport.</div></html>");
                }
            }
        });

        // Ajout de ces éléments de contrôle à l'onglet 1
        onglet21.add(jLabel);
        onglet21.add(choixGranuralite);
        onglet21.add(button);
        onglet21.add(previsua);

        // Gestion des événements pour le choix de la granularité
        choixGranuralite.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String selectedChoice = (String) choixGranuralite.getSelectedItem();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (selectedChoice.equals("Départements")) {
                        scrollPaneDeptContainer.setVisible(true);
                        region.setVisible(false);
                        granChecked = true;
                    } else if (selectedChoice.equals("Régions")) {
                        region.setVisible(true);
                        scrollPaneDeptContainer.setVisible(false);
                        granChecked = false;
                    }
                    onglet1.revalidate();
                    onglet1.repaint();
                }
            }
        });

        // Ajout des onglets au panel principal
        panelOnglet.add(this.onglets);

    }

    /**
     * Méthode pour récupérer le panneau principal contenant les onglets.
     * 
     * @return Le panneau principal contenant les onglets.
     */
    public JPanel GetPanel() {
        return this.panelOnglet;
    }

    /**
     * Méthode pour récupérer le premier onglet.
     * 
     * @return Le premier onglet.
     */
    public JComponent GetOnglet1() {
        return (JComponent) this.onglets.getComponent(0);
    }

    /**
     * Méthode pour récupérer le deuxième onglet.
     * 
     * @return Le deuxième onglet.
     */
    public JComponent GetOnglet2() {
        return (JComponent) this.onglets.getComponent(1);
    }

    public JPanel GetPanelPrevi() {
        return this.previ;
    }

    /**
     * Méthode pour récupérer la case à cocher pour afficher les stations.
     * 
     * @return La case à cocher pour afficher les stations.
     */
    public static JCheckBox getAfficheStation() {
        return afficheStation;
    }

    /**
     * Méthode pour récupérer les panneaux de diagrammes.
     * 
     * @return Les panneaux de diagrammes.
     */
    public static JPanel[] getDiag() {
        return diag;
    }

    /**
     * Méthode pour récupérer l'état d'affichage des stations les moins chère.
     * 
     * @return L'état d'affichage des stations.
     */
    public static Boolean getIsStationsAffichees() {
        return isStationsAffichees;
    }

    /**
     * Méthode pour récupérer l'état de la granularité.
     * 
     * @return L'état de la granularité. (true pour département et false pour
     *         régions)
     */
    public static Boolean getGranChecked() {
        return granChecked;
    }
}