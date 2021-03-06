package application;

/* SMB request data */
public class smb_request {
	private smb_req_state state;
	private Byte path;
	private int tid;
	private int fid;
	private Object result;
	
	public smb_request(smb_req_state state, Byte path, int tid, int fid, Object result) {
		setState(state);
		setPath(path);
		setTid(tid);
		setFid(fid);
		setResult(result);
	}
	public smb_request() {
	}
	
	public smb_req_state getState() {
		return state;
	}
	public void setState(smb_req_state newState) {
		state = newState;
	}
	public Byte getPath() {
		return path;
	}
	public void setPath(Byte newPath) {
		path = newPath;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int newTid) {
		tid = newTid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int newFid) {
		fid = newFid;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object newResult) {
		result = newResult;
	}
}
