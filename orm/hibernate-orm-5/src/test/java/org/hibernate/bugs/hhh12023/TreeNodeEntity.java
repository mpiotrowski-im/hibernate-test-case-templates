/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.bugs.hhh12023;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Michał Piotrowski <mpiotrowski@im.gda.pl>
 */
@Entity
public class TreeNodeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<TreeNodeEntity> children = new ArrayList<>();

    @OneToOne
    private SomeDataEntity data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TreeNodeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeEntity> children) {
        this.children = children;
    }

    public SomeDataEntity getData() {
        return data;
    }

    public void setData(SomeDataEntity data) {
        this.data = data;
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
        if (!(object instanceof TreeNodeEntity)) {
            return false;
        }
        TreeNodeEntity other = (TreeNodeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hibernate.bugs.hhh123.TreeNodeEntity[ id=" + id + " ]";
    }

}
