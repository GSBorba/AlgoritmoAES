import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int BLOCK_SIZE = 16;
    private static String simpleText;
    private static String finalText = "";
    private static int ultimaPosicao;
    private static int simpleTextValues;
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Para o programa funcionar coloque o arquivo que deseja ser cifrado na pasta do programa.\n" +
                "O nome do arquivo deve conter a extensão dele junto. Ex: input.txt\n" +
                "\nDigite o nome do arquivo: ");
        String nomeArquivo = s.nextLine();

        System.out.println("\nInforme o nome do arquivo de saida com a extensão dele: ");
        String nomeArquivoSaida = s.nextLine();

        System.out.println("\nDigite a chave de criptografia em formato decimal separado por vírgula");
        String chave = s.nextLine();

        File inputFile = new File(nomeArquivo); // arquivo de entrada
        if (!inputFile.exists()) {
            System.err.println("Arquivo de entrada não encontrado: " + inputFile.getAbsolutePath());
            return;
        }

        File outputFile = new File(nomeArquivoSaida); // arquivo de saída com padding aplicado

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[BLOCK_SIZE];
            int bytesRead;
            boolean lastBlock = false;

            while ((bytesRead = fis.read(buffer)) != -1) {
                if (bytesRead < BLOCK_SIZE) {
                    // Último bloco, aplicar padding PKCS7
                    lastBlock = true;
                    buffer = applyPKCS7Padding(buffer, bytesRead);
                }
                fos.write(buffer, 0, BLOCK_SIZE);
                if (lastBlock) {
                    break;
                }
            }

            if (!lastBlock) {
                // Caso o arquivo seja múltiplo de BLOCK_SIZE, precisamos adicionar um bloco adicional de padding
                byte[] paddingBlock = applyPKCS7Padding(new byte[BLOCK_SIZE], 0);
                fos.write(paddingBlock, 0, BLOCK_SIZE);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verifique o conteúdo do arquivo de saída
        simpleText = verifyOutputFile(outputFile);
        System.out.println("teste: " + simpleText);
        howManyReps();

        KeyExpansion key = new KeyExpansion(chave);

        for (int i = 0; i <= simpleTextValues / 16; i++) {
            String bloco = breakBlocksBy16();
            CypherRounds cypher = new CypherRounds(bloco, key.getWords());
            convertToCharacter(cypher);
        }

        // Limpar o arquivo output e inserir o conteúdo de finaltext
        try (FileWriter fw = new FileWriter(outputFile, false)) {
            fw.write(finalText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] applyPKCS7Padding(byte[] block, int bytesRead) {
        int paddingValue = BLOCK_SIZE - bytesRead;
        byte[] paddedBlock = new byte[BLOCK_SIZE];
        System.arraycopy(block, 0, paddedBlock, 0, bytesRead);
        for (int i = bytesRead; i < BLOCK_SIZE; i++) {
            paddedBlock[i] = (byte) paddingValue;
        }
        return paddedBlock;
    }

    private static String verifyOutputFile(File outputFile) {
        StringBuilder result = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(outputFile)) {
            byte[] buffer = new byte[BLOCK_SIZE];
            int bytesRead;
            boolean first = true;
            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    if (!first) {
                        result.append(",");
                    }
                    result.append(buffer[i] & 0xFF); // & 0xFF para garantir que os bytes sejam tratados como valores unsigned
                    first = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String breakBlocksBy16() {
        int contagem = 0;
        String bloco = "";

        for (int i = ultimaPosicao; i < simpleText.length(); i++) {
            if (contagem == 16) {
                ultimaPosicao = i;
                break;
            }

            if (simpleText.charAt(i) == ',') {
                if (contagem != 15) {
                    bloco += simpleText.charAt(i);
                }
                contagem++;
                continue;
            }

            bloco += simpleText.charAt(i);
        }

        return bloco;
    }

    public static void howManyReps() {
        for (int i = 0; i < simpleText.length(); i++) {
            if (simpleText.charAt(i) == ',') {
                simpleTextValues++;
            }
        }
    }

    public static void convertToCharacter(CypherRounds cypher) {
        int[][] words = cypher.getWords();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                text.append((char) words[j][i]);
            }
        }
        finalText += text.toString();
    }
}