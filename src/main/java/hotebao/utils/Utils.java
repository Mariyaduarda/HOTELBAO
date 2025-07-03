package hotebao.utils;

import java.security.SecureRandom;

public class Utils {

    // String contendo todos os caracteres possíveis para a geração (letras maiúsculas e números)
    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // gerador de números aleatórios seguro (mais seguro que Random comum)
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * Gera uma string aleatória alfanumérica com o comprimento especificado
     * @param length - comprimento da string a ser gerada
     * @return string aleatória contendo apenas letras maiúsculas e números
     */
    public static String generateString(int length) {
        // stringBuffer para construir a string (mais eficiente que concatenação)
        StringBuffer stringBuilder = new StringBuffer();

        // loop para gerar cada caractere da string
        for (int i = 0; i < length; i++) {
            // gera um índice aleatório entre 0 e o tamanho da string de caracteres
            int randomIndex = SECURE_RANDOM.nextInt(ALPHANUMERIC_STRING.length());

            // seleciona o caractere no índice aleatório
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);

            // adiciona o caractere ao resultado final
            stringBuilder.append(randomChar);
        }

        // retorna a string gerada
        return stringBuilder.toString();
    }
}