
package com.emergentes.dao;

import com.emergentes.modelo.Usuarios;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO{

    @Override
    public void insert(Usuarios usuarios) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO usuarios (usuario,correo,clave) values (?,?,MD5(?))");
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, usuarios.getCorreo());
            ps.setString(3, usuarios.getClave());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Usuarios usuarios) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE usuarios SET usuario = ?, correo = ?, clave = md5(?) where id = ?");
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, usuarios.getCorreo());
            ps.setString(3, usuarios.getClave());
            ps.setInt(4, usuarios.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Usuarios getById(int id) throws Exception {
        Usuarios usus = new Usuarios();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                usus.setId(rs.getInt("id"));
                usus.setUsuario(rs.getString("usuario"));
                usus.setCorreo(rs.getString("correo"));
                usus.setClave(rs.getString("clave"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return usus;
    }

    @Override
    public List<Usuarios> getAll() throws Exception {
        List<Usuarios> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Usuarios>();
            while (rs.next()){
                Usuarios usus =  new Usuarios();
                
                usus.setId(rs.getInt("id"));
                usus.setUsuario(rs.getString("usuario"));
                usus.setCorreo(rs.getString("correo"));
                usus.setClave(rs.getString("clave"));
                
                lista.add(usus);
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
