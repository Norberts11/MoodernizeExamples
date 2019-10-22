package application;

public class dict {
	private dictType[] type;
	private Object[] privdata;
	private Object[] ht;
	private int rehashidx;
	private int iterators;
	
	public dict(dictType[] type, Object[] privdata, Object[] ht, int rehashidx, int iterators) {
		setType(type);
		setPrivdata(privdata);
		setHt(ht);
		setRehashidx(rehashidx);
		setIterators(iterators);
	}
	public dict() {
	}
	
	public void redis_add(dict[] d, int times) {
		int i;
		sds key = new sds();
		sds val = new sds();
		byte[] buf = new byte[20];
		dict dict = new dict();
		for (i = 0; i < times; ++i) {
			.snprintf(buf, 20, "key%d", i);
			key = ModernizedCProgram.sdsnew(buf);
			.snprintf(buf, 20, "val%d", i);
			val = ModernizedCProgram.sdsnew(buf);
			((dict.dictAdd(d, key, val) == 0) ? (Object)0 : ._assert("dictAdd(d, key, val) == DICT_OK", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\CBufferedTree\\src\\performance.c", 55));
		}
	}
	public void redis_fetch(dict[] d, int times) {
		int i;
		int j;
		byte[] buf = new byte[20];
		sds key = new sds();
		sds val = new sds();
		.srand(1992);
		dict dict = new dict();
		for (i = 0; i < times; ++i) {
			j = .rand() % times;
			.snprintf(buf, 20, "key%d", j);
			key = ModernizedCProgram.sdsnew(buf);
			((dict.dictFetchValue(d, key)) ? (Object)0 : ._assert("dictFetchValue(d, key)", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\CBufferedTree\\src\\performance.c", 115));
			ModernizedCProgram.sdsfree(key);
		}
	}
	public void redis_del(dict[] d, int times) {
		int i;
		int j;
		byte[] buf = new byte[20];
		sds key = new sds();
		sds val = new sds();
		.srand(1992);
		dict dict = new dict();
		for (i = 0; i < times; ++i) {
			j = .rand() % times;
			.snprintf(buf, 20, "key%d", j);
			key = ModernizedCProgram.sdsnew(buf);
			dict.dictDelete(d, key);
			ModernizedCProgram.sdsfree(key);
		}
	}
	/* Create a new hash table */
	public dict[] dictCreate(dictType[] type, Object[] privDataPtr) {
		dict[] d = .malloc();
		ModernizedCProgram._dictInit(d, type, privDataPtr);
		return d;
	}
	/* Resize the table to the minimal size that contains all the elements,
	 * but with the invariant of a USED/BUCKETS ratio near to <= 1 */
	public int dictResize(dict[] d) {
		int minimal;
		int generatedRehashidx = (d).getRehashidx();
		if (!ModernizedCProgram.dict_can_resize || (generatedRehashidx != -1)) {
			return 1;
		} 
		Object[] generatedHt = d.getHt();
		minimal = generatedHt[0].getUsed();
		if (minimal < 4) {
			minimal = 4;
		} 
		dict dict = new dict();
		return dict.dictExpand(d, minimal);
	}
	/* Expand or create the hash table */
	public int dictExpand(dict[] d, long size) {
		/* the new hash table */dictht n = new dictht();
		long realsize = ModernizedCProgram._dictNextPower(size);
		int generatedRehashidx = (d).getRehashidx();
		Object[] generatedHt = d.getHt();
		if ((generatedRehashidx != -1) || generatedHt[0].getUsed() > /* the size is invalid if it is smaller than the number of
		     * elements already inside the hash table */size) {
			return 1;
		} 
		n.setSize(/* Allocate the new hash table and initialize all pointers to NULL */realsize);
		n.setSizemask(realsize - 1);
		n.setTable(.malloc(realsize * ));
		n.setUsed(0);
		if (generatedHt[0].getTable() == ((Object)/* Is this the first initialization? If so it's not really a rehashing
		     * we just set the first hash table so that it can accept keys. */0)) {
			generatedHt[0] = n;
			return 0;
		} 
		generatedHt[1] = /* Prepare a second hash table for incremental rehashing */n;
		d.setRehashidx(0);
		return 0/* Performs N steps of incremental rehashing. Returns 1 if there are still
		 * keys to move from the old to the new hash table, otherwise 0 is returned.
		 * Note that a rehashing step consists in moving a bucket (that may have more
		 * thank one key as we use chaining) from the old to the new hash table. */;
	}
	public int dictRehash(dict[] d, int n) {
		int generatedRehashidx = (d).getRehashidx();
		if (!(generatedRehashidx != -1)) {
			return 0;
		} 
		Object[] generatedHt = d.getHt();
		dictht dictht = new dictht();
		dictEntry[] generatedNext = de.getNext();
		Object[] generatedKey = de.getKey();
		while (n--) {
			dictEntry[] de = new dictEntry();
			dictEntry[] nextde = new dictEntry();
			if (generatedHt[0].getUsed() == /* Check if we already rehashed the whole table... */0) {
				.free(generatedHt[0].getTable());
				generatedHt[0] = generatedHt[1];
				dictht._dictReset(generatedHt[1]);
				d.setRehashidx(-1);
				return 0;
			} 
			((generatedHt[0].getSize() > (int)generatedRehashidx) ? (Object)0 : ._assert("d->ht[0].size > (unsigned)d->rehashidx", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\CBufferedTree\\src\\redis-dict.c", /* Note that rehashidx can't overflow as we are sure there are more
			         * elements because ht[0].used != 0 */257));
			while (generatedHt[0].getTable()[generatedRehashidx] == ((Object)0)) {
				generatedRehashidx++;
			}
			de = generatedHt[0].getTable()[generatedRehashidx];
			while (/* Move all the keys in this bucket from the old to the new hash HT */de) {
				int h;
				nextde = generatedNext;
				h = .UNRECOGNIZEDFUNCTIONNAME(generatedKey) & generatedHt[1].getSizemask();
				de.setNext(generatedHt[1].getTable()[h]);
				generatedHt[1].getTable()[h] = de;
				generatedHt[0].getUsed()--;
				generatedHt[1].getUsed()++;
				de = nextde;
			}
			generatedHt[0].getTable()[generatedRehashidx] = ((Object)0);
			generatedRehashidx++;
		}
		return 1;
	}
	/* Rehash for an amount of time between ms milliseconds and ms+1 milliseconds */
	public int dictRehashMilliseconds(dict[] d, int ms) {
		long start = ModernizedCProgram.timeInMilliseconds();
		int rehashes = 0;
		dict dict = new dict();
		while (dict.dictRehash(d, 100)) {
			rehashes += 100;
			if (ModernizedCProgram.timeInMilliseconds() - start > ms) {
				break;
			} 
		}
		return rehashes/* This function performs just a step of rehashing, and only if there are
		 * no safe iterators bound to our hash table. When we have iterators in the
		 * middle of a rehashing we can't mess with the two hash tables otherwise
		 * some element can be missed or duplicated.
		 *
		 * This function is called by common lookup or update operations in the
		 * dictionary so that the hash table automatically migrates from H1 to H2
		 * while it is actively used. */;
	}
	public void _dictRehashStep(dict[] d) {
		int generatedIterators = d.getIterators();
		dict dict = new dict();
		if (generatedIterators == 0) {
			dict.dictRehash(d, 1);
		} 
	}
	/* Add an element to the target hash table */
	public int dictAdd(dict[] d, Object[] key, Object[] val) {
		dictEntry dictEntry = new dictEntry();
		dictEntry[] entry = dictEntry.dictAddRaw(d, key);
		if (!entry) {
			return 1;
		} 
		dictType[] generatedType = (d).getType();
		Object[] generatedValDup = generatedType.getValDup();
		Object[] generatedPrivdata = (d).getPrivdata();
		 generatedV = entry.getV();
		do {
			if (generatedValDup) {
				generatedV.setVal(.UNRECOGNIZEDFUNCTIONNAME(generatedPrivdata, val));
			} else {
					generatedV.setVal((val));
			} 
		} while (0);
		return 0/* Low level add. This function adds the entry but instead of setting
		 * a value returns the dictEntry structure to the user, that will make
		 * sure to fill the value field as he wishes.
		 *
		 * This function is also directly exposed to the user API to be called
		 * mainly in order to store non-pointers inside the hash value, example:
		 *
		 * entry = dictAddRaw(dict,mykey);
		 * if (entry != NULL) dictSetSignedIntegerVal(entry,1000);
		 *
		 * Return values:
		 *
		 * If key already exists NULL is returned.
		 * If key was added, the hash entry is returned to be manipulated by the caller.
		 */;
	}
	public int dictReplace(dict[] d, Object[] key, Object[] val) {
		dictEntry entry = new dictEntry();
		dictEntry auxentry = new dictEntry();
		dict dict = new dict();
		if (dict.dictAdd(d, key, val) == /* Try to add the element. If the key
		     * does not exists dictAdd will suceed. */0) {
			return 1;
		} 
		dictEntry dictEntry = new dictEntry();
		entry = dictEntry.dictFind(d, /* It already exists, get the entry */key/* Set the new value and free the old one. Note that it is important
		     * to do that in this order, as the value may just be exactly the same
		     * as the previous one. In this context, think to reference counting,
		     * you want to increment (set), and then decrement (free), and not the
		     * reverse. */);
		auxentry = entry;
		dictType[] generatedType = (d).getType();
		Object[] generatedValDup = generatedType.getValDup();
		Object[] generatedPrivdata = (d).getPrivdata();
		 generatedV = entry.getV();
		do {
			if (generatedValDup) {
				generatedV.setVal(.UNRECOGNIZEDFUNCTIONNAME(generatedPrivdata, val));
			} else {
					generatedV.setVal((val));
			} 
		} while (0);
		Object[] generatedValDestructor = generatedType.getValDestructor();
		Object generatedVal = generatedV.getVal();
		if (generatedValDestructor) {
			.UNRECOGNIZEDFUNCTIONNAME(generatedPrivdata, generatedVal);
		} 
		return 0/* dictReplaceRaw() is simply a version of dictAddRaw() that always
		 * returns the hash entry of the specified key, even if the key already
		 * exists and can't be added (in that case the entry of the already
		 * existing key is returned.)
		 *
		 * See dictAddRaw() for more information. */;
	}
	/* Search and remove an element */
	public int dictGenericDelete(dict[] d, Object[] key, int nofree) {
		int h;
		int idx;
		dictEntry[] he = new dictEntry();
		dictEntry[] prevHe = new dictEntry();
		int table;
		Object[] generatedHt = d.getHt();
		if (generatedHt[0].getSize() == 0) {
			return /* d->ht[0].table is NULL */1;
		} 
		int generatedRehashidx = (d).getRehashidx();
		dict dict = new dict();
		if ((generatedRehashidx != -1)) {
			dict._dictRehashStep(d);
		} 
		h = .UNRECOGNIZEDFUNCTIONNAME(key);
		dictType[] generatedType = (d).getType();
		Object[] generatedKeyCompare = generatedType.getKeyCompare();
		Object[] generatedPrivdata = (d).getPrivdata();
		Object[] generatedKey = he.getKey();
		dictEntry[] generatedNext = he.getNext();
		Object[] generatedKeyDestructor = generatedType.getKeyDestructor();
		Object[] generatedValDestructor = generatedType.getValDestructor();
		 generatedV = (he).getV();
		Object generatedVal = generatedV.getVal();
		for (table = 0; table <= 1; table++) {
			idx = h & generatedHt[table].getSizemask();
			he = generatedHt[table].getTable()[idx];
			prevHe = ((Object)0);
			while (he) {
				if (((generatedKeyCompare) ? .UNRECOGNIZEDFUNCTIONNAME(generatedPrivdata, key, generatedKey) : (key) == (generatedKey))) {
					if (/* Unlink the element from the list */prevHe) {
						prevHe.setNext(generatedNext);
					} else {
							generatedHt[table].getTable()[idx] = generatedNext;
					} 
					if (!nofree) {
						if (generatedKeyDestructor) {
							.UNRECOGNIZEDFUNCTIONNAME(generatedPrivdata, generatedKey);
						} 
						if (generatedValDestructor) {
							.UNRECOGNIZEDFUNCTIONNAME(generatedPrivdata, generatedVal);
						} 
					} 
					.free(he);
					generatedHt[table].getUsed()--;
					return 0;
				} 
				prevHe = he;
				he = generatedNext;
			}
			if (!(generatedRehashidx != -1)) {
				break;
			} 
		}
		return /* not found */1;
	}
	public int dictDelete(dict[] ht, Object[] key) {
		dict dict = new dict();
		return dict.dictGenericDelete(ht, key, 0);
	}
	public int dictDeleteNoFree(dict[] ht, Object[] key) {
		dict dict = new dict();
		return dict.dictGenericDelete(ht, key, 1);
	}
	/* Clear & Release the hash table */
	public void dictRelease(dict[] d) {
		Object[] generatedHt = d.getHt();
		ModernizedCProgram._dictClear(d, generatedHt[0]);
		ModernizedCProgram._dictClear(d, generatedHt[1]);
		.free(d);
	}
	public Object[] dictFetchValue(dict[] d, Object[] key) {
		dictEntry[] he = new dictEntry();
		dictEntry dictEntry = new dictEntry();
		he = dictEntry.dictFind(d, key);
		 generatedV = (he).getV();
		Object generatedVal = generatedV.getVal();
		return he ? (generatedVal) : ((Object)0);
	}
	/* Expand the hash table if needed */
	public int _dictExpandIfNeeded(dict[] d) {
		int generatedRehashidx = (d).getRehashidx();
		if ((generatedRehashidx != -1)) {
			return /* Incremental rehashing already in progress. Return. */0;
		} 
		Object[] generatedHt = d.getHt();
		dict dict = new dict();
		if (generatedHt[0].getSize() == 0) {
			return dict.dictExpand(d, /* If the hash table is empty expand it to the initial size. */4/* If we reached the 1:1 ratio, and we are allowed to resize the hash
			     * table (global setting) or we should avoid it but the ratio between
			     * elements/buckets is over the "safe" threshold, we resize doubling
			     * the number of buckets. */);
		} 
		if (generatedHt[0].getUsed() >= generatedHt[0].getSize() && (ModernizedCProgram.dict_can_resize || generatedHt[0].getUsed() / generatedHt[0].getSize() > ModernizedCProgram.dict_force_resize_ratio)) {
			return dict.dictExpand(d, generatedHt[0].getUsed() * 2);
		} 
		return 0;
	}
	public int _dictKeyIndex(dict[] d, Object[] key) {
		int h;
		int idx;
		int table;
		dictEntry[] he = new dictEntry();
		dict dict = new dict();
		if (dict._dictExpandIfNeeded(d) == /* Expand the hash table if needed */1) {
			return -1;
		} 
		h = .UNRECOGNIZEDFUNCTIONNAME(/* Compute the key hash value */key);
		Object[] generatedHt = d.getHt();
		dictType[] generatedType = (d).getType();
		Object[] generatedKeyCompare = generatedType.getKeyCompare();
		Object[] generatedPrivdata = (d).getPrivdata();
		Object[] generatedKey = he.getKey();
		dictEntry[] generatedNext = he.getNext();
		int generatedRehashidx = (d).getRehashidx();
		for (table = 0; table <= 1; table++) {
			idx = h & generatedHt[table].getSizemask();
			he = generatedHt[table].getTable()[/* Search if this slot does not already contain the given key */idx];
			while (he) {
				if (((generatedKeyCompare) ? .UNRECOGNIZEDFUNCTIONNAME(generatedPrivdata, key, generatedKey) : (key) == (generatedKey))) {
					return -1;
				} 
				he = generatedNext;
			}
			if (!(generatedRehashidx != -1)) {
				break;
			} 
		}
		return idx;
	}
	public void dictEmpty(dict[] d) {
		Object[] generatedHt = d.getHt();
		ModernizedCProgram._dictClear(d, generatedHt[0]);
		ModernizedCProgram._dictClear(d, generatedHt[1]);
		d.setRehashidx(-1);
		d.setIterators(0);
	}
	public dictType[] getType() {
		return type;
	}
	public void setType(dictType[] newType) {
		type = newType;
	}
	public Object[] getPrivdata() {
		return privdata;
	}
	public void setPrivdata(Object[] newPrivdata) {
		privdata = newPrivdata;
	}
	public Object[] getHt() {
		return ht;
	}
	public void setHt(Object[] newHt) {
		ht = newHt;
	}
	public int getRehashidx() {
		return rehashidx;
	}
	public void setRehashidx(int newRehashidx) {
		rehashidx = newRehashidx;
	}
	public int getIterators() {
		return iterators;
	}
	public void setIterators(int newIterators) {
		iterators = newIterators;
	}
}
