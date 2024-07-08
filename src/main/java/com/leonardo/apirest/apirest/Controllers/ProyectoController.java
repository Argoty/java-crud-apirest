package com.leonardo.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leonardo.apirest.apirest.Repositories.ProyectoRepository;
import com.leonardo.apirest.apirest.Entities.Proyecto;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectoRepository proyectoRepository;

    @GetMapping
    public List<Proyecto> obtenerProyectos() {
        return proyectoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Proyecto obtenerProyectoPorId(@PathVariable Long id) {
        return proyectoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encuentra el proyecto con id: " + id));
    }

    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyectoNuevo) {
        return proyectoRepository.save(proyectoNuevo);
    }

    @PutMapping("/{id}")
    public Proyecto actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyectoActualizar) {
        Proyecto proyecto = proyectoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encuentra el proyecto con id: " + id));
        
        proyecto.setNombre(proyectoActualizar.getNombre());
        proyecto.setDescripcion(proyectoActualizar.getDescripcion());
        proyecto.setImgUrl(proyectoActualizar.getImgUrl());
        proyecto.setUrl(proyectoActualizar.getUrl());
    
        return proyectoRepository.save(proyecto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encuentra el proyecto con id: " + id));

        proyectoRepository.delete(proyecto);
        return "El proyecto con el id " + id + " fue eliminado!";
    }
}
