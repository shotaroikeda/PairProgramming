//UIUC CS125 FALL 2014 MP. File: RainGame.java, CS125 Project: PairProgramming, Version: 2015-02-23T22:06:27-0600.298194661
/**
 * @author ikeda2, katan2
 */
public class RainGame {

	public static void main(String[] args) {
		// To get points type your netids above (CORRECTLY-Please double check your partner correctly spells your netid correctly!!)
		// Your netid is the unique part of your @illinois email address
		// Do not put your name or your UIN. 
		// REMEMBER TO COMMIT this file...
	
		int x=0, y=Zen.getZenHeight() / 2, dx=2, dy=0, score = 0;
		String text = "" + (int) (Math.random() * 999); //Initialize text so the score doesn't update without anything done
		long startTime =System.currentTimeMillis();
		int level = 0; //Initializes level
		int levelcounter = 0; //Keeps count of numbers completed.
		
		Zen.setFont("Helvetica-64");
		boolean playerAlive = Zen.isRunning();
		
		while (playerAlive) {
			
			if (level < 10)Zen.setColor(0, 0, 0); // Color begins at BLACK
			if (level >= 10 && level < 20)Zen.setColor(255, 0, 255); // Color at level 10 turns to PINK
			if (level >= 20 && level < 30)Zen.setColor(0, 250, 0); // Color at level 20 goes to CRAZY GREEN
			if (level >= 30)Zen.setColor((int) (Math.random() * 999), (int) (Math.random() * 999), (int) (Math.random() * 999)); // Color at level 30+ is RANDOM
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			Zen.setColor(0, 255, 0);
			Zen.drawText(text, x, y);
			
			Zen.drawText("Level: " + level,10,60); //Make sure level is updating
			Zen.drawText("Score: " + score,10,130); // Make sure score is updating
			
			x += dx;
			y += dy;
			
			// Find out what keys the user has been pressing.
			String user = Zen.getEditText();
			// Reset the keyboard input to an empty string
			// So next iteration we will only get the most recently pressed keys.
			Zen.setEditText("");
			
			for(int i=0;i < user.length();i++) {
				char c = user.charAt(i);
				if(c == text.charAt(0))
					text = text.substring(1,text.length()); // all except first character
			}
			
			if (text.length() == 0) {
				x = 0;
				y = Zen.getZenHeight() / 2;
				dx = 2 + level;		//make sure the text speeds up with level
				dy = 0;
				text = "" + (int) (Math.random() * 999);
				long elapsed = System.currentTimeMillis() - startTime;
				startTime = System.currentTimeMillis();
				score += 3000 / elapsed;
				levelcounter++; // Adds to counter
				if (levelcounter % 5 == 0) level++; //every 5 numbers the level goes up
			}
			
			//check if the text is offscreen, game over screen
			if (x > Zen.getZenWidth() || y > Zen.getZenHeight()) {
				Zen.setColor(255, 255, 255);
				Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
				
				Zen.setColor(255, 0, 0);
				Zen.drawText("GAME OVER", Zen.getZenWidth() / 5, Zen.getZenHeight() / 3);
				Zen.setFont("Helvetica-32");
				Zen.drawText("Press ENTER to quit...", Zen.getZenWidth() / 5, 2 * Zen.getZenHeight() / 3);
				playerAlive = false;
			}

			Zen.flipBuffer(); // Fixes the flicker
			Zen.sleep(90);// sleep for 90 milliseconds
		}
		while (Zen.isRunning()) {
			if (Zen.isKeyPressed('\n')) {
				System.exit(0);
			}
		}
		
	}

}
