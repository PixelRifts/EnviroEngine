package com.pixelrifts.enviro.engine.batch;

import java.util.ArrayList;
import java.util.List;

import com.pixelrifts.enviro.engine.interfaces.IRenderable;
import com.pixelrifts.enviro.engine.interfaces.IProcessor;

public class Batch<T extends IRenderable> {
	private List<T> batch;
	
	public Batch() {
		batch = new ArrayList<>();
	}
	
	public void add(T t) {
		batch.add(t);
	}
	
	public boolean remove(int index) {
		if (index > batch.size() - 1)
			return false;
		batch.remove(index);
		
		if (index < batch.size() - 1)
		{
			List<T> tempBatch = new ArrayList<>();
			tempBatch.addAll(batch);
			for (int i = 0; i <= index; i++) tempBatch.remove(i);
			batch.removeAll(tempBatch);
		}
		return true;
	}
	
	public void clear() {
		batch.clear();
	}
	
	public List<T> getBatch() {
		return batch;
	}
	
	public void foreach(IProcessor<T> processor) {
		for (T t : batch) {
			processor.process(t);
		}
	}
	
	public boolean process(IProcessor<T> processor, int index) {
		if (index > batch.size() - 1 || index < 0) return false;
		processor.process(batch.get(index));
		remove(index);
		return true;
	}
}
