import server_connection_files.ServerConnectionJava;

class AlwaysCooporate extends ServerConnectionJava {
    static String myMove = "";
    static String opponentsMove = "";
    String makeMove() {
        // Implement your strategy here
        // For example, always cooperate
        return "c";
    }

    public static void main(String[] args) {
        AlwaysCooporate s = new AlwaysCooporate();
        int totalRounds = s.getTotalRounds();
        while (totalRounds > 0) {
            s.tellMove(myMove);
            opponentsMove = s.knowMove();
            myMove = s.makeMove();
            totalRounds--;
        }

        s.closeConnection();
    }
}
