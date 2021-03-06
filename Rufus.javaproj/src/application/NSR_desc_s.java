package application;

/** NSR Descriptor (ECMA 167r3 3/9.1) */
public class NSR_desc_s {
	private Object struct_type;
	private Object[] std_id;
	private Object struct_version;
	private Object reserved;
	private Object[] struct_data;
	
	public NSR_desc_s(Object struct_type, Object[] std_id, Object struct_version, Object reserved, Object[] struct_data) {
		setStruct_type(struct_type);
		setStd_id(std_id);
		setStruct_version(struct_version);
		setReserved(reserved);
		setStruct_data(struct_data);
	}
	public NSR_desc_s() {
	}
	
	public Object getStruct_type() {
		return struct_type;
	}
	public void setStruct_type(Object newStruct_type) {
		struct_type = newStruct_type;
	}
	public Object[] getStd_id() {
		return std_id;
	}
	public void setStd_id(Object[] newStd_id) {
		std_id = newStd_id;
	}
	public Object getStruct_version() {
		return struct_version;
	}
	public void setStruct_version(Object newStruct_version) {
		struct_version = newStruct_version;
	}
	public Object getReserved() {
		return reserved;
	}
	public void setReserved(Object newReserved) {
		reserved = newReserved;
	}
	public Object[] getStruct_data() {
		return struct_data;
	}
	public void setStruct_data(Object[] newStruct_data) {
		struct_data = newStruct_data;
	}
}
