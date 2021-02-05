package br.com.suritec.desafio.resource;

import br.com.suritec.desafio.domain.Cliente;
import br.com.suritec.desafio.resource.util.HeaderUtil;
import br.com.suritec.desafio.service.impl.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Collection<Cliente> findAll() {
        return this.clienteService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Cliente> findByCode(@PathVariable Long code) {
        Optional<Cliente> clienteReturned = this.clienteService.findByCode(code);
        return clienteReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente) {
        Cliente clienteSalved = this.clienteService.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(clienteSalved.getCpf()).toUri();
        return ResponseEntity.created(uri).body(clienteSalved);
    }

    @PostMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        this.clienteService.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("cliente.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Cliente> edit(@Valid @RequestBody Cliente cliente) {
        Cliente clienteReturned = this.clienteService.edit(cliente);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("cliente editado.", String.valueOf(clienteReturned.getCode()))).build();
    }
}
