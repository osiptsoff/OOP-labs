package classes;

public interface TableFriendly {
	public void SetId(int _id);
	public int GetId();
	
	public void cascadeRemove();
	public boolean isRelated();
	public Object[] toRow();
}
