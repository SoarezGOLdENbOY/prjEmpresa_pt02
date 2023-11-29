package com.daniel.PrjEmpresa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.PrjEmpresa.entities.Funcionario;
import com.daniel.PrjEmpresa.services.FuncionarioServices;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioControllers{
private final FuncionarioServices funcionarioServices;

@Autowired
public FuncionarioControllers(FuncionarioServices funcionarioServices) {
	this.funcionarioServices = funcionarioServices;
}

@GetMapping("/{id}")
public ResponseEntity<Funcionario> findFuncionariobyId(@PathVariable Long id) {
	Funcionario funcionario = funcionarioServices.getFuncionarioById(id);
	if (funcionario != null) {
		return ResponseEntity.ok(funcionario);
	} else {
		return ResponseEntity.notFound().build();
	}
}
@GetMapping("/nome/{funnome}")
public ResponseEntity<List<Funcionario>> findFuncionariosByNomeAproximado(@PathVariable String funnome) {
    List<Funcionario> funcionarios = funcionarioServices.getFuncionariosByFunnomeAproximado(funnome);
    if (!funcionarios.isEmpty()) {
        return ResponseEntity.ok(funcionarios);
    } else {
        return ResponseEntity.notFound().build();
    }
}


@GetMapping("/")
public ResponseEntity<List<Funcionario>> findAllUsuarioscontrol() {
	List<Funcionario> funcionario = funcionarioServices.getAllFuncionarios();
	return ResponseEntity.ok(funcionario);
}

@PostMapping("/")
public ResponseEntity<Funcionario> insertUsuariosControl(@RequestBody Funcionario funcionario) {
	Funcionario novofuncionario = funcionarioServices.saveFuncionario(funcionario);
	return ResponseEntity.status(HttpStatus.CREATED).body(novofuncionario);
}

@PutMapping("/{id}")
public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long funcodigo, @RequestBody Funcionario funcionario) {
    Funcionario funcionarioAtualizado = funcionarioServices.updateFuncionario(funcodigo, funcionario);
    if (funcionarioAtualizado != null) {
        return ResponseEntity.ok(funcionarioAtualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}

@DeleteMapping("/id")
public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long funcodigo) {
	boolean remover = funcionarioServices.deleteFuncionario(funcodigo);
	if (remover) {
		return ResponseEntity.ok().body("usuario Excluido com sucesso");
	} else {
		return ResponseEntity.notFound().build();
	}
}
}