package utils;

import java.io.*;
import java.util.List;

public class SerializacaoUtil {
    // Implementação de métodos para serializar e desserializar objetos
    // Exemplo: salvar em arquivo, carregar de arquivo, etc.
    // Você pode usar ObjectOutputStream e ObjectInputStream para isso.
    // Lembre-se de tratar exceções adequadamente.

    public static <T> void salvarDados(List<T> lista, String caminhoArquivo){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))){
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static <T> List<T> carregarDados(String caminhoArquivo) {
        // Implementar lógica para carregar objeto de arquivo
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))){
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }

}

