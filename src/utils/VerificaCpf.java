package utils;

public class VerificaCpf {
    public static boolean verificaCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            System.out.println("CPF inválido, deve conter 11 dígitos");
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            System.out.println("CPF inválido, todos os dígitos são iguais");
            return false;
        }

        int primeiroDigito = calculaDigitoVerificador(cpf, 10);
        int segundoDigito = calculaDigitoVerificador(cpf, 11);

        return (cpf.charAt(9) - '0' == primeiroDigito) &&
                (cpf.charAt(10) - '0' == segundoDigito);
    }

    private static int calculaDigitoVerificador(String cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += (cpf.charAt(i) - '0') * (pesoInicial - i);
        }

        int digito = 11 - (soma % 11);
        return (digito > 9) ? 0 : digito;
    }


}
