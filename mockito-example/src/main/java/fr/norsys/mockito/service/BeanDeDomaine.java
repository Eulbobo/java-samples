package fr.norsys.mockito.service;

import java.math.BigDecimal;

// cette classe représente un objet de domaine quelconque manipulé par le service
public class BeanDeDomaine {

    private long idBean;

    private String nomBean;

    private BigDecimal valeurBean;

    private EtatBeanDomain etatBean;

    /**
     * @return the idBean
     */
    public long getIdBean() {
        return idBean;
    }

    /**
     * @param idBean the idBean to set
     */
    public void setIdBean(final long idBean) {
        this.idBean = idBean;
    }

    /**
     * @return the nomBean
     */
    public String getNomBean() {
        return nomBean;
    }

    /**
     * @param nomBean the nomBean to set
     */
    public void setNomBean(final String nomBean) {
        this.nomBean = nomBean;
    }

    /**
     * @return the valeurBean
     */
    public BigDecimal getValeurBean() {
        return valeurBean;
    }

    /**
     * @param valeurBean the valeurBean to set
     */
    public void setValeurBean(final BigDecimal valeurBean) {
        this.valeurBean = valeurBean;
    }

    /**
     * @return the etatBean
     */
    public EtatBeanDomain getEtatBean() {
        return etatBean;
    }

    /**
     * @param etatBean the etatBean to set
     */
    public void setEtatBean(final EtatBeanDomain etatBean) {
        this.etatBean = etatBean;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idBean ^ (idBean >>> 32));
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BeanDeDomaine other = (BeanDeDomaine) obj;
        if (idBean != other.idBean) {
            return false;
        }
        return true;
    }

}
