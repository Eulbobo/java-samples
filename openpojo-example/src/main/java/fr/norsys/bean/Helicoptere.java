package fr.norsys.bean;

public class Helicoptere implements MoyenDeTransport {

    private Couleur couleur;

    /**
     * @return the couleur
     */
    public Couleur getCouleur() {
        //return Couleur.BLEU;
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(final Couleur couleur) {
        this.couleur = couleur;
    }

}
