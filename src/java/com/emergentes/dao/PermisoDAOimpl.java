
package com.emergentes.dao;

import com.emergentes.modelo.Permisos;
import com.emergentes.modelo.Roles;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAOimpl extends ConexionDB implements PermisoDAO {

    @Override
    public void insert(Permisos permisos) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO permisos (id_usuario, id_rol) values (?, ?");
            ps.setInt(1, permisos.getId_usuario());
            ps.setInt(2, permisos.getId_rol());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Permisos permisos) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE permisos SET id_usuario = ?, id_rol = ? where id = ?");
            ps.setInt(1, permisos.getId_usuario());
            ps.setInt(2, permisos.getId_rol());
            ps.setInt(3, permisos.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM permisos WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Permisos getById(int id) throws Exception {
        Permisos per = new Permisos();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM permisos WHERE id = ?");
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                per.setId(rs.getInt("id"));
                per.setId_usuario(rs.getInt("id_usuario"));
                per.setId_rol(rs.getInt("id_rol"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return per;}

    @Override
    public List<Permisos> getAll() throws Exception {
        List<Permisos> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Permisos>();
            while (rs.next()){
                Permisos per = new Permisos();
                per.setId(rs.getInt("id"));
                per.setId_usuario(rs.getInt("id_usuario"));
                per.setId_rol(rs.getInt("id_rol"));
                
                lista.add(per);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}
