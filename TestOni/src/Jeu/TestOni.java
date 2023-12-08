package Jeu;

/**
 *
 * @author marti
 */
public class TestOni {
    public static void main(String[] args) {
        // TODO code application logic here
        Partie partie = new Partie();
        //System.out.println(partie.creerDeckTotal());
        for(int i=0;i<128;i++){
            System.out.println(partie.recupererValeurLigne("src/Jeu/coups.txt", i+1));
        }
    } 
}
