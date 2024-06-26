import server_connection_files.ServerConnectionJava;

class AlwaysDefect extends ServerConnectionJava {
    static String myMove = "";
    static String opponentsMove = "";
    String makeMove() {
        // Implement your strategy here
        // For example, always cooperate
        return "d";
    }

    public static void main(String[] args) {
        AlwaysDefect s = new AlwaysDefect();
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
