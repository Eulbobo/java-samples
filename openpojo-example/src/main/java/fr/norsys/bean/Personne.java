package fr.norsys.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Personne {

    private String nom;

    private String prenom;

    private String nni;

    private Date dateDeNaissance;

    // open pojo n'aura aucun mal à Mocker cette interface
    private MoyenDeTransport transportPrincipal;

    // champ final, pas besoin d'un setter
    private final List<MoyenDeTransport> moyensDeTransport = new ArrayList<MoyenDeTransport>();

    // champ final, pas besoin d'un setter
    private final Map<TypeAdresse, List<Adresse>> adressesParType = new HashMap<TypeAdresse, List<Adresse>>();

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
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the nni
     */
    public String getNni() {
        return nni;
    }

    /**
     * @param nni the nni to set
     */
    public void setNni(final String nni) {
        this.nni = nni;
    }

    /**
     * @return the dateDeNaissance
     */
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    /**
     * @param dateDeNaissance the dateDeNaissance to set
     */
    public void setDateDeNaissance(final Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /**
     * @return the moyensDeTransport
     */
    public List<MoyenDeTransport> getMoyensDeTransport() {
        return moyensDeTransport;
    }

    /**
     * @return the adressesParType
     */
    public Map<TypeAdresse, List<Adresse>> getAdressesParType() {
        return adressesParType;
    }

    /**
     * @return the transportPrincipal
     */
    public MoyenDeTransport getTransportPrincipal() {
        return transportPrincipal;
    }

    /**
     * @param transportPrincipal the transportPrincipal to set
     */
    public void setTransportPrincipal(final MoyenDeTransport transportPrincipal) {
        this.transportPrincipal = transportPrincipal;
    }

    // Toutes les méthodes ci-dessous ne SONT PAS dans la norme JavaBeans
    // il faut les tester spécifiquement

    public void addMoyenDeTransport(final MoyenDeTransport moyenDeTransport) {
        this.moyensDeTransport.add(moyenDeTransport);
    }

    public void removeMoyenDeTransport(final MoyenDeTransport moyenDeTransport) {
        this.moyensDeTransport.remove(moyenDeTransport);
    }

    public void addAdresse(final TypeAdresse typeAdresse, final Adresse adresse) {
        List<Adresse> adresses = this.adressesParType.get(typeAdresse);
        if (adresses == null) {
            adresses = new ArrayList<Adresse>();
            this.adressesParType.put(typeAdresse, adresses);
        }
        adresses.add(adresse);
    }

    public void removeAdresse(final Adresse adresse) {
        for (Entry<TypeAdresse, List<Adresse>> entry : adressesParType.entrySet()) {
            entry.getValue().remove(adresse);
        }
    }

}
