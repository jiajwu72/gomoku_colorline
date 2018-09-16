import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public class Plateau_ColorLine extends Plateau {

	public static int[][] prevision=new int[3][2];
	Pion stokage=new Pion();

	int total;
	
    public Plateau_ColorLine(){}

	public Plateau_ColorLine(int nbColonne, int nbLigne) {
		super(nbColonne, nbLigne);
		vue.miseAJourScore();
        
	}



	//retourne le coordonnée de prochain pion
   
    boolean estVide(){
    	return nbPions==0;
    }
	
    void miseAJour(){
    	for(int i=0;i<nbLigne;i++){
    		for(int j=0;j<nbColonne;j++){
    			if(pions[j][i].estVide()){
    	        	pions[j][i].setIcon(null);
    	        	insereImg(pions[j][i],"colorLine_vide.bmp");
    	        }
    		}
    	}
    }
    
    
    
    static boolean memeCoordonnee(int [] coord1,int[] coord2){
    	return (coord1[0]==coord2[0] && coord1[1]==coord2[1]);
    }

    void aligner(int[] origine) {
        int colonne = 0;
        int ligne = 1;
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        int count = 0;
        int tmp = 0;
        int point = 0;
        boolean removeOrigine = false;
        Color color = pions[origine[colonne]][origine[ligne]].getColor();


        for (int[] direction : directions) {
            try {
                while ((!pions[origine[colonne] + direction[colonne] * tmp][origine[ligne] + direction[ligne] * tmp].estVide())
                        && pions[origine[colonne] + direction[colonne] * tmp][origine[ligne] + direction[ligne] * tmp].memeCouleur(color))
                        {
                    count++;
                    tmp++;
                    
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
                tmp = 0;
                count--;
            try {
                while ((!pions[origine[colonne] + direction[colonne] * (-tmp)][origine[ligne] + direction[ligne] * (-tmp)].estVide())
                        && pions[origine[colonne] + direction[colonne] * (-tmp)][origine[ligne] + direction[ligne] * (-tmp)].memeCouleur(color)
                        ) {
                    count++;
                    tmp++;
                }
            } catch (IndexOutOfBoundsException ignored) {

            }

            if (count >= 5) {
            	point+=count;
                tmp = 0;
                removeOrigine = true;
                

                try {
                    while ((!pions[origine[colonne] + direction[colonne] * tmp][origine[ligne] + direction[ligne] * tmp].estVide())
                            && pions[origine[colonne] + direction[colonne] * tmp][origine[ligne] + direction[ligne] * tmp].memeCouleur(color)
                            ) {
                        if (tmp != 0) {
                            pions[origine[colonne] + direction[colonne] * tmp][origine[ligne] + direction[ligne] * tmp].viderPion();
                            nbPions--;
                        }
                        tmp++;
                    }
                } catch (IndexOutOfBoundsException ignored) {}
                    tmp = 0;
                try {
                    while ((!pions[origine[colonne] + direction[colonne] * (-tmp)][origine[ligne] + direction[ligne] * (-tmp)].estVide())
                            && pions[origine[colonne] + direction[colonne] * (-tmp)][origine[ligne] + direction[ligne] * (-tmp)].memeCouleur(color)
                            ) {
                        if (tmp != 0) {
                            pions[origine[colonne] + direction[colonne] * (-tmp)][origine[ligne] + direction[ligne] * (-tmp)].viderPion();
                            nbPions--;
                        }
                        tmp++;
                    }
                } catch (IndexOutOfBoundsException ignored) {}

            }
            tmp = 0;
            count = 0;
        }
        if (removeOrigine) {
            pions[origine[colonne]][origine[ligne]].viderPion();
            nbPions--;
            total += point*(point-4);
        }
    }


	


	//boolean caseValide vérifie si la case existe dans le plateau
	boolean caseValide(int h,int l){
		return (h>=0 && h<nbLigne && l>=0 && l<nbColonne );
	}

	
	void aligner() {
		// TODO Auto-generated method stub
		
	}
    
	
	public static void main(String [] args){
		/*Plateau colorLine=new Plateau_ColorLine(5,5);
		Plateau_ColorLine p=(Plateau_ColorLine) colorLine;
		p.miseAJourPrevision();
		int[][] prev=p.prevision;
		for(int i=0;i<3;i++){
			System.out.println(prev[i][0]+","+prev[i][1]);
		}*/
		
		
		
		
		
		
	}
}