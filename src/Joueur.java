/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public class Joueur {
	private boolean estOrdi;
    private int point;
    private int nbGagne;

    public void joue(Plateau plateau){
    	if(estOrdi){
    		this.ordiJoue(plateau);
    	}else{
    		this.joueurJoue(plateau);
    	}
    }
    
    public void joueurJoue(Plateau plateau) {
    	
    }

    public void ordiJoue(Plateau plateau) {
    	
    }

    public boolean gagnee(Plateau plateau) {
        return false;
    }

}