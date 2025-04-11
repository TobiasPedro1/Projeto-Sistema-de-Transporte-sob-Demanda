package model;

public class Avaliacao  implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String comentario;
    private int nota;

    public Avaliacao(String comentario, int nota){
        this.comentario = comentario;
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Avaliações: " +
                "nota: " + nota +
                ", comentario: " + comentario;
    }
}
