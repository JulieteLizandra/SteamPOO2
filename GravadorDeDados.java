import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class GravadorDeDados {

	private String jogosArquivo;
	
	public GravadorDeDados() {
		this.jogosArquivo = "jogos.txt";
	}
	
	public GravadorDeDados(String jogosArquivo) {
		this.jogosArquivo = jogosArquivo;
	}
	
	public Collection <Jogo> recuperarJogos() throws IOException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(jogosArquivo));
			Collection<Jogo> jogosAGravar = (Collection <Jogo>) in.readObject();
			return jogosAGravar;
		} catch (FileNotFoundException e) {
			throw new IOException("Não foi encontrado o arquivo "+ jogosArquivo);
		} catch (IOException e) {
			throw e;
		} catch (ClassNotFoundException e){
			throw new  IOException("Classe do objeto gravado no arquivo" +jogosArquivo+ "Não existe :(");
		}finally{
			if (in != null){
				in.close();
			}
		}
	}
	
	public void gravarJogos(Collection<Jogo> jogos) throws IOException {
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(jogosArquivo));
			List<Jogo> jogosAGravar = new ArrayList<Jogo>();
			jogosAGravar.addAll(jogos);
			out.writeObject(jogosAGravar);
		}catch(FileNotFoundException e){
			throw new IOException("Não foi encontrado o arquivo"+jogosArquivo);
		}catch(IOException e){
			throw e;
		}finally{
			if (out != null){
				out.close();
			}
		}

	}
}