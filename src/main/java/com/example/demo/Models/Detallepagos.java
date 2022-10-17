/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author camil
 */
@Entity
@Table(name = "detallepagos")
@NamedQueries({
    @NamedQuery(name = "Detallepagos.findAll", query = "SELECT d FROM Detallepagos d"),
    @NamedQuery(name = "Detallepagos.findById", query = "SELECT d FROM Detallepagos d WHERE d.id = :id"),
    @NamedQuery(name = "Detallepagos.findByPagoId", query = "SELECT d FROM Detallepagos d WHERE d.pagoId = :pagoId"),
    @NamedQuery(name = "Detallepagos.findByProductoId", query = "SELECT d FROM Detallepagos d WHERE d.productoId = :productoId")})
public class Detallepagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pago_id")
    private Integer pagoId;
    @Column(name = "producto_id")
    private Integer productoId;

    public Detallepagos() {
    }

    public Detallepagos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
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
        if (!(object instanceof Detallepagos)) {
            return false;
        }
        Detallepagos other = (Detallepagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.Models.Detallepagos[ id=" + id + " ]";
    }
    
}
