public class Main {
    public static void main(String[] args) {
        KeyExpansion key = new KeyExpansion("65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80");
        CypherRounds cypher = new CypherRounds("68,69,83,69,78,86,79,76,86,73,77,69,78,84,79,33", key.getWords());
    }
}