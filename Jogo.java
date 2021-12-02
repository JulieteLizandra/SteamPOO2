import java.util.List;
import java.io.Serializable;
import java.util.LinkedList;

public class Jogo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
    private double nota;
    private List<String> categorias;

    public static final String CATEGORIA_MOBA = "MOBA"; //ok
    public static final String CATEGORIA_RPG = "RPG"; //ok
    public static final String CATEGORIA_PLATAFORMA = "Plataforma"; //ok
    public static final String CATEGORIA_AVENTURA = "Aventura"; // ok
    public static final String CATEGORIA_MMORPG = "MMORPG"; //ok
    public static final String CATEGORIA_ACAO = "Ação"; //ok
    public static final String CATEGORIA_LUTA = "Luta"; //ok


    public Jogo() {
        this("", 0.0, new LinkedList<>());
    }

    public Jogo(String nome) {
        this(nome, 0.0, new LinkedList<>());
    }

    public Jogo(String nome, double nota, List<String> categorias) {
        this.nome = nome;
        this.nota = nota;
        this.categorias = categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jogo other = (Jogo) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return "Nome : " + nome + "\nNota : " + nota + "\nCategorias : " + categorias;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double novaNota) {
        this.nota = novaNota;
    }

    public boolean ehDaCategoria(String categoria) {
        for (String cat : this.categorias) {
            if (cat.equals(categoria)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getCategorias() {
        return this.categorias;
    }

}

