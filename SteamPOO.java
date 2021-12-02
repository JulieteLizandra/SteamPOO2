import java.util.Collection;
import java.util.List;

public interface SteamPOO {

    public void cadastraJogo(Jogo jogo) throws JogoJaExisteException;
    public void setJogos(Collection<Jogo> jogos);
    public Collection<Jogo> getJogos();
    public List<Jogo> pesquisaJogosDaCategoria(String categoria);
    public List<Jogo> mostrarTodosOsJogos();
    public double pesquisaNotaDoJogo(String nome) throws JogoInexistenteException;
    public List<Jogo> pesquisaJogoComNota(double nota);
    public void configurarCategorias(Jogo jogo);
    public void removerJogo(String nome) throws JogoInexistenteException;
}