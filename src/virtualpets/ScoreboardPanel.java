package virtualpets;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 * Panel to display the results of the game.
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class ScoreboardPanel extends JPanel {
    /**
     * Label to show the ranking of the first player.
     */
    private JLabel lblFirst;
    /**
     * Label to show the ranking of the second player.
     */
    private JLabel lblSecond;
    /**
     * Label to show the ranking of the third player.
     */
    private JLabel lblThird;
    /**
     * Label to show the player in first place.
     */
    private JLabel lblFirstPlace;
    /**
     * Label to show the player in second place.
     */
    private JLabel lblSecondPlace;
    /**
     * Label to show the player in third place.
     */
    private JLabel lblThirdPlace;

    /**
     * Create the panel.
     */
    public ScoreboardPanel() {
        setLayout(null);

        JLabel lblHeader = new JLabel("The game is over and the scores are in...");
        lblHeader.setBounds(10, 11, 430, 14);
        add(lblHeader);

        lblFirst = new JLabel("1");
        lblFirst.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFirst.setBounds(10, 60, 83, 14);
        add(lblFirst);

        lblSecond = new JLabel("2");
        lblSecond.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSecond.setBounds(10, 110, 83, 14);
        add(lblSecond);

        lblThird = new JLabel("3");
        lblThird.setHorizontalAlignment(SwingConstants.RIGHT);
        lblThird.setBounds(10, 153, 83, 14);
        add(lblThird);

        lblFirstPlace = new JLabel("firstPlace");
        lblFirstPlace.setBounds(103, 60, 337, 14);
        add(lblFirstPlace);

        lblSecondPlace = new JLabel("secondPlace");
        lblSecondPlace.setBounds(103, 110, 337, 14);
        add(lblSecondPlace);

        lblThirdPlace = new JLabel("thirdPlace");
        lblThirdPlace.setBounds(103, 153, 337, 14);
        add(lblThirdPlace);
    }

    /**
     * Display the ranking of players.
     * @param playerList Array of players in decending order of score.
     */
    public void showRanking(Player[] playerList) {
        String text;
        switch (playerList.length) {
            case (1):
                lblFirst.setText("");
                text = playerList[0].getName() + ": " + playerList[0].getScore();
                lblFirstPlace.setText(text);
                lblSecond.setText("");
                lblSecondPlace.setText("");
                lblThird.setText("");
                lblThirdPlace.setText("");
                break;

            case (2):
                if (playerList[0].getScore() == playerList[1].getScore()) {
                    //If a tie
                    lblFirst.setText("First equal.");
                    text = playerList[0].getName() + ": "
                           + playerList[0].getScore();
                    lblFirstPlace.setText(text);
                    lblSecond.setText("First equal.");
                    text = playerList[1].getName() + ": "
                           + playerList[1].getScore();
                    lblSecondPlace.setText(text);
                    lblThird.setText("");
                    lblThirdPlace.setText("");
                } else {
                    //otherwise
                    lblFirst.setText("First.");
                    text = playerList[0].getName() + ": "
                           + playerList[0].getScore();
                    lblFirstPlace.setText(text);
                    lblSecond.setText("Second.");
                    text = playerList[1].getName() + ": "
                           + playerList[1].getScore();
                    lblSecondPlace.setText(text);
                    lblThird.setText("");
                    lblThirdPlace.setText("");
                }
                break;

            case (3):
                Player player1 = playerList[0];
                Player player2 = playerList[1];
                Player player3 = playerList[2];

                Player winner = null;
                Player runnerUp = null;
                Player loser = null;
                boolean highTie = false;
                boolean lowTie = false;
                boolean threeWayTie = false;

                if (player1.getScore() == player2.getScore()) {
                    winner = player1;
                    runnerUp = player2;
                    loser = player3;
                    if (player2.getScore() == player3.getScore()) {
                        threeWayTie = true;
                    } else {
                        highTie = true;
                    }
                } else if (player2.getScore() == player3.getScore()) {
                    winner = player1;
                    runnerUp = player3;
                    loser = player2;
                    lowTie = true;
                } else {
                    winner = playerList[0];
                    runnerUp = playerList[1];
                    loser = playerList[2];
                }

                if (threeWayTie) {
                    lblFirst.setText("First equal.");
                    text = winner.getName() + ": " + winner.getScore();
                    lblFirstPlace.setText(text);
                    lblSecond.setText("First equal.");
                    text = runnerUp.getName() + ": " + runnerUp.getScore();
                    lblSecondPlace.setText(text);
                    lblThird.setText("First equal.");
                    text = loser.getName() + ": " + loser.getScore();
                    lblThirdPlace.setText(text);

                } else if (lowTie) {
                    //if players 2 and 3 tie
                    lblFirst.setText("First.");
                    text = winner.getName() + ": " + winner.getScore();
                    lblFirstPlace.setText(text);
                    lblSecond.setText("Second equal.");
                    text = runnerUp.getName() + ": " + runnerUp.getScore();
                    lblSecondPlace.setText(text);
                    lblThird.setText("Second equal.");
                    text = loser.getName() + ": " + loser.getScore();
                    lblThirdPlace.setText(text);

                } else if (highTie) {
                    //if players 1 and 2 tie
                    lblFirst.setText("First equal.");
                    text = winner.getName() + ": " + winner.getScore();
                    lblFirstPlace.setText(text);
                    lblSecond.setText("First equal.");
                    text = runnerUp.getName() + ": " + runnerUp.getScore();
                    lblSecondPlace.setText(text);
                    lblThird.setText("Third.");
                    text = loser.getName() + ": " + loser.getScore();
                    lblThirdPlace.setText(text);

                } else {
                    //no ties
                    lblFirst.setText("First.");
                    text = winner.getName() + ": " + winner.getScore();
                    lblFirstPlace.setText(text);
                    lblSecond.setText("Second.");
                    text = runnerUp.getName() + ": " + runnerUp.getScore();
                    lblSecondPlace.setText(text);
                    lblThird.setText("Third.");
                    text = loser.getName() + ": " + loser.getScore();
                    lblThirdPlace.setText(text);
                }
                break;
        }
    }

    /**
     * Testing functionality.
     * @param args Input arguments.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
          //ignore all exceptions
        }
        JFrame myFrame = new JFrame();

        myFrame.setBounds(0, 0, 465, 225);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        Player[] playerList = new Player[3];

        playerList[0] = new Player();
        playerList[0].setName("A");
        playerList[0].getPetList().add(new Pet("cat"));
        playerList[0].calculateScore();

        playerList[1] = new Player();
        playerList[1].setName("B");
        playerList[1].getPetList().add(new Pet("cat"));
        playerList[1].getPetList().get(0).die();
        playerList[1].calculateScore();

        playerList[2] = new Player();
        playerList[2].setName("C");
        playerList[2].getPetList().add(new Pet("cat"));
        playerList[2].getPetList().get(0).die();
        playerList[2].calculateScore();


        ScoreboardPanel myPanel = new ScoreboardPanel();

        myPanel.showRanking(playerList);

        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(450, 200);

        myFrame.setVisible(true);
    }
}
