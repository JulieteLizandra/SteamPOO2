import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class SteamGUI extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GravadorDeDados gravador;
    JLabel linha1, linha2;
    ImageIcon iconImg = new ImageIcon("Steam_Logo.png");
    ImageIcon addImg = new ImageIcon("/icons/add.png");
    ImageIcon searchImg = new ImageIcon("/icons/search.png");
    SteamPOO sistema;
    JMenuBar barraMenu = new JMenuBar();
    
    public SteamGUI(){
        gravador = new GravadorDeDados();
        sistema = new SteamPOOHash();
        try{
            Collection<Jogo> jogosRecuperados =  gravador.recuperarJogos();
            sistema.setJogos(jogosRecuperados);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Não foi possível recuperar jogos anteriores :(");
        }


        setTitle("STEAM POO 2.0");
        setSize(500,500);
        setLocation(150, 150);
        setResizable(false);
        setBackground(Color.BLACK);
        linha1 = new JLabel("Steam POO 2.0",JLabel.CENTER);
        linha1.setForeground(Color.BLACK);
        linha1.setFont(new Font("Impact", Font.BOLD, 24));
        linha2 = new JLabel(iconImg, JLabel.CENTER);
        JButton buttonAdd = new JButton ("Adicionar jogo", addImg);
        buttonAdd.addActionListener(
            (ae) -> {
                this.setVisible(false);
                String nome = JOptionPane.showInputDialog(this,"Digite o nome do jogo");
                Jogo jogo = new Jogo(nome);
                try {
                    double nota = Double.parseDouble(JOptionPane.showInputDialog(this,"Digite sua nota ao jogo"));
                    jogo.setNota(nota);
                    sistema.configurarCategorias(jogo);
                    sistema.cadastraJogo(jogo);
                    JOptionPane.showMessageDialog(this, "Jogo cadastrado com sucesso");
                    this.setVisible(true);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Valor inválido. O preço deve ser um número real com .");
                    } catch (JogoJaExisteException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                
            }
        );
        JButton buttonSearch = new JButton ("Buscar Jogo por Categoria", searchImg);
        buttonSearch.addActionListener(
            (ae) -> {
                String categoriaPesq = JOptionPane.showInputDialog(this,"Qual a categoria a pesquisar?");
                List<Jogo> jogosAchados = sistema.pesquisaJogosDaCategoria(categoriaPesq);
                if (jogosAchados.size() == 0){
                    JOptionPane.showMessageDialog(this, "Nao tem nem um jogo aqui Campeao :(");
                }else{
                    for (Jogo jogoAchado: jogosAchados) {
                        JOptionPane.showMessageDialog(this, jogoAchado.toString());
                    }
                }
        }   
        );
        JButton buttonRemove = new JButton ("Remover jogo", addImg);
        buttonRemove.addActionListener(
            (ae) -> {
                String nomeJogoRemover = JOptionPane.showInputDialog(this,"Digite o nome do jogo que deseja remover");
                try{
                    sistema.removerJogo(nomeJogoRemover);
                    JOptionPane.showMessageDialog(this,"Jogo removido com sucesso");
                }catch (JogoInexistenteException e){
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }    
            }
        );
        JButton buttonSair = new JButton ("Sair");
        buttonSair.addActionListener(
            (ae) -> {
                JOptionPane.showMessageDialog(this,"Você está saindo, Beijos de Luz");
                System.exit(0);
            }
        );
        
        setLayout(new GridLayout(3,2));
        add(linha1);
        add(linha2);
        add(buttonSearch);
        add(buttonAdd);
        add(buttonRemove);
        add(buttonSair);

        JMenu menuSalvar = new JMenu("Salvar");
        JMenu menuPesquisar = new JMenu("Pesquisas");
        JMenu menuMostrar = new JMenu("Mostrar");
        JMenuItem menuPesquisarNota = new JMenuItem("Pesquisar jogos com nota");
        JMenuItem menuPesquisarDaCategoria = new JMenuItem("Pesquisar jogos da categoria");
        JMenuItem menuPesquisarJogoEspecifico = new JMenuItem("Pesquisar nota do jogo");
        JMenuItem menuMostrarItem = new JMenuItem ("Mostrar todos os jogos");
        JMenuItem menuSalvarDados = new JMenuItem("Salvar jogos");
        menuPesquisar.add(menuPesquisarNota);
        menuPesquisar.add(menuPesquisarDaCategoria);
        menuPesquisar.add(menuPesquisarJogoEspecifico);
        menuMostrar.add(menuMostrarItem);
        menuSalvar.add(menuSalvarDados);

        menuPesquisarNota.addActionListener(
            (ae) -> {
                double nota = Double.parseDouble(JOptionPane.showInputDialog("Qual a nota do jogo que deseja pesquisar?"));
                    JOptionPane.showMessageDialog(this, sistema.pesquisaJogoComNota(nota));
            }
        );
        menuPesquisarDaCategoria.addActionListener(
            (ae) -> {
                String categoriaPesq = JOptionPane.showInputDialog(this,"Qual a categoria a pesquisar?");
                List<Jogo> jogosAchados = sistema.pesquisaJogosDaCategoria(categoriaPesq);
                for (Jogo jogoAchado: jogosAchados) {
                    JOptionPane.showMessageDialog(this, jogoAchado.toString());
                }
            }
        );
        menuPesquisarJogoEspecifico.addActionListener(
            (ae) -> {
                String nomeJogoAPesquisar = JOptionPane.showInputDialog("Qual o nome do jogo a pesquisar?");
                    try {
                        double notaDoJogo = sistema.pesquisaNotaDoJogo(nomeJogoAPesquisar);
                        JOptionPane.showMessageDialog(null, "A nota do jogo é " + notaDoJogo);
                    } catch (JogoInexistenteException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
            }
        );
        menuMostrarItem.addActionListener(
            (ae) ->{
                JOptionPane.showMessageDialog(this, sistema.mostrarTodosOsJogos());
            }
        );
        menuSalvarDados.addActionListener(
            (ae) -> {
                try {
                    gravador.gravarJogos(sistema.getJogos());
                    JOptionPane.showMessageDialog(this, "Jogos salvos com sucesso");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Problema ao salvar dados");
                    e.printStackTrace();
                }
            }
        );

        barraMenu.add(menuPesquisar);
        barraMenu.add(menuSalvar);
        barraMenu.add(menuMostrar);
        setJMenuBar(barraMenu);
    }
    public static void main (String [] args){
        JFrame window = new SteamGUI();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
