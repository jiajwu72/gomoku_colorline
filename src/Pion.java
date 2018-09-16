import javax.swing.*;
import java.awt.*;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public class Pion extends JButton{
    private Color color;
    /*private int ligne,colonne;*/

    public Pion() {
        
        
    }

    

    public Pion(Color color) {
        this.color = color;
        
    }

    /*public int[] getCoord() {
        return new int[] {ligne, colonne};
    }*/
    
    public Pion(int i, int j) {
		// TODO Auto-generated constructor stub
	}



	public Pion(Color black, int colonne, int ligne) {
		// TODO Auto-generated constructor stub
	}



	public int getRgb(){
    	return color.getRGB();
    }


    public void setPion(Color color) {
        this.color = color;
    }

    public void viderPion() {
        this.color = null;
    }

    public Color getColor() {
        return color;
    }

    public boolean memeCouleur(Color color) {

        return this.color==color;
    }
    
    public String col(){
    	if (this.memeCouleur(Color.white)){
    		return "blanc";
    	}
    	if(this.memeCouleur(Color.black)){
    		return "noir";
    	}
    	return "";
    }
    
    public boolean estVide(){
    	return color==null;
    }

}