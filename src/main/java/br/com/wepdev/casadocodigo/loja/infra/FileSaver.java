package br.com.wepdev.casadocodigo.loja.infra;

import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

/**
 * CLASSE QUE TRABALHA COM ARQUIVOS
 */
public class FileSaver {

    // Não precisa de uma instancia para ser acessado
    public static final String SERVER_PATH = "/casadocodigo"; // Local no servidor onde ficara armazenado os arquivos



    /**
     * Metodo de escrita de qualquer tipo de arquivo no disco. Pode ser utilizado em qualquer lugar
     * @param arquivo
     * @param path
     * @return
     */
    public String write(Part arquivo , String path)  {

        // Variavel que concatena o path que e o local de armazenamento do tipo de arquivo especifico e o arquivo selecionado
        String relativePath = path + "/" + arquivo.getSubmittedFileName();
        try {
            arquivo.write(SERVER_PATH + "/" + relativePath);
            return relativePath;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo responsavel por transferir o arquivo
     * @param source
     * @param outputStream
     */
    public static void transfer(Path source, OutputStream outputStream)  {

        try {
            FileInputStream input = new FileInputStream(source.toFile()); // Entrada do arquivo no sistema

            // try() -> Esse tipo de try funciona fechando esse recurso automaticamente, ou seja ele fecha o canal
            try(ReadableByteChannel inputChannel = Channels.newChannel(input); // Canal de entrada

                WritableByteChannel outputChannel = Channels.newChannel(outputStream)){ // Canal de saida

                ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10); // Capacidade de alocamento. Transferencia de 10 em 10 kbytes

                while(inputChannel.read(buffer) != -1 ){ // - 1 = Ja leu tudo do buffer
                    buffer.flip(); //Seta os bytes para 0. Ao utilizar um buffer na entrada(Leitura)
                    outputChannel.write(buffer); // Escrevendo
                    buffer.clear(); // Limpa o buffer para que ele possa ler novamente
                }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }
}
