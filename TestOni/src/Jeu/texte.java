/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jeu;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author marti
 */
public class texte extends JButton{

    String lienTexte;
    public texte(String texte){
        this.lienTexte = texte;
    }
    
    // Méthode gérant le dessin de la cellule
    @Override
    protected void paintComponent(Graphics g) {
            //setContentAreaFilled(false);
            
    Image imageADessiner = null;
    
    System.out.println("=======>"+lienTexte);
    //imageADessiner = new ImageIcon("src/Jeu/images/droite.jpg").getImage();

    imageADessiner = new ImageIcon("src/Jeu/images/"+lienTexte+".jpg").getImage();
    
   
    
    // Dessin de l'image dans le composant
    if (imageADessiner != null) {
    //System.out.println("ok");
    g.drawImage(imageADessiner,0,0,getWidth(),getHeight(),this);
    
}
    
}
}
