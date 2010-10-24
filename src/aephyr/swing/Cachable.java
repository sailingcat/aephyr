package aephyr.swing;

import java.awt.event.KeyListener;

public interface Cachable {
	
	public int getLoadingDelay();

	public int getFirstVisibleIndex();
	
	public int getLastVisibleIndex();
	
	public CachingModel getCachingModel();
}