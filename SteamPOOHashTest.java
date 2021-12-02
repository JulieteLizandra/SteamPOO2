import static org.junit.jupiter.api.Assertions.*;



import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;



class SteamPOOHashTest {
	/**
	 * 
	 */

	SteamPOOHash sistema = new SteamPOOHash();
	Jogo Jogo = new Jogo();

	@Test
	void testCadastraJogo() {
		List<String> categoria = new LinkedList<>();
		SteamPOOHash sistema = new SteamPOOHash();
		// categorias.add(CategoriaDeJogo.MOBA);
		Jogo Jogo = new Jogo("Pinball", 5.0, categoria);
		Jogo Jogo2 = new Jogo("Call Of Duty", 4.0, categoria);
		try {
			sistema.cadastraJogo(Jogo);
			sistema.cadastraJogo(Jogo2);
		} catch (JogoJaExisteException e) {
			fail("Nao deveria ter lancado excecao");
		}
		List<CategoriaDeJogo> categoriasJogo = new LinkedList<>();
		categoriasJogo.add(CategoriaDeJogo.ARCADE);
		categoriasJogo.add(CategoriaDeJogo.ACAO);
		try {
			sistema.cadastraJogo(Jogo);
			fail("Nao deveria ter deixado cadastrar um jogo igual");
		} catch (JogoJaExisteException e) {
			System.out.println("Ok");
		}
	}

	@Test
	void testPesquisaJogosDaCategoria() {
		List<String> categorias = new LinkedList<>();
		categorias.add("Ação");
		
		//Jogo pinbalGame = new Jogo("Pinball", 5.0, categorias, categorias);
		Jogo pubgGame = new Jogo("PUBG", 4.0, categorias);

		SteamPOOHash sistema = new SteamPOOHash();

		try {
			//sistema.cadastraJogo(pinbalGame);
			sistema.cadastraJogo(pubgGame);

			List<Jogo> jogosAcao = sistema.pesquisaJogosDaCategoria("Ação");
			assertTrue(jogosAcao.size() == 1);

			List<Jogo> jogosArcade = sistema.pesquisaJogosDaCategoria("Arcade");
			assertTrue(jogosArcade.size() == 0);
		} catch (JogoJaExisteException e) {
			fail("Não deveria lançar a exceção");
		}
	}

	@Test
	void testPesquisaNotaDoJogo() {
		List<CategoriaDeJogo> categorias = new LinkedList<>();
		categorias.add(CategoriaDeJogo.ARCADE);
		SteamPOOHash sistema = new SteamPOOHash();
		try {
			sistema.pesquisaNotaDoJogo("Pinball");
			fail("Deveria ter lancado excecao");
		} catch (JogoInexistenteException e) {
			System.out.println("Ok. Excecao esperada");
		}
	}

	@Test
	void testMostrarTodosOsJogos() throws JogoInexistenteException {
		// List<String> categorias = new LinkedList<>();
		// Jogo jogo = new Jogo("Pinball",5.0, categorias);
		// Jogo jogo2 = new Jogo("PUBG",4.0, categorias);
		SteamPOOHash sistema = new SteamPOOHash();
		try {
			sistema.cadastraJogo(Jogo);
		} catch (JogoJaExisteException e) {
			System.out.println("Ok! Exceção esperada");
		}
		sistema.mostrarTodosOsJogos();

	}

	@Test
	void testRemoverJogo() {
		List<CategoriaDeJogo> categorias = new LinkedList<>();
		categorias.add(CategoriaDeJogo.ARCADE);
		SteamPOOHash sistema = new SteamPOOHash();
		try {
			sistema.removerJogo("Pinball");
			fail("Deveria ter lancado excecao");
		} catch (JogoInexistenteException e) {
			System.out.println("Ok. Excecao esperada");
		}
	}

	void testPesquisaJogoComNota() throws JogoInexistenteException {
		SteamPOOHash sistema = new SteamPOOHash();
		sistema.pesquisaJogoComNota(4.3);
	}
}
