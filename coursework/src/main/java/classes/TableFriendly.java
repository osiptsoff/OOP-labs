package classes;

public interface TableFriendly {
	public void SetId(int _id);
	public int GetId();
	
	public void remove();
	public Object[] toRow();
}
