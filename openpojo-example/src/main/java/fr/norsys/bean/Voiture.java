package fr.norsys.bean;

public class Voiture implements MoyenDeTransport {

    private String nom;

    private String marque;

    private Couleur couleur;

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(final String marque) {
        this.marque = marque;
    }

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
