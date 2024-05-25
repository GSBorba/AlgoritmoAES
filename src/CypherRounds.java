public class CypherRounds {

    private static int[][] words = new int[4][4];
    private static final String[][] SBox = {
        {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
        {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
        {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
        {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
        {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
        {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
        {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
        {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
        {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
        {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
        {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
        {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
        {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
        {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
        {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
        {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
    };

    public CypherRounds(String simpleText, int[][] roundKey) {
        String[] simpleTextSplit = simpleText.split(",");
        
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = Integer.parseInt(simpleTextSplit[count++]);
                String hex = Integer.toHexString(value);
                // Verificando se o código hexadecimal tem apenas um digito, se tiver adiciona um 0 (zero) na frente
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                // Esse 16 junto do parseInt é para dizer que tem que ser na base 16, ou seja, hexadecimal
                words[j][i] = Integer.parseInt(hex, 16);
            }
        }

        System.out.println("**** |CypherRounds| RoundKey=0 |CypherRounds| ****");
        System.out.println("0x" + Integer.toHexString(words[0][0]) + "   0x" + Integer.toHexString(words[0][1]) + "   0x" + Integer.toHexString(words[0][2]) + "   0x" + Integer.toHexString(words[0][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[1][0]) + "   0x" + Integer.toHexString(words[1][1]) + "   0x" + Integer.toHexString(words[1][2]) + "   0x" + Integer.toHexString(words[1][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[2][0]) + "   0x" + Integer.toHexString(words[2][1]) + "   0x" + Integer.toHexString(words[2][2]) + "   0x" + Integer.toHexString(words[2][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[3][0]) + "   0x" + Integer.toHexString(words[3][1]) + "   0x" + Integer.toHexString(words[3][2]) + "   0x" + Integer.toHexString(words[3][3])); // Apenas para teste, remover depois
        xor(roundKey);
        subBytes();
        shiftRows();
    }

    private void xor(int[][] roundKey) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                words[j][i] = words[j][i] ^ roundKey[j][i];
            }
        }

        System.out.println("**** |CypherRounds| Stage1 |CypherRounds| ****");
        System.out.println("0x" + Integer.toHexString(words[0][0]) + "   0x" + Integer.toHexString(words[0][1]) + "   0x" + Integer.toHexString(words[0][2]) + "   0x" + Integer.toHexString(words[0][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[1][0]) + "   0x" + Integer.toHexString(words[1][1]) + "   0x" + Integer.toHexString(words[1][2]) + "   0x" + Integer.toHexString(words[1][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[2][0]) + "   0x" + Integer.toHexString(words[2][1]) + "   0x" + Integer.toHexString(words[2][2]) + "   0x" + Integer.toHexString(words[2][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[3][0]) + "   0x" + Integer.toHexString(words[3][1]) + "   0x" + Integer.toHexString(words[3][2]) + "   0x" + Integer.toHexString(words[3][3])); // Apenas para teste, remover depois
    }

    public void subBytes() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int row = (words[j][i] >> 4) & 0x0F;
                int col = words[j][i] & 0x0F;
                words[j][i] = Integer.parseInt(SBox[row][col], 16);
            }
        }

        System.out.println("**** |CypherRounds| Stage2 |CypherRounds| ****");
        System.out.println("0x" + Integer.toHexString(words[0][0]) + "   0x" + Integer.toHexString(words[0][1]) + "   0x" + Integer.toHexString(words[0][2]) + "   0x" + Integer.toHexString(words[0][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[1][0]) + "   0x" + Integer.toHexString(words[1][1]) + "   0x" + Integer.toHexString(words[1][2]) + "   0x" + Integer.toHexString(words[1][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[2][0]) + "   0x" + Integer.toHexString(words[2][1]) + "   0x" + Integer.toHexString(words[2][2]) + "   0x" + Integer.toHexString(words[2][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[3][0]) + "   0x" + Integer.toHexString(words[3][1]) + "   0x" + Integer.toHexString(words[3][2]) + "   0x" + Integer.toHexString(words[3][3])); // Apenas para teste, remover depois
    }

    public static void shiftRows() {
        int rows = words.length;
        int cols = words[0].length;

        int[][] newWord = new int[rows][cols];

        newWord[0] = words[0].clone();

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newWord[i][j] = words[i][(j + i) % cols];
            }
        }
        
        words = newWord;

        System.out.println("**** |CypherRounds| Stage3 |CypherRounds| ****");
        System.out.println("0x" + Integer.toHexString(words[0][0]) + "   0x" + Integer.toHexString(words[0][1]) + "   0x" + Integer.toHexString(words[0][2]) + "   0x" + Integer.toHexString(words[0][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[1][0]) + "   0x" + Integer.toHexString(words[1][1]) + "   0x" + Integer.toHexString(words[1][2]) + "   0x" + Integer.toHexString(words[1][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[2][0]) + "   0x" + Integer.toHexString(words[2][1]) + "   0x" + Integer.toHexString(words[2][2]) + "   0x" + Integer.toHexString(words[2][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[3][0]) + "   0x" + Integer.toHexString(words[3][1]) + "   0x" + Integer.toHexString(words[3][2]) + "   0x" + Integer.toHexString(words[3][3])); // Apenas para teste, remover depois
    }

    public void mixColumns() {
        
    }
}
