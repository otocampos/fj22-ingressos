package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.GerenciadorSessao;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorSessaoTeste {

	@Test
	public void deveRetornarFalseQuandoNaoCabe() {
		LocalTime duasEmeia = LocalTime.of(14, 30);
		Filme f1 = new Filme("Harry Poter", Duration.ofMinutes(120), "Aventura", BigDecimal.ONE);
		Sala sala = new Sala();

		Sessao s1 = new Sessao(duasEmeia, f1, sala);
		List<Sessao> sessoesExistentes = Arrays.asList(
				new Sessao(LocalTime.of(14, 30), new Filme(
						"velozes e Furiosos", Duration.ofMinutes(120), "Acao",BigDecimal.ONE),
						sala), new Sessao(LocalTime.of(20, 10),
						new Filme("Esqueceram de mim", Duration.ofMinutes(90),
								"Comédia",BigDecimal.ONE), sala),
				new Sessao(LocalTime.of(10, 00), new Filme("Anjos da Noite",
						Duration.ofMinutes(150), "Terror",BigDecimal.ONE), sala));

		GerenciadorSessao g = new GerenciadorSessao(sessoesExistentes);
		Assert.assertFalse(g.cabe(s1));

	}

}
