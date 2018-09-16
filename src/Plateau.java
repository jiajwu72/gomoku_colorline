import java.awt.*;
import java.awt.peer.LightweightPeer;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public abstract class Plateau {
    protected Pion[][] pions;
    protected int nbColonne,nbLigne;
    protected boolean jeuFini;
    protected int nbPions;
    Vue vue;
    public static final Color noir=Color.black;
    public static final Color blanc=Color.white;
    public static final Color rouge=Color.red;
    public static final Color bleu=Color.blue;
    public static final Color vert=Color.green;
    public static final Color[] tabColor={noir,blanc, rouge,bleu,vert};
    public static final int [][]voisins={{1,0},{2,0},{3,0},{4,0},
    									 {1,1},{2,2},{3,3},{4,4},
    									 {0,1},{0,2},{0,3},{0,4},
    									 {-1,1},{-2,2},{-3,3},{-4,4},
    									};
    int tour;

    public Plateau(){
    	
    }


    public static void afficheCoord(int [] coord){
		System.out.println(coord[0]+","+coord[1]);
	}
    int [] coordoneeAlea(){
    	
    	int ligne=(int)(Math.random()*(nbLigne));
    	int colonne=(int)(Math.random()*(nbColonne));
    	if(!pions[ligne][colonne].estVide()){
    		return this.coordoneeAlea();
    	}
    	int [] coordonneDePion={ligne,colonne};
    	return coordonneDePion;
    }


    public Plateau(int nbColonne, int nbLigne) {
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
        //vue = new Vue();
    }
    
    void placer1Pion(){

		int indiceCouleurHasard=(int) (Math.random()*5);
		Color couleurHasard=tabColor[indiceCouleurHasard];
		int []coord=this.coordoneeAlea();
		
	
	
		pions[coord[0]][coord[1]].setPion(couleurHasard);
		if(couleurHasard==Color.white){
			insereImg(pions[coord[0]][coord[1]],"colorLine_blanc.bmp" );
		} else if(couleurHasard==Color.black){
			insereImg(pions[coord[0]][coord[1]],"colorLine_noir.bmp" );
		} else if(couleurHasard==Color.blue){
            insereImg(pions[coord[0]][coord[1]],"colorLine_bleu.bmp" );
        }else if(couleurHasard==Color.red){
            insereImg(pions[coord[0]][coord[1]],"colorLine_rouge.bmp" );
        } else if (couleurHasard == Color.green) {
            insereImg(pions[coord[0]][coord[1]], "colorLine_vert.bmp");
        }
        System.out.println("abscisse:"+coord[0]+",ordonnee"+coord[1]);
		nbPions++;
    }

    void placerAuPlus3Pion(){
    	for(int i=0;i<3;i++){
    		if(nbPions<nbLigne*nbColonne){
    			placer1Pion();
    		}
    	}
    }
    
    
    
    public void insereImg(JButton jb,String str){
    	Image img = null;
        try {
            img = ImageIO.read(getClass().getResource(str));
            jb.setIcon(new ImageIcon(img));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public void setPion(Color color, int colonne, int ligne) {
        pions[colonne][ligne].setPion(color);
    }

    public boolean estPlein() {
        return nbPions == nbColonne * nbLigne;
    }

    public boolean pionVide(int colonne, int ligne) {
        return (pions[colonne][ligne].estVide());
    }

    abstract void aligner();
    abstract void aligner(int[] coord);

    void initialiser(int nbColonne, int nbLigne){
    	this.nbColonne=nbColonne;
    	this.nbLigne=nbLigne;
    	this.pions=new Pion[nbLigne][nbColonne];
    	for(int i=0;i<pions.length;i++){
    		for(int j=0;j<pions[i].length;j++){
    			pions[i][j]=new Pion(null,i,j);
    		}
    	}
    }

    void afficher() {
        for (int i = 0; i < this.nbLigne; i++) {
            for (int j = 0; j < this.nbColonne; j++) {

                if (pions[j][i].estVide()) {
                    System.out.print(". ");
                } else if (pions[j][i].getColor() == Color.black) {
                    System.out.print("B ");
                }else if (pions[j][i].getColor() == Color.white) {
                    System.out.print("W ");
                }else if (pions[j][i].getColor() == Color.red) {
                    System.out.print("R ");
                }
            }
            System.out.println("");
        }
    }
    
    public boolean jeuFini() {
        return this.estPlein();
    }

	boolean caseValide(int h, int l) {
		return (h>=0 && h<nbLigne && l>=0 && l<nbColonne);
	}

	public static void main(String []args){
		Plateau p=new Plateau_ColorLine(5, 5);
		p.afficher();
	}
}