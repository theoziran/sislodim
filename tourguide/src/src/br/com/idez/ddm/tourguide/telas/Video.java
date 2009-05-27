package br.com.idez.ddm.tourguide.telas;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

public class Video extends Canvas {

	private static Video instance;

	public static Video getInstance() {
		if (instance == null) {
			instance = new Video();
		}
		return instance;
	}

	protected void paint(Graphics arg0) {
		InputStream is = getClass().getResourceAsStream("/video.mpg");
		Player player = null;
		try {
			player = Manager.createPlayer(is, "video/mpeg");
			player.realize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VideoControl vc = (VideoControl) player.getControl("VideoControl");
		if (vc != null) {
			vc.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, this);
			vc.setVisible(true);
			try {
				player.start();
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
