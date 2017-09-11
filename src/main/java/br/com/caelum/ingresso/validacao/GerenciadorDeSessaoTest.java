package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
		Filme filme = new Filme("Roque one", Duration.ofMinutes(120), "SCI-FI",BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");

		Sala sala = new Sala();
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario, filme, sala));
		Sessao sessao = new Sessao(horario, filme, sala);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {
		Filme filme = new Filme("Roque One", Duration.ofMinutes(120), "SCI-FI",BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");

		Sala sala = new Sala();
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario, filme, sala));
		Sessao sessao = new Sessao(horario.minusHours(1), filme, sala);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));

	}

	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {
		Filme filme = new Filme("Roque One", Duration.ofMinutes(120), "SCI-FI",
				BigDecimal.ONE);
		LocalTime horario = LocalTime.now();
		Sala sala = new Sala("Eldorado-Imax", BigDecimal.ONE);

		List<Sessao> sessoesdaSala = Arrays.asList(new Sessao(horario, filme,
				sala));

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesdaSala);
		Assert.assertFalse(gerenciador.cabe(new Sessao(horario.minusHours(1),
				filme, sala)));

	}

	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilme() {
		Sala sala = new Sala("Eldorado-Imax", BigDecimal.ONE);
		Filme filme1 = new Filme("Roque One ", Duration.ofMinutes(120),
				"SCI-FI", BigDecimal.ONE);

		LocalTime dezHoras =LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(dezHoras, filme1, sala);
		
		Filme filme2= new Filme ("Roque One ",Duration.ofMinutes(120),"SCI-FI",BigDecimal.ONE);
		LocalTime dezoitoHoras = LocalTime.parse("18:00");
		Sessao sessaoDezoito = new Sessao(dezoitoHoras,filme2,sala);
		
List<Sessao> sessoes  =Arrays.asList(sessaoDasDez,sessaoDezoito);
GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
Assert.assertTrue(gerenciador.cabe(new Sessao(LocalTime.parse("13:00:00"),filme2,sala)));

	}

}
