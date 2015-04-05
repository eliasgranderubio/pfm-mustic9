package es.uem.cracking.master.jpa;

/**
 * 
 * Attack Windows Id entity
 * 
 * @author egrande
 *
 */
public class Attack_Windows_Id {
	
	// Private attributes
	private long activeAttackId;
	private long id;
	
	
	// Override methods
	
	/** 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Attack_Windows_Id)) {
			return false;
		}
		Attack_Windows_Id obj_tmp =  (Attack_Windows_Id) obj;
		return ((this.activeAttackId == obj_tmp.getActiveAttackId()) && (this.id == obj_tmp.getId()));
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}


	// Getters and setters

	/**
	 * Gets {@link #activeAttackId}
	 */
	public long getActiveAttackId() {
		return activeAttackId;
	}

	/**
	 * Sets {@link #activeAttackId}
	 */
	public void setActiveAttackId(long activeAttackId) {
		this.activeAttackId = activeAttackId;
	}
	
	/**
	 * Gets {@link #id}
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets {@link #id}
	 */
	public void setId(long id) {
		this.id = id;
	}

}
