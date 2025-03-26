package utils;

public class VerificaCpf {
    public static boolean verificaCPF(String cpf){
        cpf = cpf.replaceAll("[^0-9]","");

        if(cpf.length()  != 11){
            //atualizar futuramente pra lançar exceção
            System.out.println("CPF inválido, deve conter 11 dígitos");

            return false;
        }

        if(cpf.matches("(\\d)\\1{10}")){
            //atualizar futuramente pra lançar exceção
            System.out.println("CPF inválido, todos os dígitos são iguais");

            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            // - '0' é pora dar o valor correto do numero inteiro na tabela ASCII, pela subitração do valor do '0'
            soma += (cpf.charAt(i) - '0') * (10-i);
        }

        int primeiroDigito = 11 - (soma % 11);
        if(primeiroDigito > 9){
            primeiroDigito = 0;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma+= (cpf.charAt(i) - '0') * (11-i);
        }

        int segundoDigito = 11 - (soma % 11);
        if(segundoDigito>9){
            segundoDigito = 0;
        }

        return cpf.charAt(9) - '0' == primeiroDigito && cpf.charAt(10) - '0' == segundoDigito;


    }

}
