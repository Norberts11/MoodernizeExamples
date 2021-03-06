package application;

/* the host to connect to. valid only if
                                   bits.conn_to_host is set */
public class hostname {
	private Byte rawalloc;
	private Byte encalloc;
	private byte[] name;
	private Object dispname;
	
	public hostname(Byte rawalloc, Byte encalloc, byte[] name, Object dispname) {
		setRawalloc(rawalloc);
		setEncalloc(encalloc);
		setName(name);
		setDispname(dispname);
	}
	public hostname() {
	}
	
	public void strip_trailing_dot() {
		size_t len = new size_t();
		byte[] generatedName = this.getName();
		if (!host || !generatedName) {
			return /*Error: Unsupported expression*/;
		} 
		len = /*Error: Function owner not recognized*/strlen(generatedName);
		if (len && (generatedName[len - 1] == (byte)'.')) {
			generatedName[len - 1] = 0/*
			 * Perform any necessary IDN conversion of hostname
			 */;
		} 
	}
	public void free_idnconverted_hostname() {
		(Object)/* must be freed with free() since this was
		                           allocated by curl_win32_idn_to_ascii */host;
	}
	/* must be freed with idn2_free() since this was
	                                 allocated by libidn */
	public Byte getRawalloc() {
		return rawalloc;
	}
	public void setRawalloc(Byte newRawalloc) {
		rawalloc = newRawalloc;
	}
	public Byte getEncalloc() {
		return encalloc;
	}
	public void setEncalloc(Byte newEncalloc) {
		encalloc = newEncalloc;
	}
	public byte[] getName() {
		return name;
	}
	public void setName(byte[] newName) {
		name = newName;
	}
	public Object getDispname() {
		return dispname;
	}
	public void setDispname(Object newDispname) {
		dispname = newDispname;
	}
}
