package application;

/* Volume info relevant to file cluster & sector info */
public class S_NTFSSECT_VOLINFO_ {
	private Object Size;
	private Object Handle;
	private Object BytesPerSector;
	private Object SectorsPerCluster;
	private Object PartitionLba;
	
	public S_NTFSSECT_VOLINFO_(Object Size, Object Handle, Object BytesPerSector, Object SectorsPerCluster, Object PartitionLba) {
		setSize(Size);
		setHandle(Handle);
		setBytesPerSector(BytesPerSector);
		setSectorsPerCluster(SectorsPerCluster);
		setPartitionLba(PartitionLba);
	}
	public S_NTFSSECT_VOLINFO_() {
	}
	
	/* -------------------------------------------------------------------------- *
	 *
	 *   Copyright 2011 Shao Miller - All Rights Reserved
	 *
	 *   This program is free software; you can redistribute it and/or modify
	 *   it under the terms of the GNU General Public License as published by
	 *   the Free Software Foundation, Inc., 53 Temple Place Ste 330,
	 *   Boston MA 02111-1307, USA; either version 2 of the License, or
	 *   (at your option) any later version; incorporated herein by reference.
	 *
	 * ------------------------------------------------------------------------- */
	/****
	 * ntfssect.c
	 *
	 * Fetch NTFS file cluster & sector information via Windows
	 *
	 * With special thanks to Mark Roddy for his article:
	 *   http://www.wd-3.com/archive/luserland.htm
	 */
	/*** Macros */
	/*** Function declarations */
	/* Internal use only */
	public Object NtfsSectGetVolumeHandle(Object VolumeName) {
		CHAR[] volname = "\\\\.\\";
		CHAR volname_short = volname + /*Error: sizeof expression not supported yet*/ - 1;
		CHAR[] c = new CHAR();
		DWORD rc = new DWORD();
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/strcpy(volname + /*Error: sizeof expression not supported yet*/ - 1, /* Prefix "\\.\" onto the passed volume name */VolumeName);
		for (c = volname_short; c; ++/* Find the last non-null character */c) {
			;
		}
		if (c[-1] == /* Remove trailing back-slash */(byte)'\\') {
			c[-1] = 0;
		} 
		this.setHandle(/*Error: Function owner not recognized*/CreateFileA(volname, -1024, -1024 | -1024, (null), 3, 0, (null)));
		rc = /*Error: Function owner not recognized*/GetLastError();
		Object generatedHandle = this.getHandle();
		if (generatedHandle == (HANDLE)(true)) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("Unable to open volume handle!"));
			;
		} 
		return -1024;
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/CloseHandle(generatedHandle);
	}
	public Object NtfsSectGetVolumeInfo(Object VolumeName) {
		S_NTFSSECT_XPFUNCS xp_funcs = new S_NTFSSECT_XPFUNCS();
		DWORD rc = new DWORD();
		DWORD free_clusts = new DWORD();
		DWORD total_clusts = new DWORD();
		BOOL ok = new BOOL();
		if (!VolumeName || !VolumeInfo) {
			return -1024;
		} 
		Object generatedHandle = this.getHandle();
		if ((generatedHandle == (null)) || (generatedHandle == (HANDLE)(/* Only create a handle if it's not already been set */true))) {
			rc = VolumeInfo.NtfsSectGetVolumeHandle(VolumeName);
			if (rc != -1024) {
				;
			} 
			if ((generatedHandle == (null)) || (generatedHandle == (HANDLE)(true))) {
				return -1024;
			} 
		} 
		rc = xp_funcs.NtfsSectLoadXpFuncs();
		if (rc != -1024) {
			;
		} 
		Object generatedSectorsPerCluster = this.getSectorsPerCluster();
		Object generatedBytesPerSector = this.getBytesPerSector();
		ok = /*Error: Function owner not recognized*/ERROR_UNRECOGNIZED_FUNCTIONNAME(VolumeName, generatedSectorsPerCluster, generatedBytesPerSector, free_clusts, total_clusts);
		rc = /*Error: Function owner not recognized*/GetLastError();
		if (!ok) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("GetDiskFreeSpace() failed!"));
			;
		} 
		rc = VolumeInfo.NtfsSectGetVolumePartitionLba();
		if (rc != -1024) {
			;
		} 
		this.setSize(/*Error: sizeof expression not supported yet*/);
		rc = -1024;
	}
	public Object NtfsSectGetVolumeInfoFromFileName(Object FileName) {
		S_NTFSSECT_XPFUNCS xp_funcs = new S_NTFSSECT_XPFUNCS();
		DWORD rc = new DWORD();
		CHAR[] volname = new CHAR();
		BOOL ok = new BOOL();
		if (!FileName || !VolumeInfo) {
			return -1024;
		} 
		rc = xp_funcs.NtfsSectLoadXpFuncs();
		if (rc != -1024) {
			;
		} 
		ok = /*Error: Function owner not recognized*/ERROR_UNRECOGNIZED_FUNCTIONNAME(FileName, volname, /*Error: sizeof expression not supported yet*/);
		rc = /*Error: Function owner not recognized*/GetLastError();
		if (!ok) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("GetVolumePathName() failed!"));
			;
		} 
		rc = VolumeInfo.NtfsSectGetVolumeInfo(volname);
	}
	/* Internal use only */
	public Object NtfsSectGetVolumePartitionLba() {
		BOOL ok = new BOOL();
		VOLUME_DISK_EXTENTS vol_disk_extents = new VOLUME_DISK_EXTENTS();
		DWORD output_size = new DWORD();
		DWORD rc = new DWORD();
		Object generatedHandle = this.getHandle();
		ok = /*Error: Function owner not recognized*/DeviceIoControl(generatedHandle, (((((ULONG)(byte)'V')) << 16) | ((true) << 14) | ((false) << 2) | (false)), (null), 0, vol_disk_extents, /*Error: sizeof expression not supported yet*/, output_size, (null));
		rc = /*Error: Function owner not recognized*/GetLastError();
		if (!ok) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("Couldn't fetch volume disk extent(s)!"));
			;
		} 
		Object generatedNumberOfDiskExtents = vol_disk_extents.getNumberOfDiskExtents();
		if (generatedNumberOfDiskExtents != 1) {
			(ModernizedCProgram.NtfsSectLastErrorMessage = ("Unsupported number of volume disk extents!"));
			;
		} 
		Object generatedExtents = vol_disk_extents.getExtents();
		Object generatedBytesPerSector = this.getBytesPerSector();
		Object generatedPartitionLba = this.getPartitionLba();
		generatedPartitionLba.setQuadPart((generatedExtents[0].getStartingOffset().getQuadPart() / generatedBytesPerSector));
		return -1024;
	}
	/*** Objects */
	public Object getSize() {
		return Size;
	}
	public void setSize(Object newSize) {
		Size = newSize;
	}
	public Object getHandle() {
		return Handle;
	}
	public void setHandle(Object newHandle) {
		Handle = newHandle;
	}
	public Object getBytesPerSector() {
		return BytesPerSector;
	}
	public void setBytesPerSector(Object newBytesPerSector) {
		BytesPerSector = newBytesPerSector;
	}
	public Object getSectorsPerCluster() {
		return SectorsPerCluster;
	}
	public void setSectorsPerCluster(Object newSectorsPerCluster) {
		SectorsPerCluster = newSectorsPerCluster;
	}
	public Object getPartitionLba() {
		return PartitionLba;
	}
	public void setPartitionLba(Object newPartitionLba) {
		PartitionLba = newPartitionLba;
	}
}
