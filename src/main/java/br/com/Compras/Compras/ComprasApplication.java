package br.com.Compras.Compras;

import br.com.Compras.Compras.Entity.Carrinho;
import br.com.Compras.Compras.Entity.enums.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComprasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ComprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
