import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JIAJUN.WU on 05/01/2017.
 */
abstract class Controleur_jeu implements ActionListener {
    Vue vue;
    Plateau plateau;

    public Controleur_jeu(Vue vue) {
        this.vue = vue;
    }
}

class Choix_Gomoku extends Controleur_jeu implements ActionListener {

    public Choix_Gomoku(Vue vue) {
        super(vue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	JMenuItem jm=new JMenuItem();
        plateau = new Plateau_Gomoku();
        vue.setJeu(plateau,new Controleur_Gomoku(plateau,vue));
        if(e.getSource().getClass()!=jm.getClass()){
        	vue.setTaille();
        }else{
        	System.out.println("lalala");
        	jm=(JMenuItem) e.getSource();
    		String s=jm.getText();
            if(jm.getText()=="Gomoku_16*16" || jm.getText()=="colorLine_16*16"){
            	System.out.println("lllllll");
            	plateau.initialiser(16,16);
            	System.out.println(plateau.nbColonne);
            	vue.fillGrid();
            }
            if(jm.getText()=="Gomoku_12*12" || jm.getText()=="colorLine_12*12"){
            	plateau.initialiser(12,12);
            	vue.fillGrid();
            }
        }
    }
}


class Choix_ColorLine extends Controleur_jeu implements ActionListener {

    public Choix_ColorLine(Vue vue) {
        super(vue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	JMenuItem jm=new JMenuItem();
    	plateau = new Plateau_ColorLine();
		vue.setJeu(plateau,new Controleur_ColorLine(plateau,vue));
		
    	if(e.getSource().getClass()!=jm.getClass()){
            System.out.println("here-----------");
    		vue.setTaille();
    	}else{
    		jm=(JMenuItem) e.getSource();
    		String s=jm.getText();
            if(jm.getText()=="Gomoku_16*16" || jm.getText()=="colorLine_16*16"){
            	System.out.println("lllllll");
            	plateau.initialiser(16,16);
            	System.out.println(plateau.nbColonne);
            	vue.fillGrid();
            }
            if(jm.getText()=="Gomoku_12*12" || jm.getText()=="colorLine_12*12"){
            	plateau.initialiser(12,12);
            	vue.fillGrid();
            }
    	}
    }
}

class Choix_taille extends Controleur_jeu implements ActionListener {
    int taille;

    public Choix_taille(Vue vue, Plateau plateau, int taille) {
        super(vue);
        this.plateau = plateau;
        this.taille = taille;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	JButton jb=new JButton();
    	JMenuItem jm=new JMenuItem();
    	jb = (JButton) e.getSource();
    	System.out.println(jb.getName() + ", " + jb.getText());
    	if (taille == 0) {
    		plateau.initialiser(5,5);
        } else if (taille == 8) {
            plateau.initialiser(taille,taille);
        } else if (taille == 12) {
            plateau.initialiser(taille, taille);
        } else if (taille == 16) {
            plateau.initialiser(taille, taille);
        }

    	System.out.println(e.getSource().getClass()+"\n"+jm.getClass());

        
        vue.fillGrid();
    }
}

class Choix_retour extends Controleur_jeu implements ActionListener {

    public Choix_retour(Vue vue) {
        super(vue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vue.retour();
    }
}
