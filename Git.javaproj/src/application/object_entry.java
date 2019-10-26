package application;

public class object_entry {
	private pack_idx_entry idx;
	private Object delta_data;
	private Object in_pack_offset;
	private Object hash;
	private int size_;
	private int size_valid;
	private Object delta_idx;
	private Object delta_child_idx;
	private Object delta_sibling_idx;
	private int delta_size_;
	private int delta_size_valid;
	private byte in_pack_header_size;
	private int in_pack_idx;
	private int z_delta_size;
	private int type_valid;
	private int no_try_delta;
	private int type_;
	private int in_pack_type;
	private int preferred_base;
	private int tagged;
	private int filled;
	private int dfs_state;
	private int depth;
	private int ext_base;
	
	public object_entry(pack_idx_entry idx, Object delta_data, Object in_pack_offset, Object hash, int size_, int size_valid, Object delta_idx, Object delta_child_idx, Object delta_sibling_idx, int delta_size_, int delta_size_valid, byte in_pack_header_size, int in_pack_idx, int z_delta_size, int type_valid, int no_try_delta, int type_, int in_pack_type, int preferred_base, int tagged, int filled, int dfs_state, int depth, int ext_base) {
		setIdx(idx);
		setDelta_data(delta_data);
		setIn_pack_offset(in_pack_offset);
		setHash(hash);
		setSize_(size_);
		setSize_valid(size_valid);
		setDelta_idx(delta_idx);
		setDelta_child_idx(delta_child_idx);
		setDelta_sibling_idx(delta_sibling_idx);
		setDelta_size_(delta_size_);
		setDelta_size_valid(delta_size_valid);
		setIn_pack_header_size(in_pack_header_size);
		setIn_pack_idx(in_pack_idx);
		setZ_delta_size(z_delta_size);
		setType_valid(type_valid);
		setNo_try_delta(no_try_delta);
		setType_(type_);
		setIn_pack_type(in_pack_type);
		setPreferred_base(preferred_base);
		setTagged(tagged);
		setFilled(filled);
		setDfs_state(dfs_state);
		setDepth(depth);
		setExt_base(ext_base);
	}
	public object_entry() {
	}
	
	public void oe_set_type(object_type type) {
		if (object_type.type >= object_type.OBJ_ANY) {
			ModernizedCProgram.BUG_fl("E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Git\\src\\pack-objects.h", 220, "OBJ_ANY cannot be set in pack-objects code");
		} 
		this.setType_valid(object_type.type >= object_type.OBJ_NONE);
		this.setType_((int)object_type.type);
	}
	public object_entry oe_delta(Object pack, Object e) {
		if (!e.getDelta_idx()) {
			return ((Object)0);
		} 
		if (e.getExt_base()) {
			return pack.getExt_bases()[e.getDelta_idx() - 1];
		} else {
				return pack.getObjects()[e.getDelta_idx() - 1];
		} 
	}
	public object_entry oe_delta_child(Object pack, Object e) {
		if (e.getDelta_child_idx()) {
			return pack.getObjects()[e.getDelta_child_idx() - 1];
		} 
		return ((Object)0);
	}
	public object_entry oe_delta_sibling(Object pack, Object e) {
		if (e.getDelta_sibling_idx()) {
			return pack.getObjects()[e.getDelta_sibling_idx() - 1];
		} 
		return ((Object)0);
	}
	public void add_to_write_order(int endp, object_entry e) {
		int generatedFilled = e.getFilled();
		if (generatedFilled || ModernizedCProgram.oe_layer(ModernizedCProgram.to_pack, e) != ModernizedCProgram.write_layer) {
			return ;
		} 
		wo[(endp)++] = e;
		e.setFilled(1);
	}
	public void add_descendants_to_write_order(Integer endp, object_entry e) {
		int add_to_order = 1;
		object_entry object_entry = new object_entry();
		object_entry object_entry = new object_entry();
		object_entry object_entry = new object_entry();
		while (e) {
			if (add_to_order) {
				object_entry s = new object_entry();
				wo.add_to_write_order(endp, /* add this node... */e);
				for (s = object_entry.oe_delta_sibling(ModernizedCProgram.to_pack, e); s; s = object_entry.oe_delta_sibling(ModernizedCProgram.to_pack, /* all its siblings... */s)) {
					wo.add_to_write_order(endp, s);
				}
			} 
			if (object_entry.oe_delta_child(ModernizedCProgram.to_pack, /* drop down a level to add left subtree nodes if possible */e)) {
				add_to_order = 1;
				e = object_entry.oe_delta_child(ModernizedCProgram.to_pack, e);
			} else {
					add_to_order = 0;
					if (object_entry.oe_delta_sibling(ModernizedCProgram.to_pack, /* our sibling might have some children, it is next */e)) {
						e = object_entry.oe_delta_sibling(ModernizedCProgram.to_pack, e);
						continue;
					} 
					e = object_entry.oe_delta(ModernizedCProgram.to_pack, /* go back to our parent node */e);
					while (e && !object_entry.oe_delta_sibling(ModernizedCProgram.to_pack, e/* we're on the right side of a subtree, keep
									 * going up until we can go right again */)) {
						e = object_entry.oe_delta(ModernizedCProgram.to_pack, e);
					}
					if (!e/* done- we hit our original root node */) {
						return ;
					} 
					e = object_entry.oe_delta_sibling(ModernizedCProgram.to_pack, /* pass it off to sibling at this level */e);
			} 
		}
		;
	}
	public void add_family_to_write_order(Integer endp, object_entry e) {
		object_entry root = new object_entry();
		object_entry object_entry = new object_entry();
		for (root = e; object_entry.oe_delta(ModernizedCProgram.to_pack, root); root = object_entry.oe_delta(ModernizedCProgram.to_pack, root)) {
			;
		}
		wo.add_descendants_to_write_order(endp, root);
	}
	public void compute_layer_order(Integer wo_end) {
		int i;
		int last_untagged;
		object_entry objects = ModernizedCProgram.to_pack.getObjects();
		for (i = 0; i < ModernizedCProgram.to_pack.getNr_objects(); i++) {
			if (objects[i].getTagged()) {
				break;
			} 
			wo.add_to_write_order(wo_end, objects[i]);
		}
		last_untagged = i/*
			 * Then fill all the tagged tips.
			 */;
		for (; i < ModernizedCProgram.to_pack.getNr_objects(); i++) {
			if (objects[i].getTagged()) {
				wo.add_to_write_order(wo_end, objects[i]);
			} 
		}
		for (i = last_untagged; i < ModernizedCProgram.to_pack.getNr_objects(); /*
			 * And then all remaining commits and tags.
			 */i++) {
			if (ModernizedCProgram.oe_type(objects[i]) != object_type.OBJ_COMMIT && ModernizedCProgram.oe_type(objects[i]) != object_type.OBJ_TAG) {
				continue;
			} 
			wo.add_to_write_order(wo_end, objects[i]);
		}
		for (i = last_untagged; i < ModernizedCProgram.to_pack.getNr_objects(); /*
			 * And then all the trees.
			 */i++) {
			if (ModernizedCProgram.oe_type(objects[i]) != object_type.OBJ_TREE) {
				continue;
			} 
			wo.add_to_write_order(wo_end, objects[i]);
		}
		for (i = last_untagged; i < ModernizedCProgram.to_pack.getNr_objects(); /*
			 * Finally all the rest in really tight order
			 */i++) {
			if (!objects[i].getFilled() && ModernizedCProgram.oe_layer(ModernizedCProgram.to_pack, objects[i]) == ModernizedCProgram.write_layer) {
				wo.add_family_to_write_order(wo_end, objects[i]);
			} 
		}
	}
	public object_entry compute_write_order() {
		uint32_t max_layers = 1;
		int i;
		int wo_end;
		object_entry wo = new object_entry();
		object_entry objects = ModernizedCProgram.to_pack.getObjects();
		for (i = 0; i < ModernizedCProgram.to_pack.getNr_objects(); i++) {
			objects[i].setTagged(0);
			objects[i].setFilled(0);
			ModernizedCProgram.oe_set_delta_child(ModernizedCProgram.to_pack, objects[i], ((Object)0));
			ModernizedCProgram.oe_set_delta_sibling(ModernizedCProgram.to_pack, objects[i], ((Object)0/*
				 * Fully connect delta_child/delta_sibling network.
				 * Make sure delta_sibling is sorted in the original
				 * recency order.
				 */));
		}
		object_entry object_entry = new object_entry();
		for (i = ModernizedCProgram.to_pack.getNr_objects(); i > 0; ) {
			object_entry e = objects[--i];
			if (!object_entry.oe_delta(ModernizedCProgram.to_pack, e)) {
				continue;
			} 
			e.setDelta_sibling_idx(object_entry.oe_delta(ModernizedCProgram.to_pack, e).getDelta_child_idx());
			ModernizedCProgram.oe_set_delta_child(ModernizedCProgram.to_pack, object_entry.oe_delta(ModernizedCProgram.to_pack, e), e);
		}
		ModernizedCProgram.for_each_tag_ref(mark_tagged, ((Object)/*
			 * Mark objects that are at the tip of tags.
			 */0));
		if (ModernizedCProgram.use_delta_islands) {
			max_layers = ModernizedCProgram.to_pack.compute_pack_layers();
		} 
		(wo) = ModernizedCProgram.xmalloc(ModernizedCProgram.st_mult(, (ModernizedCProgram.to_pack.getNr_objects())));
		wo_end = 0;
		for (; ModernizedCProgram.write_layer < max_layers; ++ModernizedCProgram.write_layer) {
			wo.compute_layer_order(wo_end);
		}
		if (wo_end != ModernizedCProgram.to_pack.getNr_objects()) {
			ModernizedCProgram.die(ModernizedCProgram._("ordered %u objects, expected %u"), wo_end, ModernizedCProgram.to_pack.getNr_objects());
		} 
		return wo;
	}
	public int can_reuse_delta(Object base_sha1, object_entry base_out) {
		object_entry base = new object_entry();
		object_id base_oid = new object_id();
		if (!base_sha1) {
			return 0;
		} 
		base_oid.oidread(base_sha1/*
			 * First see if we're already sending the base (or it's explicitly in
			 * our "excluded" list).
			 */);
		base = .packlist_find(ModernizedCProgram.to_pack, base_oid);
		pack_idx_entry generatedIdx = this.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		if (base) {
			if (!ModernizedCProgram.in_same_island(generatedOid, generatedOid)) {
				return 0;
			} 
			base_out = base;
			return 1/*
				 * Otherwise, reachability bitmaps may tell us if the receiver has it,
				 * even if it was buried too deep in history to make it into the
				 * packing list.
				 */;
		} 
		if (ModernizedCProgram.thin && ModernizedCProgram.bitmap_git.bitmap_has_oid_in_uninteresting(base_oid)) {
			if (ModernizedCProgram.use_delta_islands) {
				if (!ModernizedCProgram.in_same_island(generatedOid, base_oid)) {
					return 0;
				} 
			} 
			base_out = ((Object)0);
			return 1;
		} 
		return 0;
	}
	public void check_object() {
		long canonical_size;
		packed_git packed_git = new packed_git();
		Object generatedIn_pack_offset = this.getIn_pack_offset();
		int generatedIn_pack_type = this.getIn_pack_type();
		int generatedPreferred_base = this.getPreferred_base();
		revindex_entry revindex_entry = new revindex_entry();
		int generatedNr = revidx.getNr();
		Object generatedDelta_child_idx = base_entry.getDelta_child_idx();
		byte generatedIn_pack_header_size = this.getIn_pack_header_size();
		pack_idx_entry generatedIdx = this.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		entry.oe_set_type(ModernizedCProgram.the_repository.oid_object_info(generatedOid, canonical_size));
		int generatedType_valid = this.getType_valid();
		if (generatedType_valid) {
			ModernizedCProgram.oe_set_size(ModernizedCProgram.to_pack, entry, canonical_size/*
					 * Bad object type is checked in prepare_pack().  This is
					 * to permit a missing preferred base object to be ignored
					 * as a preferred base.  Doing so can result in a larger
					 * pack file, but the transfer will still take place.
					 */);
		} 
		obj_buffer obj_buf = new obj_buffer();
		if (!obj) {
			return 1;
		} 
		int generatedFlags = obj.getFlags();
		if (generatedFlags & (-1024 << 21)) {
			return 0;
		} 
		int generatedType = obj.getType();
		if (ModernizedCProgram.type != object_type.OBJ_ANY && generatedType != ModernizedCProgram.type) {
			ModernizedCProgram.die("object type mismatch");
		} 
		object_id generatedOid = obj.getOid();
		if (!(generatedFlags & (-1024 << 20))) {
			long size;
			int type = ModernizedCProgram.the_repository.oid_object_info(generatedOid, size);
			if (ModernizedCProgram.type != generatedType || ModernizedCProgram.type <= 0) {
				ModernizedCProgram.die("object of unexpected type");
			} 
			generatedFlags |=  (-1024 << 21);
			return 0;
		} 
		obj_buffer obj_buffer = new obj_buffer();
		obj_buf = obj_buffer.lookup_object_buffer(obj);
		if (!obj_buf) {
			ModernizedCProgram.die("Whoops! Cannot find object '%s'", ModernizedCProgram.oid_to_hex(generatedOid));
		} 
		Byte generatedBuffer = obj_buf.getBuffer();
		long generatedSize = obj_buf.getSize();
		if (.fsck_object(obj, generatedBuffer, generatedSize, ModernizedCProgram.fsck_options)) {
			ModernizedCProgram.die("fsck error in packed object");
		} 
		ModernizedCProgram.fsck_options.setWalk(check_object);
		if (.fsck_walk(obj, ((Object)0), ModernizedCProgram.fsck_options)) {
			ModernizedCProgram.die("Error on reachable objects of %s", ModernizedCProgram.oid_to_hex(generatedOid));
		} 
		ModernizedCProgram.write_cached_object(obj, obj_buf);
		return 0;
		if (!obj) {
			return 0;
		} 
		int generatedFlags = obj.getFlags();
		if (!(generatedFlags & (-1024 << 20))) {
			return 0;
		} 
		object_id generatedOid = obj.getOid();
		int generatedType = obj.getType();
		if (!(generatedFlags & (-1024 << 21))) {
			long size;
			int type = ModernizedCProgram.the_repository.oid_object_info(generatedOid, size);
			if (ModernizedCProgram.type <= 0) {
				ModernizedCProgram.die(ModernizedCProgram._("did not receive expected object %s"), ModernizedCProgram.oid_to_hex(generatedOid));
			} 
			if (ModernizedCProgram.type != generatedType) {
				ModernizedCProgram.die(ModernizedCProgram._("object %s: expected type %s, found %s"), ModernizedCProgram.oid_to_hex(generatedOid), ModernizedCProgram.type_name(generatedType), ModernizedCProgram.type_name(ModernizedCProgram.type));
			} 
			generatedFlags |=  (-1024 << 21);
			return 1;
		} 
		return 0;
		if (ModernizedCProgram.verbose) {
			(_iob[2]).fprintf_ln(ModernizedCProgram._("Checking %s"), obj.describe_object());
		} 
		int generatedFlags = obj.getFlags();
		if (generatedFlags & -1024) {
			obj.check_reachable_object();
		} else {
				obj.check_unreachable_object();
		} 
	}
	public void drop_reused_delta() {
		Object generatedDelta_idx = this.getDelta_idx();
		int idx = ModernizedCProgram.to_pack.getObjects()[generatedDelta_idx - 1].getDelta_child_idx();
		object_info oi = new object_info(((Object)0));
		object_type type;
		long size;
		Object generatedDelta_sibling_idx = oe.getDelta_sibling_idx();
		while (idx) {
			object_entry oe = ModernizedCProgram.to_pack.getObjects()[idx - 1];
			if (oe == entry) {
				idx = generatedDelta_sibling_idx;
			} else {
					idx = generatedDelta_sibling_idx;
			} 
		}
		ModernizedCProgram.oe_set_delta(ModernizedCProgram.to_pack, entry, ((Object)0));
		this.setDepth(0);
		oi.setSizep(size);
		oi.setTypep(object_type.type);
		packed_git packed_git = new packed_git();
		Object generatedIn_pack_offset = this.getIn_pack_offset();
		pack_idx_entry generatedIdx = this.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		if (ModernizedCProgram.packed_object_info(ModernizedCProgram.the_repository, packed_git.oe_in_pack(ModernizedCProgram.to_pack, entry), generatedIn_pack_offset, oi) < 0/*
				 * We failed to get the info from this pack for some reason;
				 * fall back to oid_object_info, which may find another copy.
				 * And if that fails, the error will be recorded in oe_type(entry)
				 * and dealt with in prepare_pack().
				 */) {
			entry.oe_set_type(ModernizedCProgram.the_repository.oid_object_info(generatedOid, size));
		} else {
				entry.oe_set_type(object_type.type);
		} 
		ModernizedCProgram.oe_set_size(ModernizedCProgram.to_pack, entry, size/*
		 * Follow the chain of deltas from this entry onward, throwing away any links
		 * that cause us to hit a cycle (as determined by the DFS state flags in
		 * the entries).
		 *
		 * We also detect too-long reused chains that would violate our --depth
		 * limit.
		 */);
	}
	public void break_delta_chains() {
		uint32_t total_depth = new uint32_t();
		object_entry cur = new object_entry();
		object_entry next = new object_entry();
		int generatedDfs_state = cur.getDfs_state();
		int generatedDepth = cur.getDepth();
		object_entry object_entry = new object_entry();
		for (; cur; ) {
			if (generatedDfs_state == dfs_state.DFS_DONE/*
						 * We've already seen this object and know it isn't
						 * part of a cycle. We do need to append its depth
						 * to our count.
						 */) {
				total_depth += generatedDepth;
				break;
			} 
			if (generatedDfs_state != dfs_state.DFS_NONE) {
				ModernizedCProgram.BUG_fl("E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Git\\src\\pack-objects.c", 1779, "confusing delta dfs state in first pass: %d", generatedDfs_state);
			} 
			if (!object_entry.oe_delta(ModernizedCProgram.to_pack, cur)) {
				cur.setDfs_state(dfs_state.DFS_DONE);
				break;
			} 
			cur.setDfs_state(dfs_state.DFS_ACTIVE);
			if (generatedDfs_state == dfs_state.DFS_ACTIVE) {
				cur.drop_reused_delta();
				cur.setDfs_state(dfs_state.DFS_DONE);
				break;
			} 
		}
		for (cur = entry; cur; cur = next) {
			next = object_entry.oe_delta(ModernizedCProgram.to_pack, cur/*
					 * We should have a chain of zero or more ACTIVE states down to
					 * a final DONE. We can quit after the DONE, because either it
					 * has no bases, or we've already handled them in a previous
					 * call.
					 */);
			if (generatedDfs_state == dfs_state.DFS_DONE) {
				break;
			}  else if (generatedDfs_state != dfs_state.DFS_ACTIVE) {
				ModernizedCProgram.BUG_fl("E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Git\\src\\pack-objects.c", 1836, "confusing delta dfs state in second pass: %d", generatedDfs_state);
			} 
			cur.setDepth((total_depth--) % (ModernizedCProgram.depth + 1));
			if (!generatedDepth) {
				cur.drop_reused_delta();
			} 
			cur.setDfs_state(dfs_state.DFS_DONE);
		}
	}
	/*
		 * The actual depth of each object we will write is stored as an int,
		 * as it cannot exceed our int "depth" limit. But before we break
		 * changes based no that limit, we may potentially go as deep as the
		 * number of objects, which is elsewhere bounded to a uint32_t.
		 */
	public int check_delta_limit(int n) {
		object_entry object_entry = new object_entry();
		object_entry child = object_entry.oe_delta_child(ModernizedCProgram.to_pack, me);
		int m = n;
		object_entry object_entry = new object_entry();
		while (child) {
			int c = child.check_delta_limit(n + 1);
			if (m < c) {
				m = c;
			} 
			child = object_entry.oe_delta_sibling(ModernizedCProgram.to_pack, child);
		}
		return m;
	}
	public void find_deltas(int list_size, int window, int depth, int processed) {
		uint32_t i = new uint32_t();
		uint32_t idx = 0;
		uint32_t count = 0;
		unpacked array = new unpacked();
		long mem_usage = 0;
		array = ModernizedCProgram.xcalloc(window, );
		int generatedPreferred_base = entry.getPreferred_base();
		object_entry object_entry = new object_entry();
		object_entry generatedEntry = m.getEntry();
		Object generatedDelta_data = entry.getDelta_data();
		int generatedZ_delta_size = entry.getZ_delta_size();
		object_entry object_entry = new object_entry();
		int generatedDepth = n.getDepth();
		for (i = 0; i < window; ++i) {
			array[i].getIndex().free_delta_index();
			ModernizedCProgram.free(array[i].getData());
		}
		ModernizedCProgram.free(array/*
		 * The main object list is split into smaller lists, each is handed to
		 * one worker.
		 *
		 * The main thread waits on the condition that (at least) one of the workers
		 * has stopped working (which is indicated in the .working member of
		 * struct thread_params).
		 *
		 * When a work thread has completed its work, it sets .working to 0 and
		 * signals the main thread and waits on the condition that .data_ready
		 * becomes 1.
		 *
		 * The main thread steals half of the work from the worker that has
		 * most work left to hand it to the idle worker.
		 */);
	}
	public void ll_find_deltas(int list_size, int window, int depth, Integer processed) {
		thread_params p = new thread_params();
		int i;
		int ret;
		int active_threads = 0;
		ModernizedCProgram.init_threaded_search();
		if (ModernizedCProgram.delta_search_threads <= 1) {
			ModernizedCProgram.list.find_deltas(list_size, window, depth, processed);
			ModernizedCProgram.cleanup_threaded_search();
			return ;
		} 
		if (ModernizedCProgram.progress > ModernizedCProgram.pack_to_stdout) {
			(_iob[2]).fprintf_ln(ModernizedCProgram._("Delta compression using up to %d threads"), ModernizedCProgram.delta_search_threads);
		} 
		p = ModernizedCProgram.xcalloc(ModernizedCProgram.delta_search_threads, );
		for (i = 0; i < ModernizedCProgram.delta_search_threads; /* Partition the work amongst work threads. */i++) {
			int sub_size = list_size / (ModernizedCProgram.delta_search_threads - i);
			if (sub_size < 2 * window && i + 1 < /* don't use too small segments or no deltas will be found */ModernizedCProgram.delta_search_threads) {
				sub_size = 0;
			} 
			p[i].setWindow(window);
			p[i].setDepth(depth);
			p[i].setProcessed(processed);
			p[i].setWorking(1);
			p[i].setData_ready(0);
			while (sub_size && sub_size < /* try to split chunks on "path" boundaries */list_size && ModernizedCProgram.list[sub_size].getHash() && ModernizedCProgram.list[sub_size].getHash() == ModernizedCProgram.list[sub_size - 1].getHash()) {
				sub_size++;
			}
			p[i].setList(ModernizedCProgram.list);
			p[i].setList_size(sub_size);
			p[i].setRemaining(sub_size);
			ModernizedCProgram.list += sub_size;
			list_size -= sub_size;
		}
		for (i = 0; i < ModernizedCProgram.delta_search_threads; /* Start work threads. */i++) {
			if (!p[i].getList_size()) {
				continue;
			} 
			.pthread_mutex_init(p[i].getMutex(), ((Object)0));
			.pthread_cond_init(p[i].getCond(), ((Object)0));
			ret = p[i].getThread().pthread_create(((Object)0), threaded_find_deltas, p[i]);
			if (ret) {
				ModernizedCProgram.die(ModernizedCProgram._("unable to create thread: %s"), .strerror(ret));
			} 
			active_threads/*
				 * Now let's wait for work completion.  Each time a thread is done
				 * with its work, we steal half of the remaining work from the
				 * thread with the largest number of unprocessed objects and give
				 * it to that newly idle thread.  This ensure good load balancing
				 * until the remaining object list segments are simply too short
				 * to be worth splitting anymore.
				 */++;
		}
		int generatedRemaining = victim.getRemaining();
		object_entry generatedList = victim.getList();
		int generatedList_size = victim.getList_size();
		while (active_threads) {
			thread_params target = ((Object)0);
			thread_params victim = ((Object)0);
			int sub_size = 0;
			.pthread_mutex_lock(ModernizedCProgram.progress_mutex);
			for (; ; ) {
				for (i = 0; !ModernizedCProgram.target && i < ModernizedCProgram.delta_search_threads; i++) {
					if (!p[i].getWorking()) {
						ModernizedCProgram.target = p[i];
					} 
				}
				if (ModernizedCProgram.target) {
					break;
				} 
				.pthread_cond_wait(ModernizedCProgram.progress_cond, ModernizedCProgram.progress_mutex);
			}
			for (i = 0; i < ModernizedCProgram.delta_search_threads; i++) {
				if (p[i].getRemaining() > 2 * window && (!victim || generatedRemaining < generatedRemaining)) {
					victim = p[i];
				} 
			}
			if (victim) {
				sub_size = generatedRemaining / 2;
				ModernizedCProgram.list = generatedList + generatedList_size - sub_size;
				while (sub_size && ModernizedCProgram.list[0].getHash() && ModernizedCProgram.list[0].getHash() == ModernizedCProgram.list[-1].getHash()) {
					ModernizedCProgram.list++;
					sub_size--;
				}
				if (!sub_size/*
								 * It is possible for some "paths" to have
								 * so many objects that no hash boundary
								 * might be found.  Let's just steal the
								 * exact half in that case.
								 */) {
					sub_size = generatedRemaining / 2;
					ModernizedCProgram.list -= sub_size;
				} 
				ModernizedCProgram.target.setList(ModernizedCProgram.list);
				generatedList_size -= sub_size;
				generatedRemaining -= sub_size;
			} 
			ModernizedCProgram.target.setList_size(sub_size);
			ModernizedCProgram.target.setRemaining(sub_size);
			ModernizedCProgram.target.setWorking(1);
			.pthread_mutex_unlock(ModernizedCProgram.progress_mutex);
			.pthread_mutex_lock(ModernizedCProgram.target.getMutex());
			ModernizedCProgram.target.setData_ready(1);
			.pthread_cond_signal(ModernizedCProgram.target.getCond());
			.pthread_mutex_unlock(ModernizedCProgram.target.getMutex());
			if (!sub_size) {
				.pthread_join(ModernizedCProgram.target.getThread(), ((Object)0));
				.pthread_cond_destroy(ModernizedCProgram.target.getCond());
				.pthread_mutex_destroy(ModernizedCProgram.target.getMutex());
				active_threads--;
			} 
		}
		ModernizedCProgram.cleanup_threaded_search();
		ModernizedCProgram.free(p);
	}
	public object_entry new_object(object_id oid) {
		object_entry e = new object_entry();
		if (ModernizedCProgram.blocks.getNext_free() == ModernizedCProgram.blocks.getEnd()) {
			ModernizedCProgram.alloc_objects(ModernizedCProgram.object_entry_alloc);
		} 
		e = ModernizedCProgram.blocks.getNext_free()++;
		pack_idx_entry generatedIdx = e.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		generatedOid.oidcpy(oid);
		return e;
	}
	public object_entry find_object(object_id oid) {
		Object generatedHash = oid.getHash();
		int h = generatedHash[0] << 8 | generatedHash[1];
		object_entry e = new object_entry();
		pack_idx_entry generatedIdx = e.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		Object generatedNext = e.getNext();
		for (e = ModernizedCProgram.object_table[h]; e; e = generatedNext) {
			if (ModernizedCProgram.oideq(oid, generatedOid)) {
				return e;
			} 
		}
		return ((Object)0);
	}
	public object_entry insert_object(object_id oid) {
		Object generatedHash = oid.getHash();
		int h = generatedHash[0] << 8 | generatedHash[1];
		object_entry e = ModernizedCProgram.object_table[h];
		pack_idx_entry generatedIdx = e.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		Object generatedNext = e.getNext();
		while (e) {
			if (ModernizedCProgram.oideq(oid, generatedOid)) {
				return e;
			} 
			e = generatedNext;
		}
		object_entry object_entry = new object_entry();
		e = object_entry.new_object(oid);
		e.setNext(ModernizedCProgram.object_table[h]);
		generatedIdx.setOffset(0);
		ModernizedCProgram.object_table[h] = e;
		return e;
	}
	public void insert_mark(Object idnum) {
		mark_set s = ModernizedCProgram.marks;
		int generatedShift = s.getShift();
		 generatedData = s.getData();
		Object generatedSets = generatedData.getSets();
		while ((idnum >> generatedShift) >= 1024) {
			s = ModernizedCProgram.fi_mem_pool.mem_pool_calloc(1, );
			s.setShift(generatedShift + 10);
			generatedSets[0] = ModernizedCProgram.marks;
			ModernizedCProgram.marks = s;
		}
		while (generatedShift) {
			uintmax_t i = idnum >> generatedShift;
			idnum -= i << generatedShift;
			if (!generatedSets[i]) {
				generatedSets[i] = ModernizedCProgram.fi_mem_pool.mem_pool_calloc(1, );
				generatedSets[i].setShift(generatedShift - 10);
			} 
			s = generatedSets[i];
		}
		Object generatedMarked = generatedData.getMarked();
		if (!generatedMarked[idnum]) {
			ModernizedCProgram.marks_set_count++;
		} 
		generatedMarked[idnum] = oe;
	}
	public object_entry find_mark(Object idnum) {
		uintmax_t orig_idnum = idnum;
		mark_set s = ModernizedCProgram.marks;
		object_entry oe = ((Object)0);
		int generatedShift = s.getShift();
		 generatedData = s.getData();
		Object generatedSets = generatedData.getSets();
		Object generatedMarked = generatedData.getMarked();
		if ((idnum >> generatedShift) < 1024) {
			while (s && generatedShift) {
				uintmax_t i = idnum >> generatedShift;
				idnum -= i << generatedShift;
				s = generatedSets[i];
			}
			if (s) {
				oe = generatedMarked[idnum];
			} 
		} 
		if (!oe) {
			ModernizedCProgram.die("mark :%llu not declared", orig_idnum);
		} 
		return oe;
	}
	public Object gfi_unpack_entry(Long sizep) {
		object_type type;
		Object generatedPack_id = this.getPack_id();
		packed_git p = ModernizedCProgram.all_packs[generatedPack_id];
		Object generatedPack_size = p.getPack_size();
		if (p == ModernizedCProgram.pack_data && generatedPack_size < (ModernizedCProgram.pack_size + ModernizedCProgram.the_repository.getHash_algo().getRawsz())) {
			p.close_pack_windows();
			ModernizedCProgram.pack_file/* We have to offer rawsz bytes additional on the end of
					 * the packfile as the core unpacker code assumes the
					 * footer is present at the file end and must promise
					 * at least rawsz bytes within any window it maps.  But
					 * we don't actually create the footer here.
					 */.hashflush();
			p.setPack_size(ModernizedCProgram.pack_size + ModernizedCProgram.the_repository.getHash_algo().getRawsz());
		} 
		pack_idx_entry generatedIdx = this.getIdx();
		Object generatedOffset = generatedIdx.getOffset();
		return ModernizedCProgram.unpack_entry(ModernizedCProgram.the_repository, p, generatedOffset, object_type.type, sizep);
	}
	public object_entry dereference(object_id oid) {
		long size;
		byte buf = ((Object)0);
		int hexsz = ModernizedCProgram.the_repository.getHash_algo().getHexsz();
		object_entry object_entry = new object_entry();
		pack_idx_entry generatedIdx = this.getIdx();
		if (!oe) {
			object_type type = ModernizedCProgram.the_repository.oid_object_info(oid, ((Object)0));
			if (object_type.type < 0) {
				ModernizedCProgram.die("object not found: %s", ModernizedCProgram.oid_to_hex(oid));
			} 
			oe = object_entry.insert_object(/* cache it! */oid);
			this.setType(object_type.type);
			this.setPack_id(((1 << 16) - 1));
			generatedIdx.setOffset(1);
		} 
		Object generatedType = this.getType();
		switch (generatedType) {
		case object_type.OBJ_COMMIT:
		case /* easy case. */object_type.OBJ_TREE:
				return oe;
		case object_type.OBJ_TAG:
				break;
		default:
				ModernizedCProgram.die("Not a tree-ish: %s", ModernizedCProgram.command_buf.getBuf());
		}
		Object generatedPack_id = this.getPack_id();
		if (generatedPack_id != ((1 << 16) - /* in a pack being written */1)) {
			buf = oe.gfi_unpack_entry(size);
		} else {
				object_type unused;
				buf = ModernizedCProgram.the_repository.repo_read_object_file(oid, object_type.unused, size);
		} 
		if (!buf) {
			ModernizedCProgram.die("Can't load object %s", ModernizedCProgram.oid_to_hex(oid));
		} 
		switch (generatedType) {
		case object_type.OBJ_TAG:
				if (size < hexsz + .strlen("object ") || oid.get_oid_hex(buf + .strlen("object "))) {
					ModernizedCProgram.die("Invalid SHA1 in tag: %s", ModernizedCProgram.command_buf.getBuf());
				} 
				break;
		case object_type.OBJ_COMMIT:
				if (size < hexsz + .strlen("tree ") || oid.get_oid_hex(buf + .strlen("tree "))) {
					ModernizedCProgram.die("Invalid SHA1 in commit: %s", ModernizedCProgram.command_buf.getBuf());
				} 
		}
		ModernizedCProgram.free(buf);
		object_entry object_entry = new object_entry();
		return object_entry.find_object(oid);
	}
	public object_entry parse_treeish_dataref(Object p) {
		object_id oid = new object_id();
		object_entry e = new object_entry();
		object_entry object_entry = new object_entry();
		pack_idx_entry generatedIdx = e.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		object_entry object_entry = new object_entry();
		if (p == /* <mark> */(byte)':') {
			e = object_entry.find_mark(ModernizedCProgram.parse_mark_ref_space(p));
			if (!e) {
				ModernizedCProgram.die("Unknown mark: %s", ModernizedCProgram.command_buf.getBuf());
			} 
			oid.oidcpy(generatedOid);
		} else {
				if (oid.parse_oid_hex(p, /* <sha1> */p)) {
					ModernizedCProgram.die("Invalid dataref: %s", ModernizedCProgram.command_buf.getBuf());
				} 
				e = object_entry.find_object(oid);
				if ((p)++ != (byte)' ') {
					ModernizedCProgram.die("Missing space after tree-ish: %s", ModernizedCProgram.command_buf.getBuf());
				} 
		} 
		Object generatedType = e.getType();
		while (!e || generatedType != object_type.OBJ_TREE) {
			e = e.dereference(oid);
		}
		return e;
	}
	public Object unpack_data(Object consume, Object cb_data) {
		off_t from = obj[0].getIdx().getOffset() + obj[0].getHdr_size();
		off_t len = obj[1].getIdx().getOffset() - from;
		byte data;
		byte inbuf;
		git_zstream stream = new git_zstream();
		int status;
		Object generatedSize = this.getSize();
		data = ModernizedCProgram.xmallocz(consume ? 64 * 1024 : generatedSize);
		inbuf = ModernizedCProgram.xmalloc((len < 64 * 1024) ? (int)len : 64 * 1024);
		.memset(stream, 0, );
		stream.git_inflate_init();
		stream.setNext_out(data);
		stream.setAvail_out(consume ? 64 * 1024 : generatedSize);
		long generatedAvail_in = stream.getAvail_in();
		thread_local thread_local = new thread_local();
		Byte generatedNext_out = stream.getNext_out();
		do {
			ssize_t n = (len < 64 * 1024) ? (ssize_t)len : 64 * 1024;
			n = ModernizedCProgram.xpread(thread_local.get_thread_data().getPack_fd(), inbuf, n, from);
			if (n < 0) {
				ModernizedCProgram.die_errno(ModernizedCProgram._("cannot pread pack file"));
			} 
			if (!n) {
				ModernizedCProgram.die(ModernizedCProgram.Q_("premature end of pack file, %llu byte missing", "premature end of pack file, %llu bytes missing", (int)len), (uintmax_t)len);
			} 
			from += n;
			len -= n;
			stream.setNext_in(inbuf);
			stream.setAvail_in(n);
			if (!consume) {
				status = stream.git_inflate(0);
			} else {
					do {
						status = stream.git_inflate(0);
						if (.consume(data, generatedNext_out - data, cb_data)) {
							ModernizedCProgram.free(inbuf);
							ModernizedCProgram.free(data);
							return ((Object)0);
						} 
						stream.setNext_out(data);
						stream.setAvail_out(64 * 1024);
					} while (status == Z_OK && generatedAvail_in);
			} 
		} while (len && status == Z_OK && !generatedAvail_in);
		long generatedTotal_out = stream.getTotal_out();
		if (status != Z_STREAM_END || generatedTotal_out != generatedSize) {
			ModernizedCProgram.die(ModernizedCProgram._("serious inflate inconsistency"));
		} 
		stream.git_inflate_end();
		ModernizedCProgram.free(inbuf);
		if (consume) {
			do {
				ModernizedCProgram.free(data);
				(data) = ((Object)0);
			} while (0);
		} 
		return data;
	}
	public Object get_data_from_pack() {
		return obj.unpack_data(((Object)0), ((Object)0));
	}
	public int check_collison() {
		compare_data data = new compare_data();
		object_type type;
		long size;
		Object generatedSize = this.getSize();
		Object generatedType = this.getType();
		if (generatedSize <= ModernizedCProgram.big_file_threshold || generatedType != object_type.OBJ_BLOB) {
			return -1;
		} 
		.memset(data, 0, );
		data.setEntry(entry);
		pack_idx_entry generatedIdx = this.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		git_istream git_istream = new git_istream();
		data.setSt(git_istream.open_istream(generatedOid, object_type.type, size, ((Object)0)));
		git_istream generatedSt = data.getSt();
		if (!generatedSt) {
			return -1;
		} 
		if (size != generatedSize || object_type.type != generatedType) {
			ModernizedCProgram.die(ModernizedCProgram._("SHA1 COLLISION FOUND WITH %s !"), ModernizedCProgram.oid_to_hex(generatedOid));
		} 
		entry.unpack_data(compare_objects, data);
		generatedSt.close_istream();
		Byte generatedBuf = data.getBuf();
		ModernizedCProgram.free(generatedBuf);
		return 0;
	}
	public void sha1_object(Object data, long size, object_type type, Object oid) {
		Object new_data = ((Object)0);
		int collision_test_needed = 0;
		((data || obj_entry) ? (Object)0 : ._assert("data || obj_entry", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Git\\src\\index-pack.c", 779));
		if (ModernizedCProgram.startup_info.getHave_repository()) {
			ModernizedCProgram.lock_mutex(ModernizedCProgram.read_mutex);
			collision_test_needed = ModernizedCProgram.the_repository.repo_has_object_file_with_flags(oid, 8);
			ModernizedCProgram.unlock_mutex(ModernizedCProgram.read_mutex);
		} 
		if (collision_test_needed && !data) {
			ModernizedCProgram.lock_mutex(ModernizedCProgram.read_mutex);
			if (!obj_entry.check_collison()) {
				collision_test_needed = 0;
			} 
			ModernizedCProgram.unlock_mutex(ModernizedCProgram.read_mutex);
		} 
		if (collision_test_needed) {
			Object has_data;
			object_type has_type;
			long has_size;
			ModernizedCProgram.lock_mutex(ModernizedCProgram.read_mutex);
			object_type.has_type = ModernizedCProgram.the_repository.oid_object_info(oid, has_size);
			if (object_type.has_type < 0) {
				ModernizedCProgram.die(ModernizedCProgram._("cannot read existing object info %s"), ModernizedCProgram.oid_to_hex(oid));
			} 
			if (object_type.has_type != object_type.type || has_size != size) {
				ModernizedCProgram.die(ModernizedCProgram._("SHA1 COLLISION FOUND WITH %s !"), ModernizedCProgram.oid_to_hex(oid));
			} 
			has_data = ModernizedCProgram.the_repository.repo_read_object_file(oid, object_type.has_type, has_size);
			ModernizedCProgram.unlock_mutex(ModernizedCProgram.read_mutex);
			if (!data) {
				data = new_data = obj_entry.get_data_from_pack();
			} 
			if (!has_data) {
				ModernizedCProgram.die(ModernizedCProgram._("cannot read existing object %s"), ModernizedCProgram.oid_to_hex(oid));
			} 
			if (size != has_size || object_type.type != object_type.has_type || .memcmp(data, has_data, size) != 0) {
				ModernizedCProgram.die(ModernizedCProgram._("SHA1 COLLISION FOUND WITH %s !"), ModernizedCProgram.oid_to_hex(oid));
			} 
			ModernizedCProgram.free(has_data);
		} 
		blob blob = new blob();
		object generatedObject = blob.getObject();
		int generatedFlags = generatedObject.getFlags();
		object object = new object();
		object_id generatedOid = obj.getOid();
		int generatedType = obj.getType();
		if (ModernizedCProgram.strict || ModernizedCProgram.do_fsck_object) {
			ModernizedCProgram.lock_mutex(ModernizedCProgram.read_mutex);
			if (object_type.type == object_type.OBJ_BLOB) {
				blob blob = blob.lookup_blob(ModernizedCProgram.the_repository, oid);
				if (blob) {
					generatedFlags |=  (-1024 << 21);
				} else {
						ModernizedCProgram.die(ModernizedCProgram._("invalid blob object %s"), ModernizedCProgram.oid_to_hex(oid));
				} 
				if (ModernizedCProgram.do_fsck_object && .fsck_object(generatedObject, (Object)data, size, ModernizedCProgram.fsck_options)) {
					ModernizedCProgram.die(ModernizedCProgram._("fsck error in packed object"));
				} 
			} else {
					object obj = new object();
					int eaten;
					Object buf = (Object)data;
					((data && "data can only be NULL for large _blobs_") ? (Object)0 : ._assert("data && \"data can only be NULL for large _blobs_\"", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Git\\src\\index-pack.c", 832/*
								 * we do not need to free the memory here, as the
								 * buf is deleted by the caller.
								 */));
					obj = object.parse_object_buffer(ModernizedCProgram.the_repository, oid, object_type.type, size, ModernizedCProgram.buf, eaten);
					if (!obj) {
						ModernizedCProgram.die(ModernizedCProgram._("invalid %s"), ModernizedCProgram.type_name(object_type.type));
					} 
					if (ModernizedCProgram.do_fsck_object && .fsck_object(obj, ModernizedCProgram.buf, size, ModernizedCProgram.fsck_options)) {
						ModernizedCProgram.die(ModernizedCProgram._("fsck error in packed object"));
					} 
					if (ModernizedCProgram.strict && .fsck_walk(obj, ((Object)0), ModernizedCProgram.fsck_options)) {
						ModernizedCProgram.die(ModernizedCProgram._("Not all child objects of %s are reachable"), ModernizedCProgram.oid_to_hex(generatedOid));
					} 
					if (generatedType == object_type.OBJ_TREE) {
						tree item = (tree)obj;
						item.setBuffer(((Object)0));
						obj.setParsed(0);
					} 
					if (generatedType == object_type.OBJ_COMMIT) {
						commit commit = (commit)obj;
						if (.detach_commit_buffer(commit, ((Object)0)) != data) {
							ModernizedCProgram.BUG_fl("E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Git\\src\\index-pack.c", 857, "parse_object_buffer transmogrified our buffer");
						} 
					} 
					generatedFlags |=  (-1024 << 21);
			} 
			ModernizedCProgram.unlock_mutex(ModernizedCProgram.read_mutex);
		} 
		ModernizedCProgram.free(new_data/*
		 * This function is part of find_unresolved_deltas(). There are two
		 * walkers going in the opposite ways.
		 *
		 * The first one in find_unresolved_deltas() traverses down from
		 * parent node to children, deflating nodes along the way. However,
		 * memory for deflated nodes is limited by delta_base_cache_limit, so
		 * at some point parent node's deflated content may be freed.
		 *
		 * The second walker is this function, which goes from current node up
		 * to top parent if necessary to deflate the node. In normal
		 * situation, its parent node would be already deflated, so it just
		 * needs to apply delta.
		 *
		 * In the worst case scenario, parent node is no longer deflated because
		 * we're running out of delta_base_cache_limit; we need to re-deflate
		 * parents, possibly up to the top base.
		 *
		 * All deflated objects here are subject to be freed if we exceed
		 * delta_base_cache_limit, just like in find_unresolved_deltas(), we
		 * just need to make sure the last node is not freed.
		 */);
	}
	public void resolve_base() {
		base_data base_data = new base_data();
		base_data base_obj = base_data.alloc_base_data();
		base_obj.setObj(obj);
		base_obj.setData(((Object)0));
		base_obj.find_unresolved_deltas();
	}
	public object_entry append_obj_to_pack(hashfile f, Object sha1, Object buf, long size, object_type type) {
		object_entry obj = ModernizedCProgram.objects[ModernizedCProgram.nr_objects++];
		byte[] header = new byte[10];
		long s = size;
		int n = 0;
		byte c = (object_type.type << 4) | (s & 15);
		s >>=  4;
		while (s) {
			header[n++] = c | -1024;
			c = s & -1024;
			s >>=  7;
		}
		header[n++] = c;
		f.crc32_begin();
		f.hashwrite(header, n);
		obj[0].setSize(size);
		obj[0].setHdr_size(n);
		obj[0].setType(object_type.type);
		obj[0].setReal_type(object_type.type);
		obj[1].getIdx().setOffset(obj[0].getIdx().getOffset() + n);
		obj[1].getIdx().getOffset() += f.write_compressed(buf, size);
		obj[0].getIdx().setCrc32(f.crc32_end());
		f.hashflush();
		pack_idx_entry generatedIdx = obj.getIdx();
		object_id generatedOid = generatedIdx.getOid();
		Object generatedHash = generatedOid.getHash();
		ModernizedCProgram.hashcpy(generatedHash, sha1);
		return obj;
	}
	public pack_idx_entry getIdx() {
		return idx;
	}
	public void setIdx(pack_idx_entry newIdx) {
		idx = newIdx;
	}
	public Object getDelta_data() {
		return delta_data;
	}
	public void setDelta_data(Object newDelta_data) {
		delta_data = newDelta_data;
	}
	public Object getIn_pack_offset() {
		return in_pack_offset;
	}
	public void setIn_pack_offset(Object newIn_pack_offset) {
		in_pack_offset = newIn_pack_offset;
	}
	public Object getHash() {
		return hash;
	}
	public void setHash(Object newHash) {
		hash = newHash;
	}
	public int getSize_() {
		return size_;
	}
	public void setSize_(int newSize_) {
		size_ = newSize_;
	}
	public int getSize_valid() {
		return size_valid;
	}
	public void setSize_valid(int newSize_valid) {
		size_valid = newSize_valid;
	}
	public Object getDelta_idx() {
		return delta_idx;
	}
	public void setDelta_idx(Object newDelta_idx) {
		delta_idx = newDelta_idx;
	}
	public Object getDelta_child_idx() {
		return delta_child_idx;
	}
	public void setDelta_child_idx(Object newDelta_child_idx) {
		delta_child_idx = newDelta_child_idx;
	}
	public Object getDelta_sibling_idx() {
		return delta_sibling_idx;
	}
	public void setDelta_sibling_idx(Object newDelta_sibling_idx) {
		delta_sibling_idx = newDelta_sibling_idx;
	}
	public int getDelta_size_() {
		return delta_size_;
	}
	public void setDelta_size_(int newDelta_size_) {
		delta_size_ = newDelta_size_;
	}
	public int getDelta_size_valid() {
		return delta_size_valid;
	}
	public void setDelta_size_valid(int newDelta_size_valid) {
		delta_size_valid = newDelta_size_valid;
	}
	public byte getIn_pack_header_size() {
		return in_pack_header_size;
	}
	public void setIn_pack_header_size(byte newIn_pack_header_size) {
		in_pack_header_size = newIn_pack_header_size;
	}
	public int getIn_pack_idx() {
		return in_pack_idx;
	}
	public void setIn_pack_idx(int newIn_pack_idx) {
		in_pack_idx = newIn_pack_idx;
	}
	public int getZ_delta_size() {
		return z_delta_size;
	}
	public void setZ_delta_size(int newZ_delta_size) {
		z_delta_size = newZ_delta_size;
	}
	public int getType_valid() {
		return type_valid;
	}
	public void setType_valid(int newType_valid) {
		type_valid = newType_valid;
	}
	public int getNo_try_delta() {
		return no_try_delta;
	}
	public void setNo_try_delta(int newNo_try_delta) {
		no_try_delta = newNo_try_delta;
	}
	public int getType_() {
		return type_;
	}
	public void setType_(int newType_) {
		type_ = newType_;
	}
	public int getIn_pack_type() {
		return in_pack_type;
	}
	public void setIn_pack_type(int newIn_pack_type) {
		in_pack_type = newIn_pack_type;
	}
	public int getPreferred_base() {
		return preferred_base;
	}
	public void setPreferred_base(int newPreferred_base) {
		preferred_base = newPreferred_base;
	}
	public int getTagged() {
		return tagged;
	}
	public void setTagged(int newTagged) {
		tagged = newTagged;
	}
	public int getFilled() {
		return filled;
	}
	public void setFilled(int newFilled) {
		filled = newFilled;
	}
	public int getDfs_state() {
		return dfs_state;
	}
	public void setDfs_state(int newDfs_state) {
		dfs_state = newDfs_state;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int newDepth) {
		depth = newDepth;
	}
	public int getExt_base() {
		return ext_base;
	}
	public void setExt_base(int newExt_base) {
		ext_base = newExt_base;
	}
}