package fr.norsys.bean;

public class SkateBoard implements MoyenDeTransport {

    private Couleur couleur;

    /**
     * @return the couleur
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(final Couleur couleur) {
        this.couleur = couleur;
    }

}
