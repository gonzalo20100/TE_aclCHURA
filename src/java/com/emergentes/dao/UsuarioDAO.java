package com.emergentes.dao;

import com.emergentes.modelo.Usuarios;
import java.util.List;

public interface UsuarioDAO {
    public void insert(Usuarios usuarios) throws Exception;
    public void update(Usuarios usuarios) throws Exception;
    public void delete(int id) throws Exception;
    public Usuarios getById(int id) throws Exception;
    public List<Usuarios> getAll() throws Exception;
}
