import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public class Controleur_Gomoku extends Controleur {

	
	
    public Controleur_Gomoku(Plateau plateau, Vue vue) {
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

        Plateau_Gomoku gomo=(Plateau_Gomoku) plateau;
        System.out.println(gomo.estPlein());

        if (plateau.pions[colonne][ligne].estVide()) {
        	plateau.tour++;
            plateau.nbPions++;

			if (plateau.tour != 0) {
				vue.removeJPanel();
			}


			if(gomo.tour%2==1){
        		gomo.pions[colonne][ligne].setPion(Color.black);
                button.setIcon(null);
        		insereImg(button,"pion_noir.bmp");
        		System.out.println("joueur1 joue:"+gomo.tour);
            }

            if(gomo.tour%2==0 &&gomo.ordi==false ){
                gomo.pions[colonne][ligne].setPion(Color.white);
                button.setIcon(null);
        		insereImg(button,"pion_blanc.bmp");
                vue.repaint();
                System.out.println("joueur2 joue:"+gomo.tour);
                
        	}
        	
        	
        	if(gomo.ordi && !gomo.estPlein()){
        		gomo.tour++;
                plateau.nbPions++;
        		
        		int[] coord=gomo.coordoneeAlea();
        		JButton jb=gomo.pions[coord[0]][coord[1]];
        		plateau.pions[coord[0]][coord[1]].setPion(Color.white);
				jb.setIcon(null);
        		insereImg(jb,"pion_blanc.bmp");
        		System.out.println("ordi joue:"+plateau.tour+" ("+coord[0]+","+coord[1]+")");
        		
        	}
        	
        }
        gomo.afficher();
        if(gomo.estPlein()){
        	gomo.aligner();
        	String str="";
        	if(gomo.nbBlanc>gomo.nbNoir){
        		str="Le blanc gagne la partie!";
        	}
        	if(gomo.nbNoir>gomo.nbBlanc){
        		str="Le noir gagne la partie!";
        	}else{
        		str="Egalité!!";
        	}
        	JOptionPane.showMessageDialog(vue.getContentPane(),
    				str, "INFO GOMOGU", JOptionPane.INFORMATION_MESSAGE);
        	System.out.println("nbPion"+plateau.nbPions);
        }
        
        
        
    }
    
    

}