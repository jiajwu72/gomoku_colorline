import java.awt.*;
import java.util.ArrayList;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public class Plateau_Gomoku extends Plateau {

	
	boolean ordi;
	int nbBlanc,nbNoir;

    public Plateau_Gomoku() {
    }

    public Plateau_Gomoku(int nbColonne, int nbLigne){
    	super(nbColonne, nbLigne);
    }

    
    void aligner() {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {-1,1}};
        int colonne = 0;
        int ligne = 1;
        int nbPointBlanc = 0;
        int nbPiontNoir = 0;
        int tmp = 0;
        int count = 0;

        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                Color color = pions[j][i].getColor();
                for (int[] direction : directions) {
                    try {
                        while ((!pions[j + direction[colonne] * tmp][i + direction[ligne] * tmp].estVide())
                                && pions[j + direction[colonne] * tmp][i + direction[ligne] * tmp].memeCouleur(color)
                                && tmp <= 5){
                            count++;
                            tmp++;
                        }
                    } catch (IndexOutOfBoundsException e) {}
                    if (count >= 5) {
                        if (color == Color.black) {
                            nbPiontNoir++;
                            
                        } else {
                            nbPointBlanc++;
                            
                        }
                    }

                    tmp = 0;
                    count = 0;
                }
            }
        }
        nbBlanc=nbPointBlanc;
        nbNoir=nbPiontNoir;
        System.out.println("Point Noir:" + nbPiontNoir+ ", Point Blanc: " + nbPointBlanc);
    }


    public void poser(int colonne, int ligne) {
        pions[nbColonne][nbLigne] = new Pion(Color.black,colonne,ligne);
    }

    public void initialiser(int nbColonne, int nbLigne) {
        pions = new Pion[nbColonne][nbLigne];
        this.nbColonne = nbColonne;
        this.nbLigne = nbLigne;
        jeuFini = false;
        nbPions = 0;
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                pions[j][i] = new Pion(i, j);
            }
        }
    }

    public void contreOrdi(){
    	ordi=true;
    }
    
	@Override
	void aligner(int[] coord) {
		
	}

	
	
	public static void main(String []args){
		Plateau gomo=new Plateau_Gomoku(5, 5);
		gomo.aligner();
		
		
	}
	
}