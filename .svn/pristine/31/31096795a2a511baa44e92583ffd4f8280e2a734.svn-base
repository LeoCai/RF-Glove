package dislab.rfidaction.reference;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class piano {
	InputStream in_1;
	AudioStream as_1;
	InputStream in_2;
	AudioStream as_2;
	InputStream in_3;
	AudioStream as_3;
	InputStream in_4;
	AudioStream as_4;
	InputStream in_5;
	AudioStream as_5;
	InputStream in_6;
	AudioStream as_6;
	InputStream in_7;
	AudioStream as_7;

	public piano() throws IOException {
		in_1 = new FileInputStream("bin/sound/1.au");
		as_1 = new AudioStream(in_1);

		in_2 = new FileInputStream("bin/sound/2.au");
		as_2 = new AudioStream(in_2);

		in_3 = new FileInputStream("bin/sound/3.au");
		as_3 = new AudioStream(in_3);

		in_4 = new FileInputStream("bin/sound/4.au");
		as_4 = new AudioStream(in_4);

		in_5 = new FileInputStream("bin/sound/5.au");
		as_5 = new AudioStream(in_5);

		in_6 = new FileInputStream("bin/sound/6.au");
		as_6 = new AudioStream(in_6);

		in_7 = new FileInputStream("bin/sound/7.au");
		as_7 = new AudioStream(in_7);
	}

	public void Play_1() throws IOException{
		piano pi=new piano();
		pi.play_1();
	}
	
	public void Play_2() throws IOException{
		piano pi=new piano();
		pi.play_2();
	}
	
	public void Play_3() throws IOException{
		piano pi=new piano();
		pi.play_3();
	}
	
	public void Play_4() throws IOException{
		piano pi=new piano();
		pi.play_4();
	}
	
	public void Play_5() throws IOException{
		piano pi=new piano();
		pi.play_5();
	}
	
	public void Play_6() throws IOException{
		piano pi=new piano();
		pi.play_6();
	}
	
	public void Play_7() throws IOException{
		piano pi=new piano();
		pi.play_7();
	}
	
	public void play_1() throws IOException {
		AudioPlayer.player.start(as_1);
	}

	public void play_2() throws IOException {
		AudioPlayer.player.start(as_2);
	}

	public void play_3() throws IOException {
		AudioPlayer.player.start(as_3);
	}
	
	public void play_4() throws IOException {
		AudioPlayer.player.start(as_4);
	}

	public void play_5() throws IOException {
		AudioPlayer.player.start(as_5);
	}

	public void play_6() throws IOException {
		AudioPlayer.player.start(as_6);
	}
	
	public void play_7() throws IOException {
		AudioPlayer.player.start(as_7);
	}
	
	public void zero(){
		AudioPlayer.player.stop(as_1);
		AudioPlayer.player.stop(as_2);
		AudioPlayer.player.stop(as_3);
		AudioPlayer.player.stop(as_4);
		AudioPlayer.player.stop(as_5);
		AudioPlayer.player.stop(as_6);
		AudioPlayer.player.stop(as_7);
	}
	
}
