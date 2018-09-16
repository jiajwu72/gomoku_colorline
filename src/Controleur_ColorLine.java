import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.naming.NoInitialContextException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public class Controleur_ColorLine extends Controleur {



	public Controleur_ColorLine(Plateau plateau, Vue vue) {
        super(plateau, vue);
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    	JButton button = (JButton) e.getSource();
        JButton b = (JButton)e.getSource();
        Rectangle r = b.getBounds();
        Point p = b.getLocation();
        int ligne = p.y / r.height;
        int colonne = p.x / r.width;
        
        
        
        /*System.out.println(colorLine.estVide());
        System.out.println(colorLine.pions[colonne][ligne].estVide());*/
        
        Plateau_ColorLine colorLine=(Plateau_ColorLine) plateau;
        Pion pion=colorLine.pions[colonne][ligne];
        JButton jb=pion;
        
        Pion stokage=colorLine.stokage;
        JButton jbStokage=stokage;
        
        /*if(colorLine.pions[colonne][ligne].estVide()){
        	System.out.println("vide!!!");
        	colorLine.pions[colonne][ligne]button.setIcon(null);
        	insereImg(jb,"case_vide.bmp");
        }*/
        if(colorLine.estPlein()){
        	
        }
        if(!colorLine.estPlein() ){
        	if(colorLine.pions[colonne][ligne].estVide()){
        		System.out.println(colorLine.stokage.getColor());
            	if(!colorLine.stokage.estVide()){
            		System.out.println("stokage non nul:"+colorLine.stokage.getColor());
            		
            		if(stokage.memeCouleur(Color.black)){
            			colorLine.pions[colonne][ligne].setPion(Color.black);
            			insereImg(jb,"colorLine_noir.bmp");
            			System.out.println("noir");
            			System.out.println("nbPions:"+colorLine.nbPions);
            		} else if(stokage.memeCouleur(Color.white)){
            			colorLine.pions[colonne][ligne].setPion(Color.white);
            			insereImg(jb,"colorLine_blanc.bmp");
            			System.out.println("blanc");
            			System.out.println("nbPions:"+colorLine.nbPions);
            		}else if(stokage.memeCouleur(Color.blue)){
						colorLine.pions[colonne][ligne].setPion(Color.blue);
						insereImg(jb,"colorLine_bleu.bmp");
						System.out.println("bleu");
						System.out.println("nbPions:"+colorLine.nbPions);
					}else if(stokage.memeCouleur(Color.red)){
						colorLine.pions[colonne][ligne].setPion(Color.red);
						insereImg(jb,"colorLine_rouge.bmp");
					}else if(stokage.memeCouleur(Color.green)){
						colorLine.pions[colonne][ligne].setPion(Color.green);
						insereImg(jb,"colorLine_vert.bmp");
					}
            		
            		//colorLine.nbPions++;
            		colorLine.nbPions+=2;
            		colorLine.stokage.viderPion();
            		int [] coord={colonne,ligne};
            		colorLine.afficheCoord(coord);
            		colorLine.aligner(coord);
            		colorLine.miseAJour();
            		colorLine.placerAuPlus3Pion();
            		colorLine.afficher();
            		
            	}
            }
        	else if (!colorLine.pions[colonne][ligne].estVide()) {
            	if(colorLine.stokage.estVide()){
            		colorLine.stokage.setPion(pion.getColor());
            		//System.out.println("remplir Stokage");
            		
            		pion.viderPion();
            		//System.out.println(pion.estVide());
            		pion.setIcon(null);
            		insereImg(jb, "colorLine_vide.bmp");
            		colorLine.nbPions--;
            	}
            }
		}else if(colorLine.estPlein()){

			String str="Jeu fini!";
			JOptionPane.showMessageDialog(vue.getContentPane(),
					str, "INFO GOMOGU", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("nbPion"+plateau.nbPions);
		}

        vue.miseAJourScore();

    }

    	
    
    
    
    
    
    

}