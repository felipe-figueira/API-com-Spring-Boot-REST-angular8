package com.rest.controller;

import java.util.List;
import java.util.Optional;

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

import com.rest.model.Usuario;
import com.rest.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class IndexController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario() {

		List<Usuario> list = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

		// adicionar o id do usuario na tabela telefone
		for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {

		// adicionar o id do usuario na tabela telefone
		for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}

		Usuario updateUsuer = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(updateUsuer, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public String delete(@PathVariable(value = "id") Long id) {

		usuarioRepository.deleteById(id);

		return "ok";
	}
}
