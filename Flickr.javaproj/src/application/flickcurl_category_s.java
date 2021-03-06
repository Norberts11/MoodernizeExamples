package application;

public class flickcurl_category_s {
	private Byte id;
	private Byte name;
	private Byte path;
	private int count;
	private flickcurl_category_s[][] categories;
	private int categories_count;
	private [][] groups;
	private int groups_count;
	
	public flickcurl_category_s(Byte id, Byte name, Byte path, int count, flickcurl_category_s[][] categories, int categories_count, [][] groups, int groups_count) {
		setId(id);
		setName(name);
		setPath(path);
		setCount(count);
		setCategories(categories);
		setCategories_count(categories_count);
		setGroups(groups);
		setGroups_count(groups_count);
	}
	public flickcurl_category_s() {
	}
	
	public void command_print_category() {
		Byte generatedId = this.getId();
		Byte generatedName = this.getName();
		Byte generatedPath = this.getPath();
		int generatedCount = this.getCount();
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[1]), "category: id %s  name '%s'  path '%s'  count %d\n", generatedId, generatedName, generatedPath, generatedCount);
		flickcurl_category_s[][] generatedCategories = this.getCategories();
		if (generatedCategories) {
			int i;
			for (i = 0; generatedCategories[i]; i++) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[1]), "%s: Category %d\n", ModernizedCProgram.program, i);
				generatedCategories[i].command_print_category();
			}
		} 
		[][] generatedGroups = this.getGroups();
		if (generatedGroups) {
			int i;
			for (i = 0; generatedGroups[i]; i++) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[1]), "%s: Group %d\n", ModernizedCProgram.program, i);
				generatedGroups[i].command_print_group();
			}
		} 
	}
	/* -*- Mode: c; c-basic-offset: 2 -*-
	 *
	 * groups-api.c - Flickr flickr.groups.* API calls
	 *
	 * Copyright (C) 2007-2012, David Beckett http://www.dajobe.org/
	 * 
	 * This file is licensed under the following three licenses as alternatives:
	 *   1. GNU Lesser General Public License (LGPL) V2.1 or any newer version
	 *   2. GNU General Public License (GPL) V2 or any newer version
	 *   3. Apache License, V2.0 or any newer version
	 * 
	 * You may not use this file except in compliance with at least one of
	 * the above three licenses.
	 * 
	 * See LICENSE.html or LICENSE.txt at the top of this package for the
	 * complete terms and further detail along with the license texts for
	 * the licenses in COPYING.LIB, COPYING and LICENSE-2.0.txt respectively.
	 * 
	 */
	/**
	 * flickcurl_groups_browse:
	 * @fc: flickcurl context
	 * @cat_id: The category id to fetch a list of groups and sub-categories for. If not specified, it defaults to zero, the root of the category tree. (or NULL)
	 * 
	 * Browse the group category tree, finding groups and sub-categories.
	 *
	 * Implements flickr.groups.browse (0.13)
	 * 
	 * Return value: non-0 on failure
	 **/
	public flickcurl_category_s flickcurl_groups_browse(flickcurl_s fc, int cat_id) {
		 doc = (null);
		 xpathCtx = (null);
		flickcurl_category category = (null);
		byte[] cat_id_str = new byte[10];
		fc.flickcurl_init_params(0);
		if (cat_id >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(cat_id_str, "%d", cat_id);
			fc.flickcurl_add_param("cat_id", cat_id_str);
		} 
		fc.flickcurl_end_params();
		if (fc.flickcurl_prepare("flickr.groups.browse")) {
			;
		} 
		doc = fc.flickcurl_invoke();
		if (!doc) {
			;
		} 
		xpathCtx = /*Error: Function owner not recognized*/xmlXPathNewContext(doc);
		if (!xpathCtx) {
			fc.flickcurl_error("Failed to create XPath context for document");
			fc.setFailed(1);
			;
		} 
		category = (flickcurl_category)/*Error: Function owner not recognized*/calloc(/*Error: Unsupported expression*/, 1);
		int generatedCategories_count = category.getCategories_count();
		flickcurl_category_s flickcurl_category_s = new flickcurl_category_s();
		category.setCategories(flickcurl_category_s.flickcurl_build_categories(fc, xpathCtx, ()"/rsp/category/subcat", generatedCategories_count));
		int generatedGroups_count = category.getGroups_count();
		category.setGroups(fc.flickcurl_build_groups(xpathCtx, ()"/rsp/category/group", generatedGroups_count));
		int generatedFailed = fc.getFailed();
		if (generatedFailed) {
			if (category) {
				category.flickcurl_free_category();
			} 
			category = (null);
		} 
		return category/**
		 * flickcurl_groups_getInfo:
		 * @fc: flickcurl context
		 * @group_id: The NSID of the group to fetch information for.
		 * @lang: The language of the group name and description to fetch.  If the language is not found, the primary language of the group will be returned (or NULL)
		 *
		 * Get information about a group.
		 *
		 * Implements flickr.groups.getInfo (0.13)
		 * 
		 * Return value: non-0 on failure
		 **/;
	}
	/* -*- Mode: c; c-basic-offset: 2 -*-
	 *
	 * category.c - Flickcurl category functions
	 *
	 * Copyright (C) 2007-2008, David Beckett http://www.dajobe.org/
	 * 
	 * This file is licensed under the following three licenses as alternatives:
	 *   1. GNU Lesser General Public License (LGPL) V2.1 or any newer version
	 *   2. GNU General Public License (GPL) V2 or any newer version
	 *   3. Apache License, V2.0 or any newer version
	 * 
	 * You may not use this file except in compliance with at least one of
	 * the above three licenses.
	 * 
	 * See LICENSE.html or LICENSE.txt at the top of this package for the
	 * complete terms and further detail along with the license texts for
	 * the licenses in COPYING.LIB, COPYING and LICENSE-2.0.txt respectively.
	 * 
	 */
	/**
	 * flickcurl_free_category:
	 * @category: category object
	 *
	 * Destructor for category object
	 */
	public void flickcurl_free_category() {
		do {
			if (!category) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[2]), "%s:%d: (%s) assertion failed: object pointer of type flickcurl_category is NULL.\n", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Flickr\\src\\category.c", 46, __func__);
				return /*Error: Unsupported expression*/;
			} 
		} while (0);
		Byte generatedId = this.getId();
		if (generatedId) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(generatedId);
		} 
		Byte generatedName = this.getName();
		if (generatedName) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(generatedName);
		} 
		flickcurl_category_s[][] generatedCategories = this.getCategories();
		if (generatedCategories) {
			generatedCategories.flickcurl_free_categories();
		} 
		[][] generatedGroups = this.getGroups();
		if (generatedGroups) {
			generatedGroups.flickcurl_free_groups();
		} 
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(category/**
		 * flickcurl_free_categories:
		 * @categories_object: category object array
		 *
		 * Destructor for array of category object
		 */);
	}
	public void flickcurl_free_categories() {
		int i;
		do {
			if (!categories_object) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[2]), "%s:%d: (%s) assertion failed: object pointer of type flickcurl_category is NULL.\n", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Flickr\\src\\category.c", 75, __func__);
				return /*Error: Unsupported expression*/;
			} 
		} while (0);
		for (i = 0; categories_object[i]; i++) {
			categories_object[i].flickcurl_free_category();
		}
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(categories_object);
	}
	public flickcurl_category_s flickcurl_build_categories(flickcurl_s fc, Object xpathCtx, Object xpathExpr, Integer category_count_p) {
		flickcurl_category[][] categories = (null);
		int nodes_count;
		int category_count;
		int i;
		 xpathObj = (null);
		 nodes = new ();
		xpathObj = /*Error: Function owner not recognized*/xmlXPathEvalExpression(xpathExpr, xpathCtx);
		if (!xpathObj) {
			fc.flickcurl_error("Unable to evaluate XPath expression \"%s\"", xpathExpr);
			fc.setFailed(1);
			;
		} 
		nodes = xpathObj.getNodesetval();
		nodes_count = /*Error: Function owner not recognized*/xmlXPathNodeSetGetLength(/* This is a max size - it can include nodes that are CDATA */nodes);
		categories = (flickcurl_category)/*Error: Function owner not recognized*/calloc(/*Error: Unsupported expression*/, nodes_count + 1);
		for (; i < nodes_count; i++) {
			 node = nodes.getNodeTab()[i];
			 attr = new ();
			flickcurl_category c = new flickcurl_category();
			if (node.getType() != XML_ELEMENT_NODE) {
				fc.flickcurl_error("Got unexpected node type %d", node.getType());
				fc.setFailed(1);
				break;
			} 
			c = (flickcurl_category)/*Error: Function owner not recognized*/calloc(/*Error: Unsupported expression*/, 1);
			for (attr = node.getProperties(); attr; attr = attr.getNext()) {
				size_t attr_len = /*Error: Function owner not recognized*/strlen((byte)attr.getChildren().getContent());
				Byte attr_name = (byte)attr.getName();
				Byte attr_value;
				attr_value = (byte)/*Error: Function owner not recognized*/malloc(attr_len + 1);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/memcpy(attr_value, attr.getChildren().getContent(), attr_len + 1);
				if (!/*Error: Function owner not recognized*/strcmp(attr_name, "id")) {
					c.setId(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "name")) {
					c.setName(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "path")) {
					c.setPath(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "count")) {
					c.setCount(/*Error: Function owner not recognized*/atoi(attr_value));
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				} else {
						/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				} 
			}
			categories[category_count++] = c/* for nodes */;
		}
		if (category_count_p) {
			category_count_p = category_count;
		} 
		return categories;
	}
	public Byte getId() {
		return id;
	}
	public void setId(Byte newId) {
		id = newId;
	}
	public Byte getName() {
		return name;
	}
	public void setName(Byte newName) {
		name = newName;
	}
	public Byte getPath() {
		return path;
	}
	public void setPath(Byte newPath) {
		path = newPath;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int newCount) {
		count = newCount;
	}
	public flickcurl_category_s[][] getCategories() {
		return categories;
	}
	public void setCategories(flickcurl_category_s[][] newCategories) {
		categories = newCategories;
	}
	public int getCategories_count() {
		return categories_count;
	}
	public void setCategories_count(int newCategories_count) {
		categories_count = newCategories_count;
	}
	public [][] getGroups() {
		return groups;
	}
	public void setGroups([][] newGroups) {
		groups = newGroups;
	}
	public int getGroups_count() {
		return groups_count;
	}
	public void setGroups_count(int newGroups_count) {
		groups_count = newGroups_count;
	}
}
/**
 * flickcurl_gallery: 
 * @id: gallery ID
 * @url: URL of gallery
 * @owner: owner NSID
 * @date_create: creation date of gallery
 * @date_update: update / last modified date of gallery
 * @primary_photo: primary photo for the gallery
 * @count_photos: number of photos in the gallery
 * @count_videos: number of photos in the gallery
 * @title: Gallery title
 * @description: Gallery description
 *
 * A photo gallery.
 *
 * The list of photos in the gallery is available via the API calls
 * flickcurl_galleries_getPhotos() or
 * flickcurl_galleries_getPhotos_params()
 *
 */
