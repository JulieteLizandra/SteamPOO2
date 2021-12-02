import java.util.Map;

import javax.swing.JOptionPane;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class SteamPOOHash implements SteamPOO, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Jogo> jogos;

    public SteamPOOHash() {
        this.jogos = new HashMap<>();
    }

    public void cadastraJogo(Jogo jogo) throws JogoJaExisteException {
        if (this.jogos.containsKey(jogo.getNome())) {
            throw new JogoJaExisteException("Já existe jogo com o nome " + jogo.getNome());
        } else {
            this.jogos.put(jogo.getNome(), jogo);
        }
    }

    public List<Jogo> pesquisaJogosDaCategoria(String categoria) {
        List<Jogo> jogosDaCategoria = new LinkedList<>();

        for (Jogo j: this.jogos.values()) {
            if (j.ehDaCategoria(categoria)) {
                jogosDaCategoria.add(j);
            }
        }
        return jogosDaCategoria;
    }

    public double pesquisaNotaDoJogo(String nome) throws JogoInexistenteException {
        if (this.jogos.containsKey(nome)) {
            return this.jogos.get(nome).getNota();
        } else {
            throw new JogoInexistenteException("Não existe jogo com o nome " + nome);
        }
    }

    public List<Jogo> mostrarTodosOsJogos() {
        List<Jogo> jogosDaCategoria = new LinkedList<>();
        for (Jogo j: this.jogos.values()) {
            jogosDaCategoria.add(j);
        }
        return jogosDaCategoria;
    }
    public List<Jogo> pesquisaJogoComNota(double nota) {
        List<Jogo> jogosComNota = new LinkedList<>();
        for (Jogo j: this.jogos.values()){
            if (j.getNota() == nota){
                jogosComNota.add(j);
            }
        }
        return jogosComNota;
    }
    public void configurarCategorias(Jogo jogo) {
            List<String> categorias = new LinkedList<>();
            boolean continuar = true;
            while (continuar) {
                String opcao = JOptionPane.showInputDialog("Qual categoria você deseja adicionar?\n1."+ Jogo.CATEGORIA_ACAO + "\n2." + Jogo.CATEGORIA_RPG + "\n3." + Jogo.CATEGORIA_AVENTURA + "\n4."+ Jogo.CATEGORIA_PLATAFORMA + "\n5."+Jogo.CATEGORIA_LUTA+"\n6."+Jogo.CATEGORIA_MMORPG+"\n7."+Jogo.CATEGORIA_MOBA+"\n8.Não adicionar mais categorias");
                switch (opcao) {
                    case "1":
                        categorias.add(Jogo.CATEGORIA_ACAO);
                        continuar = false;
                        break;
                    case "2":
                        categorias.add(Jogo.CATEGORIA_RPG);
                        continuar = false;
                        break;
                    case "3":
                        categorias.add(Jogo.CATEGORIA_AVENTURA);
                        continuar = false;
                        break;
                    case "4":
                        categorias.add(Jogo.CATEGORIA_PLATAFORMA);
                        continuar = false;
                        break;
                    case "5":
                        categorias.add(Jogo.CATEGORIA_LUTA);
                        continuar = false;
                        break;
                    case "6":
                        categorias.add(Jogo.CATEGORIA_MMORPG);
                        continuar = false;
                        break;
                    case "7":
                        categorias.add(Jogo.CATEGORIA_MOBA);
                        continuar = false;
                        break;
                    case "8":
                        continuar = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }
                jogo.setCategorias(categorias);
            }
    }
    public void removerJogo(String nome) throws JogoInexistenteException{
        if (this.jogos.containsKey(nome)) {
            this.jogos.remove(nome);
        }else{
            throw new JogoInexistenteException("O jogo que você deseja remover, não existe :(");
        }
    }

    @Override
    public void setJogos(Collection<Jogo> jogosCollection) {
        for (Jogo c: jogosCollection){
            this.jogos.put(c.getNome(), c);
        }
    }

    @Override
    public Collection<Jogo> getJogos() {
        return this.jogos.values();
    }

}