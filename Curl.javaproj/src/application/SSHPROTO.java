package application;

/* Last state in SFTP-DO */
/* Last state in SFTP-DONE */
/* First state in SFTP-DISCONNECT */
/* First state in SCP-DO */
/* Last state in SCP-DONE */
/* First state in SCP-DISCONNECT */
/* Last state in SCP/SFTP-DISCONNECT */
/* never used */
/* this struct is used in the HandleData struct which is part of the
   Curl_easy, which means this is used on a per-easy handle basis.
   Everything that is strictly related to a connection is banned from this
   struct. */
public class SSHPROTO {
	private Byte path;
	
	public SSHPROTO(Byte path) {
		setPath(path);
	}
	public SSHPROTO() {
	}
	
	public Byte getPath() {
		return path;
	}
	public void setPath(Byte newPath) {
		path = newPath;
	}
}
