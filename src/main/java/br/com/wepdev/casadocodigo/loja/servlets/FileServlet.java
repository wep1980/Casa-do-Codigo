package br.com.wepdev.casadocodigo.loja.servlets;

import br.com.wepdev.casadocodigo.loja.infra.FileSaver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe que trabalha os arquivos para exibição
 */
@WebServlet("/file/*") // Possibilita o uso do getRequestURI()
public class FileServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Quebra a URL em uma string e pega o que esta na posição 1, é o que vem depois do file
        String path = req.getRequestURI().split("/file")[1];

        // Eviando de volta para a tela, o fato de ser um arquivo ele segue algumas regras

        Path source = Paths.get(FileSaver.SERVER_PATH + "/" + path); // Pegando o arquivo que foi salvo
        FileNameMap fileNameMap = URLConnection.getFileNameMap();  // Conectando ao arquivo
        String contentType = fileNameMap.getContentTypeFor("file:" + source); // "file:" -> Protocolo

        resp.reset(); // LIMPA O RESPONSE. Antes de operar qualquer coisa no Response utilizando e importante realizar o reset()
        resp.setContentType(contentType); // Para que o navegador funcione corretamente de acordo com o tipo de contentType
        resp.setHeader("Content-Length" , String.valueOf(Files.size(source))); // Obtem o tamanho do arquivo
        resp.setHeader("Content-Disposition" ,
                       "filename=\"" + source.getFileName().toString() + "\""); // Para que o navegador possa baixar(Download), exibir esse arquivo

        FileSaver.transfer(source , resp.getOutputStream()); // Metodo responsavel por transferir o arquivo atraves do response

    }
}
