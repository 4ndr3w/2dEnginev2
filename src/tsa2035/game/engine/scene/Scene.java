package tsa2035.game.engine.scene;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import tsa2035.game.engine.scene.background.Background;
import tsa2035.game.engine.scene.background.SolidBackground;

public abstract class Scene {
	private HashMap<String, Sprite> objects = new HashMap<String, Sprite>();
	private Background bg = new SolidBackground(Color.WHITE);
	
	PlayerController playerController = null;
	
	public Scene()
	{
		
	}
	
	public void setBackground(Background bg)
	{
		this.bg = bg;
	}
	
	public Sprite addToScene(String name, Sprite sprite)
	{
		objects.put(name, sprite);
		return sprite;
	}
	
	public Sprite getObject(String name)
	{
		return objects.get(name);
	}
	
	public abstract void sceneLogic();

	public void render()
	{
		Keyboard.poll();
		
		bg.render();

		Iterator<Sprite> it = objects.values().iterator();
		while ( it.hasNext() )
		{
			GL11.glPushMatrix();
			Sprite obj = it.next();
			obj.render(this);
			GL11.glPopMatrix();
		}
		
		sceneLogic();
		
		if ( playerController != null )
			playerController.poll(this);
		
	}
	
	public Iterator<Sprite> iterator()
	{
		return objects.values().iterator();
	}
	
	public void setPlayer(PlayerController pc)
	{
		playerController = pc;
	}
}
