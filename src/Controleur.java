import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by JIAJUN.WU on 20/12/2016.
 */
public abstract class Controleur implements ActionListener {
	protected Plateau plateau;
    protected Vue vue;


    public Controleur(Vue vue) {
        this.vue = vue;
    }

    public Controleur(Plateau plateau, Vue vue) {
        this.plateau = plateau;
        this.vue = vue;
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

}