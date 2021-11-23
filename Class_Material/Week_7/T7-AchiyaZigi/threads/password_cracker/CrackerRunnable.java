package threads.password_cracker;

public class CrackerRunnable implements Runnable {
    int passwordSize;
    String startingString;
    String endingString;
    Safe safe;
    static boolean cracked = false;

    private static char getNextChar(char c) {
        if (c == '9') {
            return 'a';
        }
        return (char) (c + 1);
    }

    private static String getNextGuess(String guess) {
        String nextGuess = guess;
        int index = guess.length() - 1;
        while (index > 0 && nextGuess.charAt(index) == 'z') {
            index--;
        }
        if (index == 0 && nextGuess.charAt(index) == 'z') {
            return "0".repeat(guess.length() + 1);
        }
        nextGuess = nextGuess.substring(0, index) + getNextChar(nextGuess.charAt(index))
                + "0".repeat((guess.length() - index - 1));
        return nextGuess;
    }

    public CrackerRunnable(Safe safe, String startingString, String endingString) {
        this.startingString = startingString;
        this.endingString = endingString;
        this.safe = safe;
    }

    @Override
    public void run() {
        String guess = this.startingString;
        while (!cracked && !this.safe.open(guess) && !guess.equals(this.endingString)) {
            // System.out.println(guess);
            guess = getNextGuess(guess);
            // try {
            // Thread.sleep(50);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
        if (this.safe.open(guess)) {
            System.out.println("cracked! " + guess + " by: " + Thread.currentThread().getName());
            cracked = true;
        }
    }

}
