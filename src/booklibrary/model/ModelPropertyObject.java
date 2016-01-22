package booklibrary.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelPropertyObject {
	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changeSupport == null) ? 0 : changeSupport.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelPropertyObject other = (ModelPropertyObject) obj;
		if (changeSupport == null) {
			if (other.changeSupport != null)
				return false;
		} else if (!changeSupport.equals(other.changeSupport))
			return false;
		return true;
	}
}
