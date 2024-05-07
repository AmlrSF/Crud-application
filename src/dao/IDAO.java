package dao;
import java.util.Collection;
public interface IDAO <T> {
	 public abstract T findByID (Object id);
	    public abstract  Collection <T> findAll();
	    public abstract boolean update (T o);
	    public abstract boolean delete (T o);
	    public abstract boolean insert (T o);

}

