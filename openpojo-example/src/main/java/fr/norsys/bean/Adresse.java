package fr.norsys.bean;

public class Adresse {

    private Long numero;

    private String rue;

    private String ville;

    private String codePostal;

    /**
     * @return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(final Long numero) {
        this.numero = numero;
    }

    /**
     * @return the rue
     */
    public String getRue() {
//        if (rue != null){
//            return rue.toUpperCase();
//        }
        return rue;
    }

    /**
     * @param rue the rue to set
     */
    public void setRue(final String rue) {
        this.rue = rue;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(final String ville) {
        this.ville = ville;
    }

    /**
     * @return the codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(final String codePostal) {
        this.codePostal = codePostal;
    }


}
