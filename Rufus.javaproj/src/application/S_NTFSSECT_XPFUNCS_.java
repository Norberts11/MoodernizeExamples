package application;

/* Stores function pointers to some Windows functions */
public class S_NTFSSECT_XPFUNCS_ {
	private Object Size;
	private Object Kernel32;
	private Object GetVolumePathName;
	private Object GetDiskFreeSpace;
	
	public S_NTFSSECT_XPFUNCS_(Object Size, Object Kernel32, Object GetVolumePathName, Object GetDiskFreeSpace) {
		setSize(Size);
		setKernel32(Kernel32);
		setGetVolumePathName(GetVolumePathName);
		setGetDiskFreeSpace(GetDiskFreeSpace);
	}
	public S_NTFSSECT_XPFUNCS_() {
	}
	
	public Object NtfsSectLoadXpFuncs() {
		DWORD rc = new DWORD();
		if (!XpFuncs) {
			return -1024;
		} 
		this.setSize(/*Error: sizeof expression not supported yet*/);
		this.setKernel32(/*Error: Function owner not recognized*/LoadLibraryA("kernel32.dll"));
		rc = /*Error: Function owner not recognized*/GetLastError();
		Object generatedKernel32 = this.getKernel32();
		if (!generatedKernel32) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("KERNEL32.DLL not found!"));
			;
		} 
		this.setGetVolumePathNameA((F_KERNEL32_GETVOLUMEPATHNAME)(/*Error: Function owner not recognized*/GetProcAddress(generatedKernel32, "GetVolumePathNameA")));
		rc = /*Error: Function owner not recognized*/GetLastError();
		Object generatedGetVolumePathNameA = this.getGetVolumePathNameA();
		if (!generatedGetVolumePathNameA) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("GetVolumePathName() not found in KERNEL32.DLL!"));
			;
		} 
		this.setGetDiskFreeSpaceA((F_KERNEL32_GETDISKFREESPACE)(/*Error: Function owner not recognized*/GetProcAddress(generatedKernel32, "GetDiskFreeSpaceA")));
		rc = /*Error: Function owner not recognized*/GetLastError();
		Object generatedGetDiskFreeSpaceA = this.getGetDiskFreeSpaceA();
		if (!generatedGetDiskFreeSpaceA) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("GetDiskFreeSpace() not found in KERNEL32.DLL!"));
			;
		} 
		return -1024;
		return rc;
	}
	public void NtfsSectUnloadXpFuncs() {
		if (!XpFuncs) {
			return /*Error: Unsupported expression*/;
		} 
		this.setGetDiskFreeSpaceA((null));
		this.setGetVolumePathNameA((null));
		Object generatedKernel32 = this.getKernel32();
		if (generatedKernel32) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/FreeLibrary(generatedKernel32);
		} 
		this.setKernel32((null));
		this.setSize(0);
		return /*Error: Unsupported expression*/;
	}
	public Object getSize() {
		return Size;
	}
	public void setSize(Object newSize) {
		Size = newSize;
	}
	public Object getKernel32() {
		return Kernel32;
	}
	public void setKernel32(Object newKernel32) {
		Kernel32 = newKernel32;
	}
	public Object getGetVolumePathName() {
		return GetVolumePathName;
	}
	public void setGetVolumePathName(Object newGetVolumePathName) {
		GetVolumePathName = newGetVolumePathName;
	}
	public Object getGetDiskFreeSpace() {
		return GetDiskFreeSpace;
	}
	public void setGetDiskFreeSpace(Object newGetDiskFreeSpace) {
		GetDiskFreeSpace = newGetDiskFreeSpace;
	}
}
/*** Function types */
/* The function type for Kernel32.dll's GetDiskFreeSpace() */
/* The function type for Kernel32.dll's GetVolumePathName() */
