package Model;

public enum MusicState {
	Death("Gameover.wav"), Play("BackgroundMusic.wav"), Win("Win.wav");

	private String filename;

	private MusicState(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return this.filename;
	}
}
