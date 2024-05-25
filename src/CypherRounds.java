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
    private static final String[][] tableL = {
        {"00", "00", "19", "01", "32", "02", "1a", "c6", "4b", "c7", "1b", "68", "33", "ee", "df", "03"},
        {"64", "04", "e0", "0e", "34", "8d", "81", "ef", "4c", "71", "08", "c8", "f8", "69", "1c", "c1"},
        {"7d", "2d", "b5", "f9", "b9", "27", "a4", "4d", "e4", "e5", "7a", "c9", "09", "78", "d9", "63"},
        {"65", "2f", "8a", "05", "21", "d0", "e1", "24", "f0", "82", "45", "35", "93", "d4", "8a", "8e"},
        {"96", "8f", "db", "bd", "36", "d0", "96", "13", "5c", "d2", "f1", "40", "46", "83", "88", "0a"},
        {"66", "dd", "fd", "30", "bf", "0e", "8b", "62", "b3", "25", "e2", "98", "22", "04", "86", "7d"},
        {"7e", "6e", "48", "c3", "a3", "b6", "1e", "42", "3a", "6b", "28", "54", "fa", "85", "93", "ba"},
        {"2b", "79", "0a", "15", "9b", "9f", "5e", "ca", "4e", "d4", "ac", "e5", "f3", "73", "a7", "57"},
        {"af", "58", "a8", "50", "f4", "ea", "d6", "74", "4f", "4e", "a9", "e9", "d5", "e7", "e6", "e8"},
        {"2c", "d7", "75", "7a", "eb", "0b", "f5", "59", "cb", "5f", "b0", "9c", "a9", "51", "a0", "d8"},
        {"7f", "0c", "f6", "6f", "17", "c4", "49", "ec", "d8", "43", "1f", "2d", "a4", "76", "7b", "b7"},
        {"cc", "bb", "3e", "5a", "fb", "60", "b1", "86", "38", "52", "a1", "6c", "aa", "59", "2b", "9d"},
        {"97", "b2", "87", "90", "61", "be", "dc", "fc", "bc", "95", "cf", "cd", "37", "3f", "b5", "d1"},
        {"53", "39", "84", "3c", "41", "a2", "6d", "47", "14", "2a", "9e", "5d", "56", "f2", "15", "ab"},
        {"a3", "11", "92", "d9", "b4", "d2", "7c", "b4", "7c", "8d", "77", "29", "e3", "e3", "a5", "2a"},
        {"67", "4a", "ed", "de", "c5", "31", "fe", "18", "0d", "63", "8c", "80", "c0", "f7", "70", "07"}
    };
    private static final String[][] tableE = {
        {"01", "03", "05", "0f", "11", "33", "55", "ff", "1a", "2e", "72", "96", "a1", "f8", "13", "35"},
        {"5f", "e1", "38", "48", "d8", "73", "95", "a4", "f7", "02", "06", "0a", "1e", "22", "66", "aa"},
        {"e5", "34", "5c", "e4", "37", "59", "eb", "26", "6a", "be", "d9", "70", "90", "ab", "e6", "31"},
        {"53", "f5", "04", "0c", "14", "3c", "44", "cc", "cf", "d1", "68", "b8", "b5", "d8", "26", "88"},
        {"a4", "d4", "67", "a9", "e0", "3b", "4d", "d7", "6a", "6f", "18", "28", "78", "83", "9e", "04"},
        {"83", "9e", "8b", "0d", "6b", "bd", "dc", "7f", "81", "98", "b3", "49", "8d", "76", "9a", "b5"},
        {"b5", "c4", "57", "f9", "10", "30", "50", "f0", "0b", "1d", "27", "69", "b8", "d6", "61", "a3"},
        {"fe", "19", "2b", "7d", "87", "92", "ad", "ec", "2f", "71", "93", "ae", "e9", "20", "60", "41"},
        {"fb", "16", "3a", "4e", "d5", "6d", "b7", "c2", "5d", "e7", "32", "56", "fa", "15", "3f", "a1"},
        {"c3", "5e", "e2", "3d", "47", "c9", "40", "5b", "ed", "2c", "74", "9c", "bf", "da", "75", "9f"},
        {"9f", "ba", "d5", "64", "ac", "ef", "2a", "7e", "82", "9d", "bc", "df", "7a", "8e", "89", "80"},
        {"9b", "b6", "c1", "58", "e8", "23", "65", "af", "ea", "21", "b1", "c8", "43", "c5", "54", "fc"},
        {"fc", "1f", "21", "63", "a5", "f4", "07", "09", "1b", "2d", "77", "99", "45", "4c", "56", "ca"},
        {"45", "4c", "56", "ca", "78", "a8", "e3", "3e", "42", "84", "8a", "ca", "12", "36", "5a", "de"},
        {"12", "36", "5a", "de", "29", "7b", "8d", "8c", "8f", "84", "94", "a7", "f2", "0d", "17", "01"},
        {"39", "4b", "dd", "7c", "84", "97", "a2", "fd", "1c", "24", "6c", "b4", "c7", "52", "f6", "01"}
    };
    private static final int[][] MIX_COLUMNS_MATRIX = {
        {0x02, 0x03, 0x01, 0x01},
        {0x01, 0x02, 0x03, 0x01},
        {0x01, 0x01, 0x02, 0x03},
        {0x03, 0x01, 0x01, 0x02}
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
        mixColumns();
        addRoundKey(roundKey);
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

    private static void mixColumns() {
        int[][] temp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[j][i] = (meAjudaPorFavor(words[0][i], MIX_COLUMNS_MATRIX[j][0])) 
                           ^ (meAjudaPorFavor(words[1][i], MIX_COLUMNS_MATRIX[j][1])) 
                           ^ (meAjudaPorFavor(words[2][i], MIX_COLUMNS_MATRIX[j][2])) 
                           ^ (meAjudaPorFavor(words[3][i], MIX_COLUMNS_MATRIX[j][3])); 
            }
        }
        words = temp;

        System.out.println("**** |CypherRounds| Stage4 |CypherRounds| ****");
        System.out.println("R1 = " + mostValuableBitL(words[0][0]) + "termo = " + MIX_COLUMNS_MATRIX[0][0] );
        System.out.println("0x" + Integer.toHexString(words[0][0]) + "   0x" + Integer.toHexString(words[0][1]) + "   0x" + Integer.toHexString(words[0][2]) + "   0x" + Integer.toHexString(words[0][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[1][0]) + "   0x" + Integer.toHexString(words[1][1]) + "   0x" + Integer.toHexString(words[1][2]) + "   0x" + Integer.toHexString(words[1][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[2][0]) + "   0x" + Integer.toHexString(words[2][1]) + "   0x" + Integer.toHexString(words[2][2]) + "   0x" + Integer.toHexString(words[2][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[3][0]) + "   0x" + Integer.toHexString(words[3][1]) + "   0x" + Integer.toHexString(words[3][2]) + "   0x" + Integer.toHexString(words[3][3])); // Apenas para teste, remover depois
    }

    private static int meAjudaPorFavor(int termo1, int termo2) {
        if (termo1 != 0 && termo1 != 1 && termo2 != 0 && termo2 != 1) {
            int var = mostValuableBitL(termo1) + mostValuableBitL(termo2);

            if (var > 255) {
                var -= 255;
            }

            return mostValuableBitE(var);
        }

        return termo1 * termo2; 
        
    }

    private static int mostValuableBitL(int termo) {
        int row = (termo >> 4) & 0x0F;
        int col = termo & 0x0F;
        return Integer.parseInt(tableL[row][col], 16);
    }

    private static int mostValuableBitE(int termo) {
        int row = (termo >> 4) & 0x0F;
        int col = termo & 0x0F;
        return Integer.parseInt(tableE[row][col], 16);
    }

    public static void addRoundKey(int[][] roundKey) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                words[j][i] ^= roundKey[j][i];
            }
        }

        System.out.println("**** |CypherRounds| Stage5 |CypherRounds| ****");
        System.out.println("0x" + Integer.toHexString(words[0][0]) + "   0x" + Integer.toHexString(words[0][1]) + "   0x" + Integer.toHexString(words[0][2]) + "   0x" + Integer.toHexString(words[0][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[1][0]) + "   0x" + Integer.toHexString(words[1][1]) + "   0x" + Integer.toHexString(words[1][2]) + "   0x" + Integer.toHexString(words[1][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[2][0]) + "   0x" + Integer.toHexString(words[2][1]) + "   0x" + Integer.toHexString(words[2][2]) + "   0x" + Integer.toHexString(words[2][3])); // Apenas para teste, remover depois
        System.out.println("0x" + Integer.toHexString(words[3][0]) + "   0x" + Integer.toHexString(words[3][1]) + "   0x" + Integer.toHexString(words[3][2]) + "   0x" + Integer.toHexString(words[3][3])); // Apenas para teste, remover depois
    }
}
