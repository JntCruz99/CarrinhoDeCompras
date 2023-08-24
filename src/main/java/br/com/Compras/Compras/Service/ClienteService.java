package br.com.Compras.Compras.Service;

import br.com.Compras.Compras.Entity.Cliente;
import br.com.Compras.Compras.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Object save(Cliente cliente) throws IllegalAccessException {
        String cpf = cliente.getCpf();
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if (clienteOptional.isEmpty()){
            return clienteRepository.save(cliente);
        }else {
            throw new RuntimeException("Cliente com CPF: " + cpf +" já existe");
        }
    }

    @Transactional
    public void delete(Long id) throws IllegalAccessException {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        Cliente cliente = clienteOptional.get();
        if(clienteOptional.isPresent()){
            clienteRepository.delete(cliente);
        }else{
            throw new IllegalAccessException("Cliente com ID "+ id + " não encontrado");
        }
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) throws IllegalAccessException {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            return clienteRepository.findById(id);
        }else{
            throw new RuntimeException("Cliente de Id " + id + " não encontrado");
        }
    }

    public Cliente update(Long id, Cliente obj){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            if (obj.getNome() != null){
                cliente.setNome(obj.getNome());
            }if(obj.getCpf() != null){
                cliente.setCpf(obj.getCpf());
            }
            return clienteRepository.save(cliente);
        }else {
            throw new RuntimeException("O cliente de ID " + id +" não foi encontrado");
        }

    }


}
