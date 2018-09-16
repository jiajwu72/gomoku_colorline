
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */

public class Vue extends JFrame{
    private Plateau plateau;
    private Controleur controleur;
    private JFrame fenetre;
    private JMenuBar menuBar;
    private JPanel jPanel;
    private JPanel grille;
    private JLabel score=new JLabel("score: 0");
    
    public Vue() {
        super();
        fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(600,600);
        fenetre.setTitle("Jeux");
        fenetre.setLocation(600,300);
        fenetre.setResizable(false);
        fenetre.setLayout(new BorderLayout());
        menuBar = new JMenuBar();
        JMenu fichier = new JMenu("Fichier" );
        JMenu nouveau = new JMenu("Nouvelle partie" );
        JMenuItem quitter = new JMenuItem("Quitter" );
        quitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.dispose();
				
			}
		});
        fichier.add(nouveau);
        fichier.add(quitter);
        menuBar.add(fichier);
        fenetre.add(menuBar,BorderLayout.NORTH);
        
        Choix_Gomoku choix1 = new Choix_Gomoku(this);
        Choix_ColorLine choix2= new Choix_ColorLine(this);
        
        JMenu go=new JMenu("Gomoku");
        JMenuItem gomokuGrand=new JMenuItem("Gomoku_16*16");
        JMenuItem gomokuPetit=new JMenuItem("Gomoku_12*12");
        go.add(gomokuGrand);
        go.add(gomokuPetit);

        gomokuGrand.addActionListener(choix1);
        gomokuPetit.addActionListener(choix1);
        nouveau.add(go);
        
        JMenu cl=new JMenu("colorLine");
        JMenuItem colorLineGrand=new JMenuItem("colorLine_16*16");
        JMenuItem colorLinePetit=new JMenuItem("colorLine_12*12");
        cl.add(colorLineGrand);
        cl.add(colorLinePetit);

        colorLineGrand.addActionListener(choix2);
        colorLinePetit.addActionListener(choix2);
        nouveau.add(cl);

        JButton gomoku = new JButton("GOMOKU");
        
        gomoku.addActionListener(choix1);
        JButton colorLine = new JButton("COLORLINE");
        
        colorLine.addActionListener(choix2);
        jPanel = new JPanel();

        jPanel.add(gomoku);
        jPanel.add(colorLine);
        fenetre.add(jPanel);
        fenetre.setVisible(true);
    }

    public void setTaille() {
        jPanel.removeAll();
        if(grille!=null){
        	fenetre.remove(grille);
        }
        JButton tailleDefaut = new JButton("Par defaut");
        Choix_taille choix_taille = new Choix_taille(this,plateau, 0);
        tailleDefaut.addActionListener(choix_taille);
        jPanel.add(tailleDefaut);

        JButton taille8 = new JButton("8 x 8");
        Choix_taille choix_taille_8 = new Choix_taille(this,plateau, 8);
        taille8.addActionListener(choix_taille_8);
        jPanel.add(taille8);

        JButton taille12 = new JButton("12 x 12");
        Choix_taille choix_taille_12 = new Choix_taille(this,plateau, 12);
        taille12.addActionListener(choix_taille_12);
        jPanel.add(taille12);

        JButton retour = new JButton("Retour");
        Choix_retour choix_retour= new Choix_retour(this);
        retour.addActionListener(choix_retour);
        jPanel.add(retour);
        fenetre.repaint();
        fenetre.setVisible(true);
    }


    public void setJeu(Plateau plateau, Controleur controleur) {
        this.plateau = plateau;
        this.controleur = controleur;
    }

    public void fillGrid() {
        fenetre.remove(jPanel);
        if(grille!=null){
        	fenetre.remove(grille);
        }

        grille = new JPanel(new GridLayout(plateau.nbLigne, plateau.nbColonne));
        if (plateau.getClass().equals(Plateau_Gomoku.class)) {
            fenetre.setSize(50 * plateau.nbLigne, 50 * plateau.nbLigne);
        } else {
            fenetre.setSize(58 * plateau.nbLigne,100+ 58 * plateau.nbLigne);
            grille.setSize(58 * plateau.nbLigne,58 * plateau.nbLigne);
        }
        fenetre.add(grille, BorderLayout.CENTER);
        for (int i = 0; i < plateau.nbLigne; i++) {
            for (int j = 0; j < plateau.nbColonne; j++) {
            	//System.out.println(i+" "+j);
                if (plateau.pionVide(j, i)) {
                	
                	
                	plateau.pions[j][i].setOpaque(false);
                	plateau.pions[j][i].setContentAreaFilled(false);
                	plateau.pions[j][i].setBorderPainted(false);
                	plateau.pions[j][i].addActionListener(controleur);
                    grille.add(plateau.pions[j][i]);

                    try {
                        Image img;
                        if (plateau.getClass().equals(Plateau_ColorLine.class)) {
                            img = ImageIO.read(getClass().getResource("colorLine_vide.bmp"));
                        } else {
                            img = ImageIO.read(getClass().getResource("case_vide.bmp"));
                        }
                        plateau.pions[j][i].setIcon(new ImageIcon(img));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                } else {
                    plateau.pions[j][i].setOpaque(false);
                    plateau.pions[j][i].setContentAreaFilled(false);
                    plateau.pions[j][i].setBorderPainted(false);
                    plateau.pions[j][i].addActionListener(controleur);
                    grille.add(plateau.pions[j][i]);
                }
                
            }
        }
        
        if(plateau.getClass().equals(Plateau_ColorLine.class)){
            Plateau_ColorLine colorLine=(Plateau_ColorLine) plateau;
            plateau.placerAuPlus3Pion();
            //colorLine.nbPions+=3;
            score = new JLabel("Points: 0");
            jPanel = new JPanel();
            jPanel.add(score);
            fenetre.add(jPanel,BorderLayout.AFTER_LAST_LINE);
        }
        System.out.println(plateau.tour);
        if(plateau.getClass().equals(Plateau_Gomoku.class)){
        	
        	
        	
        	final Plateau_Gomoku gomo=(Plateau_Gomoku) plateau;
        	final JButton vsJ=new JButton("vsJoueur");
        	final JButton vsO=new JButton("vsOrdi");
        	jPanel=new JPanel();
        	jPanel.add(vsJ);
        	jPanel.add(vsO);
        	fenetre.add(jPanel,BorderLayout.AFTER_LAST_LINE);
        	vsJ.addActionListener(new ActionListener() {
        		
        		@Override
        		public void actionPerformed(ActionEvent e) {

        			gomo.ordi=false;
        			jPanel.remove(vsO);
        			jPanel.remove(vsJ);
        			grille.repaint();
        			jPanel.repaint();
        			fenetre.repaint();
        		}
        	});
        	vsO.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					gomo.ordi=true;
					jPanel.remove(vsJ);
					jPanel.remove(vsO);
					grille.repaint();
					jPanel.repaint();
        			fenetre.repaint();
					
				}
			});
        	
        	
        	
        	
        	
        	
        }
        fenetre.setVisible(true);
    }

    public void retour() {
        jPanel.removeAll();
        JButton gomoku = new JButton("GOMOKU");
        Choix_Gomoku choix1 = new Choix_Gomoku(this);
        gomoku.addActionListener(choix1);
        JButton colorLine = new JButton("COLORLINE");
        Choix_ColorLine choix2= new Choix_ColorLine(this);
        colorLine.addActionListener(choix2);
        jPanel = new JPanel();

        jPanel.add(gomoku);
        jPanel.add(colorLine);
        fenetre.add(jPanel);
        jPanel.repaint();
        fenetre.setVisible(true);
    }

    void miseAJourScore() {
        score.setText("score: " +((Plateau_ColorLine) plateau).total);
        fenetre.repaint();
    }

    public void removeJPanel() {
        fenetre.remove(jPanel);
    }

    private Vue getVue() {
        return this;
    }



}