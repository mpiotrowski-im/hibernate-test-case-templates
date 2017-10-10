/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.bugs.hhh12023;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Michał Piotrowski <mpiotrowski@im.gda.pl>
 */
@Entity
public class RootEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @OneToOne
    private TreeNodeEntity root;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TreeNodeEntity getRoot() {
        return root;
    }

    public void setRoot(TreeNodeEntity root) {
        this.root = root;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RootEntity)) {
            return false;
        }
        RootEntity other = (RootEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hibernate.bugs.hhh123.RootEntity[ id=" + id + " ]";
    }

}
