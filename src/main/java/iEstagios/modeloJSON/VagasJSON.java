package iEstagios.modeloJSON;

import iEstagios.modelo.Vaga;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gerardo
 */
public class VagasJSON {
    
    private List<Vaga> vagas;

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }  

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.vagas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VagasJSON other = (VagasJSON) obj;
        if (!Objects.equals(this.vagas, other.vagas)) {
            return false;
        }
        return true;
    }
}
