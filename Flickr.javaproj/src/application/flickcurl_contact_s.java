package application;

public class flickcurl_contact_s {
	private Byte nsid;
	private Byte username;
	private int iconserver;
	private Byte realname;
	private int is_friend;
	private int is_family;
	private int ignored;
	private int uploaded;
	
	public flickcurl_contact_s(Byte nsid, Byte username, int iconserver, Byte realname, int is_friend, int is_family, int ignored, int uploaded) {
		setNsid(nsid);
		setUsername(username);
		setIconserver(iconserver);
		setRealname(realname);
		setIs_friend(is_friend);
		setIs_family(is_family);
		setIgnored(ignored);
		setUploaded(uploaded);
	}
	public flickcurl_contact_s() {
	}
	
	public void command_print_contact(int i) {
		Byte generatedNsid = this.getNsid();
		Byte generatedUsername = this.getUsername();
		int generatedIconserver = this.getIconserver();
		Byte generatedRealname = this.getRealname();
		int generatedIs_friend = this.getIs_friend();
		int generatedIs_family = this.getIs_family();
		int generatedIgnored = this.getIgnored();
		int generatedUploaded = this.getUploaded();
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[1]), "contact %d: NSID %s username %s iconserver %d realname %s friend %d family %d ignored %d upload count %d\n", i, generatedNsid, generatedUsername, generatedIconserver, generatedRealname, generatedIs_friend, generatedIs_family, generatedIgnored, generatedUploaded);
	}
	/* -*- Mode: c; c-basic-offset: 2 -*-
	 *
	 * contacts-api.c - Flickr flickr.contacts.* API calls
	 *
	 * Copyright (C) 2007-2012, David Beckett http://www.dajobe.org/
	 * Copyright (C) 2007 Vanilla I. Shu <vanilla -at- fatpipi.cirx.org>
	 *   (flickcurl_contacts_getList, flickcurl_contacts_getPublicList)
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
	 * flickcurl_contacts_getList:
	 * @fc: flickcurl context
	 * @filter: An optional filter of the results. The following values are valid:  friends, family, both or neither (or NULL)
	 * @page: The page of results to return. If this argument is omitted, it defaults to 1. (or NULL)
	 * @per_page: Number of photos to return per page. If this argument is omitted, it defaults to 1000. The maximum allowed value is 1000. (or NULL)
	 * 
	 * Get a list of contacts for the calling user.
	 *
	 * Implements flickr.contacts.getList (0.11)
	 * 
	 * Return value: NULL on failure
	 **/
	public flickcurl_contact_s flickcurl_contacts_getList(flickcurl_s fc, Object filter, int page, int per_page) {
		 doc = (null);
		 xpathCtx = (null);
		flickcurl_contact contacts = (null);
		int contacts_count = 0;
		byte[] page_str = new byte[10];
		byte[] per_page_str = new byte[10];
		fc.flickcurl_init_params(1);
		if (filter) {
			fc.flickcurl_add_param("filter", filter);
		} 
		if (page >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(page_str, "%d", page);
			fc.flickcurl_add_param("page", page_str);
		} 
		if (per_page >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(per_page_str, "%d", per_page);
			fc.flickcurl_add_param("per_page", per_page_str);
		} 
		fc.flickcurl_end_params();
		if (fc.flickcurl_prepare("flickr.contacts.getList")) {
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
		flickcurl_contact_s flickcurl_contact_s = new flickcurl_contact_s();
		contacts = flickcurl_contact_s.flickcurl_build_contacts(fc, xpathCtx, ()"/rsp/contacts/contact", contacts_count);
		int generatedFailed = fc.getFailed();
		if (generatedFailed) {
			if (contacts) {
				contacts.flickcurl_free_contacts();
			} 
			contacts = (null);
		} 
		return contacts/**
		 * flickcurl_contacts_getListRecentlyUploaded:
		 * @fc: flickcurl context
		 * @date_lastupload: Limits the results to contacts that have uploaded photos since this date (in the form of a Unix timestamp).  The default, and maximum, offset is 1 hour.  (or < 0)
		 * @filter: Limit the result set to all contacts or only those who are friends or family. Valid options are: ff: friends and family, all: all your contacts. Default value is "all". (or NULL)
		 * 
		 * Return a list of contacts for a user who have recently uploaded
		 * photos along with the total count of photos uploaded.
		 *
		 * This API added 2009-01-14 as announced in
		 * http://tech.groups.yahoo.com/group/yws-flickr/message/4668
		 *
		 * Implements flickr.contacts.getListRecentlyUploaded (1.8)
		 * 
		 * Return value: NULL on failure
		 **/;
	}
	public flickcurl_contact_s flickcurl_contacts_getListRecentlyUploaded(flickcurl_s fc, int date_lastupload, Object filter) {
		 doc = (null);
		 xpathCtx = (null);
		flickcurl_contact contacts = (null);
		int contacts_count = 0;
		byte[] date_lastupload_str = new byte[20];
		fc.flickcurl_init_params(0);
		if (date_lastupload >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(date_lastupload_str, "%d", date_lastupload);
			fc.flickcurl_add_param("date_lastupload", date_lastupload_str);
		} 
		if (filter) {
			fc.flickcurl_add_param("filter", filter);
		} 
		fc.flickcurl_end_params();
		if (fc.flickcurl_prepare("flickr.contacts.getListRecentlyUploaded")) {
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
		flickcurl_contact_s flickcurl_contact_s = new flickcurl_contact_s();
		contacts = flickcurl_contact_s.flickcurl_build_contacts(fc, xpathCtx, ()"/rsp/contacts/contact", contacts_count);
		int generatedFailed = fc.getFailed();
		if (generatedFailed) {
			if (contacts) {
				contacts.flickcurl_free_contacts();
			} 
			contacts = (null);
		} 
		return contacts/**
		 * flickcurl_contacts_getPublicList:
		 * @fc: flickcurl context
		 * @user_id: The NSID of the user to fetch the contact list for.
		 * @page: The page of results to return. If this argument is omitted, it defaults to 1. (or NULL)
		 * @per_page: Number of photos to return per page. If this argument is omitted, it defaults to 1000. The maximum allowed value is 1000. (or NULL)
		 * 
		 * Get the contact list for a user.
		 *
		 * Implements flickr.contacts.getPublicList (0.11)
		 * 
		 * Return value: list of contacts or NULL on failure
		 **/;
	}
	public flickcurl_contact_s flickcurl_contacts_getPublicList(flickcurl_s fc, Object user_id, int page, int per_page) {
		 doc = (null);
		 xpathCtx = (null);
		flickcurl_contact contacts = (null);
		int contacts_count = 0;
		byte[] page_str = new byte[10];
		byte[] per_page_str = new byte[10];
		fc.flickcurl_init_params(1);
		if (!user_id) {
			return (null);
		} 
		fc.flickcurl_add_param("user_id", user_id);
		if (page >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(page_str, "%d", page);
			fc.flickcurl_add_param("page", page_str);
		} 
		if (per_page >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(per_page_str, "%d", per_page);
			fc.flickcurl_add_param("per_page", per_page_str);
		} 
		fc.flickcurl_end_params();
		if (fc.flickcurl_prepare("flickr.contacts.getPublicList")) {
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
		flickcurl_contact_s flickcurl_contact_s = new flickcurl_contact_s();
		contacts = flickcurl_contact_s.flickcurl_build_contacts(fc, xpathCtx, ()"/rsp/contacts/contact", contacts_count);
		int generatedFailed = fc.getFailed();
		if (generatedFailed) {
			if (contacts) {
				contacts.flickcurl_free_contacts();
			} 
			contacts = (null);
		} 
		return contacts/**
		 * flickcurl_contacts_getTaggingSuggestions:
		 * @fc: flickcurl context
		 * @include_self: Return calling user in the list of suggestions. Default: true. (or NULL)
		 * @include_address_book: Include suggestions from the user's address book. Default: false (or NULL)
		 * @page: The page of results to return. If this argument is omitted, it defaults to 1. (or < 0)
		 * @per_page: Number of contacts to return per page. If this argument is omitted, all contacts will be returned. (or < 0)
		 * 
		 * Get suggestions for tagging people in photos based on the calling user's contacts.
		 *
		 * Implements flickr.contacts.getTaggingSuggestions (1.25)
		 *
		 * NOTE: Parameter order is @page, @per_page like all other
		 * flickr.contacts.* calls, NOT @per_page, @page like in the API
		 * docs.
		 * 
		 * Return value: list of contacts or NULL on failure
		 **/;
	}
	public flickcurl_contact_s flickcurl_contacts_getTaggingSuggestions(flickcurl_s fc, Object include_self, Object include_address_book, int page, int per_page) {
		 doc = (null);
		 xpathCtx = (null);
		flickcurl_contact contacts = (null);
		int contacts_count = 0;
		byte[] page_str = new byte[10];
		byte[] per_page_str = new byte[10];
		fc.flickcurl_init_params(0);
		if (include_self) {
			fc.flickcurl_add_param("include_self", include_self);
		} 
		if (include_address_book) {
			fc.flickcurl_add_param("include_address_book", include_address_book);
		} 
		if (page >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(page_str, "%d", page);
			fc.flickcurl_add_param("page", page_str);
		} 
		if (per_page >= 0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/sprintf(per_page_str, "%d", per_page);
			fc.flickcurl_add_param("per_page", per_page_str);
		} 
		fc.flickcurl_end_params();
		if (fc.flickcurl_prepare("flickr.contacts.getTaggingSuggestions")) {
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
		flickcurl_contact_s flickcurl_contact_s = new flickcurl_contact_s();
		contacts = flickcurl_contact_s.flickcurl_build_contacts(fc, xpathCtx, ()"/rsp/contacts/contact", contacts_count);
		int generatedFailed = fc.getFailed();
		if (generatedFailed) {
			if (contacts) {
				contacts.flickcurl_free_contacts();
			} 
			contacts = (null);
		} 
		return contacts;
	}
	/* -*- Mode: c; c-basic-offset: 2 -*-
	 *
	 * contacts.c - Flickcurl method contact functions
	 *
	 * Copyright (C) 2007-2009, David Beckett http://www.dajobe.org/
	 * Copyright (C) 2007 Vanilla I. Shu 
	 *   (flickcurl_free_contact, flickcurl_free_contacts,
	 *    flickcurl_build_contacts)
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
	 * flickcurl_free_contact:
	 * @contact_object: contact object
	 *
	 * Destructor for contact object
	 */
	public void flickcurl_free_contact() {
		do {
			if (!contact_object) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[2]), "%s:%d: (%s) assertion failed: object pointer of type flickcurl_contact is NULL.\n", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Flickr\\src\\contacts.c", 49, __func__);
				return /*Error: Unsupported expression*/;
			} 
		} while (0);
		Byte generatedNsid = this.getNsid();
		if (generatedNsid) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(generatedNsid);
		} 
		Byte generatedUsername = this.getUsername();
		if (generatedUsername) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(generatedUsername);
		} 
		Byte generatedRealname = this.getRealname();
		if (generatedRealname) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(generatedRealname);
		} 
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(contact_object/**
		 * flickcurl_free_contacts:
		 * @contacts_object: contact object array
		 *
		 * Destructor for array of contact object
		 */);
	}
	public void flickcurl_free_contacts() {
		int i;
		do {
			if (!contacts_object) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/fprintf((_iob[2]), "%s:%d: (%s) assertion failed: object pointer of type flickcurl_contact is NULL.\n", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Flickr\\src\\contacts.c", 73, __func__);
				return /*Error: Unsupported expression*/;
			} 
		} while (0);
		for (i = 0; contacts_object[i]; i++) {
			contacts_object[i].flickcurl_free_contact();
		}
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(contacts_object);
	}
	public flickcurl_contact_s flickcurl_build_contacts(flickcurl_s fc, Object xpathCtx, Object xpathExpr, Integer contact_count_p) {
		flickcurl_contact[][] contacts = (null);
		int nodes_count;
		int contact_count;
		int i;
		 xpathObj = (null);
		 nodes = new ();
		xpathObj = /*Error: Function owner not recognized*/xmlXPathEvalExpression(xpathExpr, /* Now do contacts */xpathCtx);
		if (!xpathObj) {
			fc.flickcurl_error("Unable to evaluate XPath expression \"%s\"", xpathExpr);
			fc.setFailed(1);
			;
		} 
		nodes = xpathObj.getNodesetval();
		nodes_count = /*Error: Function owner not recognized*/xmlXPathNodeSetGetLength(/* This is a max size - it can include nodes that are CDATA */nodes);
		contacts = (flickcurl_contact)/*Error: Function owner not recognized*/calloc(/*Error: Unsupported expression*/, nodes_count + 1);
		for (; i < nodes_count; i++) {
			 node = nodes.getNodeTab()[i];
			 attr = new ();
			flickcurl_contact contact_object = new flickcurl_contact();
			if (node.getType() != XML_ELEMENT_NODE) {
				fc.flickcurl_error("Got unexpected node type %d", node.getType());
				fc.setFailed(1);
				break;
			} 
			contact_object = (flickcurl_contact)/*Error: Function owner not recognized*/calloc(/*Error: Unsupported expression*/, 1);
			for (attr = node.getProperties(); attr; attr = attr.getNext()) {
				size_t attr_len = /*Error: Function owner not recognized*/strlen((byte)attr.getChildren().getContent());
				Byte attr_name = (byte)attr.getName();
				Byte attr_value;
				attr_value = (byte)/*Error: Function owner not recognized*/malloc(attr_len + 1);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/memcpy(attr_value, attr.getChildren().getContent(), attr_len + 1);
				if (!/*Error: Function owner not recognized*/strcmp(attr_name, "nsid")) {
					contact_object.setNsid(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "username")) {
					contact_object.setUsername(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "iconserver")) {
					contact_object.setIconserver(/*Error: Function owner not recognized*/atoi((byte)attr_value));
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "realname")) {
					contact_object.setRealname(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "friend")) {
					contact_object.setIs_friend(/*Error: Function owner not recognized*/atoi((byte)attr_value));
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "family")) {
					contact_object.setIs_family(/*Error: Function owner not recognized*/atoi((byte)attr_value));
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "ignored")) {
					contact_object.setIgnored(/*Error: Function owner not recognized*/atoi((byte)attr_value));
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				}  else if (!/*Error: Function owner not recognized*/strcmp(attr_name, "uploaded")) {
					contact_object.setUploaded(/*Error: Function owner not recognized*/atoi((byte)attr_value));
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				} else {
						/*Error: Function owner not recognized*//*Error: Function owner not recognized*/free(attr_value);
				} 
			}
			contacts[contact_count++] = contact_object/* for nodes */;
		}
		if (contact_count_p) {
			contact_count_p = contact_count;
		} 
		return contacts;
	}
	public Byte getNsid() {
		return nsid;
	}
	public void setNsid(Byte newNsid) {
		nsid = newNsid;
	}
	public Byte getUsername() {
		return username;
	}
	public void setUsername(Byte newUsername) {
		username = newUsername;
	}
	public int getIconserver() {
		return iconserver;
	}
	public void setIconserver(int newIconserver) {
		iconserver = newIconserver;
	}
	public Byte getRealname() {
		return realname;
	}
	public void setRealname(Byte newRealname) {
		realname = newRealname;
	}
	public int getIs_friend() {
		return is_friend;
	}
	public void setIs_friend(int newIs_friend) {
		is_friend = newIs_friend;
	}
	public int getIs_family() {
		return is_family;
	}
	public void setIs_family(int newIs_family) {
		is_family = newIs_family;
	}
	public int getIgnored() {
		return ignored;
	}
	public void setIgnored(int newIgnored) {
		ignored = newIgnored;
	}
	public int getUploaded() {
		return uploaded;
	}
	public void setUploaded(int newUploaded) {
		uploaded = newUploaded;
	}
}
