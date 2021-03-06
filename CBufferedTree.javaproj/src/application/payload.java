package application;

public class payload {
	private Object key;
	private Object val;
	private payload next;
	private payload_type type;
	
	public payload(Object key, Object val, payload next, payload_type type) {
		setKey(key);
		setVal(val);
		setNext(next);
		setType(type);
	}
	public payload() {
	}
	
	public payload payload_create(Object key, Object val, payload_type type) {
		payload payload = new payload();
		payload = /*Error: Function owner not recognized*/malloc(/*Error: sizeof expression not supported yet*/);
		payload.setKey(key);
		payload.setVal(val);
		payload.setNext((null));
		payload.setType(payload_type.type);
		return payload;
	}
	public payload get_payload(Object compare, Object key, Integer is_equal) {
		payload curr_payload = new payload();
		payload prev_payload = new payload();
		int compared;
		prev_payload = (null);
		curr_payload = payload_start;
		is_equal = 0;
		Object generatedKey = curr_payload.getKey();
		payload generatedNext = curr_payload.getNext();
		while (curr_payload) {
			compared = /*Error: Function owner not recognized*/compare(key, generatedKey);
			if (compared <= 0) {
				if (compared == 0) {
					is_equal = 1;
					return curr_payload;
				} 
				return prev_payload;
			} 
			prev_payload = curr_payload;
			curr_payload = generatedNext;
		}
		return prev_payload;
	}
	public payload container_get(bftree tree, node node, Object container_idx, Object key) {
		payload curr_payload = new payload();
		container container = new container();
		int compare;
		int is_equal;
		Object generatedContainer_size = node.getContainer_size();
		if (container_idx >= generatedContainer_size) {
			return (null);
		} 
		bftree_opts generatedOpts = tree.getOpts();
		Object generatedKey_compare = generatedOpts.getKey_compare();
		compare = generatedKey_compare;
		container[][] generatedContainers = node.getContainers();
		container = generatedContainers[container_idx];
		payload generatedPayload_first = container.getPayload_first();
		curr_payload = generatedPayload_first.get_payload(compare, key, is_equal);
		payload_type generatedType = curr_payload.getType();
		if (is_equal) {
			if (generatedType == payload_type.Put) {
				return curr_payload;
			} 
			return (null);
		} 
		node generatedChild = container.getChild();
		payload payload = new payload();
		if (generatedChild) {
			container_idx = generatedChild.find_container(compare, key, 0);
			return payload.container_get(tree, generatedChild, container_idx, key);
		} 
		return (null);// ================================================================
	}
	public payload bftree_next(bftree_iterator iter) {
		bftree tree = new bftree();
		container container = new container();
		node node = new node();
		payload curr = new payload();
		payload next = new payload();
		payload min = new payload();
		uint32_t idx = new uint32_t();
		key_compare_func key_compare = new key_compare_func();
		int is_equal;
		int generatedClosed = iter.getClosed();
		if (generatedClosed) {
			return (null);
		} 
		bftree generatedTree = iter.getTree();
		tree = generatedTree;
		bftree_opts generatedOpts = tree.getOpts();
		Object generatedKey_compare = generatedOpts.getKey_compare();
		key_compare = generatedKey_compare;
		payload generatedNext = iter.getNext();
		node generatedRoot = tree.getRoot();
		Object generatedContainer_size = generatedRoot.getContainer_size();
		container[][] generatedContainers = generatedRoot.getContainers();
		if (!generatedNext) {
			if (generatedContainer_size == 0) {
				return (null);
			} 
			iter.setNext(generatedContainers[0].getPayload_first());
		} 
		curr = generatedNext;
		min = (null);
		node = generatedRoot;
		Object generatedKey = curr.getKey();
		payload generatedPayload_first = container.getPayload_first();
		node generatedChild = container.getChild();
		do {
			idx = node.find_container(key_compare, generatedKey, 0);
			container = generatedContainers[idx];
			next = generatedPayload_first.get_payload(key_compare, generatedKey, is_equal);
			if (!next) {
				next = generatedPayload_first;
			} else {
					next = generatedNext;
			} 
			if (next) {
				if (!min) {
					min = next;
				}  else if (/*Error: Function owner not recognized*/key_compare(generatedKey, generatedKey) < 0) {
					min = next;
				} 
			} 
			node = generatedChild;
		} while (node);
		iter.setNext(min);
		if (!min) {
			iter.setClosed(1);
		} 
		return curr;
	}
	public Object getKey() {
		return key;
	}
	public void setKey(Object newKey) {
		key = newKey;
	}
	public Object getVal() {
		return val;
	}
	public void setVal(Object newVal) {
		val = newVal;
	}
	public payload getNext() {
		return next;
	}
	public void setNext(payload newNext) {
		next = newNext;
	}
	public payload_type getType() {
		return type;
	}
	public void setType(payload_type newType) {
		type = newType;
	}
}
