package com.osn.locadora.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osn.locadora.domain.Cidade;
import com.osn.locadora.domain.Cliente;
import com.osn.locadora.domain.Custos;
import com.osn.locadora.domain.Endereco;
import com.osn.locadora.domain.Estado;
import com.osn.locadora.domain.Hospedagem;
import com.osn.locadora.domain.Reserva;
import com.osn.locadora.domain.enums.Perfil;
import com.osn.locadora.domain.enums.TipoIntermedio;
import com.osn.locadora.domain.enums.TipoLimpeza;
import com.osn.locadora.repository.CidadeRepository;
import com.osn.locadora.repository.ClienteRepository;
import com.osn.locadora.repository.CustosRepository;
import com.osn.locadora.repository.EnderecoRepository;
import com.osn.locadora.repository.EstadoRepository;
import com.osn.locadora.repository.HospedagemRepository;
import com.osn.locadora.repository.ReservaRepository;

@Service
public class DBService {

	@Autowired
	EstadoRepository estadoRepo;

	@Autowired
	CidadeRepository cidadeRepo;

	@Autowired
	EnderecoRepository enderecoRepo;

	@Autowired
	HospedagemRepository hospedagemRepo;

	@Autowired
	ClienteRepository clieneRepo;

	@Autowired
	ReservaRepository reservaRepo;

	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	CustosRepository custoRepo;

	@Transactional
	public void instanciatedTestDatabase() {

		// incluindo pedidos e lista de pedido
		Estado e1 = new Estado("Santa Catarina");
		Estado e2 = new Estado("Rio Grando do Sul");
		Estado e3 = new Estado("São Paulo");

		Cidade c1 = new Cidade("Palhoça", e1);
		Cidade c2 = new Cidade("Jaguaruna", e1);
		Cidade c3 = new Cidade("Porto", e2);
		Cidade c4 = new Cidade("Tramandai", e2);
		Cidade c5 = new Cidade("Florianopolis", e1);
		Cidade c6 = new Cidade("Nova Veneza", e1);
		Cidade c7 = new Cidade("Campinas", e3);
		Cidade c8 = new Cidade("Santo Antonio", e3);

		e1.getCidades().add(c1);
		e1.getCidades().add(c2);
		e1.getCidades().add(c5);
		e1.getCidades().add(c6);
		e2.getCidades().add(c3);
		e2.getCidades().add(c4);
		e3.getCidades().add(c7);
		e3.getCidades().add(c8);

		estadoRepo.save(e1);
		estadoRepo.save(e2);
		estadoRepo.save(e3);

		cidadeRepo.save(c1);
		cidadeRepo.save(c2);
		cidadeRepo.save(c3);
		cidadeRepo.save(c4);
		cidadeRepo.save(c5);
		cidadeRepo.save(c6);
		cidadeRepo.save(c7);
		cidadeRepo.save(c8);

		Endereco end1 = new Endereco("Rua laguna", "100", "casa-1", "Camacho", "88750-000", c2);
		Endereco end2 = new Endereco("Rua laguna", "100", "casa-2", "Camacho", "88750-000", c2);
		Endereco end3 = new Endereco("Rua Tubarão", "1320", "casa", "Nova Palhoça", "88750-001", c1);
		Endereco end4 = new Endereco("Rua Lages", "111", "apto-101", "Jaguaruna", "88750-000", c2);
		Endereco end5 = new Endereco("Rua Gaucha", "1340", "casa", "Porto Alegre", "88750-000", c3);
		Endereco end6 = new Endereco("Rua Novo Hamburgo", "343", "casa", "Novo Hamburgo", "88750-000", c3);
		Endereco end7 = new Endereco("Rua Haleluia", "1040", "casa", "Tamanda", "88750-000", c4);
		Endereco end8 = new Endereco("Rua João Bento Tomaz", "84", "casa", "Costeira", "88750-047", c5);
		Endereco end9 = new Endereco("Rua Morretes", "45", "casa", "Nova Veneza", "88750-000", c6);
		Endereco end10 = new Endereco("Rua Paulista", "100", "casa", "Campinas", "88750-000", c7);
		Endereco end11 = new Endereco("Rua Sete", "654", "casa", "Centro", "88750-000", c7);
		Endereco end12 = new Endereco("Rua Oito", "1543", "casa", "Centro", "88750-000", c8);
		Endereco end13 = new Endereco("Rua Jaguaruna", "444", "casa", "Centro", "88750-000", c5);
		
		enderecoRepo.save(end1);
		enderecoRepo.save(end2);
		enderecoRepo.save(end3);
		enderecoRepo.save(end4);
		enderecoRepo.save(end5);
		enderecoRepo.save(end6);
		enderecoRepo.save(end7);
		enderecoRepo.save(end8);
		enderecoRepo.save(end9);
		enderecoRepo.save(end10);
		enderecoRepo.save(end11);
		enderecoRepo.save(end12);
		enderecoRepo.save(end13);

		Hospedagem h1 = new Hospedagem("Nordeste", 4, 100.00, 50.00, 50.00, end1, null);
		Hospedagem h2 = new Hospedagem("Suli", 4, 100.00, 50.00, 50.00, end2, null);
		end1.setHospedagem(h1);
		end2.setHospedagem(h2);

		Cliente cli1 = new Cliente("João Silva", "rafaaguiar@ymail.com", end3);
		cli1.getTelefones().add("9175-5501");
		end3.setCliente(cli1);
		Cliente cli2 = new Cliente("Maria Silva", "rafaaguiar.29.07@gmail.com", end4);
		cli2.getTelefones().add("8876-5555");
		end4.setCliente(cli2);
		Cliente cli3 = new Cliente("Jorge Silva", "osvaldosneto1983@gmail.com", end5);
		cli3.getTelefones().add("3624-5555");
		end5.setCliente(cli3);
		Cliente cli4 = new Cliente("Tereza Silva", "osvaldo.sn@aluno.ifsc.edu.br", end6);
		cli4.getTelefones().add("9175-5555");
		end6.setCliente(cli4);
		Cliente cli5 = new Cliente("Antonio Silva", "antonio@ymail.com", end7);
		cli5.getTelefones().add("5323-5555");
		end7.setCliente(cli5);
		Cliente cli6 = new Cliente("Neto Silva", "neto@ymail.com", end8);
		cli6.getTelefones().add("9175-6788");
		end8.setCliente(cli6);
		Cliente cli7 = new Cliente("Almir Silva", "almir@ymail.com", end9);
		cli7.getTelefones().add("9155-6543");
		end9.setCliente(cli7);
		Cliente cli8 = new Cliente("Beth Silva", "beth@ymail.com", end10);
		cli8.getTelefones().add("6578-9874");
		end10.setCliente(cli8);
		Cliente cli9 = new Cliente("Rafa Silva", "rafa@ymail.com", end11);
		cli9.getTelefones().add("6578-5432");
		end11.setCliente(cli9);
		Cliente cli10 = new Cliente("Pinto Silva", "pinto@ymail.com", end12);
		cli10.getTelefones().add("3214-5555");
		end12.setCliente(cli10);
		Cliente cli11 = new Cliente("Osvaldo Silva", "osvaldosneto@hotmail.com", end13);
		cli11.getTelefones().add("3333-5555");
		end13.setCliente(cli11);
		
		LocalDate d1 = LocalDate.of(2020, 1, 8);
		LocalDate d2 = LocalDate.of(2020, 1, 10);
		LocalDate d3 = LocalDate.of(2020, 2, 8);
		LocalDate d4 = LocalDate.of(2020, 3, 8);
		LocalDate d5 = LocalDate.of(2020, 3, 12);
		
		for (LocalDate ld = d1; ld.isBefore(d2); ld = ld.plusDays(1)) {
			h1.getListaDatas().add(ld);
		}
		
		for (LocalDate ld = d1; ld.isBefore(d2); ld = ld.plusDays(1)) {
			h2.getListaDatas().add(ld);
		}

		Reserva res1 = new Reserva(d1, d2, 2, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res2 = new Reserva(d1, d2, 1, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res3 = new Reserva(d2, d3, 4, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res4 = new Reserva(d2, d3, 4, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.NAO);
		Reserva res5 = new Reserva(d4, d5, 3, TipoIntermedio.LOCAL, 0.00, TipoLimpeza.SIM);
		Reserva res6 = new Reserva(d4, d5, 3, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res7 = new Reserva(d4, d5, 2, TipoIntermedio.LOCAL, 0.00, TipoLimpeza.NAO);
		Reserva res8 = new Reserva(d4, d5, 2, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res9 = new Reserva(d2, d3, 2, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.NAO);
		Reserva res10 = new Reserva(d2, d3, 4, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res11 = new Reserva(d2, d3, 1, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.NAO);
		Reserva res12 = new Reserva(d1, d2, 2, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.NAO);
		Reserva res13 = new Reserva(d1, d2, 1, TipoIntermedio.LOCAL, 0.00, TipoLimpeza.NAO);
		Reserva res14 = new Reserva(d1, d2, 1, TipoIntermedio.LOCAL, 0.00, TipoLimpeza.NAO);
		Reserva res15 = new Reserva(d4, d5, 4, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res16 = new Reserva(d4, d5, 3, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res17 = new Reserva(d4, d5, 2, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.SIM);
		Reserva res18 = new Reserva(d4, d5, 3, TipoIntermedio.AIRBNB, 0.00, TipoLimpeza.NAO);

		res1.setHospedagem(h1);
		res2.setHospedagem(h1);
		res3.setHospedagem(h2);
		res4.setHospedagem(h1);
		res5.setHospedagem(h2);
		res6.setHospedagem(h2);
		res7.setHospedagem(h1);
		res8.setHospedagem(h1);
		res9.setHospedagem(h1);
		res10.setHospedagem(h2);
		res11.setHospedagem(h2);
		res12.setHospedagem(h2);
		res13.setHospedagem(h1);
		res14.setHospedagem(h1);
		res15.setHospedagem(h2);
		res16.setHospedagem(h2);
		res17.setHospedagem(h1);
		res18.setHospedagem(h1);

		cli1.getReservas().add(res1);
		cli1.getReservas().add(res2);
		cli1.getReservas().add(res3);
		res1.setCliente(cli1);
		res2.setCliente(cli1);
		res3.setCliente(cli1);

		cli2.getReservas().add(res4);
		cli2.getReservas().add(res5);
		cli2.getReservas().add(res6);
		res4.setCliente(cli2);
		res5.setCliente(cli2);
		res6.setCliente(cli2);

		cli3.getReservas().add(res7);
		res7.setCliente(cli3);

		cli4.getReservas().add(res8);
		res8.setCliente(cli4);

		cli5.getReservas().add(res9);
		res9.setCliente(cli5);

		cli6.getReservas().add(res10);
		res10.setCliente(cli6);

		cli7.getReservas().add(res11);
		cli7.getReservas().add(res12);
		res11.setCliente(cli7);
		res12.setCliente(cli7);

		cli8.getReservas().add(res13);
		cli8.getReservas().add(res14);
		cli8.getReservas().add(res15);
		res13.setCliente(cli8);
		res14.setCliente(cli8);
		res15.setCliente(cli8);

		cli9.getReservas().add(res16);
		cli9.getReservas().add(res17);
		res16.setCliente(cli9);
		res17.setCliente(cli9);

		cli10.getReservas().add(res18);
		res18.setCliente(cli10);

		List<Reserva> listaH1 = new ArrayList<>();
		List<Reserva> listaH2 = new ArrayList<>();
		listaH1.add(res1);
		listaH1.add(res2);
		listaH1.add(res4);
		listaH1.add(res7);
		listaH1.add(res8);
		listaH1.add(res9);
		listaH1.add(res13);
		listaH1.add(res14);
		listaH1.add(res17);
		listaH1.add(res18);
		listaH2.add(res3);
		listaH2.add(res5);
		listaH2.add(res6);
		listaH2.add(res10);
		listaH2.add(res11);
		listaH2.add(res12);
		listaH2.add(res15);
		listaH2.add(res16);

		h1.setReservas(listaH1);
		h2.setReservas(listaH2);
		
		cli11.setSenha(pe.encode("12345"));
		cli11.addPerfil(Perfil.ADMIN);

		clieneRepo.save(cli1);
		clieneRepo.save(cli2);
		clieneRepo.save(cli3);
		clieneRepo.save(cli4);
		clieneRepo.save(cli5);
		clieneRepo.save(cli6);
		clieneRepo.save(cli7);
		clieneRepo.save(cli8);
		clieneRepo.save(cli9);
		clieneRepo.save(cli10);
		clieneRepo.save(cli11);

		reservaRepo.save(res1);
		reservaRepo.save(res2);
		reservaRepo.save(res3);
		reservaRepo.save(res4);
		reservaRepo.save(res5);
		reservaRepo.save(res6);
		reservaRepo.save(res7);
		reservaRepo.save(res8);
		reservaRepo.save(res9);
		reservaRepo.save(res10);
		reservaRepo.save(res11);
		reservaRepo.save(res12);
		reservaRepo.save(res13);
		reservaRepo.save(res14);
		reservaRepo.save(res15);
		reservaRepo.save(res16);
		reservaRepo.save(res17);
		reservaRepo.save(res18);

		hospedagemRepo.save(h1);
		hospedagemRepo.save(h2);
		
		Custos cu1 = new Custos("Energia", LocalDate.now(), d1, 123.43, null);
		Custos cu2 = new Custos("Água", LocalDate.now(), d2, 245.98, null);
		Custos cu3 = new Custos("Energia", LocalDate.now(), d3, 154.98, null);
		Custos cu4 = new Custos("Internet", LocalDate.now(), d4, 123.43, null);
		Custos cu5 = new Custos("Energia", LocalDate.now(), d5, 45.503, null);
		Custos cu6 = new Custos("Energia", LocalDate.now(), d5, 143.43, null);
		Custos cu7 = new Custos("Internet", LocalDate.now(), d1, 3.43, null);
		
		custoRepo.save(cu1);
		custoRepo.save(cu2);
		custoRepo.save(cu3);
		custoRepo.save(cu4);
		custoRepo.save(cu5);
		custoRepo.save(cu6);
		custoRepo.save(cu7);

	}

	public void instanciatedDevDatabase() {
		
		Cliente cli1 = new Cliente("Osvaldo Silva", "osvaldosneto@hotmail.com", null);
		cli1.setSenha(pe.encode("12345"));
		cli1.addPerfil(Perfil.ADMIN);

		clieneRepo.save(cli1);
		
	}

}
